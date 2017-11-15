package it.oztaking.com.a02_databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2017-11-15.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "Account.db", null, 1);
    }

    //在次方中创建数据库
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement,name varchar(20),money varchar(20))");
        db.execSQL("insert into info(name,money) values(?,?)",new String[]{"张三","5000"});
        db.execSQL("insert into info(name,money) values(?,?)",new String[]{"李四","8000"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
