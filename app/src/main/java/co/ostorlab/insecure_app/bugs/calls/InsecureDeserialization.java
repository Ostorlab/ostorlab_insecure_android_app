package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import co.ostorlab.insecure_app.BugRule;

import java.io.Serializable;

public final class InsecureDeserialization extends BugRule {

    private static final String TAG = InsecureDeserialization.class.toString();

    @Override
    public String getDescription() {
        return "Insecure use of getSerializable";
    }

    @Override
    public void run() throws Exception {
        Context context = getContext();
        Intent intent = new Intent("co.ostorlab.action.INSECURE_DESERIALIZATION");

        // Create a custom Java object
        User user = new User("Ostorlab", "admin", true);

        // Pass the object as a payload
        intent.putExtra("payload", user);

        InsecureDeserialization ids = new InsecureDeserialization();
        ids.onReceive(context, intent);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            // Retrieve the object from the intent
            User user = (User) intent.getExtras().getSerializable("payload");

            // Access the object's properties
            if (user != null) {
                Log.d(TAG, "Name: " + user.getUsername());
                Log.d(TAG, "Role: " + user.getRole());
                Log.d(TAG, "Logged in: " + user.isLogged());
            }
        }
    }

    // Define a serializable Java object
    private static class User implements Serializable {
        private String username;
        private  String role;
        private boolean isLogged;

        public User(String username, String role, boolean isLogged) {
            this.username = username;
            this.role = role;
            this.isLogged = isLogged;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }

        public boolean isLogged(){
            return isLogged;
        }
    }
}
