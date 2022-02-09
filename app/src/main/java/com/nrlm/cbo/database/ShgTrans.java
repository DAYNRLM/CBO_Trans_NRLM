package com.nrlm.cbo.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


import com.nrlm.cbo.database.room.daos.BankBranchDao;
import com.nrlm.cbo.database.room.daos.BankDao;
import com.nrlm.cbo.database.room.daos.BankTypeDao;
import com.nrlm.cbo.database.room.daos.BlockDao;
import com.nrlm.cbo.database.room.daos.GpsDao;
import com.nrlm.cbo.database.room.daos.LoanTypeDao;
import com.nrlm.cbo.database.room.daos.LoginDao;
import com.nrlm.cbo.database.room.daos.MasterCompanyBranchNamedao;
import com.nrlm.cbo.database.room.daos.MasterCompanyNameDao;
import com.nrlm.cbo.database.room.daos.MasterCuttOffLoanSourceDao;
import com.nrlm.cbo.database.room.daos.MasterCuttOffMemberPurposeDao;
import com.nrlm.cbo.database.room.daos.MasterBankCompanyDao;
import com.nrlm.cbo.database.room.daos.MasterFixedDepositDao;
import com.nrlm.cbo.database.room.daos.MasterIsRfReturendDao;
import com.nrlm.cbo.database.room.daos.MasterMemberSavingDao;
import com.nrlm.cbo.database.room.daos.MasterNomineeRelationDao;
import com.nrlm.cbo.database.room.daos.MasterPaymentAgencyTypeDao;
import com.nrlm.cbo.database.room.daos.MasterPaymentDao;
import com.nrlm.cbo.database.room.daos.MasterPaymentSubTypeDao;
import com.nrlm.cbo.database.room.daos.MasterSeetingShgActivityDao;
import com.nrlm.cbo.database.room.daos.MasterSeetingShgSubActivityDao;
import com.nrlm.cbo.database.room.daos.MasterShgReceiptDao;
import com.nrlm.cbo.database.room.daos.MasterShgReceiptLoanDao;
import com.nrlm.cbo.database.room.daos.MasterShgReceiptSubTypeLoanSourceDao;
import com.nrlm.cbo.database.room.daos.MeetingFrequencyDao;
import com.nrlm.cbo.database.room.daos.MemberCutOffDao;
import com.nrlm.cbo.database.room.daos.MemberCutOffRunningLoanDao;
import com.nrlm.cbo.database.room.daos.MemberCutOffSavingDao;
import com.nrlm.cbo.database.room.daos.ShgCutoffDao;
import com.nrlm.cbo.database.room.daos.ShgDataDao;
import com.nrlm.cbo.database.room.daos.ShgLoanDao;
import com.nrlm.cbo.database.room.daos.ShgMemberDataDao;
import com.nrlm.cbo.database.room.daos.ShgRunningInsuranceDao;
import com.nrlm.cbo.database.room.daos.ShgSettingSavingFromMemberDao;
import com.nrlm.cbo.database.room.daos.VillageDao;
import com.nrlm.cbo.database.room.entities.BankBranchEntity;
import com.nrlm.cbo.database.room.entities.BankEntity;
import com.nrlm.cbo.database.room.entities.BankTypeEntity;
import com.nrlm.cbo.database.room.entities.BlockEntity;
import com.nrlm.cbo.database.room.entities.GPsEntity;
import com.nrlm.cbo.database.room.entities.LoginEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyBranchEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyNameEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffLoanTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffLoanSourceEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffMemberPurposeEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffFixedDepositEntity;
import com.nrlm.cbo.database.room.entities.MasterIsRfReturendEntity;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.MasterNomineeRelationEntity;
import com.nrlm.cbo.database.room.entities.MasterPaymentAgencyTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgSubActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptLoanEntity;
import com.nrlm.cbo.database.room.entities.MasterShgReceiptSubTypeLoanSourceEntity;
import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentEntity;
import com.nrlm.cbo.database.room.entities.MasterTransShgPaymentSubTypeEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.MemberCutOffEntity;
import com.nrlm.cbo.database.room.entities.MemberCutOffRunningLoanEntity;
import com.nrlm.cbo.database.room.entities.MemberCutOffSavingEntity;
import com.nrlm.cbo.database.room.entities.ShgCutOffRunningInsuranceEntity;
import com.nrlm.cbo.database.room.entities.ShgCutoffEntity;
import com.nrlm.cbo.database.room.entities.ShgDataEntity;
import com.nrlm.cbo.database.room.entities.ShgLoansEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.entities.VillageEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LoginEntity.class, BlockEntity.class, GPsEntity.class
        , VillageEntity.class, BankEntity.class, BankBranchEntity.class, BankTypeEntity.class,
        ShgDataEntity.class, ShgMemberDataEntity.class, MasterMemberSavingEntity.class,
        ShgSettingSavingFromMemberEntity.class,

        MasterNomineeRelationEntity.class, MasterIsRfReturendEntity.class, MasterSeetingShgActivityEntity.class,
        MasterSeetingShgSubActivityEntity.class, MeetingFrequencyEntity.class,
        MasterCuttOffLoanSourceEntity.class, MasterCuttOffMemberPurposeEntity.class,
        MasterCutoffFixedDepositEntity.class, MasterCutoffBankCompnyEntity.class,
        MasterTransShgPaymentEntity.class, MasterTransShgPaymentSubTypeEntity.class,
        MasterShgReceiptEntity.class, MasterShgReceiptSubTypeLoanSourceEntity.class,
        MasterShgReceiptLoanEntity.class, MasterPaymentAgencyTypeEntity.class,
        MasterCutoffCompanyBranchEntity.class,
        MasterCutoffCompanyNameEntity.class,
        ShgCutOffRunningInsuranceEntity.class,
        ShgLoansEntity.class,
        MasterCutoffLoanTypeEntity.class,
        ShgCutoffEntity.class,
        MemberCutOffRunningLoanEntity.class, //newly added
        MemberCutOffEntity.class,//newly added
        MemberCutOffSavingEntity.class //newly added

        }, version = 6, exportSchema = true)
public abstract class  ShgTrans extends RoomDatabase {

    public abstract LoginDao loginDao();

    public abstract BlockDao blockDao();

    public abstract GpsDao gpsDao();

    public abstract VillageDao villageDao();

    public abstract BankDao bankDao();

    public abstract BankBranchDao bankBranchDao();

    public abstract BankTypeDao bankTypeDao();

    public abstract ShgDataDao shgDataDao();

    public abstract ShgMemberDataDao shgMemberDao();

    public abstract ShgSettingSavingFromMemberDao shgSettingSavingFromMemberDao();

    public abstract MasterSeetingShgActivityDao shgActivityDao();

    public abstract MasterSeetingShgSubActivityDao shgSubActivityDao();

    public abstract MasterMemberSavingDao memberSavingDao();

    public abstract MasterNomineeRelationDao nomineeRelatioDao();

    public abstract MeetingFrequencyDao meetingFrequencyDao();

    public abstract MasterIsRfReturendDao rfReturendDao();

    public abstract MasterCuttOffLoanSourceDao loanSourceDao();

    public abstract MasterCuttOffMemberPurposeDao memberPurposeDao();

    public abstract MasterFixedDepositDao fixedDepositDao();

    public abstract MasterBankCompanyDao bankCompanyDao();

    public abstract MasterPaymentDao paymentDao();

    public abstract MasterPaymentSubTypeDao paymentSubTypeDao();

    public abstract MasterShgReceiptDao masterShgReceiptDao();

    public abstract MasterShgReceiptSubTypeLoanSourceDao masterShgReceiptSubTypeLoanSourceDao();

    public abstract MasterShgReceiptLoanDao masterShgReceiptLoanDao();

    public abstract MasterPaymentAgencyTypeDao masterPaymentAgencyTypeDao();

    public abstract MasterCompanyBranchNamedao masterCompanyBranchDao();

    public abstract MasterCompanyNameDao masterCompanyNameDao();

    public abstract ShgRunningInsuranceDao runningInsuranceDao();

    public abstract ShgLoanDao shgLoanDao();

    public abstract LoanTypeDao loanTypeDao();

    public abstract MemberCutOffRunningLoanDao masterMemberCutOffRunningLoanDao(); //newly addded

    public abstract MemberCutOffDao memberCutOffDao();

    public abstract ShgCutoffDao shgCutoffDao();

    public abstract MemberCutOffSavingDao memberCutOffSavingDao();


    public static volatile ShgTrans DBINSTANCE;
    private static final int NUMBER_OF_THREADS = 5;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static ShgTrans getDatabase(final Context context) {
        if (DBINSTANCE == null) {
            synchronized (AppDataBase.class) {
                if (DBINSTANCE == null) {
                    DBINSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShgTrans.class, "shg_trans")
                            .addMigrations(MIGRATION_3_4).build();
                }
            }
        }
        return DBINSTANCE;
    }

/*    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE bankentity "
                    + " RENAME COLUMN block_code TO entity_code");
            *//*ALTER TABLE content RENAME COLUMN archiveCount TO dismissCount*//*
        }
    };*/

    // Migration from 2 to 3, Room 2.2.0
    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE bankdetailsentity (id INTEGER PRIMARY KEY NOT NULL," +
                    " bank_code TEXT," +
                    " bank_name TEXT," +
                    " bank_level_code TEXT," +
                    " acc_length TEXT, " +
                    "entity_code TEXT)");

            database.execSQL("DROP TABLE bankentity");
            database.execSQL("ALTER TABLE bankdetailsentity RENAME TO bankentity");
        }
    };

    /*      database.execSQL("INSERT INTO new_Song (id, name, tag) " +
                    "SELECT id, name, tag FROM Song");*/

    /*CREATE TABLE bankentity (" +
                    "id INTEGER PRIMARY KEY NOT NULL," +
                    "name TEXT," +
                    "tag TEXT NOT NULL DEFAULT '')*/

 /*   CREATE TABLE bankentity (id INTEGER PRIMARY KEY NOT NULL,
                             bank_code TEXT, bank_name TEXT,
                             bank_level_code TEXT, acc_length TEXT, entity_code TEXT);*/
}
