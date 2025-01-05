package japiim.dic.morekuyubim.por;

import static japiim.dic.morekuyubim.por.MyMediaPlayer.assetExists;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.checkPermissions;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.isExternalStorageAvailable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.get_table_values.GetVideosTableValues;

public class ViewAdapterVideoPlay extends AppCompatActivity {
    Context context;
    List<GetVideosTableValues> videosList;
    long videoId;
    long senseBundleId;
    int position;
    String videoString;
    VideoView videoPlay;
    MediaPlayer mp;
    MediaController mc;

    String filePathFinal;

    int videoExists;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout_video_play);
        Log.d("Main", "VIDEO PLAY onCREATE");

        //mp = null;
        videoId = getIntent().getExtras().getLong("video_id");

        senseBundleId = getIntent().getExtras().getLong("sense_bundle_id");

        videoString = getIntent().getExtras().getString("video");

        position = getIntent().getExtras().getInt("position");


        Log.d("Main", "VIDEO PLAY onCREATE: videoString: " + videoString);

        final String videoStringNoExtension = videoString;

        int video = getResources().getIdentifier(videoStringNoExtension, "raw", getPackageName());
        Log.d("Main", " VIDEO RAW ID JAPIIM: " + video + "!!!!!!!!!");

        videoPlay = findViewById(R.id.vvVideoPlay);
        videoExists = 0;

        String mediaPath = getResources().getString(R.string.video_path);
        String mediaPath2 = getResources().getString(R.string.video_path2);

        filePathFinal = "android.resource://" + getPackageName() + "/" + R.raw.no_video;


        checkAndGetFileFromRaw(video);



        if (videoExists ==0){

            if(isExternalStorageAvailable() && checkPermissions(getApplicationContext())) {

                final String fileSdPathMp4 = Environment.getExternalStorageDirectory() + mediaPath + videoStringNoExtension + ".mp4";
                Log.d("Main", " GNM-JPP VIDEO PATH MP4: " + fileSdPathMp4);

                checkAndGetFileFromExternalStorage(fileSdPathMp4);

            }else{
                Log.d("Main", " GNM-JPP isExternalStorageAvailable: NO");

            }
        }

        if (videoExists ==0){

//            Checar se o arquivo de aúdio existe no servidor
            final String videoPathWeb = getResources().getString(R.string.video_path_web);
            String fileWebMp4 = videoPathWeb + videoStringNoExtension + ".mp4";

            checkAndGetFileFromServer(fileWebMp4);

        }

            Uri uri = Uri.parse(filePathFinal);

            MediaController mediaController = new MediaController(this);
            mediaController.setAnchorView(videoPlay);
            videoPlay.setMediaController(mediaController);
            videoPlay.setVideoURI(uri);
            videoPlay.requestFocus();

            Log.d("Main", " GNM-JPP VIDEO_PATH_WEB PLAY MP4 : " + filePathFinal);
            Log.d("Main", " GNM-JPP VIDEO_PATH_WEB VIDEO EXISTS : " + videoExists);

            videoPlay.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer videoPlay) {
                    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                            WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    videoPlay.start();
                }
            });


    }

    //Não é possível tocar vídeos do assets. Os vídeos devem ser tocados do folder raw (e gravados ali)
    public void checkAndGetFileFromRaw(int video) {


        if (video != 0) {

            filePathFinal = "android.resource://" + getPackageName() + "/" + video;

            videoExists = 1;
            Log.d("Main", " GNM-JPP PATH VIDEO RAW EXISTS: " + filePathFinal + "!!!!!!!!!");

        }


    }
    public void checkAndGetFileFromExternalStorage(String fileSdPathMp4) {


        File fileSdMP4 = new File(fileSdPathMp4);
        Log.d("Main", " GNM-JPP video MP4 exists : " + fileSdMP4.exists() + ", can read : " + fileSdMP4.canRead());

        if (fileSdMP4.exists()) {
            filePathFinal = fileSdPathMp4;
            videoExists = 3;

        }

    }

    public void checkAndGetFileFromServer(String fileWebMp4){

        Thread fileCheckThread = new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(fileWebMp4);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("HEAD");
                    connection.setInstanceFollowRedirects(false);

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        // File exists
                        filePathFinal = fileWebMp4;
                        videoExists = 2;
                        Log.d("GNM-JPP", fileWebMp4 + " exists");
                    } else {
                        // File does not exist
                        Log.d("GNM-JPP", fileWebMp4 + " does not exist");
                    }
                } catch (IOException e) {
                    // Error occurred while checking file existence
                    Log.e("GNM-JPP", "Error checking file existence: " + e.getMessage());
                } finally {
                    if (connection != null) {
                        connection.disconnect(); // Close the connection
                    }
                }
            }
        });

        fileCheckThread.start(); // Start the thread

        try {
            fileCheckThread.join(); // Wait for the thread to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }




    public void backToEntry(View view) {
        destroyMediaPlayer();
        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
        destroyMediaPlayer();
        Log.d("Main", " GNM-JPP PATH_VIDEO : PAUSED");
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Main", " GNM-JPP PATH_VIDEO : DESTROYED");
        destroyMediaPlayer();
        finish();
    }

    public void destroyMediaPlayer() {
        if (mp != null) {
            mp.stop();
            mp.release();
            Log.d("Main", " GNM-JPP PATH_VIDEO : RELEASED");
        }
        Log.d("Main", " GNM-JPP PATH_VIDEO : NOT RELEASED");
    }

}