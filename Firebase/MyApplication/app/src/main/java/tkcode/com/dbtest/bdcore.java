package tkcode.com.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by vagner on 21/02/16.
 */
public class bdcore extends SQLiteOpenHelper{
    private static final String Namebd ="";
    private static final int versao=1;


    public bdcore(Context context) {
        super(context,Namebd, null,versao);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(_id integer primary key autoincrement, name text not null , email text not null, senha text not null);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table usuario;");
        onCreate(db);
    }
}
