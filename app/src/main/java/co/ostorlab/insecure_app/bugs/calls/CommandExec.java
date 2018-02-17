package co.ostorlab.insecure_app.bugs.calls;

import co.ostorlab.insecure_app.BugRule;
import java.io.File;

public class CommandExec extends BugRule {

    private static final String TAG = CommandExec.class.toString();

    @Override
    public void run() throws Exception {

        String domainName = "google.com";
        String command = "";

        // command contains chmod
        command = "chmod 777" + domainName;
        executeCommand(command, null);

        //command executed on sdcard
        command = "ping -c 3 " + domainName;
        executeCommand(command, "/sdcard/ostorlab");


    }

    private void executeCommand(String command, String pathName) {
        Process p;
        try {
            File file = null;
            if(pathName != null)
                file = new File(pathName);

            p = Runtime.getRuntime().exec(command, null, file);
            p.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getDescription() {
        return "The application executes commands";
    }
}
