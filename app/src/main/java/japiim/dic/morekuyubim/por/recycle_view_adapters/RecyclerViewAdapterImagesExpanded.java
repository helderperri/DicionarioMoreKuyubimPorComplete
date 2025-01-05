package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static japiim.dic.morekuyubim.por.MyMediaPlayer.assetExists;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.checkPermissions;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.getExternalStoragePath;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.isExternalStorageAvailable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
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
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterImagesExpanded extends RecyclerView.Adapter<RecyclerViewAdapterImagesExpanded.ViewHolder> {

    Context context;
    List<GetImagesTableValues> imagesList;
    RecyclerView rvImagesExpanded;
    Bitmap bitmapWeb;
    String filePathFinal;
    boolean result;
    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.

    //Bitmap bitmapWeb;

    int imageExists;

    int rawFile;


    public boolean URLExists(String URLName){

        new Thread() {

            public synchronized void run() {
                try {


                    HttpURLConnection.setFollowRedirects(false);

                    HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
                    String contentType = con.getHeaderField("Content-Type");
                    result = contentType.startsWith("image/");


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("GNM-JPP PATHWeb IMAGE FILE_DONT_EXISTS", URLName);
                }
            }

        }.start();
        return result;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowImageId;
        ImageView rowImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowImageId = itemView.findViewById(R.id.tvImageId);
            rowImage = itemView.findViewById(R.id.ivImageExpanded);

        }


    }

    public class ThreadJoining extends Thread
    {
        @Override
        public void run()
        {
            for (int i = 0; i < 2; i++)
            {
                try
                {
                    Thread.sleep(500);
                    System.out.println("Current Thread: "
                            + Thread.currentThread().getName());
                }

                catch(Exception ex)
                {
                    System.out.println("Exception has" +
                            " been caught" + ex);
                }
                System.out.println(i);
            }
        }
    }


    public RecyclerViewAdapterImagesExpanded(Context context, List<GetImagesTableValues> imagesList, RecyclerView rvImagesExpanded){
        this.context = context;
        this.imagesList = imagesList;
        this.rvImagesExpanded = rvImagesExpanded;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterImagesExpanded.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_item_image_expanded, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        imageExists = 0;
        GetImagesTableValues getImagesTableValues = imagesList.get(i);
        String imageString = getImagesTableValues.getImage();
        String imageStringNoExtension = imageString.substring(0, imageString.lastIndexOf('.'));
        int noImageFile = context.getResources().getIdentifier("no_image", "raw", context.getPackageName());

        final String mediaPath = context.getResources().getString(R.string.image_path);
        final String mediaPath2 = context.getResources().getString(R.string.image_path2);

        rawFile = context.getResources().getIdentifier(imageStringNoExtension, "raw", context.getPackageName());

        if (rawFile !=0){

            imageExists = 3;
            InputStream imageStream = context.getResources().openRawResource(rawFile);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            viewHolder.rowImage.setImageBitmap(bitmap);

        }

        if (imageExists == 0) {

            final String filePathJpg = mediaPath2 + imageStringNoExtension + ".jpg";
            checkAndGetFileFromAssets(context, filePathJpg, viewHolder);

        }
        if (imageExists == 0) {

            final String filePathPng = mediaPath2 + imageStringNoExtension + ".png";
            checkAndGetFileFromAssets(context, filePathPng, viewHolder);

        }

        if (imageExists == 0) {

            final String filePathWebp = mediaPath2 + imageStringNoExtension + ".webp";
            checkAndGetFileFromAssets(context, filePathWebp, viewHolder);

        }

        if (imageExists == 0) {

            if (isExternalStorageAvailable() && checkPermissions(context)) {

                if (imageExists == 0) {

                    final String filePathSdPng = getExternalStoragePath(context, true) + mediaPath + imageStringNoExtension + ".spng";

                    checkAndGetFileFromExternalStorage(filePathSdPng, viewHolder);
                }

                if (imageExists == 0) {

                    final String filePathSdJpg = getExternalStoragePath(context, true) + mediaPath + imageStringNoExtension + ".sjpg";
                    checkAndGetFileFromExternalStorage(filePathSdJpg, viewHolder);
                }

                if (imageExists == 0) {

                    final String filePathSdWebp = getExternalStoragePath(context, true) + mediaPath + imageStringNoExtension + ".swebp";
                    checkAndGetFileFromExternalStorage(filePathSdWebp, viewHolder);

                }
            }
        }

        final String imagePathWebMin = context.getResources().getString(R.string.image_path_web_min);
        final String imagePathWebOrig = context.getResources().getString(R.string.image_path_web_orig);

        if (imageExists == 0) {

            final String imageFileJpg = imagePathWebMin + imageStringNoExtension + ".jpg";

            checkAndGetFileFromServer(imageFileJpg, viewHolder);

        }

        if (imageExists == 0) {

            final String imageFilePng = imagePathWebMin + imageStringNoExtension + ".png";

            checkAndGetFileFromServer(imageFilePng, viewHolder);

        }

        if (imageExists == 0) {

            final String imageFileWebp = imagePathWebMin + imageStringNoExtension + ".webp";

            checkAndGetFileFromServer(imageFileWebp, viewHolder);

        }


        if(imageExists == 0){

            InputStream imageStream = context.getResources().openRawResource(noImageFile);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            viewHolder.rowImage.setImageBitmap(bitmap);


        }


    }

    public void checkAndGetFileFromAssets(Context context, String imageFile, ViewHolder viewHolder) {

        if (assetExists(context, imageFile)) {
            imageExists = 1;

            InputStream imageStream = null;
            try {
                // get input stream
                imageStream = context.getAssets().open(imageFile);
                // load image as Drawable
                Drawable drawable = Drawable.createFromStream(imageStream, null);
                // set image to ImageView
                viewHolder.rowImage.setImageDrawable(drawable);
            } catch (IOException ex) {
                return;
            }
            Log.d("Main", " GNM-JPP image imageFile exists : " + imageFile + " EXISTS IN ASSETS!!!");

        }



    }
    public void checkAndGetFileFromExternalStorage(String filePathSd, ViewHolder viewHolder) {
        File imageFile = new File(filePathSd);

        if (imageFile.exists()) {
            imageExists = 1;
            filePathFinal = filePathSd;
            Bitmap imageBitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
            viewHolder.rowImage.setImageBitmap(imageBitmap);

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

                    HttpURLConnection con = (HttpURLConnection) new URL(imageFile).openConnection();
                    con.setRequestMethod("HEAD");


                    if ((con.getResponseCode() == HttpURLConnection.HTTP_OK)) {
                        imageExists = 2;
                        filePathFinal = imageFile;
                        Log.d("Main", " GNM-JPP IMAGE WEBP PATHWeb EXISTS: " + imageFile);

                        Glide.with(context)
                                .asBitmap()
                                .load(filePathFinal)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .into(new CustomTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        viewHolder.rowImage.setImageBitmap(resource);
                                        viewHolder.rowImage.buildDrawingCache();
                                    }

                                    @Override
                                    public void onLoadCleared(@Nullable Drawable placeholder) {
                                    }
                                });
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    public int getItemCount() {
        return imagesList.size();
    }


}
