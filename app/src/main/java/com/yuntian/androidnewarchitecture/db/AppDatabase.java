package com.yuntian.androidnewarchitecture.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.db.dao.UserDao;
import com.yuntian.androidnewarchitecture.db.entity.User;


@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sDatabase;

    public abstract UserDao userDao();


    private static final String DB_NAME = "ach-app.db";

    private static Migration migrations1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user" + " ADD COLUMN sex INTEGER NOT NULL DEFAULT 0");
            LogUtils.d( "upgrade 1 to 2");
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (sDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sDatabase == null) { //允许主线程访问数据库，默认是不允许
                    sDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME).addMigrations(migrations1_2).build();
                }
            }
        }
        return sDatabase;
    }

}
