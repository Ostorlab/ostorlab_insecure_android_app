package co.ostorlab.insecure_app.bugs.calls;

import java.security.SecureRandom;
import java.util.Random;

import co.ostorlab.insecure_app.BugRule;


public final class InsecureRandom extends BugRule {
    @Override
    public void run() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        Random random = new Random();
        random = new Random(12345);
        secureRandom = new SecureRandom("12345".getBytes());
        secureRandom.setSeed(12345);
        random.setSeed(12345);


    }

    @Override
    public String getDescription() {
        return "The application uses a string value to construct an Intent";
    }
}
