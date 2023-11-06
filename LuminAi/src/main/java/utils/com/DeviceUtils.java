package utils.com;

import org.python.util.PythonInterpreter;

import java.nio.file.Files;
import java.nio.file.Path;

public class DeviceUtils{
    public static void run(String file) {
        try (PythonInterpreter interpreter = new PythonInterpreter()){
            String pythonCode = String.join("\n", Files.readAllLines(Path.of(file)));
            interpreter.exec("import requests");
            interpreter.exec(pythonCode);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}