package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetSenseBundlesTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseSenseBundlesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    DatabaseEntriesAdapter databaseEntriesAdapter;
    List<GetSenseBundlesTableValues> senseBundlesList = new ArrayList<>();
    Context context;
    long senseBundleId;
    long parentEntryBundleId;
    long parentEntryId;
    long entryId;

    public DatabaseSenseBundlesAdapter(Context context, long entryId, long senseBundleIid) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.senseBundleId = senseBundleIid;
        this.entryId = entryId;
    }

    public long getSenseBundleId(long entryId) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE entry_id = " + entryId + "", null);

        try{
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            senseBundleId = cursor.getInt(index);


        }


        return senseBundleId;

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



    public long getParentEntryId(long senseBundleId) {
        Cursor cursor = db.rawQuery("SELECT entry_id FROM " + DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + "", null);

        try{
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            parentEntryId = cursor.getInt(index);


        }


        return parentEntryId;

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



    public long getParentEntryBundleIdFromSenseBundle(long senseBundleId) {
        Cursor cursor = db.rawQuery("SELECT entry_id FROM " + DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + "", null);
        try{
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            parentEntryId = cursor.getInt(index);

            databaseEntriesAdapter = new DatabaseEntriesAdapter(context, parentEntryId, 0);

            parentEntryBundleId = databaseEntriesAdapter.getParentEntryBundleId(parentEntryId);

            /*
            Cursor cursor2 = db.rawQuery("SELECT entry_bundle_id FROM entries WHERE entry_id = " + parentEntryId + "", null);

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


    public List<GetSenseBundlesTableValues> getSenseBundles(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + "  WHERE entry_id = " + entryId  + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;

        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index1);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYID);
            int entryId = cursor.getInt(index4);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);

            GetSenseBundlesTableValues getSenseBundlesTableValues = new GetSenseBundlesTableValues(entryId, entryRef, senseBundleId);
            senseBundlesList.add(getSenseBundlesTableValues);







        }
        return senseBundlesList;


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
        private static final String TABLE_NAME = "sense_bundles";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SENSEBUNDLEID = "sense_bundle_id";
        private static final String KEY_ENTRYID = "entry_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_ENTRYID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_SENSEBUNDLEID + " integer primary key autoincrement)";
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
