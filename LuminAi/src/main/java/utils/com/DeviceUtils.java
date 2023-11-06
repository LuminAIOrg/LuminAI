package utils.com;


import java.io.*;
import java.net.URL;
import java.util.function.Consumer;

public class DeviceUtils {
    private static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }
    public static void runScript(String scriptResourcePath) {
        try {
            File file = null;
            ProcessBuilder builder = new ProcessBuilder();
            boolean isWindows = System.getProperty("os.name")
                    .toLowerCase().startsWith("windows");
            System.out.println(isWindows);
            if (isWindows){
                file = getFile(scriptResourcePath + ".ps1");
            }
            else {
                file = getFile(scriptResourcePath + ".sh");
            }

            if (file == null){
                throw new InterruptedException("Driver not found");
            }
            System.out.println("file found" + file.getAbsolutePath());

            if (isWindows) {
                builder.command("cmd.exe", file.getAbsolutePath());
            } else {
                builder.command("sh", file.getAbsolutePath());
            }
            builder.directory(new File(System.getProperty("user.home")));
            System.out.println("starting exec");
            Process process = builder.start();
            StreamGobbler streamGobbler =
                    new StreamGobbler(process.getInputStream(), System.out::println);
            System.out.println("is running");
            int exitCode = process.waitFor();
            System.out.println("finished exec");

            if (exitCode == 0) {
                System.out.println("Script executed successfully.");
            } else {
                System.err.println("Script execution failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static File getFile(String scriptResourcePath){
        // Obtain the URL of the script resource
        ClassLoader classLoader = DeviceUtils.class.getClassLoader();
        URL scriptUrl = classLoader.getResource(scriptResourcePath);

        if (scriptUrl == null) {
            System.err.println("Script resource not found: " + scriptResourcePath);
            return null;
        }

        // Convert the URL to a file path
        return new File(scriptUrl.getFile());
    }
}

