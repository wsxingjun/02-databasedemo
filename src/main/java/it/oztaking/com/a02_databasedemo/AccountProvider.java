package it.oztaking.com.a02_databasedemo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Administrator on 2017-11-16.
 */

//使用ContentProvider暴露访问接口
public class AccountProvider extends ContentProvider {

    //1.定义一个uri路径匹配器
    private static final UriMatcher sURIMatcher= new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUERYSUCESS = 0;
    private static final int UPDATESUCESS = 1;
    private static final int DELETESUCESS = 2;
    private static final int INSERTSUCESS = 3;
    private MyOpenHelper myOpenHelper;
//2.创建一个静态的代码块在其中添加uri
    static {
        sURIMatcher.addURI("wangmazi","query",QUERYSUCESS);
        sURIMatcher.addURI("wangmazi","update",UPDATESUCESS);
        sURIMatcher.addURI("wangmazi","delete",DELETESUCESS);
        sURIMatcher.addURI("wangmazi","insert",INSERTSUCESS);
    }

    //1当内容提供者初始化的时候会调用此方法
    @Override
    public boolean onCreate() {
        //3.初始化myopenHelper 对象，可以获取到sqlitesdatabase对象，进而可以操作数据库
        Log.d("wsxingjun","正在调用第一个应用程序onCreate()");
        myOpenHelper = new MyOpenHelper(getContext());
        return false;
    }

//对外暴露方法
    @Override

    public Cursor query( Uri uri, String[] projection, String selection,String[] selectionArgs,  String sortOrder) {
        Log.d("wsxingjun","正在调用第一个应用程序query");
        int code = sURIMatcher.match(uri);
        if (code == QUERYSUCESS){
            //获得db 调用query方法
            SQLiteDatabase db = myOpenHelper.getReadableDatabase();
            Cursor cursor = db.query("info", projection, selection, selectionArgs, null, null, sortOrder);
            //Log.d("wsxingjun","正在调用第一个应用程序query");
            return cursor;

        }else {
            throw new IllegalArgumentException("uri路径不匹配 请检测路径");
        }

    }


    @Override
    public String getType( Uri uri) {

        return null;
    }


    @Override
    public Uri insert( Uri uri,  ContentValues values) {
        int code = sURIMatcher.match(uri);
        if (code == INSERTSUCESS){
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            long insert = db.insert("info", null, values);
            Uri uri1 = Uri.parse("com.wsxingjun/" + insert);

            db.close();
            return uri1;
        }else {
            throw new IllegalArgumentException("uri路径不匹配 请检测路径");
        }

    }

    @Override
    public int delete(Uri uri,  String selection,  String[] selectionArgs) {
        int code = sURIMatcher.match(uri);
        if (code == DELETESUCESS){
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            int delete = db.delete("info", selection, selectionArgs);
            if (delete > 0){
//                Toast.makeText(getContext(),"删除一行",Toast.LENGTH_SHORT).show();
                return delete;
            }
        }

        return 0;
    }

    @Override
    public int update( Uri uri,  ContentValues values,  String selection,  String[] selectionArgs) {
        int code = sURIMatcher.match(uri);
        if (code == UPDATESUCESS){
            SQLiteDatabase db = myOpenHelper.getWritableDatabase();
            int update = db.update("info", values, selection, selectionArgs);

            return update;
        }else {
            throw new IllegalArgumentException("update 路径不正确");
        }

    }


}

