package japiim.dic.morekuyubim.por.recycle_view_adapters;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterImagesIntroExpanded extends RecyclerView.Adapter<RecyclerViewAdapterImagesIntroExpanded.ViewHolder> {

    Context context;
    int imageId;
    RecyclerView rvImagesExpanded;
    Bitmap bitmapWeb;
    String filePathFinal;

    String imageName;
    boolean result;
    // The system "short" animation time duration, in milliseconds. This
    // duration is ideal for subtle animations or animations that occur
    // very frequently.

    //Bitmap bitmapWeb;




    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView rowImageId;
        ImageView ivIntro;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowImageId = itemView.findViewById(R.id.tvImageId);
            ivIntro = itemView.findViewById(R.id.ivImageExpanded);

        }


    }






    public RecyclerViewAdapterImagesIntroExpanded(Context context, int imageId, RecyclerView rvImagesExpanded){
        this.context = context;
        this.imageId = imageId;
        this.rvImagesExpanded = rvImagesExpanded;
    }


    @NonNull
    @Override
    public RecyclerViewAdapterImagesIntroExpanded.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.single_item_image_expanded_intro, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

//        String fileStringNoExtension2 = "territory2";
//        Integer fileId2 = context.getResources().getIdentifier(imageId, "raw", context.getPackageName());

        InputStream imageStream2 = context.getResources().openRawResource(imageId);
        Bitmap bitmap2 = BitmapFactory.decodeStream(imageStream2);
        viewHolder.ivIntro.setImageBitmap(bitmap2);
//        viewHolder.ivIntro.setImageDrawable(context.getDrawable(R.drawable.prodoclin_splash));


    }




    @Override
    public int getItemCount() {
        return 1;
    }


}
