package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetImagesTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseImagesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetImagesTableValues> imagesList = new ArrayList<>();
    Context context;
    long senseBundleId;
    long imageId;
    String image;

    public DatabaseImagesAdapter(Context context, long senseBundleIid, long imageId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.senseBundleId = senseBundleIid;
        this.imageId = imageId;


    }



    public List<GetImagesTableValues> getImages(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_IMAGEID);
            int imageId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_JPG);
            String image = cursor.getString(index4);

            GetImagesTableValues getImagesTableValues = new GetImagesTableValues(senseBundleId, entryRef, imageId, image);
            imagesList.add(getImagesTableValues);







        }
        return imagesList;


        } finally {
            cursor.close();
                        if(cursor.isClosed()) {
                Log.d("Cursor", "Cursor_Closed");
            }else{
                Log.d("Cursor", "Cursor_NOT_Closed");
            }
                        db.close();

}
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "out.db";
        private static final String TABLE_NAME = "images";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SENSEBUNDLEID = "sense_bundle_id";
        private static final String KEY_IMAGEID = "image_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String KEY_JPG = "jpg";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_SENSEBUNDLEID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_IMAGEID + " integer primary key autoincrement, " + KEY_JPG+ " text)";
        private static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            //Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try{
                //db.execSQL(CREATE_TABLE);
            }catch (SQLException e){
                //Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                //Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (SQLException e){
                //Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
