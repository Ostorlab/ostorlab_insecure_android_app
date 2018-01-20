package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import java.io.File;
import co.ostorlab.insecure_app.BugRule;


public final class InsecureFilePermissions extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Use of insecure file permissions";
    }

    @Override
    public void run() throws Exception {
        String filename = "test_filename";
        openFileOutputWorldReadable(filename);
        openFileOutputWorldWritable(filename);
        setReadableAll(filename);
        setWritableAll(filename);
    }

    private void openFileOutputWorldReadable(String filename) throws Exception {
        getContext().openFileOutput(filename, Context.MODE_WORLD_READABLE);
    }

    private void openFileOutputWorldWritable(String filename) throws Exception {
        getContext().openFileOutput(filename, Context.MODE_WORLD_WRITEABLE);
    }

    private void setReadableAll(String filename) throws Exception {
        // see https://stackoverflow.com/questions/13856757/android-alternative-to-the-deprecated-context-mode-world-readable/**/
        getContext().openFileOutput(filename, Context.MODE_PRIVATE);
        String path = getContext().getFilesDir().getAbsolutePath() + filename;
        File f = new File(path);
        f.setReadable(true, false);
    }

    private void setWritableAll(String filename) throws Exception {
        getContext().openFileOutput(filename, Context.MODE_PRIVATE);
        String path = getContext().getFilesDir().getAbsolutePath() + filename;
        File f = new File(path);
        f.setWritable(true, false);
    }

}
