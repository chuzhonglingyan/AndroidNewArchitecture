package com.yuntian.androidnewarchitecture.db;


import android.content.Context;
import com.blankj.utilcode.util.LogUtils;
import com.yuntian.androidnewarchitecture.db.dao.EventLogDao;
import com.yuntian.androidnewarchitecture.db.dao.UserDao;
import com.yuntian.androidnewarchitecture.db.entity.EventLog;
import com.yuntian.androidnewarchitecture.db.entity.User;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import static com.yuntian.androidnewarchitecture.db.AppDatabase.currentV;

/**
 * http://localhost:8077/#
 */
@Database(entities = {User.class,EventLog.class}, version = currentV)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "ach-app.db";
    public static  final  int lastV=2;
    public static  final int currentV=3;

    private static AppDatabase sDatabase;

    public abstract UserDao userDao();


    public abstract EventLogDao eventLogDao();




    private static Migration migrationslast_current = new Migration(lastV, currentV) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("ALTER TABLE user" + " ADD COLUMN sex INTEGER NOT NULL DEFAULT 0");
//            database.execSQL("CREATE TABLE IF NOT EXISTS `EventLog` (`event_name` TEXT, `event_type` INTEGER NOT NULL, `event_ower` TEXT, `event_code` INTEGER NOT NULL, `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `create_time` INTEGER, `update_time` INTEGER)");
            LogUtils.d( "upgrade "+lastV+"to"+currentV);
        }
    };

    public static AppDatabase getInstance(Context context) {
        if (sDatabase == null) {
            synchronized (AppDatabase.class) {
                if (sDatabase == null) { //允许主线程访问数据库，默认是不允许
                    sDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DB_NAME).addMigrations(migrationslast_current).build();
                }
            }
        }
        return sDatabase;
    }

}
