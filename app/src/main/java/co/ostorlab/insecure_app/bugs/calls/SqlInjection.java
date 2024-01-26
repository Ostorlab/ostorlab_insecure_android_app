package co.ostorlab.insecure_app.bugs.calls;

import android.content.Context;
import android.os.AsyncTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

import co.ostorlab.insecure_app.BugRule;

public class SqlInjection extends BugRule {

    @Override
    public void run(String user_input) throws Exception {

        if (!user_input.isEmpty()) {
            new ExecuteQueryTask().execute(user_input);
        }
        else{
            new ExecuteQueryTask().execute("INSERT INTO accounts(name, amount) VALUES(?, ?)");
        }
    }

    @Override
    public String getDescription() {
        return "The application sends unsanitized queries to the sql server";
    }
}


class ExecuteQueryTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {
        String query = params[0];
        String result = "";

        try {
            // Register the JDBC driver for SQLite
            Class.forName("org.sqlite.JDBC");

            // Create a connection to an in-memory database
            Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the query
            ResultSet resultSet = statement.executeQuery(query);

            // Process the result set (replace this with your own logic)
            while (resultSet.next()) {
                result += resultSet.getString(1) + "\n";
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }

        return result;
    }
}
