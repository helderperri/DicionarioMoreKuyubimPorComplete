package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetPhonemicsTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabasePhonemicsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetPhonemicsTableValues> phonemicsList = new ArrayList<>();
    Context context;
    long phonemicId;
    long formId;

    public DatabasePhonemicsAdapter(Context context, long formIid, long phonemicIid) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        phonemicId = phonemicIid;
        formId = formIid;
    }


    public List<GetPhonemicsTableValues> getPhonemics(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE form_id = " + formId + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_PHONEMICID);
            int phonemicId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_FORMID);
            int formId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_PHONEMIC);
            String phonemic = cursor.getString(index4);
            int index5 = cursor.getColumnIndex(DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index6);

            GetPhonemicsTableValues getPhonemicsTableValues = new GetPhonemicsTableValues(formId, entryRef, phonemicId, phonemic, sourceLang, langCode);
            phonemicsList.add(getPhonemicsTableValues);







        }
        return phonemicsList;


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
        private static final String TABLE_NAME = "phonemic";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_PHONEMICID = "phonemic_id";
        private static final String KEY_FORMID = "form_id";
        private static final String KEY_SOURCELANG = "source_lang";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String KEY_PHONEMIC = "phonemic";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_FORMID+" integer, " + KEY_ENTRYREF+ " text, " + KEY_LANGCODE+ " text, " + KEY_SOURCELANG+ " integer, "+ KEY_PHONEMICID + " integer primary key autoincrement, " + KEY_PHONEMIC + " text)";
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
