package com.nrlm.cbo.Utils;

import android.content.Context;

import com.nrlm.cbo.database.AppDataBase;
import com.nrlm.cbo.database.room.entity.BlockAssign;
import com.nrlm.cbo.database.room.entity.GpAssign;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.database.room.entity.ShgSeetingSavings;
import com.nrlm.cbo.database.room.entity.VillageAssign;
import com.nrlm.cbo.database.room.repository.ShgSettingRepo;

import java.util.ArrayList;
import java.util.List;

public class AllDataList {
    public static AllDataList dataListInstance;
    Context context;

    public AllDataList(Context context) {
        this.context = context;
    }

    public synchronized static AllDataList getInstance(Context context) {
        if (dataListInstance == null) {
            dataListInstance = new AllDataList(context);
        }
        return dataListInstance;
    }

    public List<String> getBlockData() {
        ArrayList<String> strList = new ArrayList<>();
        List<BlockAssign> bList = getBlosckList();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).blockName);
        }
        return strList;
    }

    public List<BlockAssign> getBlosckList() {
        List<BlockAssign> bList = AppDataBase.getDatabase(context).bloackCodeDao().loadAllBlockList();
        return bList;
    }

    public List<String> getGpData() {
        ArrayList<String> strList = new ArrayList<>();
        List<GpAssign> bList = getGpList();
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).gpName);
        }
        return strList;
    }

    public List<GpAssign> getGpList() {
        List<GpAssign> bList = AppDataBase.getDatabase(context).gpDao().loadAllGpList();
        return bList;
    }

    public List<String> getVillageData(String gpcode) {
        ArrayList<String> strList = new ArrayList<>();
        List<VillageAssign> bList = getVillageList(gpcode);
        for (int i = 0; i < bList.size(); i++) {
            strList.add(bList.get(i).vilageName);
        }
        return strList;
    }

    public List<VillageAssign> getVillageList(String gpcode) {
        List<VillageAssign> bList = AppDataBase.getDatabase(context).villageDao().loadVillageBasedOnGP(gpcode);
        return bList;
    }

    public List<Shg> getAllShg(String villageCode) {
        List<Shg> shgList = AppDataBase.getDatabase(context).shgDAO().loadAllShgWithVillage(villageCode);
        return shgList;
    }

    public List<String> getShgName(String villageCode) {
        ArrayList<String> strList = new ArrayList<>();
        List<Shg> shgList = getAllShg(villageCode);
        for (int i = 0; i < shgList.size(); i++) {
            strList.add(shgList.get(i).shgName);
        }
        return strList;
    }

    public String getMemberCount(String sgfCode) {
        int count = AppDataBase.getDatabase(context).memberDAO().memberCount(sgfCode);
        return "" + count;
    }

    public List<ShgMember> getmemberWithShg(String shgcode) {
        List<ShgMember> member = AppDataBase.getDatabase(context).memberDAO().getmemberBasedShg(shgcode);
        return member;
    }

    public String getShgUniqName(String shgCode) {
        String name = AppDataBase.getDatabase(context).shgDAO().getShgname(shgCode);
        return name;
    }
    public void updateVerifyStatus(String shgCode,String status){
         AppDataBase.getDatabase(context).shgDAO().updateVerificationStatus(shgCode,status);
    }
    public String getVerifyStatus(String shgCode){
        String status = AppDataBase.getDatabase(context).shgDAO().getVerificationStatus(shgCode);
        return status;
    }

    public List<ShgSeetingSavings> getAllShgSaving(String shgCode){
        List<ShgSeetingSavings> list = AppDataBase.getDatabase(context).shgSavingDao().getSavingShg(shgCode);
        return list;
    }

    public void insertSavigData(ShgSeetingSavings saving){
        AppDataBase.getDatabase(context).shgSavingDao().inserAll(saving);
    }

    public ShgSettingRepo shgSettingRepo(){
        ShgSettingRepo shgSeting =ShgSettingRepo.getInstance(context);
        return shgSeting;
    }



}
