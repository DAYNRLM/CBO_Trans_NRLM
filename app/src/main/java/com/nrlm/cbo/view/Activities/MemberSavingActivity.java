package com.nrlm.cbo.view.Activities;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.Utils.PreferenceFactory;
import com.nrlm.cbo.Utils.PrefrenceManager;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.MemberInfoAndSavingsEntityDataModel;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgSavingInfoEntityDataModel;
import com.nrlm.cbo.database.room.entities.ShgSettingSavingFromMemberEntity;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.database.room.repositories.ShgSettingSavingFromMemberRepo;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.adapters.MemberSavingDetailsAdapter;
import com.nrlm.cbo.database.room.entity.Shg;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.nrlm.cbo.R;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
public class MemberSavingActivity extends AppCompatActivity {

    @BindView(R.id.shg_nameTV)
    TextView shg_nameTV;

    @BindView(R.id.shg_codeTV)
    TextView shg_codeTV;

    @BindView(R.id.last_meeting_dateTV)
    TextView last_meeting_dateTV;

    @BindView(R.id.meeting_noTV)
    TextView meeting_noTV;

    @BindView(R.id.cash_book_noTV)
    TextView cash_book_noTV;

    @BindView(R.id.cash_book_page_noTV)
    EditText cash_book_page_noTV;

    @BindView(R.id.submitTV)
    TextView submitTV;

    @BindView(R.id.backTV)
    TextView backTV;

    @BindView(R.id.no_member_foundTV)
    TextView no_member_foundTV;


    @BindView(R.id.member_savingRV)
    RecyclerView member_savingRV;

    private Context context;
    private MemberSavingDetailsAdapter memberSavingDetailsAdapter;
    private String selectedShgCode,selectedEntityCode,cashBookPageNo;
    private ShgDataRepo shgDataRepo;
    private ShgSavingInfoEntityDataModel shgSavingInfoEntityDataModel;
    private ShgmemberRepo shgmemberRepo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_saving);
        ButterKnife.bind(this);
        context=this;
        shgmemberRepo=new ShgmemberRepo(getApplication());
        shgDataRepo= new ShgDataRepo(getApplication());
        selectedShgCode=PreferenceFactory.getInstance().getSharedPrefrencesData(PrefrenceManager.getPrefShgCode(),context);
        selectedEntityCode=PreferenceFactory.getInstance().getSharedPrefrencesData(PrefrenceManager.getPrefEntityCode(),context);
        AppUtils.getInstance().showLog("selectedEntityCode"+selectedEntityCode+"selectedShgCode"+selectedShgCode,MemberSavingActivity.class);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        try {
            shgSavingInfoEntityDataModel=shgDataRepo.getShgSavingInfo(selectedEntityCode,selectedShgCode);
            if (shgSavingInfoEntityDataModel!=null) {
                AppUtils.getInstance().showLog("SavingMemberInfo="+shgSavingInfoEntityDataModel.getShgName()+
                        ""+shgSavingInfoEntityDataModel.getShgCode()+shgSavingInfoEntityDataModel.getLastMeetingDate()
                        +"Mno="+shgSavingInfoEntityDataModel.getLastMeetingNo(),MemberSavingActivity.class);
                shg_nameTV.setText(shgSavingInfoEntityDataModel.getShgName());
                shg_codeTV.setText(shgSavingInfoEntityDataModel.getShgCode());
                last_meeting_dateTV.setText(shgSavingInfoEntityDataModel.getLastMeetingDate());
                meeting_noTV.setText(shgSavingInfoEntityDataModel.getLastMeetingNo());
                cash_book_noTV.setText("10");

                cashBookPageNo="10";

                List<MemberInfoAndSavingsEntityDataModel> memberInfoList= shgmemberRepo.getMemberInfo(selectedEntityCode,selectedShgCode);
                cash_book_page_noTV.setText(cashBookPageNo);
                callMemberSavingDetailsAdapter(memberInfoList);

                cash_book_page_noTV.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus){
                            cash_book_page_noTV.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                }
                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    cashBookPageNo=String.valueOf(s);
                                    callMemberSavingDetailsAdapter(memberInfoList);
                                }
                                @Override
                                public void afterTextChanged(Editable s) {

                                }
                            });
                        }
                    }
                });
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        backTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUtils.getInstance().makeIntent(context,SavingActivity.class,false);
            }
        });

        submitTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Data Saved Successfully.",Toast.LENGTH_LONG).show();
                AppUtils.getInstance().makeIntent(context,SavingActivity.class,false);
            }
        });

    }

    private void callMemberSavingDetailsAdapter(List<MemberInfoAndSavingsEntityDataModel> memberInfoList){
        if (selectedShgCode.trim().equalsIgnoreCase(shgSavingInfoEntityDataModel.getShgCode().trim())){

                   /* ShgSettingSavingFromMemberEntity shgSettingSavingFromMemberEntity=new ShgSettingSavingFromMemberEntity();
                    shgSettingSavingFromMemberEntity.shg_code=selectedShgCode;
                    shgSettingSavingFromMemberEntity.saving_type="1";
                    shgSettingSavingFromMemberEntity.amount="1";
                    shgSettingSavingFromMemberEntity.roi="10";

                   new ShgSettingSavingFromMemberRepo(getApplication()).insert(shgSettingSavingFromMemberEntity);*/

            /*fetch member data and savings */
            //getMemberSavingsInfo();

            if (memberInfoList.size()>0){
                member_savingRV.setVisibility(View.VISIBLE);
                no_member_foundTV.setVisibility(View.GONE);
                memberSavingDetailsAdapter = new MemberSavingDetailsAdapter(context,getApplication(),selectedShgCode,cashBookPageNo,memberInfoList);
                member_savingRV.setLayoutManager(new LinearLayoutManager(context));
                member_savingRV.setAdapter(memberSavingDetailsAdapter);
            }else {
                member_savingRV.setVisibility(View.GONE);
                no_member_foundTV.setVisibility(View.VISIBLE);
            }
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reciepts,menu);
        MenuItem menuItem= (MenuItem) menu.findItem(R.id.search_menu);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_by_membername));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                memberSavingDetailsAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppUtils.getInstance().makeIntent(this,SavingActivity.class,false);
    }
}