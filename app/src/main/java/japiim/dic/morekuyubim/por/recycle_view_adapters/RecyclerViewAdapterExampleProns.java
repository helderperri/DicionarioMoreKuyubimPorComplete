
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.os.Build.VERSION.SDK_INT;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.PlayMediaFromAssets;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.assetExists;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.checkPermissions;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.isExternalStorageAvailable;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.myDownloader;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.stopAllMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.FileDownloader;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.get_table_values.GetExamplePronsTableValues;


public class RecyclerViewAdapterExampleProns extends RecyclerView.Adapter<RecyclerViewAdapterExampleProns.ViewHolder> {

    Context context;
    List<GetExamplePronsTableValues> examplePronsList;
    RecyclerView rvExampleProns;


    //URL fileUrl;
    //String filePathWeb;
    //String filePathWeb;
    // Audio manager instance to manage or
    // handle the audio interruptions
    //protected AudioManager audioManager;

    // Audio attributes instance to set the playback
    // attributes for the media player instance
    // these attributes specify what type of media is
    // to be played and used to callback the audioFocusChangeListener

    protected int soundExists;

    // media player is handled according to the
    // change in the focus which Android system grants for


    MediaPlayer mediaPlayer;
    String filePathFinal;
    //int PauseResumeFlag, PausedLength;
    int rawFile;

    private static String getExternalStoragePath(Context mContext, boolean is_removable) {

        StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
        Class<?> storageVolumeClazz = null;
        try {
            storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
            Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
            Method getPath = storageVolumeClazz.getMethod("getPath");
            Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
            Object result = getVolumeList.invoke(mStorageManager);
            final int length = Array.getLength(result);
            for (int i = 0; i < length; i++) {
                Object storageVolumeElement = Array.get(result, i);
                String path = (String) getPath.invoke(storageVolumeElement);
                boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
                if (is_removable == removable) {
                    return path;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

//        TextView rowExamplePronId;
//        TextView rowExamplePron;
        @SuppressLint("StaticFieldLeak")
        public ImageView rowExamplePronBtn;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowExamplePronId = itemView.findViewById(R.id.tvExamplePronId);
            //rowExamplePron = itemView.findViewById(R.id.tvExamplePron);
            rowExamplePronBtn = itemView.findViewById(R.id.btnExamplePron);
        }
    }

    public RecyclerViewAdapterExampleProns(Context context, List<GetExamplePronsTableValues> examplePronsList, RecyclerView rvExampleProns){
        this.context = context;
        this.examplePronsList = examplePronsList;
        this.rvExampleProns = rvExampleProns;
    }


        @NonNull
    @Override
    public RecyclerViewAdapterExampleProns.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_example_pron, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        sViews.add(viewHolder.rowExamplePronBtn);

        return viewHolder;
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterExampleProns.ViewHolder viewHolder, int i) {


        View.OnClickListener mStartListener = new View.OnClickListener() {

            public void onClick(View v) {
//                viewHolder.rowExamplePronBtn.setImageResource(R.drawable.ic_stop);

                AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
            @Override
            public void onAudioFocusChange(final int focusChange) {
                if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                    entryMediaPlayer.start();
//                    mediaPlayer.start();
                    Log.d("Main", " GNM-JPP EXAMPLE PRONS AUDIOFOCUS_GAIN");
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {

                    Log.d("Main", " GNM-JPP EXAMPLE PRONS AUDIOFOCUS_LOSS_TRANSIENT");
                } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {


                        Log.d("Main", " GNM-JPP EXAMPLE PRONS AUDIOFOCUS_LOSS file:");
//                        viewHolder.rowExamplePronBtn.setImageResource(R.drawable.ic_play);



                }
            }

        };


                final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

                // initiate the audio playback attributes
                AudioAttributes playbackAttributes = new AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                        .build();

                // set the playback attributes for the focus requester
                final AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN)
                        .setAudioAttributes(playbackAttributes)
                        .setAcceptsDelayedFocusGain(true)
                        .setOnAudioFocusChangeListener(audioFocusChangeListener)
                        .build();

                // request the audio focus and
                // store it in the int variable
                int audioFocusRequest = audioManager.requestAudioFocus(focusRequest);


                if (audioFocusRequest == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {

                    stopAllMediaPlayer();

                    viewHolder.rowExamplePronBtn.setImageResource(R.drawable.ic_stop);

                    entryMediaPlayer = new MediaPlayer();
                    if(soundExists == 1){

                        try {
                            String tag = ((List<String>) viewHolder.rowExamplePronBtn.getTag()).get(0).toString();
                            //String string = (String)  viewHolder.rowPronBtn.getTag(1);
                            //GetPronsTableValues getPronTableValues = pronsList.get(tag);
                            //final String pronString = getPronTableValues.getPron();

                            Log.d("Main", " GNM-JPP  EXAMPLE PRON STARTED TAG TAG" + tag + "STRING: "+tag);
//                            mediaPlayer.setDataSource(tag);
                            entryMediaPlayer.setDataSource(tag);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            entryMediaPlayer.prepare();
//                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else if(soundExists == 0){
                        entryMediaPlayer = MediaPlayer.create(context, R.raw.no_sound);
//                        mediaPlayer = MediaPlayer.create(context, R.raw.no_sound);
                        Toast.makeText(context, "Mídia não encontrada. Cheque sua conexão com a Internet.", Toast.LENGTH_LONG).show();

                    }else if(soundExists == 2){

                        PlayMediaFromAssets(context, filePathFinal);


                    } else if (soundExists == 3) {
                        entryMediaPlayer = MediaPlayer.create(context, rawFile);
                    }


//                    RecyclerViewAdapterEntries.mediaPlayerArray.add(mediaPlayer);
                    entryMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

//                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            //mediaPlayer.reset();
//                            mediaPlayer.release();
                            //mediaPlayer = null;
                            entryMediaPlayer.release();
                            Log.d("Main", " GNM-JPP PATH WEB EXAMPLE PRON : RELEASED ON COMPLETION file:");


                            viewHolder.rowExamplePronBtn.setImageResource(R.drawable.ic_play);
                            //audioManager.abandonAudioFocus(audioFocusChangeListener);


                        }
                    });
                    entryMediaPlayer.start();

                    Log.d("Main", " GNM-JPP EXAMPLE PRON STARTED file");
                }

            }


        };

        GetExamplePronsTableValues getExamplePronTableValues = examplePronsList.get(i);

        final String examplePronString = getExamplePronTableValues.getExamplePron();

        final String examplePronStringNoExtension = examplePronString.substring(0, examplePronString.lastIndexOf('.'));

        soundExists = 0;

        String mediaPath = context.getResources().getString(R.string.audio_path);
        String mediaPath2 = context.getResources().getString(R.string.audio_path2);

        final String filePathWav = mediaPath2 + examplePronStringNoExtension + ".wav";
        final String filePathMp3 = mediaPath2 + examplePronStringNoExtension + ".mp3";
        Log.d("Main", " GNM-JPP EXAMPLE PRON PATH : "+ filePathMp3);
        Log.d("Main", " GNM-JPP EXAMPLE PRON PATH : "+ filePathWav);


        rawFile = context.getResources().getIdentifier(examplePronStringNoExtension, "raw", context.getPackageName());


        if ( rawFile != 0 ) {  // the resouce exists...

            soundExists = 3;
        }

        if (soundExists ==0) {
            checkAndGetFileFromAssets(context, filePathWav);
        }

        if (soundExists ==0){

            checkAndGetFileFromAssets(context, filePathMp3);
        }

        if (soundExists ==0){

            if(isExternalStorageAvailable () && checkPermissions(context)) {

                final String filePathSdMp3 = Environment.getExternalStorageDirectory() + mediaPath + examplePronStringNoExtension + ".mp3";
                final String filePathSdWav = Environment.getExternalStorageDirectory() + mediaPath + examplePronStringNoExtension + ".wav";

                checkAndGetFileFromExternalStorage(filePathSdWav, viewHolder);

                if (soundExists ==0){
                    checkAndGetFileFromExternalStorage(filePathSdMp3, viewHolder);
                }

            }
        }
        if (soundExists ==0){

            final String audioPathWebMp3 = context.getResources().getString(R.string.audio_path_web_mp3);
            final String audioPathWebWav = context.getResources().getString(R.string.audio_path_web_wav);

            final String filePathWebMp3 = audioPathWebMp3 + examplePronStringNoExtension + ".mp3";
            final String filePathWebWav = audioPathWebWav + examplePronStringNoExtension + ".wav";

            checkAndGetFileFromServer(filePathWebMp3, viewHolder);

            if(soundExists ==0){

                checkAndGetFileFromServer(filePathWebWav, viewHolder);
            }

        }

        viewHolder.rowExamplePronBtn.setOnClickListener(mStartListener);

    }

    public void checkAndGetFileFromExternalStorage(String filePathSd, ViewHolder viewHolder) {
        File fileSd = new File(filePathSd);

        if (fileSd.exists()){

            filePathFinal = filePathSd;
            List<String> data = new ArrayList<String>();
            data.add(filePathFinal);

            viewHolder.rowExamplePronBtn.setTag(data);
            soundExists = 1;
            Log.d("Main", " GNM-JPP PATHWeb EXAMPLE PRONS : " + filePathFinal);


        }
    }

    public void checkAndGetFileFromAssets(Context context, String filePath) {

        if(assetExists(context, filePath)){

            filePathFinal = filePath;
            soundExists = 2;
        }

    }

    public void checkAndGetFileFromServer(String audioFile, ViewHolder viewHolder){

        new Thread() {

            public void run() {
                //your "file checking code" goes here like this
                //write your results to log cat, since you cant do Toast from threads without handlers also...

                try {
                    HttpURLConnection.setFollowRedirects(false);
                    // note : you may also need
                    //HttpURLConnection.setInstanceFollowRedirects(false)

                    HttpURLConnection con =  (HttpURLConnection) new URL(audioFile).openConnection();
                    con.setRequestMethod("HEAD");
                    if( (con.getResponseCode() == HttpURLConnection.HTTP_OK) ){


                        //filePathFinal = filePathWeb;
                        filePathFinal = audioFile;
                        soundExists = 1;
                        List<String> data = new ArrayList<String>();
                        data.add(filePathFinal);


                        viewHolder.rowExamplePronBtn.setTag(data);



                        Log.d("GNM-JPP PATHWeb EXAMPLE PRONS FILE_EXISTS",  audioFile);


                    }


                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.d("GNM-JPP PATHWeb EXAMPLE PRONS EXAMPLE FILE_DONT_EXISTS", audioFile);
                }
            }
        }.start();

    }


    protected void onDestroy() {

    }

    @Override
    public int getItemCount() {
        return examplePronsList.size();
    }

}
