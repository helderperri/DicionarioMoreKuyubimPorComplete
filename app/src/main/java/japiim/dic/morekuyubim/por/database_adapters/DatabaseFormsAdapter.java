package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetFormsTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseFormsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetFormsTableValues> formsList = new ArrayList<>();
    Context context;
    long formBundleId;
    long formId;

    public DatabaseFormsAdapter(Context context, long formBundleIid, long formIid) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        formBundleId = formBundleIid;
        formId = formId;
    }


    public List<GetFormsTableValues> getForms(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE form_bundle_id = " + formBundleId + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;

        try{

        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMBUNDLEID);
            int formBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMID);
            int formId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_VERNACULAR);
            String vernacular = cursor.getString(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index6);


            GetFormsTableValues getFormsTableValues = new GetFormsTableValues(formBundleId, entryRef, formId, vernacular, langCode, sourceLang);
            formsList.add(getFormsTableValues);







        }
        return formsList;


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



    public List<GetFormsTableValues> getVernacularsToSearchDisplay(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " ORDER BY " + DatabaseHelper.KEY_VERNACULAR + " COLLATE UNICODE", null);
        //StringBuffer buffer = new StringBuffer();
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMBUNDLEID);
            int formBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMID);
            int formId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_VERNACULAR);
            String vernacular = cursor.getString(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index6);

            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetFormsTableValues getFormsTableValues = new GetFormsTableValues(formBundleId, entryRef, formId, vernacular, langCode, sourceLang);
            formsList.add(getFormsTableValues);

        }
        return formsList;

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
        private static final String TABLE_NAME = "forms";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_FORMBUNDLEID = "form_bundle_id";
        private static final String KEY_FORMID = "form_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String KEY_VERNACULAR = "vernacular";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String KEY_SOURCELANG = "source_lang";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_FORMBUNDLEID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_FORMID + " integer primary key autoincrement, "+ KEY_LANGCODE+" text,  "+ KEY_SOURCELANG+" integer)";
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
