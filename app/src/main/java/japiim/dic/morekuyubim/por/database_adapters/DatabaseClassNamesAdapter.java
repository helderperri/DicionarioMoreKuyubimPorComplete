package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetClassNamesTableValues;
import japiim.dic.morekuyubim.por.R;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabaseClassNamesAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetClassNamesTableValues> classNamesList = new ArrayList<>();
    Context context;
    long classNameId;
    long classId;
    String className;
    String langCodeSearch;


    public DatabaseClassNamesAdapter(Context context, long classId, long classNameId) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        this.classNameId = classNameId;
        this.classId = classId;
    }



    public String getClassName(long classId) {
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);

        Cursor cursor = db.rawQuery("SELECT class_name FROM class_names WHERE class_id = " + classId + " AND lang_code =  '" + langCodeSearch + "'", null);
        try{
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSNAME);
            className = cursor.getString(index);

            /*

            while(cursor2.moveToNext()) {
                int index2 = cursor.getColumnIndex("entry_bundle_id");
                parentEntryBundleId = cursor.getInt(index2);


            }

            */

        }

        return className;


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


    public List<GetClassNamesTableValues> getClassNames(){
        langCodeSearch = context.getResources().getString(R.string.target_lang_code);

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE class_id = " + classId + "  lang_code =  '" + langCodeSearch + "'", null);
        //Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSNAME);
            String className = cursor.getString(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSID);
            int classId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_CLASSNAMEID);
            int classNameId = cursor.getInt(index3);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index5);


            GetClassNamesTableValues getClassNamesTableValues = new GetClassNamesTableValues(classId, classNameId, langCode, className);
            classNamesList.add(getClassNamesTableValues);



        }
        return classNamesList;


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
        private static final String TABLE_NAME = "class_names";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_CLASSID = "class_id";
        private static final String KEY_CLASSNAMEID = "class_name_id";
         private static final String KEY_CLASSNAME = "class_name";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_CLASSID+" integer, " + KEY_CLASSNAMEID + " integer primary key autoincrement, "+ KEY_CLASSNAME +", text"+ KEY_LANGCODE +", text)";
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
