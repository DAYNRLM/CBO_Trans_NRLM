package com.nrlm.cbo.view.Activities;

import android.content.Context;
import android.os.Bundle;

import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgEntityDataModel;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.view.adapters.ShgDetailsPaymentAdapter;
import com.nrlm.cbo.database.room.entity.Shg;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nrlm.cbo.R;
import com.nrlm.cbo.view.adapters.ShgDetailsReceiptsAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaymentActivity extends AppCompatActivity {

    @BindView(R.id.shg_details_paymentRV)
    RecyclerView shg_details_paymentRV;

    @BindView(R.id.title)
    TextView title_activityTV;

    private ShgDetailsPaymentAdapter shgDetailsPaymentAdapter;
    private Context context;

    private ShgDataRepo shgDataRepo;
    private List<ShgEntityDataModel> shgEntityDataModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ButterKnife.bind(this);

        context=this;
        shgDataRepo=new ShgDataRepo(getApplication());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        AppBarLayout mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    title_activityTV.setVisibility(View.GONE);
                } else if (isShow) {
                    isShow = false;
                    title_activityTV.setVisibility(View.VISIBLE);

                }
            }
        });
        try {
            shgEntityDataModelList = shgDataRepo.getAllShgInfo();

            if (shgEntityDataModelList.size()>0){

                Toast.makeText(this, "" + shgEntityDataModelList.size(), Toast.LENGTH_LONG).show();

                shgDetailsPaymentAdapter = new ShgDetailsPaymentAdapter(context, shgEntityDataModelList);
                shg_details_paymentRV.setLayoutManager(new LinearLayoutManager(context));
                shg_details_paymentRV.setAdapter(shgDetailsPaymentAdapter);

            }else {
                Toast.makeText(this, "size is zero", Toast.LENGTH_LONG).show();
            }

        } catch (ExecutionException e) {
            Toast.makeText(this, "ExecutionException"+e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (InterruptedException e) {
            Toast.makeText(this, "InterruptedException"+e.toString(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }catch (IndexOutOfBoundsException indexOutOfBoundsException){
            Toast.makeText(this, "IndexOutOfBoundsException"+indexOutOfBoundsException.toString(), Toast.LENGTH_LONG).show();
            indexOutOfBoundsException.printStackTrace();
        }


/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reciepts,menu);
        MenuItem menuItem= (MenuItem) menu.findItem(R.id.search_menu);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getString(R.string.search_by_shgname));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                shgDetailsPaymentAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        AppUtils.getInstance().makeIntent(this,HomeActivity.class,true);
    }
}