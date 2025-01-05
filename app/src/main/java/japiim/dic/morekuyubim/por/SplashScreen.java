 package japiim.dic.morekuyubim.por;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;

 public class SplashScreen extends AppCompatActivity {

    String filePathVersionWeb;
    String filePathWeb;
    private File tmpDir;
    private File tmpDir_version;
    private long referenceID;
    private long referenceIDVersion;
    private DownloadManager downloadManager;
    private DownloadManager downloadManagerVersion;
    Context context;
     String destPath;
     String destPath2;
     String destPath3;
     String destPath4;
     String destPath5;
     String destPath6;
     String destPath7;
     String destPath8;
     String destPath9;

     private int nversion;
    private int cversion;
    private int currentVersion;
    private int areSame;


    final static int MY_PERMISSIONS_REQUEST = 1;
    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public static  int someVariable;

    public static  int getSomeVariable() {
        return someVariable;
    }

    public void setSomeVariable(int someVariable) {
        this.someVariable = someVariable;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        

        int PERMISSION_ALL = 1;
        String[] PERMISSIONS = {
                android.Manifest.permission.INTERNET,
                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.ACCESS_NETWORK_STATE,
        };


        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


//        destPath = context.getFilesDir().getPath()+ this.getPackageName() + "/databases";
        destPath = "/data/data/"+ this.getPackageName() + "/databases";

        File f = new File(destPath);

        if(!f.exists()){
            //folder does not exist
            f.mkdir();
            try {
                //copy the db from assets folder into the databases folder
                rawCopy(this.getAssets().open("out.db"), new FileOutputStream(destPath + "/out.db"));

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d("GNM-JPP NEITHER FOLDER OR OUT.DB EXIST IN THE DEVICE. COPY FROM ASSETS and RE-RUN SPLASHSCREEN", destPath);



                //setSomeVariable(5);
            Intent main_intent = new Intent(this, androidx.core.splashscreen.SplashScreen.class);
            startActivity(main_intent);
            finish();

        }else{

            tmpDir = new File(destPath+"/out.db");
            boolean exists = tmpDir.exists();
            if(!exists) {

                try {
                    //copy the db from assets folder into the databases folder
                    rawCopy(this.getAssets().open("out.db"), new FileOutputStream(destPath + "/out.db"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
                Log.d("GNM-JPP  FOLDER EXISTS BUT OUT.DB DOESN'T. COPY FROM ASSETS and RE-RUN SPLASHSCREEN", destPath);

                //setSomeVariable(5);
                Intent main_intent = new Intent(this, androidx.core.splashscreen.SplashScreen.class);
                startActivity(main_intent);
                finish();


            }else{

                try {
                    cversion = getDbVersionFromFile(tmpDir);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("GNM-JPP  BOTH FOLDER AND OUT.DB EXISTS. VERSION: "+ cversion+" START MAIN ACTIVITY", destPath);


                if(NetworkUtils.isNetworkConnected(this)){


                    Log.d("GNM-JPP  OUT.DB EXISTS. CONNECTED. CURRENT VERSION: "+ cversion+"", destPath);


                    final String outPathWeb = this.getResources().getString(R.string.out_path_web);
                    filePathWeb = outPathWeb + "out.db";
                    filePathVersionWeb = outPathWeb + "version.db";




                    Thread firstThread = new Thread(() -> {

                         //your "file checking code" goes here like this
                            //write your results to log cat, since you cant do Toast from threads without handlers also...

                            try {
                                HttpURLConnection.setFollowRedirects(false);
                                // note : you may also need
                                //HttpURLConnection.setInstanceFollowRedirects(false)

                                HttpURLConnection con =  (HttpURLConnection) new URL(filePathVersionWeb).openConnection();

                                con.setRequestMethod("HEAD");

                                if( (con.getResponseCode() == HttpURLConnection.HTTP_OK) ){

                                    //out.db exists in the server (check db version in the server)


                                    InputStream in_version = new URL(filePathVersionWeb).openStream();
                                    rawCopy(in_version, new FileOutputStream(destPath + "/version.db"));

                                    Log.d("GNM-JPP OUT.DB FILE_EXISTS IN SERVER: ",  destPath);






                                }else{


                                    //out.db does not exist in the server and does not exist in the device - copy db from the assets

                                    Log.d("GNM-JPP OUT.DB FILE_DOESNT_EXIST in the SERVER KEEP THE CV: "+cversion, filePathWeb);

                                }


                            }
                            catch (Exception e) {
                                e.printStackTrace();
                                Log.d("GNM-JPP PATHWeb  FILE_DONT_EXISTS", filePathWeb);
                            }


                    });

                    Thread secondThread = new Thread(() -> {



                        tmpDir_version = new File(destPath+"/version.db");



                        try {
                            nversion = getDbVersionFromFile(tmpDir_version);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                        if(cversion == nversion){

                            //versions are the same. do nothing

                            areSame=1;
                            Log.d("GNM-JPP OUT.DB ARE THE SAME: CV: "+cversion+" NV: "+nversion +" Are the same: "+areSame,  destPath);

                        }else{
                            //versions are not the same. download db.

                            Log.d("GNM-JPP OUT.DB ARE NOT THE SAME: CV: "+cversion+" NV: "+nversion+" Are the same: "+areSame,  destPath);

                            areSame=0;

                        }



                    });


                    Thread thirdThread = new Thread(() -> {

                        try {
                            HttpURLConnection.setFollowRedirects(false);
                            // note : you may also need
                            //HttpURLConnection.setInstanceFollowRedirects(false)

                            HttpURLConnection con =  (HttpURLConnection) new URL(filePathWeb).openConnection();

                            con.setRequestMethod("HEAD");

                            if( (con.getResponseCode() == HttpURLConnection.HTTP_OK) ){

                                //out.db exists in the server (check db version in the server)


                                InputStream in_version = new URL(filePathWeb).openStream();
                                rawCopy(in_version, new FileOutputStream(destPath + "/out.db"));

                                //Log.d("GNM-JPP OUT.DB FILE_EXISTS IN SERVER: ",  destPath);






                            }else{
                                setSomeVariable(cversion);
                                Intent main_intent = new Intent(this, MainActivity.class);
                                startActivity(main_intent);
                                finish();


                                //out.db does not exist in the server and does not exist in the device - copy db from the assets

                                //Log.d("GNM-JPP OUT.DB FILE_DOESNT_EXIST in the SERVER KEEP THE CV: "+cversion, filePathWeb);

                            }


                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Log.d("GNM-JPP PATHWeb  FILE_DONT_EXISTS", filePathWeb);
                        }

                    });



                    Thread fourthThread = new Thread(() -> {

                                setSomeVariable(nversion);
                                Intent main_intent = new Intent(this, MainActivity.class);
                                startActivity(main_intent);
                                finish();

                    });


                firstThread.start();

                    try {
                        firstThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    secondThread.start();
                    try {
                        secondThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(areSame==1){
                        Log.d("GNM-JPP OUT.DB ARE THE SAME (OUTSIDE): CV: "+cversion+" NV: "+nversion +" Are the same: "+areSame,  destPath);

                        setSomeVariable(cversion);
                        Intent main_intent = new Intent(this, MainActivity.class);
                        startActivity(main_intent);
                        finish();

                    }else{

                        Log.d("GNM-JPP OUT.DB ARE NOT THE SAME (OUTSIDE): CV: "+cversion+" NV: "+nversion +" Are the same: "+areSame,  destPath);

                        thirdThread.start();
                        try {
                            thirdThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        fourthThread.start();
                        try {
                            fourthThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }



                }else{

                    Log.d("GNM-JPP  OUT.DB EXISTS. NO INTERNET TO CHECK VERSION. CURRENT VERSION: "+ cversion+" START MAIN ACTIVITY", destPath);

                    setSomeVariable(cversion);
                    Intent main_intent = new Intent(this, MainActivity.class);
                    startActivity(main_intent);
                    finish();



                }





            }


            //folder does exist
/*
            tmpDir = new File(destPath+"/out.db");
            boolean exists = tmpDir.exists();
            if(exists){


                //file and folder both exist (check db version in the server)



                final String outPathWeb = this.getResources().getString(R.string.out_path_web);
                filePathWeb = outPathWeb + "out.db";
                filePathVersionWeb = outPathWeb + "version.db";

                Uri version_uri = Uri.parse(filePathVersionWeb);
                referenceIDVersion = DownloadDBVersion(version_uri);
                registerReceiver(receiverVersion, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

            }else{
                //file do not exists (but folder does exist)

                try {
                    //copy the db from assets folder into the databases folder
                    rawCopy(this.getAssets().open("out.db"), new FileOutputStream(destPath + "/out.db"));
                    Log.d("GNM-JPP OUT.DB FILE_DOESNT_EXIST in the SERVER NEITHER IN THE DEVICE. COPY FROM ASSETS", destPath+ "/out.db");

                } catch (Exception e) {
                    e.printStackTrace();
                }
                setSomeVariable(1);
                Intent main_intent = new Intent(context, MainActivity.class);
                startActivity(main_intent);
                finish();



            }
*/

        }
















    }
    public void CreateFolderIfNotExists(String folderPath){

        File f = new File(folderPath);

        if(!f.exists()) {
            //folder does not exist
            f.mkdir();

        }

    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){



                DownloadManager.Query ImageDownloadQuery = new DownloadManager.Query();
                //set the query filter to our previously Enqueued download
                ImageDownloadQuery.setFilterById(referenceID);

                //Query the download manager about downloads that have been requested.
                Cursor cursor = downloadManager.query(ImageDownloadQuery);

                if(cursor.moveToFirst()){

                    int statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    int downloadManagerDownloadStatus = cursor.getInt(statusColumnIndex);

                    if (DownloadManager.STATUS_SUCCESSFUL == downloadManagerDownloadStatus) {

                        setSomeVariable(nversion);
                        Intent main_intent = new Intent(context, MainActivity.class);
                        startActivity(main_intent);
                        finish();

                        Log.d("GNM-JPP OUT.DB DOWNLOAD COMPLETE?" + nversion, destPath + "/out.db");

                        //Toast.makeText(this, DownloadStatus(cursor), Toast.LENGTH_SHORT).show();
                    }
                }



            }

        }
    };

    private BroadcastReceiver receiverVersion = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action2 = intent.getAction();

            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action2)){



                DownloadManager.Query VersionDownloadQuery = new DownloadManager.Query();
                //set the query filter to our previously Enqueued download
                VersionDownloadQuery.setFilterById(referenceIDVersion);

                //Query the download manager about downloads that have been requested.
                Cursor cursor = downloadManagerVersion.query(VersionDownloadQuery);


                tmpDir_version = new File(destPath+"/version.db");

                try {
                    nversion = getDbVersionFromFile(tmpDir_version);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.d("GNM-JPP OUT.DB. Version OUT.DB WEB: "+nversion,  destPath);

                if(cursor.moveToFirst()){

                    //int statusColumnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                   //int downloadManagerDownloadStatus = cursor.getInt(statusColumnIndex);



               }



            }

        }
    };

    private String DownloadStatus(Cursor cursor){

        //column for download  status
        int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
        int status = cursor.getInt(columnIndex);
        //column for reason code if the download failed or paused
        int columnReason = cursor.getColumnIndex(DownloadManager.COLUMN_REASON);
        int reason = cursor.getInt(columnReason);



        String statusText = "";
        String reasonText = "";

        switch(status){
            case DownloadManager.STATUS_FAILED:
                statusText = "STATUS_FAILED";
                switch(reason){
                    case DownloadManager.ERROR_CANNOT_RESUME:
                        reasonText = "ERROR_CANNOT_RESUME";
                        break;
                    case DownloadManager.ERROR_DEVICE_NOT_FOUND:
                        reasonText = "ERROR_DEVICE_NOT_FOUND";
                        break;
                    case DownloadManager.ERROR_FILE_ALREADY_EXISTS:
                        reasonText = "ERROR_FILE_ALREADY_EXISTS";
                        break;
                    case DownloadManager.ERROR_FILE_ERROR:
                        reasonText = "ERROR_FILE_ERROR";
                        break;
                    case DownloadManager.ERROR_HTTP_DATA_ERROR:
                        reasonText = "ERROR_HTTP_DATA_ERROR";
                        break;
                    case DownloadManager.ERROR_INSUFFICIENT_SPACE:
                        reasonText = "ERROR_INSUFFICIENT_SPACE";
                        break;
                    case DownloadManager.ERROR_TOO_MANY_REDIRECTS:
                        reasonText = "ERROR_TOO_MANY_REDIRECTS";
                        break;
                    case DownloadManager.ERROR_UNHANDLED_HTTP_CODE:
                        reasonText = "ERROR_UNHANDLED_HTTP_CODE";
                        break;
                    case DownloadManager.ERROR_UNKNOWN:
                        reasonText = "ERROR_UNKNOWN";
                        break;
                }
                break;
            case DownloadManager.STATUS_PAUSED:
                statusText = "STATUS_PAUSED";
                switch(reason){
                    case DownloadManager.PAUSED_QUEUED_FOR_WIFI:
                        reasonText = "PAUSED_QUEUED_FOR_WIFI";
                        break;
                    case DownloadManager.PAUSED_UNKNOWN:
                        reasonText = "PAUSED_UNKNOWN";
                        break;
                    case DownloadManager.PAUSED_WAITING_FOR_NETWORK:
                        reasonText = "PAUSED_WAITING_FOR_NETWORK";
                        break;
                    case DownloadManager.PAUSED_WAITING_TO_RETRY:
                        reasonText = "PAUSED_WAITING_TO_RETRY";
                        break;
                }
                break;
            case DownloadManager.STATUS_PENDING:
                statusText = "STATUS_PENDING";
                break;
            case DownloadManager.STATUS_SUCCESSFUL:
                statusText = "Image Saved Successfully";
                //reasonText = "Filename:\n" + filename;
                Toast.makeText(this, "Download Status:" + "\n" + statusText + "\n" + reasonText, Toast.LENGTH_SHORT).show();
                break;
        }

        return statusText + reasonText;


    }


    private long DownloadDB(Uri uri){

        long downloadReference;

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle("DB Download");

        //Setting description of request
        request.setDescription("DB download using DownloadManager.");


        request.setDestinationInExternalFilesDir(this, destPath, "/out.db");
        //request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadReference = downloadManager.enqueue(request);


        return  downloadReference;
    }

    private long DownloadDBVersion(Uri uri){

        long downloadReference;

        downloadManagerVersion = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        Uri myUri = Uri.parse(destPath+"/version.db");
        DownloadManager.Request request = new DownloadManager.Request(uri);
        //Setting title of request
        request.setTitle("DB Version Download");

        //Setting description of request
        request.setDescription("DB Version download using DownloadManager.");
        //request.setDestinationInExternalFilesDir(this,Environment.DIRECTORY_DOWNLOADS,"CountryList.json"); 

        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS,  destPath+"/version.db");
        //request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, destPath+"/version.db");
        //request.setDestinationUri(myUri);
        //request.set
        //request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadReference = downloadManagerVersion.enqueue(request);


        return  downloadReference;
    }


    public static void rawCopy(InputStream inputStream, OutputStream outputStream) throws IOException{
        // copu 1k bytes at a time
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) > 0){
            outputStream.write(buffer, 0, length);
        }
        inputStream.close();
        outputStream.close();
    }
    public static int getDbVersionFromFile(File file) throws IOException
    {
        RandomAccessFile fp = new RandomAccessFile(file,"r");
        fp.seek(60);
        byte[] buff = new byte[4];
        fp.read(buff, 0, 4);
        return ByteBuffer.wrap(buff).getInt();
    }

}