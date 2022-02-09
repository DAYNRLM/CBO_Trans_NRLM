package com.nrlm.cbo.database.room.repositories;

import android.app.Application;

import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.ShgTrans;
import com.nrlm.cbo.database.room.daos.LoanTypeDao;
import com.nrlm.cbo.database.room.daos.MasterBankCompanyDao;
import com.nrlm.cbo.database.room.daos.MasterCompanyBranchNamedao;
import com.nrlm.cbo.database.room.daos.MasterCompanyNameDao;
import com.nrlm.cbo.database.room.daos.MasterFixedDepositDao;
import com.nrlm.cbo.database.room.daos.MasterCuttOffLoanSourceDao;
import com.nrlm.cbo.database.room.daos.MasterCuttOffMemberPurposeDao;
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
import com.nrlm.cbo.database.room.entities.MasterCutoffBankCompnyEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyBranchEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffCompanyNameEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffFixedDepositEntity;
import com.nrlm.cbo.database.room.entities.MasterCutoffLoanTypeEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffLoanSourceEntity;
import com.nrlm.cbo.database.room.entities.MasterCuttOffMemberPurposeEntity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MasterDataRepo {
    private MasterSeetingShgActivityDao masterSeetingShgActivityDao;
    private MasterSeetingShgSubActivityDao masterSeetingShgSubActivityDao;
    private MasterMemberSavingDao masterMemberSavingDao;
    private MasterNomineeRelationDao masterNomineeRelationDao;
    private MeetingFrequencyDao meetingFrequencyDao;
    private MasterIsRfReturendDao masterIsRfReturendDao;
    private MasterFixedDepositDao masterFixedDepositDao;
    private MasterBankCompanyDao masterBankCompanyDao;
    private MasterPaymentSubTypeDao masterPaymentSubTypeDao;
    private MasterPaymentDao masterPaymentDao;
    private MasterCuttOffLoanSourceDao masterCuttOffLoanSourceDao;
    private MasterCuttOffMemberPurposeDao masterCuttOffMemberPurposeDao;
    private MasterShgReceiptDao masterShgReceiptDao;
    private MasterShgReceiptSubTypeLoanSourceDao masterShgReceiptSubTypeLoanSourceDao;
    private MasterShgReceiptLoanDao masterShgReceiptLoanDao;
    private MasterPaymentAgencyTypeDao masterPaymentAgencyTypeDao;
    private MasterCompanyNameDao masterCompanyNameDao;
    private MasterCompanyBranchNamedao masterCompanyBranchNamedao;
    private LoanTypeDao masterCutoffLoanTypeDao;
    private MemberCutOffRunningLoanDao masterMemberCutOffRunningLoanDao;
    private MemberCutOffDao memberCutOffDao;
    private MemberCutOffSavingDao memberCutOffSavingDao;


    AppUtils appUtils;


    public MasterDataRepo(Application application) {
        ShgTrans shgTransDB = ShgTrans.getDatabase(application);
        masterSeetingShgActivityDao = shgTransDB.shgActivityDao();
        masterSeetingShgSubActivityDao = shgTransDB.shgSubActivityDao();
        masterMemberSavingDao = shgTransDB.memberSavingDao();
        masterNomineeRelationDao = shgTransDB.nomineeRelatioDao();
        meetingFrequencyDao = shgTransDB.meetingFrequencyDao();
        masterIsRfReturendDao = shgTransDB.rfReturendDao();
        masterFixedDepositDao = shgTransDB.fixedDepositDao();
        masterBankCompanyDao = shgTransDB.bankCompanyDao();
        masterPaymentSubTypeDao = shgTransDB.paymentSubTypeDao();
        masterPaymentDao = shgTransDB.paymentDao();
        masterCuttOffLoanSourceDao = shgTransDB.loanSourceDao();
        masterCuttOffMemberPurposeDao = shgTransDB.memberPurposeDao();
        masterShgReceiptDao = shgTransDB.masterShgReceiptDao();
        masterShgReceiptSubTypeLoanSourceDao = shgTransDB.masterShgReceiptSubTypeLoanSourceDao();
        masterShgReceiptLoanDao = shgTransDB.masterShgReceiptLoanDao();
        masterPaymentAgencyTypeDao = shgTransDB.masterPaymentAgencyTypeDao();
        masterCompanyNameDao = shgTransDB.masterCompanyNameDao();
        masterCompanyBranchNamedao = shgTransDB.masterCompanyBranchDao();
        masterCutoffLoanTypeDao = shgTransDB.loanTypeDao();
        masterMemberCutOffRunningLoanDao=shgTransDB.masterMemberCutOffRunningLoanDao();
        memberCutOffDao=shgTransDB.memberCutOffDao();
        memberCutOffSavingDao=shgTransDB.memberCutOffSavingDao();//newly added
        appUtils =AppUtils.getInstance();
    }

    public void insertAllMemberPurpose(MasterCuttOffMemberPurposeEntity masterCuttOffMemberPurposeEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterCuttOffMemberPurposeDao.insertAll(masterCuttOffMemberPurposeEntity);
            }
        });

    }

    public void insertAllLoanSource(MasterCuttOffLoanSourceEntity masterCuttOffLoanSourceEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterCuttOffLoanSourceDao.insertAll(masterCuttOffLoanSourceEntity);
            }
        });

    }

    public void insertAllShgActivity(MasterSeetingShgActivityEntity masterActivityData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterSeetingShgActivityDao.insertAll(masterActivityData);
            }
        });
    }

    public void insertAllShgSubActivity(MasterSeetingShgSubActivityEntity masterSubActivityData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterSeetingShgSubActivityDao.insertAll(masterSubActivityData);
            }
        });
    }

    public void insertAllMemberSaving(MasterMemberSavingEntity savingData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterMemberSavingDao.insertAll(savingData);
            }
        });
    }

    public void insertAllNomineeRelation(MasterNomineeRelationEntity nomineeData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterNomineeRelationDao.insertAll(nomineeData);
            }
        });
    }

    public void insertAllFrequency(MeetingFrequencyEntity frequencyData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                meetingFrequencyDao.insertAll(frequencyData);
            }
        });
    }

    public void insertAllRfReturend(MasterIsRfReturendEntity rfData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterIsRfReturendDao.insertAll(rfData);
            }
        });
    }

    public void insertAllBankCompany(MasterCutoffBankCompnyEntity companyData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterBankCompanyDao.insertAll(companyData);
            }
        });
    }

    public void insertAllFD(MasterCutoffFixedDepositEntity fdData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterFixedDepositDao.insertAll(fdData);
            }
        });
    }

    public void insertAllPaymentType(MasterTransShgPaymentEntity paymentData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterPaymentDao.insertAll(paymentData);
            }
        });
    }

    public void insertAllPaymentSubType(MasterTransShgPaymentSubTypeEntity paymentSubData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterPaymentSubTypeDao.insertAll(paymentSubData);
            }
        });
    }

    public void insertAllCompany(MasterCutoffCompanyNameEntity companyName) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterCompanyNameDao.insertAll(companyName);
            }
        });
    }

    public void insertAllCompanyBranch(MasterCutoffCompanyBranchEntity companyBranchName) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterCompanyBranchNamedao.insertAll(companyBranchName);
            }
        });
    }
    /************by lincon****************/

    public void insertAllLoanType(MasterCutoffLoanTypeEntity masterCutoffLoanTypeEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterCutoffLoanTypeDao.insertAll(masterCutoffLoanTypeEntity);
            }
        });
    }

    public List<MeetingFrequencyEntity> getMettingFreqData(){
        List<MeetingFrequencyEntity> list = null;
        try {
            Callable<List<MeetingFrequencyEntity>> callable = new Callable<List<MeetingFrequencyEntity>>() {
                @Override
                public List<MeetingFrequencyEntity> call() throws Exception {
                    return meetingFrequencyDao.getAllData();
                }
            };
            Future<List<MeetingFrequencyEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getFrequencyName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MeetingFrequencyEntity> bList = getMettingFreqData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).meeting_frequency_name);
        }
        return strList;
    }

    public List<MasterIsRfReturendEntity> getRFReturenedData(){
        List<MasterIsRfReturendEntity> list = null;
        try {
            Callable<List<MasterIsRfReturendEntity>> callable = new Callable<List<MasterIsRfReturendEntity>>() {
                @Override
                public List<MasterIsRfReturendEntity> call() throws Exception {
                    return masterIsRfReturendDao.getRfData();
                }
            };
            Future<List<MasterIsRfReturendEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getRfName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterIsRfReturendEntity> bList = getRFReturenedData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).rf_to_be_return_name);
        }
        return strList;
    }

    public List<MasterSeetingShgActivityEntity> getShgActivityData(){
        List<MasterSeetingShgActivityEntity> list = null;
        try {
            Callable<List<MasterSeetingShgActivityEntity>> callable = new Callable<List<MasterSeetingShgActivityEntity>>() {
                @Override
                public List<MasterSeetingShgActivityEntity> call() throws Exception {
                    return masterSeetingShgActivityDao.getAllData();
                }
            };
            Future<List<MasterSeetingShgActivityEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getActivityName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterSeetingShgActivityEntity> bList = getShgActivityData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).category_name);
        }
        return strList;
    }

    public List<MasterSeetingShgSubActivityEntity> getShgSubActivityData(String catValue){
        List<MasterSeetingShgSubActivityEntity> list = null;
        try {
            Callable<List<MasterSeetingShgSubActivityEntity>> callable = new Callable<List<MasterSeetingShgSubActivityEntity>>() {
                @Override
                public List<MasterSeetingShgSubActivityEntity> call() throws Exception {
                    return masterSeetingShgSubActivityDao.getAllData(catValue);
                }
            };
            Future<List<MasterSeetingShgSubActivityEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getSubActivityName(String catValue){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterSeetingShgSubActivityEntity> bList = getShgSubActivityData(catValue);
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).sub_activity_name);
        }
        return strList;
    }

    public List<MasterMemberSavingEntity> getAllMasterSaving(){
        List<MasterMemberSavingEntity> list = null;
        try {
            Callable<List<MasterMemberSavingEntity>> callable = new Callable<List<MasterMemberSavingEntity>>() {
                @Override
                public List<MasterMemberSavingEntity> call() throws Exception {
                    return masterMemberSavingDao.getAllSaving();
                }
            };
            Future<List<MasterMemberSavingEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }
    public ArrayList<String> getMasterMemberSavingName()
    {
        List<MasterMemberSavingEntity> masterMemberSavings=getAllMasterSaving();
        ArrayList<String> masterMemberSavingName=new ArrayList<>();
        for(MasterMemberSavingEntity masterMemberSavingEntity:masterMemberSavings)
        {
            masterMemberSavingName.add(masterMemberSavingEntity.saving_type_name);
        }
        return masterMemberSavingName;
    }
    public List<MasterNomineeRelationEntity> getRelationData(){
        List<MasterNomineeRelationEntity> list = null;
        try {
            Callable<List<MasterNomineeRelationEntity>> callable = new Callable<List<MasterNomineeRelationEntity>>() {
                @Override
                public List<MasterNomineeRelationEntity> call() throws Exception {
                    return masterNomineeRelationDao.getAllRelation();
                }
            };
            Future<List<MasterNomineeRelationEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }
    public List<String> getRelationName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterNomineeRelationEntity> bList = getRelationData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).relation_name);
        }
        return strList;
    }

    public List<MasterCutoffBankCompnyEntity> getMasterBankCompanyData(){
        List<MasterCutoffBankCompnyEntity> list = null;
        try {
            Callable<List<MasterCutoffBankCompnyEntity>> callable = new Callable<List<MasterCutoffBankCompnyEntity>>() {
                @Override
                public List<MasterCutoffBankCompnyEntity> call() throws Exception {
                    return masterBankCompanyDao.getAllData();
                }
            };
            Future<List<MasterCutoffBankCompnyEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getMasterBankCompanyName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterCutoffBankCompnyEntity> bList = getMasterBankCompanyData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).bank_company_name);
        }
        return strList;
    }

    public List<MasterCutoffFixedDepositEntity> getMasterFixedDepositData(){
        List<MasterCutoffFixedDepositEntity> list = null;
        try {
            Callable<List<MasterCutoffFixedDepositEntity>> callable = new Callable<List<MasterCutoffFixedDepositEntity>>() {
                @Override
                public List<MasterCutoffFixedDepositEntity> call() throws Exception {
                    return masterFixedDepositDao.getAllData();
                }
            };
            Future<List<MasterCutoffFixedDepositEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        return list;
    }

    public List<String> getFdName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterCutoffFixedDepositEntity> bList = getMasterFixedDepositData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).fixed_deposit_inv_name);
        }
        return strList;
    }

    public List<MasterCutoffCompanyNameEntity> getMasterCompanyData(){
        List<MasterCutoffCompanyNameEntity> list = null;
        try {
            Callable<List<MasterCutoffCompanyNameEntity>> callable = new Callable<List<MasterCutoffCompanyNameEntity>>() {
                @Override
                public List<MasterCutoffCompanyNameEntity> call() throws Exception {
                    return masterCompanyNameDao.getAllData();
                }
            };
            Future<List<MasterCutoffCompanyNameEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        MasterCutoffCompanyNameEntity masterCutoffCompanyNameEntity = new MasterCutoffCompanyNameEntity();
        masterCutoffCompanyNameEntity.company_name_id ="000";
        masterCutoffCompanyNameEntity.company_name = "Other";
        masterCutoffCompanyNameEntity.syncStatus="1";
        list.add(masterCutoffCompanyNameEntity);
        return list;
    }

    public List<String> getCompanyName(){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterCutoffCompanyNameEntity> bList = getMasterCompanyData();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).company_name);
        }
        return strList;
    }

    public List<MasterCutoffCompanyBranchEntity> getMasterCompanyBranchData(String companyId){
        List<MasterCutoffCompanyBranchEntity> list = null;
        try {
            Callable<List<MasterCutoffCompanyBranchEntity>> callable = new Callable<List<MasterCutoffCompanyBranchEntity>>() {
                @Override
                public List<MasterCutoffCompanyBranchEntity> call() throws Exception {
                    return masterCompanyBranchNamedao.getAllData(companyId);
                }
            };
            Future<List<MasterCutoffCompanyBranchEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in GP data:- " + e, GpsRepo.class);
        }
        MasterCutoffCompanyBranchEntity masterCutoffCompanyBranchEntity =new MasterCutoffCompanyBranchEntity();
        masterCutoffCompanyBranchEntity.company_name_id ="000";
        masterCutoffCompanyBranchEntity.company_branch_name ="Other";
        masterCutoffCompanyBranchEntity.company_branch_name_id ="000";
        masterCutoffCompanyBranchEntity.syncStatus="1";
        list.add(masterCutoffCompanyBranchEntity);

        return list;
    }

    public List<String> getCompanyBranchName(String companyId ){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterCutoffCompanyBranchEntity> bList = getMasterCompanyBranchData(companyId);
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).company_branch_name);
        }
        return strList;
    }

    /**************get all loan type and name**************/
    public List<MasterCutoffLoanTypeEntity> getMasterLoanType(){
        List<MasterCutoffLoanTypeEntity> list = null;
        try {
            Callable<List<MasterCutoffLoanTypeEntity>> callable = new Callable<List<MasterCutoffLoanTypeEntity>>() {
                @Override
                public List<MasterCutoffLoanTypeEntity> call() throws Exception {
                    return masterCutoffLoanTypeDao.getAllLoanType();
                }
            };
            Future<List<MasterCutoffLoanTypeEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in Master data:- " + e, MasterDataRepo.class);
        }
        return list;
    }

    public List<String> getloanTypeName( ){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterCutoffLoanTypeEntity> bList = getMasterLoanType();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).loan_type);
        }
        return strList;
    }
    /*****************************************************/

    /**************get all loan Source and name**************/
    public List<MasterCuttOffLoanSourceEntity> getMasterLoanSource(){
        List<MasterCuttOffLoanSourceEntity> list = null;
        try {
            Callable<List<MasterCuttOffLoanSourceEntity>> callable = new Callable<List<MasterCuttOffLoanSourceEntity>>() {
                @Override
                public List<MasterCuttOffLoanSourceEntity> call() throws Exception {
                    return masterCuttOffLoanSourceDao.getAllLoanSourceType();
                }
            };
            Future<List<MasterCuttOffLoanSourceEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
            list = future.get();

        } catch (Exception e) {
            appUtils.showLog("Expection in Master data:- " + e, MasterDataRepo.class);
        }
        return list;
    }

    public List<String> getloanSourceName( ){
        ArrayList<String> strList = new ArrayList<>();
        List<MasterCuttOffLoanSourceEntity> bList = getMasterLoanSource();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).loan_source_name);
        }
        return strList;
    }
    /*****************************************************/

    /***********by manish***************/

    public void insertShgReceiptPartialData(MasterShgReceiptEntity masterShgReceiptEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterShgReceiptDao.insertAll(masterShgReceiptEntity);
            }
        });
    }

    public void insertShgReceiptSubTypeLoanSource(MasterShgReceiptSubTypeLoanSourceEntity masterShgReceiptSubTypeLoanSourceEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterShgReceiptSubTypeLoanSourceDao.insertAll(masterShgReceiptSubTypeLoanSourceEntity);
            }
        });

    }

    public void insertShgReceiptLoan(MasterShgReceiptLoanEntity masterShgReceiptLoanEntity) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterShgReceiptLoanDao.insertAll(masterShgReceiptLoanEntity);
            }
        });
    }




    /*===============================================================================================================*/



    public void insertPaymentAgencyType(MasterPaymentAgencyTypeEntity masterPaymentAgencyType) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterPaymentAgencyTypeDao.insert(masterPaymentAgencyType);
            }
        });
    }


   public String  getSavingTypeName(String savingTypeId) throws ExecutionException, InterruptedException {
       Callable<String> callable=new Callable<String>() {
           @Override
           public String call() throws Exception {
               return masterMemberSavingDao.getSavingTypeName(savingTypeId);
           }
       };
       Future<String> future= Executors.newSingleThreadExecutor().submit(callable);
       return future.get();
   }


    public List<MasterShgReceiptEntity> getAllReceiptType() throws ExecutionException, InterruptedException {
        Callable<List<MasterShgReceiptEntity>> callable=new Callable<List<MasterShgReceiptEntity>>() {
            @Override
            public List<MasterShgReceiptEntity> call() throws Exception {
                return masterShgReceiptDao.getAllReceiptType();
            }
        };
        Future<List<MasterShgReceiptEntity>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }
    public List<MasterShgReceiptSubTypeLoanSourceEntity> getReceiptSubTypes(String receiptTypeId) throws ExecutionException, InterruptedException {
        Callable<List<MasterShgReceiptSubTypeLoanSourceEntity>> callable=new Callable<List<MasterShgReceiptSubTypeLoanSourceEntity>>() {
            @Override
            public List<MasterShgReceiptSubTypeLoanSourceEntity> call() throws Exception {
                return masterShgReceiptSubTypeLoanSourceDao.getReceiptSubTypes(receiptTypeId);
            }
        };
        Future<List<MasterShgReceiptSubTypeLoanSourceEntity>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }
    public List<MasterShgReceiptLoanEntity> getReceiptSubToSubTypes(String receiptSubTypeId) throws ExecutionException, InterruptedException {
        Callable<List<MasterShgReceiptLoanEntity>> callable=new Callable<List<MasterShgReceiptLoanEntity>>() {
            @Override
            public List<MasterShgReceiptLoanEntity> call() throws Exception {
                return masterShgReceiptLoanDao.getReceiptSubToSubTypes(receiptSubTypeId);
            }
        };
        Future<List<MasterShgReceiptLoanEntity>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }

    public List<MasterTransShgPaymentEntity>  getAllPaymentType() throws ExecutionException, InterruptedException {
        Callable<List<MasterTransShgPaymentEntity> > callable=new Callable<List<MasterTransShgPaymentEntity> >() {
            @Override
            public List<MasterTransShgPaymentEntity> call() throws Exception {
                return masterPaymentDao.getAllPaymentType();
            }
        };
        Future<List<MasterTransShgPaymentEntity> > future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }



    public List<MasterTransShgPaymentSubTypeEntity> getPaymentSubTypes(String paymentTypeId) throws ExecutionException, InterruptedException {

        Callable<List<MasterTransShgPaymentSubTypeEntity>> callable=new Callable<List<MasterTransShgPaymentSubTypeEntity>>() {
            @Override
            public List<MasterTransShgPaymentSubTypeEntity> call() throws Exception {
               return masterPaymentSubTypeDao.getPaymentSubTypes(paymentTypeId);
            }
        };
        Future<List<MasterTransShgPaymentSubTypeEntity>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }

    public List<MasterPaymentAgencyTypeEntity> getPaymentAgencyTypes(String paymentTypeId,String paymentSubTypeId)
            throws ExecutionException, InterruptedException {

        Callable<List<MasterPaymentAgencyTypeEntity>> callable=new Callable<List<MasterPaymentAgencyTypeEntity>>() {
            @Override
            public List<MasterPaymentAgencyTypeEntity> call() throws Exception {
                return masterPaymentAgencyTypeDao.getPaymentAgencyTypes(paymentTypeId,paymentSubTypeId);
            }
        };
        Future<List<MasterPaymentAgencyTypeEntity>> future= Executors.newSingleThreadExecutor().submit(callable);
        return future.get();
    }


    public List<MasterCuttOffMemberPurposeEntity> getMasterMemberPurpose()
    {
        List<MasterCuttOffMemberPurposeEntity> list=null;
        try {
            Callable<List<MasterCuttOffMemberPurposeEntity>> callable = new Callable<List<MasterCuttOffMemberPurposeEntity>>() {
                @Override
                public List<MasterCuttOffMemberPurposeEntity> call() throws Exception {
                    return masterCuttOffMemberPurposeDao.getAllPurpose();
                }
            };
            Future<List<MasterCuttOffMemberPurposeEntity>> future = Executors.newSingleThreadExecutor().submit(callable);
           list= future.get();
        }catch (Exception e)
        {
            appUtils.showLog("Error in CutOffMembrPurpose"+e,MasterDataRepo.class);
        }
        return list;
    }
    public List<String> getMasterCuttOffMemberPurposeName()
    {
        ArrayList<String> cutoffMembePurposeName=new ArrayList<>();
        List<MasterCuttOffMemberPurposeEntity> masterCuttOffMemberPurposeEntities= getMasterMemberPurpose();
        for(MasterCuttOffMemberPurposeEntity masterCuttOffMemberPurposeEntity:masterCuttOffMemberPurposeEntities)
        {
           cutoffMembePurposeName.add(masterCuttOffMemberPurposeEntity.discription);
        }
        return cutoffMembePurposeName;

    }
  /*  public void insertAllMemberSaving(MasterMemberSavingEntity savingData) {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterMemberSavingDao.insertAll(savingData);
            }
        });
    }*/

    public void insertAllMemberRunningLoan(MemberCutOffRunningLoanEntity memberCutOffRunningLoanEntity)
    {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterMemberCutOffRunningLoanDao.insertAll(memberCutOffRunningLoanEntity);
            }
        });
    }
public void deleteRuningLoan(String loanNumber)
{
    ShgTrans.databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            masterMemberCutOffRunningLoanDao.deleteById(loanNumber);
        }
    });
}
public void insertMemberCuttOff(MemberCutOffEntity memberCutOffEntity) {
    ShgTrans.databaseWriteExecutor.execute(new Runnable() {
        @Override
        public void run() {
            memberCutOffDao.insertAll(memberCutOffEntity);
        }
    });

    }


    public MemberCutOffEntity getMemberCutOff(String memberCode)
    {
        MemberCutOffEntity memberCutOffEntityData = null;
        try
        {
            Callable<MemberCutOffEntity> callable=new Callable<MemberCutOffEntity>() {
                @Override
                public MemberCutOffEntity call() throws Exception {
                    return memberCutOffDao.getData(memberCode);
                }
            };
            Future<MemberCutOffEntity> future=Executors.newSingleThreadExecutor().submit(callable);
            memberCutOffEntityData=future.get();
        }catch (Exception e)
        {
            appUtils.showLog("Error in getMemberCutOff"+e,MasterDataRepo.class);
        }
        return memberCutOffEntityData;
        }

        public List<MemberCutOffRunningLoanEntity> getRuningLoanData(String memberCode)
        {
            List<MemberCutOffRunningLoanEntity> memberCutOffRunningLoanData=null;
            try
            {
             Callable<List<MemberCutOffRunningLoanEntity>> callable=new Callable<List<MemberCutOffRunningLoanEntity>>() {
                 @Override
                 public List<MemberCutOffRunningLoanEntity> call() throws Exception {
                     return masterMemberCutOffRunningLoanDao.getRunningLoanData(memberCode);
                 }

             };
             Future<List<MemberCutOffRunningLoanEntity>> future=Executors.newSingleThreadExecutor().submit(callable);

            memberCutOffRunningLoanData= future.get();

            }catch (Exception e)
            {
                appUtils.showLog("Error in memberRunningLoanData"+e,MasterDataRepo.class);
            }
            return memberCutOffRunningLoanData;
        }

        /* public void deleteRuningLoan(String loanNumber)
    {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                masterMemberCutOffRunningLoanDao.deleteById(loanNumber);
            }
        });
    }*/

    public void insertMemberCutOffSaving(MemberCutOffSavingEntity memberCutOffSavingEntity)
    {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                memberCutOffSavingDao.insertAll(memberCutOffSavingEntity);
            }
        });
    }

    public List<MemberCutOffSavingEntity> getMemberCutOffSavingData(String memberCode)
    {
        List<MemberCutOffSavingEntity> memberCutOffSavingData=null;
        try {

            Callable<List<MemberCutOffSavingEntity>> callable=new Callable<List<MemberCutOffSavingEntity>>() {
                @Override
                public List<MemberCutOffSavingEntity> call() throws Exception {
                    return memberCutOffSavingDao.getMemberCutOffSavingData(memberCode);
                }
            };
            Future<List<MemberCutOffSavingEntity>> future=Executors.newSingleThreadExecutor().submit(callable);
            memberCutOffSavingData= future.get();
        }catch (Exception e)
        {
            appUtils.showLog("Error in getMemberCutOff saving data"+e,MasterDataRepo.class);
        }
   return  memberCutOffSavingData;

    }
    public String getSavingTypeCode(String savingTypeName)
    {
        String savingCode="";
        try{
            Callable<String> callable =new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return masterMemberSavingDao.getSavingTypeCode(savingTypeName);
                }
            };
            Future<String> future=ShgTrans.databaseWriteExecutor.submit(callable);
            savingCode=future.get();

        }catch (Exception e)
        {
            appUtils.showLog("Error in get saving type code"+e,MasterDataRepo.class);
        }
        return savingCode;


    }
    public void deleteMemberSaving(String memberCode,String savingName)
    {
        ShgTrans.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                memberCutOffSavingDao.deleteSavingType(memberCode,savingName);
            }
        });

    }

    public List<MemberCutOffEntity> getNotSyncData(String status)
    {
        List<MemberCutOffEntity> memberCutOffNotSyc=null;
        try{
            Callable<List<MemberCutOffEntity>> callable=new Callable<List<MemberCutOffEntity>>() {
                @Override
                public List<MemberCutOffEntity> call() throws Exception {
                    return memberCutOffDao.getNotSyncData(status);
                }
            };

            Future<List<MemberCutOffEntity>> future=ShgTrans.databaseWriteExecutor.submit(callable);
            memberCutOffNotSyc=future.get();

        }catch (Exception e)
        {
            appUtils.showLog("Error in not sync data",MasterDataRepo.class);
        }
        return memberCutOffNotSyc;

    }
    }
