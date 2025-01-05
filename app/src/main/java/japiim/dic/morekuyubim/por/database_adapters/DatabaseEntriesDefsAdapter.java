package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetEntriesDefsTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseEntriesDefsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetEntriesDefsTableValues> entriesDefsList = new ArrayList<>();
    Context context;
    long senseBundleId;
    long senseId;
    long entryBundleId;
    String langCodeSearch;

    public DatabaseEntriesDefsAdapter(Context context, long entryBundleId, long senseBundleIid, long senseId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.entryBundleId = entryBundleId;
        this.senseBundleId = senseBundleIid;
        this.senseId = senseId;

    }


    public List<GetEntriesDefsTableValues> getEntriesDefs(){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE entry_bundle_id = " + entryBundleId + " AND lang_code = '" + langCodeSearch + "' ", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;

        try{

        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEID);
            int senseId = cursor.getInt(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYDEFID);
            int entryDefId = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEORDER);
            int senseOrder = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_TARGETLANG);
            int targetLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_DEF);
            String def = cursor.getString(index9);
            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSID);
            int classId = cursor.getInt(index10);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSNAME);
            String className = cursor.getString(index11);

            GetEntriesDefsTableValues getEntriesDefsTableValues = new GetEntriesDefsTableValues(entryBundleId, entryId, senseBundleId, senseId, senseOrder, targetLang, langCode, def, classId, className);
            entriesDefsList.add(getEntriesDefsTableValues);



        }
        return entriesDefsList;


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


    public List<GetEntriesDefsTableValues> getEntriesDefsBySenseBundle(long senseBundleId){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + " AND lang_code = '" + langCodeSearch + "'", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;

        try{

        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId2 = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEID);
            int senseId = cursor.getInt(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYDEFID);
            int entryDefId = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEORDER);
            int senseOrder = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_TARGETLANG);
            int targetLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_DEF);
            String def = cursor.getString(index9);
            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSID);
            int classId = cursor.getInt(index10);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSNAME);
            String className = cursor.getString(index11);

            GetEntriesDefsTableValues getEntriesDefsTableValues = new GetEntriesDefsTableValues(entryBundleId, entryId, senseBundleId, senseId, senseOrder, targetLang, langCode, def, classId, className);
            entriesDefsList.add(getEntriesDefsTableValues);



        }
        return entriesDefsList;


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


    public List<GetEntriesDefsTableValues> getDefssToSearchDisplay(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " ORDER BY " + DatabaseHelper.KEY_DEF + " COLLATE UNICODE", null);
        //StringBuffer buffer = new StringBuffer();
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEID);
            int senseId = cursor.getInt(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYDEFID);
            int entryDefId = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEORDER);
            int senseOrder = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_TARGETLANG);
            int targetLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_DEF);
            String def = cursor.getString(index9);
            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSID);
            int classId = cursor.getInt(index10);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSNAME);
            String className = cursor.getString(index11);

            GetEntriesDefsTableValues getEntriesDefsTableValues = new GetEntriesDefsTableValues(entryBundleId, entryId, senseBundleId, senseId, senseOrder, targetLang, langCode, def, classId, className);
            entriesDefsList.add(getEntriesDefsTableValues);

        }
        return entriesDefsList;

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
        private static final String TABLE_NAME = "entries_defs";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_ENTRYBUNDLEID = "entry_bundle_id";
        private static final String KEY_ENTRYID = "entry_id";
        private static final String KEY_SENSEBUNDLEID = "sense_bundle_id";
        private static final String KEY_SENSEID = "sense_id";
        private static final String KEY_ENTRYDEFID = "entry_def_id";
        private static final String KEY_SENSEORDER = "sense_order";
        private static final String KEY_TARGETLANG = "target_lang";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String KEY_DEF = "def";
        private static final String KEY_CLASSID = "class_id";
        private static final String KEY_CLASSNAME = "class_name";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_ENTRYBUNDLEID+" integer, "+ KEY_ENTRYID+" integer, "+ KEY_SENSEBUNDLEID+" integer, " + KEY_SENSEID + " integer, "+ KEY_ENTRYDEFID+" integer primary key autoincrement, " + KEY_SENSEORDER + " integer, " + KEY_TARGETLANG + " integer, " + KEY_LANGCODE + " text, " + KEY_DEF + " text, " + KEY_CLASSID + " integer, " + KEY_CLASSNAME + " text)";
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
