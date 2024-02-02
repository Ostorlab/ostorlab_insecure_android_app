package co.ostorlab.insecure_app.bugs.calls;

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
            new ExecuteQueryTask().execute("INSERT INTO accounts(name, amount) VALUES(John Doe, 10)");
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
            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                result += resultSet.getString(1) + "\n";
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }

        return result;
    }
}
