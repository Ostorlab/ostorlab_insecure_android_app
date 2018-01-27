package co.ostorlab.insecure_app.bugs.calls;

import co.ostorlab.insecure_app.BugRule;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CommandExec extends BugRule {

    private static final String TAG = CommandExec.class.toString();

    @Override
    public void run() throws Exception {

        //command executed on sdcard
        String domainName = "google.com";
        String command = "ping -c 3 " + domainName;
        String output = executeCommand(command, "/sdcard/ostorlab");

        // command contains chmod
        command = "chmod " + domainName;
        output = executeCommand(command, null);
    }

    private String executeCommand(String command, String pathName) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            File file = new File(pathName);
            p = Runtime.getRuntime().exec(command, null, file);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

    @Override
    public String getDescription() {
        return "The application executes commands from an external storage";
    }
}
