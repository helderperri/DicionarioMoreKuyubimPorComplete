package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetSemanticDomainNamesTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseSemanticDomainNamesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetSemanticDomainNamesTableValues> semanticDomainNamesList = new ArrayList<>();
    Context context;
    long semanticDomainNameId;
    long semanticDomainId;
    String semanticDomainName;
    String langCodeSearch;


    public DatabaseSemanticDomainNamesAdapter(Context context, long semanticDomainId, long semanticDomainNameId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.semanticDomainNameId = semanticDomainNameId;
        this.semanticDomainId = semanticDomainId;
    }



    public String getSemanticDomainName() {
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT sd_name FROM sd_names WHERE sd_id = " + semanticDomainId + " AND lang_code = '" + langCodeSearch + "'", null);
        try{
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINNAME);
            semanticDomainName = cursor.getString(index);

            /*

            while(cursor2.moveToNext()) {
                int index2 = cursor.getColumnIndex("entry_bundle_id");
                parentEntryBundleId = cursor.getInt(index2);


            }

            */

        }

        return semanticDomainName;


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


    public List<GetSemanticDomainNamesTableValues> getSemanticDomainNames(){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE sd_id = " + semanticDomainId + " AND lang_code = '" + langCodeSearch + "'", null);
        //Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINNAME);
            String semanticDomainName = cursor.getString(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINID);
            int semanticDomainId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINNAMEID);
            int semanticDomainNameId = cursor.getInt(index3);

            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index5);
            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetSemanticDomainNamesTableValues getSemanticDomainNamesTableValues = new GetSemanticDomainNamesTableValues(semanticDomainId, semanticDomainNameId, langCode, semanticDomainName);
            semanticDomainNamesList.add(getSemanticDomainNamesTableValues);



        }
        return semanticDomainNamesList;


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
        private static final String TABLE_NAME = "sd_names";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SEMANTICDOMAINID = "sd_id";
        private static final String KEY_SEMANTICDOMAINNAMEID = "sd_name_id";
         private static final String KEY_SEMANTICDOMAINNAME = "sd_name";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_SEMANTICDOMAINID+" integer, " + KEY_SEMANTICDOMAINNAMEID + " integer primary key autoincrement, "+ KEY_LANGCODE +", text"+ KEY_SEMANTICDOMAINNAME +", text)";
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
                //Toast.makeText(context, "onCreate 2 called", Toast.LENGTH_SHORT).show();
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
