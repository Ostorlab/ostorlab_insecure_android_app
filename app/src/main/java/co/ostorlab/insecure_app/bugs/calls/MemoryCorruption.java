package co.ostorlab.insecure_app.bugs.calls;

import android.util.Base64;
import android.util.Log;

import java.util.Collections;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import co.ostorlab.insecure_app.BugRule;


public final class MemoryCorruption extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Memory corruption";
    }

    @Override
    public void run() throws Exception{
        String input = String.join("", Collections.nCopies(200, "()"));
        triggerStackOverflow(input);
    }

    private native String triggerStackOverflow(String input);
}
