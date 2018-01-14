package co.ostorlab.insecure_app;

import android.content.Context;
import android.os.Bundle;
import android.os.Debug;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;



import org.w3c.dom.Text;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import co.ostorlab.insecure_app.Profile;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final TextInputEditText user = (TextInputEditText)findViewById(R.id.text_1);
        final TextInputEditText pwd = (TextInputEditText)findViewById(R.id.text_2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Debug.waitForDebugger();
                String FILENAME = "insecure_file";
                FileOutputStream fos = null;
                Profile profile = new Profile("Amine", 99);
                String[] array_profiles = new String[] {"https","ostorlab", "co","//","test"};
                test_string(array_profiles);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public String test_string(String[] array_profiles)
    {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://activity.omoplanet.com").newBuilder();
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
            }
        });
        return "DONE";
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
