package com.nrlm.cbo.database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.nrlm.cbo.database.room.dao.AddShgLoanDAO;
import com.nrlm.cbo.database.room.dao.BlockCodeDao;
import com.nrlm.cbo.database.room.dao.GpDao;
import com.nrlm.cbo.database.room.dao.MasterSavingDao;
import com.nrlm.cbo.database.room.dao.ShgDao;
import com.nrlm.cbo.database.room.dao.ShgMemberDao;
import com.nrlm.cbo.database.room.dao.ShgPaymentTypeDao;
import com.nrlm.cbo.database.room.dao.ShgSavingDao;
import com.nrlm.cbo.database.room.dao.VillageDao;
import com.nrlm.cbo.database.room.entity.AddShgLoan;
import com.nrlm.cbo.database.room.entity.BlockAssign;
import com.nrlm.cbo.database.room.entity.GpAssign;
import com.nrlm.cbo.database.room.entity.MasterSavingEntity;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.database.room.entity.ShgPaymentTypeEntity;
import com.nrlm.cbo.database.room.entity.ShgSeetingSavings;
import com.nrlm.cbo.database.room.entity.VillageAssign;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {AddShgLoan.class, BlockAssign.class, GpAssign.class,
        VillageAssign.class, Shg.class, ShgMember.class, ShgSeetingSavings.class, ShgPaymentTypeEntity.class
        , MasterSavingEntity.class}
        , version = 1, exportSchema = false)

public abstract class AppDataBase extends RoomDatabase {
    public abstract AddShgLoanDAO addShgLoanDao();

    public abstract BlockCodeDao bloackCodeDao();

    public abstract GpDao gpDao();

    public abstract VillageDao villageDao();

    public abstract ShgDao shgDAO();

    public abstract ShgMemberDao memberDAO();

    public abstract ShgSavingDao shgSavingDao();

    public abstract ShgPaymentTypeDao shgPaymentTypeDao();

    public abstract MasterSavingDao savingDao();









    public static volatile AppDataBase DBINSTANCE;
    private static final int NUMBER_OF_THREADS = 5;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDataBase getDatabase(final Context context) {
        if (DBINSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (DBINSTANCE == null) {
                    DBINSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, "shg_transactions").allowMainThreadQueries().build();
                }
            }
        }
        return DBINSTANCE;
    }
}
