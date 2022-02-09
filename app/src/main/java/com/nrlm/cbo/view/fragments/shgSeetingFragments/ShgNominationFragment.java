package com.nrlm.cbo.view.fragments.shgSeetingFragments;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.model.settingsModels.NomineeModel;
import com.nrlm.cbo.view.adapters.settingAdapter.ShgNominationAdapter;
import com.nrlm.cbo.database.room.entity.Shg;
import com.nrlm.cbo.view.fragments.BaseFragment;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ShgNominationFragment extends BaseFragment {


    @BindView(R.id.commonRecyclerview)
    RecyclerView nominationRecyclerView;


    @BindView(R.id.nomineeSaveBtn)
    MaterialButton nomineeSaveBtn;


    AppSharedPreferences appSharedPreferences;
    ShgmemberRepo shgmemberRepo;
    ShgDataRepo shgDataRepo;
    MasterDataRepo masterDataRepo;
    ShgSettingSavingFromMemberRepo shgSettingSavingFromMemberRepo;
    CustomProgressDialog customProgressDialog;

    List<ShgMemberDataEntity> shgmemberdataList;


    String shgCode="";


    ShgNominationAdapter shgNominationAdapter;



    public static ShgNominationFragment newInstance() {
        ShgNominationFragment shgNomination = new ShgNominationFragment();
        return shgNomination;
    }
    @Override
    public int getFragmentLayout() {
        return R.layout.shg_nimination_farg_layout;
    }

    @Override
    public void onFragmentReady() {

        getAllInstance();

        loadMemberData();

       // String villageCode =appSharedPreferences.getVillageCodeSetting();

        shgNominationAdapter = new ShgNominationAdapter(shgmemberdataList, getContext(),getActivity().getApplication());
        nominationRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        nominationRecyclerView.setAdapter(shgNominationAdapter);
        shgNominationAdapter.notifyDataSetChanged();

    }

    private void loadMemberData() {
        shgCode = appSharedPreferences.getShgCodeForVerification();
        shgmemberdataList = shgmemberRepo.getAllMemberData(shgCode);
    }

    private void getAllInstance() {
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(getContext());
        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(getContext());
        shgDataRepo = new ShgDataRepo(getActivity().getApplication());
        shgmemberRepo = new ShgmemberRepo(getActivity().getApplication());
        masterDataRepo = new MasterDataRepo(getActivity().getApplication());
        shgSettingSavingFromMemberRepo = new ShgSettingSavingFromMemberRepo(getActivity().getApplication());
        customProgressDialog =CustomProgressDialog.newInstance(getContext());

        shgmemberdataList =new ArrayList<>();
    }


    @OnClick(R.id.nomineeSaveBtn)
    public void saveNominationDetail(){
        List<NomineeModel> nomineeDataList =shgNominationAdapter.getNomineeList();
        String listStatus = "-";
        for(int i=0;i<nomineeDataList.size();i++){
            if(nomineeDataList.get(i).getRelationCode().equalsIgnoreCase("")){
                listStatus = nomineeDataList.get(i).getRelationCode();
                break;
            }
            if(nomineeDataList.get(i).getNomineeName().equalsIgnoreCase("")){
                listStatus = nomineeDataList.get(i).getNomineeName();
                break;
            }
        }

        if(listStatus.equalsIgnoreCase("-")){
            Toast.makeText(getContext(),"data save sucessfully",Toast.LENGTH_SHORT).show();


        }else {
            Toast.makeText(getContext(),"fill all Nominee Name and Relation",Toast.LENGTH_SHORT).show();
           /* FragmentManager manager = getFragmentManager();
            if (manager.getBackStackEntryCount() > 0) {
                FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
                manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);

            }*/

           /* if(getFragmentManager().getBackStackEntryCount() > 0){
                getFragmentManager().popBackStackImmediate();
            }*/


            /*FragmentManager fragmentManager = getFragmentManager();
            Fragment fragment1 =fragmentManage*/


            /*if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                return null;
            }
            String fragmentTag = getSupportFragmentManager().getBackStackEntryAt(getSupportFragmentManager().getBackStackEntryCount() - 1).getName();
            return getSupportFragmentManager().findFragmentByTag(fragmentTag);*/

           AppUtils.getInstance().replaceFragment(getFragmentManager(),ShgSeetingFrag.newInstance(),ShgSeetingFrag.class.getName(),false,R.id.seetingCommonFramContainer);

          //  getActivity().getSupportFragmentManager().beginTransaction().remove( getTopFragment()).commit();
        }
    }

    public Fragment getTopFragment() {
        if (getFragmentManager().getBackStackEntryCount() == 0) {
            return null;
        }
        String fragmentTag = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount()).getName();
        return getFragmentManager().findFragmentByTag(fragmentTag);
    }
}
