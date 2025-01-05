
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import static japiim.dic.morekuyubim.por.MyMediaPlayer.assetExists;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.checkPermissions;
import static japiim.dic.morekuyubim.por.MyMediaPlayer.isExternalStorageAvailable;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

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


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.ViewAdapterImageExpanded;

public class RecyclerViewAdapterImages extends RecyclerView.Adapter<RecyclerViewAdapterImages.ViewHolder> {

    Context context;
    List<GetImagesTableValues> imagesList;
    RecyclerView rvImages;

    int imageExists;
    //final public View.OnClickListener onClickListener = new MyOnClickListner();

    String filePathFinal;

    int rawFile;


    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowImageId;
        ImageView rowImage;
        ImageView imageExpanded;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowImageId = itemView.findViewById(R.id.tvImageId);
            rowImage = itemView.findViewById(R.id.ivImage);

        }


    }

    public RecyclerViewAdapterImages(Context context, List<GetImagesTableValues> imagesList, RecyclerView rvImages){
        this.context = context;
        this.imagesList = imagesList;
        this.rvImages = rvImages;
    }



    @NonNull
    @Override
    public RecyclerViewAdapterImages.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_item_image, viewGroup, false);
        //view.setOnClickListener(onClickListener);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        GetImagesTableValues getImagesTableValues = imagesList.get(i);
        String imageString = getImagesTableValues.getImage();
        String imageStringNoExtension = imageString.substring(0, imageString.lastIndexOf('.'));
        imageExists = 0;

        int noImageFile = context.getResources().getIdentifier("no_image", "raw", context.getPackageName());

        String mediaPath = context.getResources().getString(R.string.image_path);
        String mediaThumbPath = context.getResources().getString(R.string.image_thumb_path);

        String mediaPath2 = context.getResources().getString(R.string.image_path2);
        String mediaThumbPath2 = context.getResources().getString(R.string.image_thumb_path2);


        rawFile = context.getResources().getIdentifier(imageStringNoExtension, "raw", context.getPackageName());


        if (rawFile !=0){

            imageExists = 3;
            InputStream imageStream = context.getResources().openRawResource(rawFile);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            viewHolder.rowImage.setImageBitmap(bitmap);

        }

        if(imageExists == 0) {

            final String fileThumbPathWebp = mediaThumbPath2 + imageStringNoExtension + ".webp";
            checkAndGetFileFromAssets(context, fileThumbPathWebp, viewHolder);

        }

        if(imageExists == 0){

            if(isExternalStorageAvailable () && checkPermissions(context)) {


                if(imageExists == 0) {

                    final String fileThumbPathSdWebp = Environment.getExternalStorageDirectory()+mediaThumbPath+imageStringNoExtension+".webp";

                    checkAndGetFileFromExternalStorage(fileThumbPathSdWebp, viewHolder);
                }

                if(imageExists == 0){

                    final String fileThumbPathSdPng = Environment.getExternalStorageDirectory()+mediaThumbPath+imageStringNoExtension+".png";

                    checkAndGetFileFromExternalStorage(fileThumbPathSdPng, viewHolder);

                }
                if(imageExists == 0){

                    final String fileThumbPathSdJpg = Environment.getExternalStorageDirectory()+mediaThumbPath+imageStringNoExtension+".jpg";

                    checkAndGetFileFromExternalStorage(fileThumbPathSdJpg, viewHolder);

                }
            }
        }


        final String imagePathWeb = context.getResources().getString(R.string.image_path_web);
        final String imageThumbPathWeb = context.getResources().getString(R.string.image_thumb_path_web);

        if(imageExists == 0){

            final String filePathThumbWebWebp = imageThumbPathWeb + imageStringNoExtension+".webp";
            checkAndGetFileFromServer(filePathThumbWebWebp, viewHolder);

        }


        if(imageExists == 0){

            final String filePathThumbWebPng = imageThumbPathWeb + imageStringNoExtension+".png";
            checkAndGetFileFromServer(filePathThumbWebPng, viewHolder);

        }

        if(imageExists == 0){

            final String filePathThumbWebJpg = imageThumbPathWeb + imageStringNoExtension+".jpg";
            checkAndGetFileFromServer(filePathThumbWebJpg, viewHolder);

        }

        if(imageExists == 0){

            InputStream imageStream = context.getResources().openRawResource(noImageFile);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            viewHolder.rowImage.setImageBitmap(bitmap);

        }

        viewHolder.rowImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Main", "IMAGE THUMB CLICKED v4");

                //int itemPosition = rvImages.getChildLayoutPosition(v);
                //String image_string = imagesList.get(itemPosition).getImage();
                //String entryRef = formsList.get(itemPosition).getEntryRef();
                long imageId = getImagesTableValues.getImageId();
                long senseBundleId = getImagesTableValues.getSenseBundleId();
                //long formId = formsList.get(itemPosition).getFormId();

                //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, ViewAdapterImageExpanded.class);
                intent.putExtra("image_id", imageId);
                intent.putExtra("sense_bundle_id", senseBundleId);
                intent.putExtra("image", imageStringNoExtension);
                intent.putExtra("position", i);

                //intent.putExtra("form_id", formId);
                //intent.putExtra("vernacular", vernacular);
                //intent.putExtra("entry_ref", entryRef);
                context.startActivity(intent);


            }

        });

    }

    private class MyOnClickListner implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = rvImages.getChildLayoutPosition(v);
            String image_string = imagesList.get(itemPosition).getImage();
            //String entryRef = formsList.get(itemPosition).getEntryRef();
            long imageId = imagesList.get(itemPosition).getImageId();
            long senseBundleId = imagesList.get(itemPosition).getSenseBundleId();
            //long formId = formsList.get(itemPosition).getFormId();

            //Toast.makeText(context, vernacular, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, ViewAdapterImageExpanded.class);
            intent.putExtra("image_id", imageId);
            intent.putExtra("sense_bundle_id", senseBundleId);
            intent.putExtra("image", image_string);

            //intent.putExtra("form_id", formId);
            //intent.putExtra("vernacular", vernacular);
            //intent.putExtra("entry_ref", entryRef);
            context.startActivity(intent);


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
    public void checkAndGetFileFromExternalStorage(String fileThumbPathSd, ViewHolder viewHolder) {

        File imageFile = new File(fileThumbPathSd);

        if (imageFile.exists()) {
            imageExists = 1;
            filePathFinal = fileThumbPathSd;
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
