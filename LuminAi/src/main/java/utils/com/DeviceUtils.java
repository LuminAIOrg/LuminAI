package utils.com;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;

import java.net.URL;

public class DeviceUtils{
    public static void runScript(URL pythonScriptFullPath) {
        try (Interpreter interp = new SharedInterpreter()) {
            interp.runScript(String.valueOf(pythonScriptFullPath));
        } catch (JepException e) {
            throw new RuntimeException("running driver script failed \n" + e.getMessage());
        }

    }
}