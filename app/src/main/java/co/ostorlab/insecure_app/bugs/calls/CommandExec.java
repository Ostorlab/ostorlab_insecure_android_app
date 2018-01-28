package co.ostorlab.insecure_app.bugs.calls;

import co.ostorlab.insecure_app.BugRule;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CommandExec extends BugRule {

    private static final String TAG = CommandExec.class.toString();

    @Override
    public void run() throws Exception {

        String domainName = "google.com";
        String command = "";

        // command contains chmod
        command = "chmod " + domainName;
        executeCommand(command, null);

        //command executed on sdcard
        //command = "ping -c 3 " + domainName;
        //executeCommand(command, "/sdcard/ostorlab");


    }

    private String executeCommand(String command, String pathName) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            File file = null;
            if(pathName != null)
                file = new File(pathName);

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
