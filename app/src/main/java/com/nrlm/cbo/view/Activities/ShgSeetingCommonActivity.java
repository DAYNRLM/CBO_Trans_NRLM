package com.nrlm.cbo.view.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.CustomProgressDialog;
import com.nrlm.cbo.database.room.entities.MasterIsRfReturendEntity;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgActivityEntity;
import com.nrlm.cbo.database.room.entities.MasterSeetingShgSubActivityEntity;
import com.nrlm.cbo.database.room.entities.MeetingFrequencyEntity;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.repositories.MasterDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.fragments.shgSeetingFragments.ShgSeetingFrag;
import com.nrlm.cbo.view.fragments.verificationFragment.ShgVerificationFragment;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/***created by
 * lincon bhalla
 * on
 * 16 dec 2020****/
public class ShgSeetingCommonActivity extends AppCompatActivity {

    /*************all text view*****************/
    @BindView(R.id.tbTitle)
    TextView tbTitle;

    @BindView(R.id.shgName_Tv)
    TextView shgName_Tv;

    @BindView(R.id.presidentFatherTv)
    TextView presidentFatherTv;

    @BindView(R.id.secretaryFatherTv)
    TextView secretaryFatherTv;

    @BindView(R.id.tressurerFatherTv)
    TextView tressurerFatherTv;

    @BindView(R.id.bookKeeperFatherTv)
    TextView bookKeeperFatherTv;

    /************all spinner***************/
    @BindView(R.id.meetingFrequencySpinner)
    AutoCompleteTextView meetingFrequencySpinner;

    @BindView(R.id.cifReturnedSpinner)
    AutoCompleteTextView cifReturnedSpinner;

    @BindView(R.id.rfReturnedSpinner)
    AutoCompleteTextView rfReturnedSpinner;

    @BindView(R.id.presidentSpinner)
    MaterialBetterSpinner presidentSpinner;

    @BindView(R.id.secretarySpinner)
    MaterialBetterSpinner secretarySpinner;

    @BindView(R.id.tressurerSpinner)
    MaterialBetterSpinner tressurerSpinner;

    @BindView(R.id.bookKeeperSpinner)
    MaterialBetterSpinner bookKeeperSpinner;

    @BindView(R.id.categorySpinner)
    AutoCompleteTextView categorySpinner;

    @BindView(R.id.subCategorySpinner)
    AutoCompleteTextView subCategorySpinner;

    @BindView(R.id.descriptionSpinner)
    AutoCompleteTextView descriptionSpinner;

    /***********all btn**************/
    @BindView(R.id.rfradioGroup)
    RadioGroup rfradioGroup;

    @BindView(R.id.saveSettingBtn)
    MaterialButton saveSettingBtn;

    @BindView(R.id.closeSettingBtn)
    MaterialButton closeSettingBtn;

    @BindView(R.id.saveFormDataBtn)
    MaterialButton saveFormDataBtn;

    @BindView(R.id.savingCB)
    CheckBox savingCB;

    @BindView(R.id.nominationCB)
    CheckBox nominationCB ;

    @BindView(R.id.addSavingBtn)
    MaterialButton addSavingBtn;




    /***********all TIL**************/
    @BindView(R.id.cifReturnedSpinnerTIL)
    TextInputLayout cifReturnedSpinnerTIL;

    @BindView(R.id.rfReturnedSpinnerTIL)
    TextInputLayout rfReturnedSpinnerTIL;

    @BindView(R.id.meetingFrequencySpinnerTIL)
    TextInputLayout meetingFrequencySpinnerTIL;

   /* @BindView(R.id.presidentSpinnerTIL)
    TextInputLayout presidentSpinnerTIL;*/

    @BindView(R.id.categorySpinnerTIL)
    TextInputLayout categorySpinnerTIL;

    @BindView(R.id.subCategorySpinnerTIL)
    TextInputLayout subCategorySpinnerTIL;

    @BindView(R.id.descriptionSpinnerTIL)
    TextInputLayout descriptionSpinnerTIL;

    /**************all layout****************/
    @BindView(R.id.addSavingLL)
    LinearLayout addSavingLL;




    /******************all instance classes**************/
    AppSharedPreferences appSharedPreferences;
    AllDataList allDataList;
    ShgmemberRepo shgmemberRepo;
    ShgDataRepo shgDataRepo;
    MasterDataRepo masterDataRepo;
    ShgSettingSavingFromMemberRepo shgSettingSavingFromMemberRepo;
    CustomProgressDialog customProgressDialog;
    AppUtils appUtils;


    /**********all global variable************/
    String verificationStatus = "";
    String shgCode = "";
    String shgName = "";
    String meetingTypeId = "";
    String rfReturnedId = "";
    String cifReturnedId = "";
    String rfReturnedRadioStatus = "yes";
    String cashBookNo = "";
    String cashBookPageNo = "";
    String shgLeaderCode = "";
    String shgSecretaryCode = "";
    String shgTressureCode = "";
    String shgBookkeeperCode = "";
    String catValue = "";
    String subCatValue = "";


    /**************all list and adapters*******************/
    List<MeetingFrequencyEntity> meetingFrequencyDataList;
    List<MasterIsRfReturendEntity> rfReturendDataList;
    List<ShgMemberDataEntity> shgmemberdataList;

    List<MasterSeetingShgActivityEntity> activityDataList;
    List<MasterSeetingShgSubActivityEntity> subActivityDataList;


    ArrayAdapter<String> frequencyAdapter;
    ArrayAdapter<String> rfAdapter;
    ArrayAdapter<String> cifAdapter;


    ArrayAdapter<String> presidentAdapter;
    ArrayAdapter<String> secretaryAdapter;
    ArrayAdapter<String> tressureryAdapter;
    ArrayAdapter<String> bookKeeperAdapter;
    ArrayAdapter<String> categoryAdapter;
    ArrayAdapter<String> subCategoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shg_seeting_common);
        ButterKnife.bind(this);

        initialization();

        setTitleAndShgName();

        loadMasterData();

        loadMemberData();

        loadActivitiesData();

        rfradioGroup.check(R.id.yesRadioBtn);

        rfradioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.yesRadioBtn:
                        clearFocus(1);
                        rfReturnedRadioStatus = "yes";

                        break;
                    case R.id.noRadioBtn:
                        clearFocus(2);
                        rfReturnedRadioStatus = "no";
                        break;
                }
            }
        });

        setAllSpinnerListner();


        savingCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    addSavingLL.setVisibility(View.VISIBLE);
                } else {
                    addSavingLL.setVisibility(View.GONE);
                }
            }
        });

        /*AppUtils.getInstance().replaceFragment(getSupportFragmentManager(),
                ShgSeetingFrag.newInstance(), ShgSeetingFrag.class.getName(), true, R.id.seetingCommonFramContainer);*/

    }

    private void loadActivitiesData() {
        activityDataList = masterDataRepo.getShgActivityData();
        categoryAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getActivityName());
        categorySpinner.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    private void loadMemberData() {
        shgmemberdataList = shgmemberRepo.getAllMemberData(shgCode);
        ArrayList<String> memberNameList = new ArrayList<>();
        for (int i = 0; i < shgmemberdataList.size(); i++) {
            memberNameList.add(shgmemberdataList.get(i).memberName);
        }

        /********president Spinner**********/
        presidentAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, memberNameList);
        presidentSpinner.setAdapter(presidentAdapter);
        presidentAdapter.notifyDataSetChanged();

        for (int i = 0; i < shgmemberdataList.size(); i++) {
            if (shgmemberdataList.get(i).leader.equalsIgnoreCase("President")) {
                presidentSpinner.setText(shgmemberdataList.get(i).memberName);
                presidentFatherTv.setText(shgmemberdataList.get(i).belongingName);
                shgLeaderCode = shgmemberdataList.get(i).shgCode;
            }
        }

        /***************sec spinner*********************/
        secretaryAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, memberNameList);
        secretarySpinner.setAdapter(secretaryAdapter);
        secretaryAdapter.notifyDataSetChanged();

        for (int i = 0; i < shgmemberdataList.size(); i++) {
            if (shgmemberdataList.get(i).leader.equalsIgnoreCase("Secretary")) {
                secretarySpinner.setText(shgmemberdataList.get(i).memberName);
                secretaryFatherTv.setText(shgmemberdataList.get(i).belongingName);
                shgSecretaryCode = shgmemberdataList.get(i).shgCode;
            }
        }

        /***************tres spinner*********************/
        tressureryAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, memberNameList);
        tressurerSpinner.setAdapter(tressureryAdapter);
        tressureryAdapter.notifyDataSetChanged();

        for (int i = 0; i < shgmemberdataList.size(); i++) {
            if (shgmemberdataList.get(i).leader.equalsIgnoreCase("Treasurer")) {
                tressurerSpinner.setText(shgmemberdataList.get(i).memberName);
                tressurerFatherTv.setText(shgmemberdataList.get(i).belongingName);
                shgTressureCode = shgmemberdataList.get(i).shgCode;
            }
        }

        /***************book keeper spinner*********************/
        bookKeeperAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, memberNameList);
        bookKeeperSpinner.setAdapter(bookKeeperAdapter);
        bookKeeperAdapter.notifyDataSetChanged();
        bookKeeperFatherTv.setText("");
    }

    private void setAllSpinnerListner() {
        meetingFrequencySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                meetingTypeId = meetingFrequencyDataList.get(position).meeting_frequency;
            }
        });

        rfReturnedSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rfReturnedId = rfReturendDataList.get(position).rf_to_be_return_value;
            }
        });

        cifReturnedSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cifReturnedId = rfReturendDataList.get(position).rf_to_be_return_value;
            }
        });

        /*************president spinner*************************/

        presidentSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgLeaderCode = shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                presidentFatherTv.setText(fName);

            }
        });

        /*************secretary spinner*************************/
        secretarySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgSecretaryCode = shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                secretaryFatherTv.setText(fName);

            }
        });

        /*************tressure spinner*************************/
        tressurerSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgTressureCode = shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                tressurerFatherTv.setText(fName);

            }
        });

        /*************bookkeeper spinner*************************/
        bookKeeperSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shgBookkeeperCode = shgmemberdataList.get(position).shgCode;

                String name = shgmemberdataList.get(position).memberName;
                String fName = shgmemberdataList.get(position).belongingName;
                bookKeeperFatherTv.setText(fName);

            }
        });

        /*************cat spinner********************************/
        categorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                catValue = activityDataList.get(position).category_value;

                subActivityDataList = masterDataRepo.getShgSubActivityData(catValue);

                subCategoryAdapter = new ArrayAdapter<String>(ShgSeetingCommonActivity.this, R.layout.spinner_textview, masterDataRepo.getSubActivityName(catValue));
                subCategorySpinner.setAdapter(subCategoryAdapter);
                subCategoryAdapter.notifyDataSetChanged();

                clearFocus(5);
            }
        });

        /*************sub-cat spinner********************************/
        subCategorySpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subCatValue=subActivityDataList.get(position).sub_activity_id;
            }
        });
    }

    private void loadMasterData() {
        meetingFrequencyDataList = masterDataRepo.getMettingFreqData();
        frequencyAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getFrequencyName());
        meetingFrequencySpinner.setAdapter(frequencyAdapter);
        frequencyAdapter.notifyDataSetChanged();

        rfReturendDataList = masterDataRepo.getRFReturenedData();
        rfAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getRfName());
        rfReturnedSpinner.setAdapter(rfAdapter);
        rfAdapter.notifyDataSetChanged();

        cifAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getRfName());
        cifReturnedSpinner.setAdapter(cifAdapter);
        cifAdapter.notifyDataSetChanged();
    }

    private void setTitleAndShgName() {
        shgCode = appSharedPreferences.getShgCodeForVerification();
        //verificationStatus =allDataList.getVerifyStatus(shgCode);
        verificationStatus = shgDataRepo.getVerificationStatus(shgCode);
        shgName = shgDataRepo.getshgName(shgCode);
        tbTitle.setText("SHG SETTING");
        shgName_Tv.setText(shgName + " SHG" + " (" + shgCode + ")");
    }

    private void initialization() {
        meetingFrequencyDataList = new ArrayList<>();
        rfReturendDataList = new ArrayList<>();
        shgmemberdataList = new ArrayList<>();
        activityDataList = new ArrayList<>();
        subActivityDataList = new ArrayList<>();

        appSharedPreferences = AppSharedPreferences.getsharedprefInstances(ShgSeetingCommonActivity.this);
        allDataList = AllDataList.getInstance(ShgSeetingCommonActivity.this);
        shgDataRepo = new ShgDataRepo(getApplication());
        shgmemberRepo = new ShgmemberRepo(getApplication());
        masterDataRepo = new MasterDataRepo(getApplication());
        shgSettingSavingFromMemberRepo = new ShgSettingSavingFromMemberRepo(getApplication());
        customProgressDialog = CustomProgressDialog.newInstance(ShgSeetingCommonActivity.this);
        appUtils =AppUtils.getInstance();

    }

    public void clearFocus(int id) {
        switch (id) {
            case 1:
                rfReturnedId = "";
                cifReturnedId = "";

                cifReturnedSpinnerTIL.setVisibility(View.VISIBLE);
                rfReturnedSpinnerTIL.setVisibility(View.VISIBLE);

                cifReturnedSpinner.setText(null);
                rfReturnedSpinner.setText(null);

                cifReturnedSpinner.setFocusable(false);
                rfReturnedSpinner.setFocusable(false);


                rfReturendDataList.clear();
                rfReturendDataList = masterDataRepo.getRFReturenedData();
                rfAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getRfName());
                rfReturnedSpinner.setAdapter(rfAdapter);
                rfAdapter.notifyDataSetChanged();

                cifAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getRfName());
                cifReturnedSpinner.setAdapter(cifAdapter);
                cifAdapter.notifyDataSetChanged();


                break;
            case 2:
                rfReturnedId = "";
                cifReturnedId = "";

                cifReturnedSpinnerTIL.setVisibility(View.VISIBLE);
                rfReturnedSpinnerTIL.setVisibility(View.GONE);

                cifReturnedSpinner.setText(null);
                rfReturnedSpinner.setText(null);

                cifReturnedSpinner.setFocusable(false);
                rfReturnedSpinner.setFocusable(false);


                rfReturendDataList.clear();
                rfReturendDataList = masterDataRepo.getRFReturenedData();
                rfAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getRfName());
                rfReturnedSpinner.setAdapter(rfAdapter);
                rfAdapter.notifyDataSetChanged();

                cifAdapter = new ArrayAdapter<String>(this, R.layout.spinner_textview, masterDataRepo.getRfName());
                cifReturnedSpinner.setAdapter(cifAdapter);
                cifAdapter.notifyDataSetChanged();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                subCategorySpinner.setText(null);
                subCatValue="";


                break;
        }
    }

    public void valiDateAllData() {
        if (meetingTypeId.equalsIgnoreCase("")) {
            Toast.makeText(this, "Select Meeting Frequency first....", Toast.LENGTH_SHORT).show();
        } else {
            if (rfReturnedRadioStatus.equalsIgnoreCase("yes")) {
                if (rfReturnedId.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Select RF returned yes/no..", Toast.LENGTH_SHORT).show();

                } else if (cifReturnedId.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Select CIF returned yes/no..", Toast.LENGTH_SHORT).show();
                } else {
                    commonValidation();
                }
            } else {
                if (cifReturnedId.equalsIgnoreCase("")) {
                    Toast.makeText(this, "Select CIF returned yes/no..", Toast.LENGTH_SHORT).show();
                } else {
                    commonValidation();
                }
            }
        }
    }

    public void commonValidation() {
        if (shgLeaderCode.isEmpty()) {
            Toast.makeText(this, "Select President first.. ", Toast.LENGTH_SHORT).show();
        } else if (shgSecretaryCode.isEmpty()) {
            Toast.makeText(this, "Select Secretary first", Toast.LENGTH_SHORT).show();
        } else if (shgTressureCode.isEmpty()) {
            Toast.makeText(this, "Select Tressure first", Toast.LENGTH_SHORT).show();
        } else if (shgBookkeeperCode.isEmpty()) {
            Toast.makeText(this, "Select Book keeper first", Toast.LENGTH_SHORT).show();
        }else if (catValue.isEmpty()) {
            Toast.makeText(this, "Select Category", Toast.LENGTH_SHORT).show();
        }else if (subCatValue.isEmpty()) {
            Toast.makeText(this, "Select Sub-Category", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data Save....", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.saveSettingBtn)
    public void saveAllShgSetting() {

    }

    @OnClick(R.id.saveFormDataBtn)
    public void saveFormData(){
        valiDateAllData();
    }

    @OnClick(R.id.addSavingBtn)
    public void addShgSaving(){
        appSharedPreferences.setSettingScreenStatus("1");
        appUtils.makeIntent(ShgSeetingCommonActivity.this, ShgSeetingFormsActivity.class, true);
    }

}