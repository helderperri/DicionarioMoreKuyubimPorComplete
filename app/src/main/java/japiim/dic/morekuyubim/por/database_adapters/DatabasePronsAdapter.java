package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetPronsTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabasePronsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetPronsTableValues> pronsList = new ArrayList<>();
    Context context;
    long pronId;
    long phoneticId2;

    public DatabasePronsAdapter(Context context, long phoneticId, long pronIid) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        pronId = pronIid;
        phoneticId2 = phoneticId;
    }


    public List<GetPronsTableValues> getProns(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE phonetic_id = " + phoneticId2 + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_PRONID);
            int pronId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_PHONETICID);
            int phoneticId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_PRON);
            String pron = cursor.getString(index4);

            GetPronsTableValues getPronsTableValues = new GetPronsTableValues(phoneticId, entryRef, pronId, pron);
            pronsList.add(getPronsTableValues);







        }
        return pronsList;


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
        private static final String TABLE_NAME = "prons";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_PRONID = "pron_id";
        private static final String KEY_PHONETICID = "phonetic_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String KEY_PRON = "wav";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_PHONETICID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_PRONID + " integer primary key autoincrement, " + KEY_PRON + " text)";
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
