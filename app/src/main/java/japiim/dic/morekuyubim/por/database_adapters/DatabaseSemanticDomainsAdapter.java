package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetSemanticDomainsTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseSemanticDomainsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    DatabaseSemanticDomainNamesAdapter databaseSemanticDomainNamesAdapter;
    List<GetSemanticDomainsTableValues> semanticDomainsList = new ArrayList<>();
    Context context;
    long senseBundleId;
    long semanticDomainTokenId;
    long semanticDomainId;
    String semanticDomainName;

    public DatabaseSemanticDomainsAdapter(Context context, long senseBundleIid, long semanticDomainTokenId, long semanticDomainId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.senseBundleId = senseBundleIid;
        this.semanticDomainId = semanticDomainId;
        this.semanticDomainTokenId = semanticDomainTokenId;
    }



    public String getSemanticDomainName(long semanticDomainId){

        databaseSemanticDomainNamesAdapter = new DatabaseSemanticDomainNamesAdapter(context, semanticDomainId, 0);

        semanticDomainName = databaseSemanticDomainNamesAdapter.getSemanticDomainName();


        return semanticDomainName;

    }

    public long getSemanticDomainId(long senseBundleId ){

        semanticDomainId = 0;
        Cursor cursor = db.rawQuery("SELECT sd_id FROM sds WHERE sense_bundle_id = " + senseBundleId + "", null);
        try{
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINID);
            semanticDomainId = cursor.getInt(index);

            /*

            while(cursor2.moveToNext()) {
                int index2 = cursor.getColumnIndex("entry_bundle_id");
                parentEntryBundleId = cursor.getInt(index2);


            }

            */

        }



        return semanticDomainId;


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

    public List<GetSemanticDomainsTableValues> getSemanticDomains(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseSemanticDomainsAdapter.DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + "", null);
        //Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;

        try{

        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINTOKENID);
            int semanticDomainTokenId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_SEMANTICDOMAINID);
            int semanticDomainId2 = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index4);
            String semanticDomainName = getSemanticDomainName(semanticDomainId2);
            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetSemanticDomainsTableValues getSemanticDomainsTableValues = new GetSemanticDomainsTableValues(senseBundleId, entryRef, semanticDomainTokenId, semanticDomainId2, semanticDomainName);
            semanticDomainsList.add(getSemanticDomainsTableValues);



        }
        return semanticDomainsList;


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
        private static final String TABLE_NAME = "sds";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SENSEBUNDLEID = "sense_bundle_id";
        private static final String KEY_SEMANTICDOMAINTOKENID = "sd_token_id";
        private static final String KEY_SEMANTICDOMAINID = "sd_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_SENSEBUNDLEID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_SEMANTICDOMAINTOKENID + " integer primary key autoincrement, "+ KEY_SEMANTICDOMAINID +", integer)";
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
