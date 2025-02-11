
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.os.Build.VERSION.SDK_INT;

import static japiim.dic.morekuyubim.por.MyMediaPlayer.assetExists;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.checkPermissions;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.isExternalStorageAvailable;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.stopAllMediaPlayer;
import static japiim.dic.morekuyubim.por.SplashScreen.rawCopy;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.*;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.Manifest;
import android.annotation.SuppressLint;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedInputStream;
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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.get_table_values.GetPronsTableValues;

public class RecyclerViewAdapterProns extends RecyclerView.Adapter<RecyclerViewAdapterProns.ViewHolder> {

    Context context;
    List<GetPronsTableValues> pronsList;
    RecyclerView rvProns;
    //AudioManager audioManager;

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

    int rawFile;

    public static class ViewHolder extends RecyclerView.ViewHolder{

//        TextView rowPronId;
//        TextView rowPron;
        @SuppressLint("StaticFieldLeak")
        public ImageView rowPronBtn;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowPronId = itemView.findViewById(R.id.tvPronId);
            //rowPron = itemView.findViewById(R.id.tvPron);
            rowPronBtn = itemView.findViewById(R.id.pronBtn);

        }
    }

    public RecyclerViewAdapterProns(Context context, List<GetPronsTableValues> pronsList, RecyclerView rvProns){
        this.context = context;
        this.pronsList = pronsList;
        this.rvProns = rvProns;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterProns.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_pron, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        sViews.add(viewHolder.rowPronBtn);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterProns.ViewHolder viewHolder, int i) {


        View.OnClickListener mStartListener = new View.OnClickListener() {


            public void onClick(View v) {

                AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                    @Override
                    public void onAudioFocusChange(final int focusChange) {
                        if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                            entryMediaPlayer.start();
//                            mediaPlayer.start();

                            Log.d("Main", " GNM-JPP  PRONS AUDIOFOCUS_GAIN");
                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {

                            Log.d("Main", " GNM-JPP  PRONS AUDIOFOCUS_LOSS_TRANSIENT");
                        } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                            //mediaPlayer.reset();
                            //mediaPlayer.release();
//                            viewHolder.rowPronBtn.setImageResource(R.drawable.ic_play);
                            Log.d("Main", " GNM-JPP  PRONS AUDIOFOCUS_LOSS file:");



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

                    viewHolder.rowPronBtn.setImageResource(R.drawable.ic_stop);
//                    mediaPlayer = new MediaPlayer();
//                    mediaPlayerArray.add(mediaPlayer);



                    entryMediaPlayer = new MediaPlayer();

                    if(soundExists == 1){
                        try {
                            String tag = ((List<String>) viewHolder.rowPronBtn.getTag()).get(0).toString();
                            //String string = (String)  viewHolder.rowPronBtn.getTag(1);
                            // GetPronsTableValues getPronTableValues = pronsList.get(tag);
                            //final String pronString = getPronTableValues.getPron();

                            Log.d("Main", " GNM-JPP  PRON STARTED TAG:" + tag);
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

                        try {
                            //String tag = ((List<String>) viewHolder.rowPronBtn.getTag()).get(0).toString();
                            AssetFileDescriptor afd = context.getAssets().openFd(filePathFinal);
                            //mediaPlayer.setDataSource(afd.getFileDescriptor());
//                            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                            entryMediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                            afd.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            entryMediaPlayer.prepare();
//                            mediaPlayer.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(soundExists == 3){
//
                        entryMediaPlayer = MediaPlayer.create(context, rawFile);
                    }


//                    RecyclerViewAdapterEntries.mediaPlayerArray.add(mediaPlayer);
                    entryMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

//                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            //mediaPlayer.reset();
                            mediaPlayer.release();

                            //mediaPlayer = null;

                            if(soundExists == 1){
                                Log.d("Main", " GNM-JPP PATH  PRON : RELEASED ON COMPLETION file:");

                            } else {

                            }
                            viewHolder.rowPronBtn.setImageResource(R.drawable.ic_play);
                            //audioManager.abandonAudioFocus(audioFocusChangeListener);


                        }
                    });
                    entryMediaPlayer.start();
//                    mediaPlayer.start();


                    Log.d("Main", " GNM-JPP  PRON STARTED file");
                }
//                viewHolder.rowPronBtn.setImageResource(R.drawable.ic_stop);

            }


        };




        GetPronsTableValues getPronTableValues = pronsList.get(i);
        final String pronString = getPronTableValues.getPron();

        soundExists = 0;
        Log.d("Main", " GNM-JPP PRON STRING "+pronString);
        final String pronStringNoExtension = pronString.substring(0, pronString.lastIndexOf('.'));


        String mediaPath = context.getResources().getString(R.string.audio_path);
        String mediaPath2 = context.getResources().getString(R.string.audio_path2);
        final String filePathWav = mediaPath2 + pronStringNoExtension + ".wav";
        final String filePathMp3 = mediaPath2 + pronStringNoExtension + ".mp3";

        rawFile = context.getResources().getIdentifier(pronStringNoExtension, "raw", context.getPackageName());


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

                final String filePathSdMp3 = Environment.getExternalStorageDirectory() + mediaPath + pronStringNoExtension + ".mp3";
                final String filePathSdWav = Environment.getExternalStorageDirectory() + mediaPath + pronStringNoExtension + ".wav";

                checkAndGetFileFromExternalStorage(filePathSdWav, viewHolder);

                if (soundExists ==0){
                    checkAndGetFileFromExternalStorage(filePathSdMp3, viewHolder);
                }

            }



        }
        if(soundExists == 0){

//                         Checar se o arquivo de aúdio existe no servidor
            //                         Checar se o arquivo de aúdio existe no servidor

            final String audioPathWebMp3 = context.getResources().getString(R.string.audio_path_web_mp3);

            final String filePathWebMp3 = audioPathWebMp3 + pronStringNoExtension + ".mp3";


            final String audioPathWebWav = context.getResources().getString(R.string.audio_path_web_wav);

            final String filePathWebWav = audioPathWebWav + pronStringNoExtension + ".wav";

            checkAndGetFileFromServer(filePathWebMp3, viewHolder);

            if(soundExists ==0){

                checkAndGetFileFromServer(filePathWebWav, viewHolder);
            }


        }

        viewHolder.rowPronBtn.setOnClickListener(mStartListener);

    }



    protected void onDestroy() {

    }


    public void checkAndGetFileFromExternalStorage(String filePathSd, ViewHolder viewHolder) {
        File fileSd = new File(filePathSd);

        if (fileSd.exists()){

            filePathFinal = filePathSd;
            List<String> data = new ArrayList<String>();
            data.add(filePathFinal);

            viewHolder.rowPronBtn.setTag(data);
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


                        viewHolder.rowPronBtn.setTag(data);



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



    @Override
    public int getItemCount() {


        return pronsList.size();
    }


}

