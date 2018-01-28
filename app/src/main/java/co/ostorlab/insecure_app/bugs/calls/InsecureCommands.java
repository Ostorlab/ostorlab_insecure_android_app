package co.ostorlab.insecure_app.bugs.calls;

import co.ostorlab.insecure_app.BugRule;
import java.io.File;

public class InsecureCommands extends BugRule {

    private static final String TAG = InsecureCommands.class.toString();

    @Override
    public void run() throws Exception {
        executeCommand("chmod 755 test_file", "/data/data/");
        executeCommand("ping -c 3 www.ostorlab.co", "/sdcard/ostorlab");
    }

    private void executeCommand(String command, String pathName) throws Exception {
        File file = new File(pathName);
        Runtime.getRuntime().exec(command, null, file);
    }

    @Override
    public String getDescription() {
        return "The application executes commands from an external storage";
    }
}
