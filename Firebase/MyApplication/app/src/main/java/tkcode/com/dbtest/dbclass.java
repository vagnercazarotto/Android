package tkcode.com.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vagner on 22/02/16.
 */
public class dbclass {
    private SQLiteDatabase db;

    public dbclass(Context context) {
        bdcore auxdb = new bdcore(context);
        db = auxdb.getWritableDatabase();
    }


    public void insert(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","vag");
        contentValues.put("email","testemail");
        contentValues.put("senha","12345");
        db.insert("usuario",null,contentValues);
    }


    public void insert(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name","vag");
        contentValues.put("email","testemail");
        db.update("usuario",contentValues, "_id = ?"
    }





}
