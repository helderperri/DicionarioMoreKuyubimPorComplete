package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import android.database.SQLException;
import android.util.Log;

import japiim.dic.morekuyubim.por.get_table_values.GetEntriesTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseEntriesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetEntriesTableValues> entriesList = new ArrayList<>();
    Context context;
    long entryId;
    long parentEntryBundleId;
    long entryBundleId;

    public DatabaseEntriesAdapter(Context context, long entryId, long entryBundleId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.entryId = entryId;
        this.entryBundleId = entryBundleId;
    }

    public long getParentEntryBundleId(long entryId) {
        Cursor cursor = db.rawQuery("SELECT entry_bundle_id FROM entries WHERE entry_id = " + entryId + "", null);

        try{
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            parentEntryBundleId = cursor.getInt(index);

            /*

            while(cursor2.moveToNext()) {
                int index2 = cursor.getColumnIndex("entry_bundle_id");
                parentEntryBundleId = cursor.getInt(index2);


            }

            */

        }

        return parentEntryBundleId;

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


    public List<GetEntriesTableValues> getAllEntries (long entryBundleId) {

        Cursor cursor = db.rawQuery("SELECT * FROM entries WHERE entry_bundle_id = " + entryBundleId + "", null);

        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entry_bundle_id = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entry_id = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entry_ref = cursor.getString(index3);
            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetEntriesTableValues getEntriesTableValues = new GetEntriesTableValues(entry_bundle_id, entry_ref, entry_id);
            entriesList.add(getEntriesTableValues);

        }
        return entriesList;






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

        public List<GetEntriesTableValues> getEntries(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE entry_id = " + entryId + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYBUNDLEID);
            int entryBundleId = cursor.getInt(index1);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index4);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);

            GetEntriesTableValues getEntriesTableValues = new GetEntriesTableValues(entryBundleId, entryRef, entryId);
            entriesList.add(getEntriesTableValues);



        }
        return entriesList;


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
        private static final String TABLE_NAME = "entries";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_ENTRYBUNDLEID = "entry_bundle_id";
        private static final String KEY_ENTRYID = "entry_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_ENTRYBUNDLEID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_ENTRYID + " integer primary key autoincrement)";
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
