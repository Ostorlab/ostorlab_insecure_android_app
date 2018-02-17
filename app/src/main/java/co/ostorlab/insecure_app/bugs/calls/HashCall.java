package co.ostorlab.insecure_app.bugs.calls;

import java.security.MessageDigest;

import co.ostorlab.insecure_app.BugRule;

public class HashCall extends BugRule {

    private static final String TAG = HashCall.class.toString();

    @Override
    public void run() throws Exception {

        String monMessage = "Ostorlab hidden message";

        MessageDigest sha = MessageDigest.getInstance("sha-1");
        sha.update(monMessage.getBytes());
        byte[] digest = sha.digest();

    }

    @Override
    public String getDescription() {
        return "The application executes the hash functions";
    }
}
