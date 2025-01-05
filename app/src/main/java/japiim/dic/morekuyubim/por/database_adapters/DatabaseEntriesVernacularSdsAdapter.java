    package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetEntriesVernacularSdsTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.SplashScreen;


    public class DatabaseEntriesVernacularSdsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetEntriesVernacularSdsTableValues> entriesVernacularList = new ArrayList<>();
    Context context;
    long formBundleId;
    long entryBundleId;
    long formId;
    String sdLangCode;

    public DatabaseEntriesVernacularSdsAdapter(Context context, long entryBundleId, long formBundleIid, long formId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.formBundleId = formBundleIid;
        this.entryBundleId = entryBundleId;
        this.formId = formId;
    }


    public List<GetEntriesVernacularSdsTableValues> getEntriesVernacularSds(){
        sdLangCode = context.getResources().getString(R.string.target_lang_code);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE entry_bundle_id = " + entryBundleId + " AND sd_lang_code = '" + sdLangCode + "'", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMBUNDLEID);
            int formBundleId = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMID);
            int formId = cursor.getInt(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYVERNACULARID);
            int entryVernacularId = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMORDER);
            int formOrder = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_VERNACULAR);
            String vernacular = cursor.getString(index9);
            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_SDID);
            int sdId = cursor.getInt(index10);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_SDNAME);
            String sdName = cursor.getString(index11);
            int index12 = cursor.getColumnIndex(DatabaseHelper.KEY_SDTARGETLANG);
            int sdTargetLang = cursor.getInt(index12);
            int index13 = cursor.getColumnIndex(DatabaseHelper.KEY_SDLANGCODE);
            String sdLangCode = cursor.getString(index13);
            int index14 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEUNDLEID);
            int senseBundleId = cursor.getInt(index14);

            GetEntriesVernacularSdsTableValues getEntriesVernacularSdsTableValues = new GetEntriesVernacularSdsTableValues(entryBundleId, entryId, senseBundleId, formBundleId, formId, formOrder, sourceLang, langCode, vernacular, sdId, sdName, sdTargetLang, sdLangCode);
            entriesVernacularList.add(getEntriesVernacularSdsTableValues);


        }
        return entriesVernacularList;

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



    public List<GetEntriesVernacularSdsTableValues> getVernacularsToSearchDisplay(){
        sdLangCode = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + "  WHERE sd_lang_code = '" + sdLangCode + "' ORDER BY " + DatabaseHelper.KEY_VERNACULAR + " COLLATE UNICODE", null);
        //StringBuffer buffer = new StringBuffer();
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMBUNDLEID);
            int formBundleId = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMID);
            int formId = cursor.getInt(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYVERNACULARID);
            int entryVernacularId = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMORDER);
            int formOrder = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_VERNACULAR);
            String vernacular = cursor.getString(index9);
            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_SDID);
            int sdId = cursor.getInt(index10);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_SDNAME);
            String sdName = cursor.getString(index11);
            int index12 = cursor.getColumnIndex(DatabaseHelper.KEY_SDTARGETLANG);
            int sdTargetLang = cursor.getInt(index12);
            int index13 = cursor.getColumnIndex(DatabaseHelper.KEY_SDLANGCODE);
            String sdLangCode = cursor.getString(index13);
            int index14 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEUNDLEID);
            int senseBundleId = cursor.getInt(index14);

            GetEntriesVernacularSdsTableValues getEntriesVernacularSdsTableValues = new GetEntriesVernacularSdsTableValues(entryBundleId, entryId, senseBundleId, formBundleId, formId, formOrder, sourceLang, langCode, vernacular, sdId, sdName, sdTargetLang, sdLangCode);
            entriesVernacularList.add(getEntriesVernacularSdsTableValues);

        }
        return entriesVernacularList;

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






    public List<GetEntriesVernacularSdsTableValues> getVernacularsToSearchDisplayBySdId(long sdId){
        sdLangCode = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE sd_id = " + sdId + "  AND sd_lang_code = '" + sdLangCode + "' ORDER BY " + DatabaseHelper.KEY_VERNACULAR + " COLLATE UNICODE", null);
        //StringBuffer buffer = new StringBuffer();
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMBUNDLEID);
            int formBundleId = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMID);
            int formId = cursor.getInt(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYVERNACULARID);
            int entryVernacularId = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMORDER);
            int formOrder = cursor.getInt(index6);
            int index7 = cursor.getColumnIndex(DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index7);
            int index8 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index8);
            int index9 = cursor.getColumnIndex(DatabaseHelper.KEY_VERNACULAR);
            String vernacular = cursor.getString(index9);
            int index10 = cursor.getColumnIndex(DatabaseHelper.KEY_SDID);
            int sdId2 = cursor.getInt(index10);
            int index11 = cursor.getColumnIndex(DatabaseHelper.KEY_SDNAME);
            String sdName = cursor.getString(index11);
            int index12 = cursor.getColumnIndex(DatabaseHelper.KEY_SDTARGETLANG);
            int sdTargetLang = cursor.getInt(index12);
            int index13 = cursor.getColumnIndex(DatabaseHelper.KEY_SDLANGCODE);
            String sdLangCode = cursor.getString(index13);
            int index14 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEUNDLEID);
            int senseBundleId = cursor.getInt(index14);

            GetEntriesVernacularSdsTableValues getEntriesVernacularSdsTableValues = new GetEntriesVernacularSdsTableValues(entryBundleId, entryId, senseBundleId, formBundleId, formId, formOrder, sourceLang, langCode, vernacular, sdId2, sdName, sdTargetLang, sdLangCode);
            entriesVernacularList.add(getEntriesVernacularSdsTableValues);

        }
        return entriesVernacularList;

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
        private static final String TABLE_NAME = "entries_vernacular_sds";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_ENTRYBUNDLEID = "entry_bundle_id";
        private static final String KEY_ENTRYID = "entry_id";
        private static final String KEY_SENSEUNDLEID = "sense_bundle_id";
        private static final String KEY_FORMBUNDLEID = "form_bundle_id";
        private static final String KEY_FORMID = "form_id";
        private static final String KEY_ENTRYVERNACULARID = "entry_vernacular_id";
        private static final String KEY_FORMORDER = "form_order";
        private static final String KEY_SOURCELANG = "source_lang";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String KEY_VERNACULAR = "vernacular";
        private static final String KEY_SDID = "sd_id";
        private static final String KEY_SDNAME = "sd_name";
        private static final String KEY_SDTARGETLANG = "sd_target_lang";
        private static final String KEY_SDLANGCODE = "sd_lang_code";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_ENTRYBUNDLEID+" integer, "+ KEY_ENTRYID+" integer, "+ KEY_SENSEUNDLEID+" integer, " + KEY_FORMBUNDLEID+" integer, " + KEY_FORMID + " integer, "+ KEY_ENTRYVERNACULARID+" integer primary key autoincrement, " + KEY_FORMORDER + " integer, " + KEY_SOURCELANG + " integer, " + KEY_LANGCODE + " text, " + KEY_VERNACULAR + " text, "+ KEY_SDID+" integer, " + KEY_SDNAME + " text, " + KEY_SDTARGETLANG + " integer, " + KEY_SDLANGCODE + " text)";
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
