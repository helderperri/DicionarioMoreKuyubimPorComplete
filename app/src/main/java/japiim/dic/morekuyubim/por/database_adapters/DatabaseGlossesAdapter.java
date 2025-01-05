package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.get_table_values.GetGlossesTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseGlossesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetGlossesTableValues> glossesList = new ArrayList<>();
    Context context;
    long senseBundleId;
    long glossId;
    String langCodeSearch;
    public DatabaseGlossesAdapter(Context context, long senseBundleId, long glossId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.senseBundleId = senseBundleId;
        this.glossId = glossId;
    }

    public List<GetGlossesTableValues> getGlosses(){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + " AND lang_code = " + langCodeSearch + " ORDER BY " + DatabaseHelper.KEY_GLOSS + "", null);
        //StringBuffer buffer = new StringBuffer();

        try{

        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index1);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_GLOSSID);
            int glossId = cursor.getInt(index4);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_GLOSS);
            String gloss = cursor.getString(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetGlossesTableValues getGlossesTableValues = new GetGlossesTableValues(senseBundleId, glossId, gloss, entryRef);
            glossesList.add(getGlossesTableValues);

        }
        return glossesList;

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



    public List<GetGlossesTableValues> getGlossesToSearchDisplay(){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE lang_code = '" + langCodeSearch + "' ORDER BY " + DatabaseHelper.KEY_GLOSS + " COLLATE UNICODE", null);
        //StringBuffer buffer = new StringBuffer();
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index1);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_GLOSSID);
            int glossId = cursor.getInt(index4);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_GLOSS);
            String gloss = cursor.getString(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetGlossesTableValues getGlossesTableValues = new GetGlossesTableValues(senseBundleId, glossId, gloss, entryRef);
            glossesList.add(getGlossesTableValues);

        }
        return glossesList;

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
        private static final String TABLE_NAME = "glosses";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SENSEBUNDLEID = "sense_bundle_id";
        private static final String KEY_GLOSSID = "gloss_id";
        private static final String KEY_GLOSS="gloss";
        private static final String KEY_ENTRYREF = "entry_ref";
        //private static final String KEY_IMAGE = "image";
        //private static final String KEY_WIKI = "wiki";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_SENSEBUNDLEID+" integer, "+ KEY_GLOSSID + " integer primary key autoincrement, "+ KEY_GLOSS+ " text, "+ KEY_ENTRYREF+ " text) DEFAULT CHARSET=utf8;";
        private static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            ////Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                //db.execSQL(CREATE_TABLE);
                //Toast.makeText(context, "onCreate called", Toast.LENGTH_SHORT).show();
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
