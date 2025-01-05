package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.assetExists;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.checkPermissions;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.getExternalStoragePath;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.isExternalStorageAvailable;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.stopAllMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.ViewAdapterImageExpanded;
import japiim.dic.morekuyubim.por.get_table_values.GetVideosTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.ViewAdapterVideoPlay;

public class RecyclerViewAdapterVideosThumb  extends RecyclerView.Adapter<RecyclerViewAdapterVideosThumb.ViewHolder> {

    Context context;
    List<GetVideosTableValues> videosList;
    RecyclerView rvVideos;

    String filePathFinal;

    int videoThumbExists;
    
    Bitmap videoThumbBitmap;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowVideoId;
        ImageView rowVideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowVideoId = itemView.findViewById(R.id.tvVideoId);
            rowVideo = itemView.findViewById(R.id.ivVideoThumb);

        }
    }

    public RecyclerViewAdapterVideosThumb(Context context, List<GetVideosTableValues> videosList, RecyclerView rvVideos){
        this.context = context;
        this.videosList = videosList;
        this.rvVideos = rvVideos;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterVideosThumb.ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        GetVideosTableValues getVideoTableValues = videosList.get(i);
        String videoString = getVideoTableValues.getVideo();

        final String videoStringNoExtension = videoString.substring(0, videoString.lastIndexOf('.'));
        int video_thumb = context.getResources().getIdentifier("no_image", "raw", context.getPackageName());
        videoThumbExists = 0;

        // PASSO 1
        // Checar se o arquivo de aúdio existe gravado no aparelho (via cópia física) (no ASSETS)

        String mediaPath = context.getResources().getString(R.string.video_thumb_path);
        String mediaPath2 = context.getResources().getString(R.string.video_thumb_path2);

        final String filePathThumbWebp = mediaPath2+videoStringNoExtension+".webp";
        final String filePathThumbPng = mediaPath2+videoStringNoExtension+".png";
        final String filePathThumbJpg = mediaPath2+videoStringNoExtension+".jpg";
        Log.d("Main", " GNM-JPP VIDEO THUMB WEBP IN ASSETS: "+ filePathThumbWebp);

        checkAndGetFileFromAssets(context, filePathThumbWebp, viewHolder);

        if(videoThumbExists == 0){

            checkAndGetFileFromAssets(context, filePathThumbPng, viewHolder);
        }

        if(videoThumbExists == 0) {

            checkAndGetFileFromAssets(context, filePathThumbJpg, viewHolder);

        }

        if(videoThumbExists == 0) {


            if(isExternalStorageAvailable() && checkPermissions(context)) {

                final String fileThumbPathSdWebp = Environment.getExternalStorageDirectory()+mediaPath2+videoStringNoExtension+".webp";
//                    Log.d("Main", " GNM-JPP VIDEO_PATHSD THUMB : "+ fileThumbPathSd);
                final String fileThumbPathSdPng = Environment.getExternalStorageDirectory()+mediaPath2+videoStringNoExtension+".png";
//                    Log.d("Main", " GNM-JPP VIDEO_PATHSD THUMB : "+ fileThumbPathSd);
                final String fileThumbPathSdJpg = Environment.getExternalStorageDirectory()+mediaPath2+videoStringNoExtension+".jpg";
//                    Log.d("Main", " GNM-JPP VIDEO_PATHSD THUMB : "+ fileThumbPathSd);



                checkAndGetFileFromExternalStorage(fileThumbPathSdWebp, viewHolder);

                if(videoThumbExists == 0) {

                    checkAndGetFileFromExternalStorage(fileThumbPathSdPng, viewHolder);

                }

                if(videoThumbExists == 0) {

                    checkAndGetFileFromExternalStorage(fileThumbPathSdJpg, viewHolder);

                }
        }

        if(videoThumbExists == 0) {

//                         Checar se o arquivo de imagem (video thumb) existe no servidor

            String videoPathWeb = context.getResources().getString(R.string.video_thumb_path_web);
            String filePathWeb = videoPathWeb+videoStringNoExtension+".webp";
            Log.d("Main", " GNM-JPP VIDEO THUMB PATHWeb : "+ filePathWeb);

            checkAndGetFileFromServer(filePathWeb, viewHolder);
        }


    }

        if(videoThumbExists == 0){

            InputStream imageStream = context.getResources().openRawResource(video_thumb);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            viewHolder.rowVideo.setImageBitmap(bitmap);


        }

        viewHolder.rowVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Main", "VIDEO THUMB CLICKED");

                stopAllMediaPlayer();

                //int itemPosition = rvImages.getChildLayoutPosition(v);
                //String image_string = imagesList.get(itemPosition).getImage();
                //String entryRef = formsList.get(itemPosition).getEntryRef();
                long videoId = getVideoTableValues.getVideoId();
                long senseBundleId = getVideoTableValues.getSenseBundleId();
                //long formId = formsList.get(itemPosition).getFormId();

                //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewAdapterVideoPlay.class);
                intent.putExtra("video_id", videoId);
                intent.putExtra("sense_bundle_id", senseBundleId);
                intent.putExtra("video", videoStringNoExtension);
                intent.putExtra("position", i);

                //intent.putExtra("form_id", formId);
                //intent.putExtra("vernacular", vernacular);
                //intent.putExtra("entry_ref", entryRef);
                context.startActivity(intent);
            }

        });

    }


    @NonNull
    @Override
    public RecyclerViewAdapterVideosThumb.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_video_thumb, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



    public void checkAndGetFileFromAssets(Context context, String imageFile, ViewHolder viewHolder) {


        if(assetExists(context, imageFile)){
            videoThumbExists = 1;

            filePathFinal = imageFile;
            AssetManager assetManager = context.getAssets();
            InputStream is = null;
            try {
                is = assetManager.open(filePathFinal);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            videoThumbBitmap = BitmapFactory.decodeStream(is);
            viewHolder.rowVideo.setImageBitmap(videoThumbBitmap);

            Log.d("Main", " GNM-JPP PATH : " + imageFile + "EXISTS !!!!!!!!!");

        }


    }
    public void checkAndGetFileFromExternalStorage(String fileThumbPathSd, ViewHolder viewHolder) {

        File imageFile = new File(fileThumbPathSd);

        if (imageFile.exists()){
            videoThumbExists = 1;
            filePathFinal = fileThumbPathSd;
            videoThumbBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());

            viewHolder.rowVideo.setImageBitmap(videoThumbBitmap);

        }

    }

    public void checkAndGetFileFromServer(String imageFile, ViewHolder viewHolder){

        new Thread() {

            public void run() {
                //your "file checking code" goes here like this
                //write your results to log cat, since you cant do Toast from threads without handlers also...

                try {
                    HttpURLConnection.setFollowRedirects(false);
                    // note : you may also need
                    //HttpURLConnection.setInstanceFollowRedirects(false)

                    HttpURLConnection con =  (HttpURLConnection) new URL(imageFile).openConnection();
                    con.setRequestMethod("HEAD");



                    if( (con.getResponseCode() == HttpURLConnection.HTTP_OK) ){
                        videoThumbExists = 2;
                        filePathFinal = imageFile;
                        Log.d("Main", " GNM-JPP VIDEO THUMB PATHWeb EXISTS: "+ imageFile);

                        Glide.with(context)
                                .asBitmap()
                                .load(filePathFinal)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        viewHolder.rowVideo.setImageBitmap(resource);
                                        viewHolder.rowVideo.buildDrawingCache();
                                    }
                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) { }
                                });
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.d("GNM-JPP PATHWeb VIDEO THUMB FILE_DONT_EXISTS", "File not found in the server");
                }
            }
        }.start();

    }



    @Override
    public int getItemCount() {
        return videosList.size();
    }

}
