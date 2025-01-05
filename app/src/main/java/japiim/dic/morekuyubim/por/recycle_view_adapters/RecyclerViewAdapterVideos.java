
package japiim.dic.morekuyubim.por.recycle_view_adapters;

import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import android.widget.VideoView;

import japiim.dic.morekuyubim.por.get_table_values.GetVideosTableValues;
import japiim.dic.morekuyubim.por.R;

public class RecyclerViewAdapterVideos extends RecyclerView.Adapter<RecyclerViewAdapterVideos.ViewHolder> {

    Context context;
    List<GetVideosTableValues> videosList;
    RecyclerView rvVideos;
    //Bitmap bitmapWeb;

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

//        TextView rowVideoId;
        VideoView rowVideo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //rowVideoId = itemView.findViewById(R.id.tvVideoId);
            rowVideo = itemView.findViewById(R.id.vvVideo);

        }
    }

    public RecyclerViewAdapterVideos(Context context, List<GetVideosTableValues> videosList, RecyclerView rvVideos){
        this.context = context;
        this.videosList = videosList;
        this.rvVideos = rvVideos;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterVideos.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.single_item_video, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterVideos.ViewHolder viewHolder, int i) {






        GetVideosTableValues getVideoTableValues = videosList.get(i);
        String videoString = getVideoTableValues.getVideo();

        String videoStringNoExtension = videoString.substring(0, videoString.lastIndexOf('.'));
        int video = context.getResources().getIdentifier(videoStringNoExtension, "raw", context.getPackageName());

        if ( video != 0 ) {



        }else {

            //final String filePath = Environment.getExternalStorageDirectory()+"/Japiim/morekuyubim/video/"+videoString+"";
            String mediaPath = context.getResources().getString(R.string.video_path);
            String mediaPath2 = context.getResources().getString(R.string.video_path);
            final String filePathMp4 = Environment.getExternalStorageDirectory()+mediaPath2+videoStringNoExtension+".mp4";
            Log.d("Main", " GNM-JPP PATH : "+ filePathMp4);
     
            File fileMp4 = new File(filePathMp4);
            Log.d("Main", " GNM-JPP video exists : "+fileMp4.exists() + ", can read : " + fileMp4.canRead());
     

            if(fileMp4.exists()){





                }else{



                final String filePathSdMp4 = getExternalStoragePath(context, true)+mediaPath+videoStringNoExtension+".mp4";
                Log.d("Main", " GNM-JPP PATHSD : "+ filePathSdMp4);

                File fileSdMp4 = new File(filePathSdMp4);
                Log.d("Main", " GNM-JPP video exists : "+fileSdMp4.exists() + ", can read : " + fileSdMp4.canRead());


                if (fileSdMp4.exists()){






                    //MediaController mediaController= new MediaController(context);
                    //mediaController.setAnchorView(viewHolder.rowVideo);
                    //Uri uri=Uri.parse(filePathSdMp4);
                    Uri uri=Uri.fromFile(fileSdMp4);
                    viewHolder.rowVideo.setVideoURI(uri);
                    //viewHolder.rowVideo.requestFocus();

                    viewHolder.rowVideo.start();


                    viewHolder.rowVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.setLooping(true);
                        }
                    });

                }else {



                    String videoPathWeb = context.getResources().getString(R.string.video_path_web);
                    final String filePathWeb = videoPathWeb+videoString+"";
                    Log.d("Main", " GNM-JPP PATHWeb : "+ filePathWeb);

                    //File fileWeb = new File(filePathWeb);
                    //Log.d("Main", " GNM-JPP video exists : "+fileWeb.exists() + ", can read : " + fileWeb.canRead());




                }








            }

        }








    }






    @Override
    public int getItemCount() {
        return videosList.size();
    }

}
