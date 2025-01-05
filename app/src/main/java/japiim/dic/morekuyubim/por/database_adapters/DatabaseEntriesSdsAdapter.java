package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetEntriesSdsTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseEntriesSdsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetEntriesSdsTableValues> entriesSdsList = new ArrayList<>();
    Context context;
    String langCode;
    String sdName;
    long targetLang;
    long sdId;
    long entrySdId;
    String langCodeSearch;

    Cursor cursor;

    public DatabaseEntriesSdsAdapter(Context context, long targetLang, String langCode) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.langCode = langCode;
        this.targetLang = targetLang;
        //this.senseBundleId = senseBundleIid;
        //this.senseId = senseId;

    }


    public List<GetEntriesSdsTableValues> getEntriesSds(){
        langCode = context.getString(R.string.target_lang_code);
        cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE lang_code = '" + langCode + "'", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYSDID);
            int entrySdId = cursor.getInt(index5);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_TARGETLANG);
            int targetLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_SDID);
            int sdId = cursor.getInt(index9);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_SDNAME);
            String sdName = cursor.getString(index11);

            GetEntriesSdsTableValues getEntriesSdsTableValues = new GetEntriesSdsTableValues(targetLang, langCode, sdId, sdName);
            entriesSdsList.add(getEntriesSdsTableValues);



        }



        return entriesSdsList;


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


    public List<GetEntriesSdsTableValues> getSdsToSearchDisplay(){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE lang_code = '" +  langCodeSearch + "'", null);
        //StringBuffer buffer = new StringBuffer();
        try{
        while(cursor.moveToNext()){

            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYSDID);
            int entrySdId = cursor.getInt(index5);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_TARGETLANG);
            int targetLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_SDID);
            int sdId = cursor.getInt(index9);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_SDNAME);
            String sdName = cursor.getString(index11);

            GetEntriesSdsTableValues getEntriesSdsTableValues = new GetEntriesSdsTableValues(targetLang, langCode, sdId, sdName);
            entriesSdsList.add(getEntriesSdsTableValues);
            Log.d("GNM-JPP", "getSdsToSearchDisplay: "+sdName);


        }
//            o statement abaixo é apenas para debug
//            for (int i2=0; i2<entriesSdsList.size(); i2++) {
//                GetEntriesSdsTableValues sdName3 = entriesSdsList.get(i2);
//                String sdname_3t = sdName3.getSdName();
//                Log.d("GNM-JPP", "getSdsToSearchDisplay sdname_93t: "+sdname_3t);
//
//            }
        return entriesSdsList;

        } finally {
            cursor.close();
                        if(cursor.isClosed()) {
                Log.d("GNM-JPP", "Cursor: Cursor_Closed");
            }else{
                Log.d("GNM-JPP", "Cursor: Cursor_NOT_Closed");
            }
                        db.close();

}
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {


        private static final String DATABASE_NAME = "out.db";
        private static final String TABLE_NAME = "entries_sds";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SDID = "sd_id";
        private static final String KEY_ENTRYSDID = "entry_sd_id";
        private static final String KEY_TARGETLANG = "target_lang";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String KEY_SDNAME = "sd_name";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_ENTRYSDID+" integer primary key autoincrement, " + KEY_TARGETLANG + " integer, " + KEY_LANGCODE + " text, " + KEY_SDID + " int, " + KEY_SDNAME + " text)";
        private static final String DROP_TABLE = "drop table if exists "+ TABLE_NAME;
        private Context context;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
//            Toast.makeText(context, "constructor called", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            try{
                //db.execSQL(CREATE_TABLE);
            }catch (SQLException e){
//                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
//                Toast.makeText(context, "onUpgrade called", Toast.LENGTH_SHORT).show();
                //db.execSQL(DROP_TABLE);
                //onCreate(db);
            }catch (SQLException e){
//                Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
