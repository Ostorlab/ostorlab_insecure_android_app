package co.ostorlab.insecure_app.bugs.calls;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import co.ostorlab.insecure_app.BugRule;


public final class StaticIV extends BugRule {

    private static final String TAG = "RULE";

    @Override
    public String getDescription() {
        return "Use hardcoded static IV";
    }

    @Override
    public void run() throws Exception{
        byte[] IV = "0123456789abcdef".getBytes();
        String clearText = "Jan van Eyck was here 1434";
        String key = "ThisIs128bitSize";
        SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, new IvParameterSpec(IV));
        byte[] encryptedMessage = cipher.doFinal(clearText.getBytes());
        Log.i(TAG, String.format("Message: %s", Base64.encodeToString(encryptedMessage, Base64.DEFAULT)));
    }
}
