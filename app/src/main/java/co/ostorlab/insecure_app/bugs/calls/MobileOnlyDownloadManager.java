package co.ostorlab.insecure_app.bugs.calls;

import android.app.DownloadManager;
import android.net.Uri;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import co.ostorlab.insecure_app.BugRule;

import static android.content.Context.DOWNLOAD_SERVICE;


public final class MobileOnlyDownloadManager extends BugRule {


        private static final String TAG = co.ostorlab.insecure_app.bugs.calls.MobileOnlyDownloadManager.class.toString();

        @Override
        public void run() throws Exception {
            // True Positive
            startDownloadManager(DownloadManager.Request.NETWORK_MOBILE);
            // False Positive
            startDownloadManager(DownloadManager.Request.NETWORK_WIFI);
        }

    private void startDownloadManager(int networkMobile) {
        SimpleDateFormat dateformat = new SimpleDateFormat("hhmmss", Locale.getDefault());
        DownloadManager manager = (DownloadManager) getContext().getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request down = new DownloadManager.Request(
                Uri.parse("https://ostorlab.co/test.large"));
        down.setAllowedNetworkTypes(networkMobile);
        String date = dateformat.format(new Date());
        down.setDestinationInExternalFilesDir(getContext(), null, date + "test.large");
        manager.enqueue(down);
    }

    @Override
        public String getDescription() {
            return "Mobile Only DownloadManager";
        }

}
