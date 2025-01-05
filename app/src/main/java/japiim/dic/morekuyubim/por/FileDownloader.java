package japiim.dic.morekuyubim.por;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class FileDownloader extends AsyncTask<String, Void, Boolean> {

    private Context context;

    public FileDownloader(Context context) {
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String fileUrl = params[0];
        String destinationPath = params[1];

        try {
            URL url = new URL(fileUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(false);
            urlConnection.connect();

            // Check if the response code is HTTP_OK (200)
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = urlConnection.getInputStream();
                FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);

                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                }

                fileOutputStream.close();
                inputStream.close();

                return true;
            } else {
                Log.e("FileDownloader", "Failed to download file. HTTP response code: " + urlConnection.getResponseCode());
                return false;
            }
        } catch (IOException e) {
            Log.e("FileDownloader", "Error downloading file: " + e.getMessage());
            return false;
        }
    }

    // Example usage:
    // String fileUrl = "https://example.com/audio.mp3";
    // String destinationPath = context.getFilesDir() + "/audio.mp3";
    // new FileDownloader(context).execute(fileUrl, destinationPath);
}
