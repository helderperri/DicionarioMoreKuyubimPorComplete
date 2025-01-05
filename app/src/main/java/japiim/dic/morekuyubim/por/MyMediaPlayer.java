package japiim.dic.morekuyubim.por;

import static android.os.Build.VERSION.SDK_INT;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.entryMediaPlayer;
import static japiim.dic.morekuyubim.por.recycle_view_adapters.RecyclerViewAdapterEntries.sViews;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
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

public class MyMediaPlayer {

    public static void PlayMediaFromAssets(Context context, String filePathFinal) {


        try {
            //String tag = ((List<String>) viewHolder.rowPronBtn.getTag()).get(0).toString();
            AssetFileDescriptor afd = context.getAssets().openFd(filePathFinal);
            //mediaPlayer.setDataSource(afd.getFileDescriptor());
//                            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            entryMediaPlayer.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());

            afd.close();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        try {
            entryMediaPlayer.prepare();
//                            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static boolean assetExists(Context context, String path) {
        boolean bAssetOk = false;
        try {
            InputStream stream = context.getAssets().open( path);
            stream.close();
            bAssetOk = true;
        } catch (FileNotFoundException e) {
            Log.w("IOUtilities", "assetExists failed: "+e.toString());
        } catch (IOException e) {
            Log.w("IOUtilities", "assetExists failed: "+e.toString());
        }
        return bAssetOk;
    }


    public static boolean checkPermissions(Context context) {
        boolean permission = false;

        String permissionRead = Manifest.permission.READ_EXTERNAL_STORAGE;
        boolean checkValRead;
        if (context.getPackageManager().checkPermission(permissionRead, context.getPackageName()) == PackageManager.PERMISSION_GRANTED){

            checkValRead = true;

        } else {

            checkValRead = false;
        }

        String permissionWrite = Manifest.permission.WRITE_EXTERNAL_STORAGE;
        boolean checkValWrite;

        if (context.getPackageManager().checkPermission(permissionWrite, context.getPackageName()) == PackageManager.PERMISSION_GRANTED){

            checkValWrite = true;

        } else {

            checkValWrite = false;
        }
        Log.d("Main", " GNM-JPP checkValRead : "+ checkValRead);

        if ((checkValRead==true)&&(checkValWrite==true)) {


            permission = true;
        }
        Log.d("Main", " GNM-JPP permission : "+ permission);

        return permission;

    }



    public void myCopyFile(Context context, String filePathWeb, String fileName){

        File path = new File(getExternalStoragePath(context, false)+"/Japiim/dw/");


    }

    public static boolean isExternalStorageAvailable () {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    public static void stopAllMediaPlayer() {


        if (entryMediaPlayer != null){

            entryMediaPlayer.release();

            entryMediaPlayer = null;

        }

        for (ImageView iv : sViews){

            iv.setImageResource(R.drawable.ic_play);

        }


    }


    public static String getExternalStoragePath(Context mContext, boolean is_removable) {

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


    public static void myDownloader(Context context, String filePathWeb, String fileName) {


        Uri uri = Uri.parse(filePathWeb);

        DownloadManager.Request request;
        request = new DownloadManager.Request(uri);
        request.setDescription("O arquivo selecionado está sendo baixado");
        request.allowScanningByMediaScanner();
        request.setTitle("Fazendo downloading do arquivo");
        String externalDir = Environment.DIRECTORY_DOWNLOADS;
        Log.w("externalDir", externalDir);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        //Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, fileName);
//        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "//japiisssm/au/"+fileName);
//            request.setDestinationUri(uri2);
//        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "//japiisssm/au/"+fileName);
        //To Store file in External Public Directory use
//            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
        DownloadManager manager = (DownloadManager)
                context.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);


    }



    public static Bitmap getBitmapFromURL (String src) {

        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        }catch (Exception e){
            //Log.d("Main", e.getMessage());
            e.printStackTrace();
            return null;

        }

    }

}
