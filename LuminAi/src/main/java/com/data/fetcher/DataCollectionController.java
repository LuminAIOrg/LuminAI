package com.data.fetcher;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

import io.quarkus.runtime.StartupEvent;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@ApplicationScoped
public class DataCollectionController {

    @ConfigProperty(name = "dataCollectionMethod")
    String dataCollectionMethod;

    void onStart(@Observes StartupEvent ev) {
        try {
            var classes = getClasses(dataCollectionMethod);
            for (var c : classes) {
                if (DataFetcher.class.isAssignableFrom(c)) {
                    DataFetcher fetcher = (DataFetcher) c.getConstructor().newInstance();
                    Log.info("Invoking data fetcher class " + c);
                    fetcher.invoke();
                } else {
                    throw new RuntimeException("Data fetcher " + c + " does not a DataFetcher");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Class[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List dirs = new ArrayList();
        while (resources.hasMoreElements()) {
            URL resource = (URL) resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        var classes = new ArrayList();
        for (Object directory : dirs) {
            classes.addAll(findClasses((File) directory, packageName));
        }
        return (Class[]) classes.toArray(new Class[classes.size()]);
    }

    private static List findClasses(File directory, String packageName) throws ClassNotFoundException {
        var classes = new ArrayList();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files == null) throw new RuntimeException("No files in package");
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }
}
