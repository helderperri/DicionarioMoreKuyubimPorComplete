package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetClassesTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseClassesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    DatabaseClassNamesAdapter databaseClassNamesAdapter;
    List<GetClassesTableValues> classesList = new ArrayList<>();
    Context context;
    long senseBundleId;
    long classTokenId;
    long classId;
    String className;

    public DatabaseClassesAdapter(Context context, long senseBundleIid, long classTokenId, long classId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.senseBundleId = senseBundleIid;
        this.classId = classId;
        this.classTokenId = classTokenId;
    }



    public String getClassName(long senseBundleId){

        classId = getClassId(senseBundleId);

        databaseClassNamesAdapter = new DatabaseClassNamesAdapter(context, classId, 0);

        className = databaseClassNamesAdapter.getClassName(classId);


        return className;

    }

    public long getClassId(long senseBundleId ){


        Cursor cursor = db.rawQuery("SELECT class_id FROM classes WHERE sense_bundle_id = " + senseBundleId + "", null);
        try{
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSID);
            classId = cursor.getInt(index);

            /*

            while(cursor2.moveToNext()) {
                int index2 = cursor.getColumnIndex("entry_bundle_id");
                parentEntryBundleId = cursor.getInt(index2);


            }

            */

        }



        return classId;

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
    public List<GetClassesTableValues> getClasses(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseClassesAdapter.DatabaseHelper.TABLE_NAME + " WHERE sense_bundle_id = " + senseBundleId + "", null);
        //Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;

        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_SENSEBUNDLEID);
            int senseBundleId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSTOKENID);
            int classTokenId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSID);
            int classId = cursor.getInt(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index4);
            //int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_WIKI);
            //String wiki = cursor.getString(index5);
            GetClassesTableValues getClassesTableValues = new GetClassesTableValues(senseBundleId, entryRef, classTokenId, classId);
            classesList.add(getClassesTableValues);



        }
        return classesList;

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
        private static final String TABLE_NAME = "classes";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_SENSEBUNDLEID = "sense_bundle_id";
        private static final String KEY_CLASSTOKENID = "class_token_id";
        private static final String KEY_CLASSID = "class_id";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_SENSEBUNDLEID+" integer, " + KEY_ENTRYREF+ " text, "+ KEY_CLASSTOKENID + " integer primary key autoincrement, "+ KEY_CLASSID +", integer)";
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
