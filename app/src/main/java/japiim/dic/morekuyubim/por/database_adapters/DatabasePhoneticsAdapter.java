package japiim.dic.morekuyubim.por.database_adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import japiim.dic.morekuyubim.por.get_table_values.GetPhoneticsTableValues;
import japiim.dic.morekuyubim.por.SplashScreen;


public class DatabasePhoneticsAdapter {
    DatabaseHelper helper;
    SQLiteDatabase db;
    List<GetPhoneticsTableValues> phoneticsList = new ArrayList<>();
    Context context;
    long phoneticId;
    long phonemicId2;

    public DatabasePhoneticsAdapter(Context context, long phonemicId, long phoneticIid) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        this.context = context;
        phoneticId = phoneticIid;
        phonemicId2 = phonemicId;
    }


    public List<GetPhoneticsTableValues> getPhonetics(){
        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME + " WHERE phonemic_id = " + phonemicId2 + "", null);
        StringBuffer buffer = new StringBuffer();
        //int count = 0;
        try{
        while(cursor.moveToNext()){

            int index1 = cursor.getColumnIndex(DatabaseHelper.KEY_PHONETICID);
            int phoneticId = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(DatabaseHelper.KEY_PHONEMICID);
            int formId = cursor.getInt(index2);
            int index3 = cursor.getColumnIndex(DatabaseHelper.KEY_ENTRYREF);
            String entryRef = cursor.getString(index3);
            int index4 = cursor.getColumnIndex(DatabaseHelper.KEY_PHONETIC);
            String phonetic = cursor.getString(index4);
            int index5 = cursor.getColumnIndex(DatabasePhoneticsAdapter.DatabaseHelper.KEY_SOURCELANG);
            int sourceLang = cursor.getInt(index5);
            int index6 = cursor.getColumnIndex(DatabasePhoneticsAdapter.DatabaseHelper.KEY_LANGCODE);
            String langCode = cursor.getString(index6);


            GetPhoneticsTableValues getPhoneticsTableValues = new GetPhoneticsTableValues(formId, entryRef, phoneticId, phonetic, sourceLang, langCode);
            phoneticsList.add(getPhoneticsTableValues);







        }
        return phoneticsList;


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
        private static final String TABLE_NAME = "phonetic";
        //When you do change the structure of the database change the version number from 1 to 2
        private static final int DATABASE_VERSION = SplashScreen.getSomeVariable();
        private static final String KEY_PHONETICID = "phonetic_id";
        private static final String KEY_PHONEMICID = "phonemic_id";
        private static final String KEY_SOURCELANG = "source_lang";
        private static final String KEY_LANGCODE = "lang_code";
        private static final String KEY_ENTRYREF = "entry_ref";
        private static final String KEY_PHONETIC = "phonetic";
        private static final String CREATE_TABLE = "create table "+ TABLE_NAME+
                " ("+ KEY_PHONEMICID+" integer, " + KEY_ENTRYREF+ "  text, " + KEY_LANGCODE+ " text, " + KEY_SOURCELANG+ " integer, "+ KEY_PHONETICID + " integer primary key autoincrement, " + KEY_PHONETIC + " text)";
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
