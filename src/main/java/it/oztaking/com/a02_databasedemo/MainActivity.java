package it.oztaking.com.a02_databasedemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.创建数据库
        //public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
        SQLiteDatabase db = myOpenHelper.getWritableDatabase();

        //2.将数据库中的数据读出来
        Cursor cursor = db.query("info", null, null, null, null, null, null);
        if (cursor != null && cursor.getCount()>0){
            while (cursor.moveToNext()){
                String name = cursor.getString(1);
                String money = cursor.getString(2);
                System.out.println("name:"+name+"-------"+money);
            }
        }

        cursor.close();

    }
}
