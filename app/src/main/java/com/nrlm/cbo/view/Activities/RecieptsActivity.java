package com.nrlm.cbo.view.Activities;

import android.content.Context;
import android.os.Bundle;

import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.datamodels.entitydatamodels.savings.ShgEntityDataModel;
import com.nrlm.cbo.database.room.repositories.ShgDataRepo;
import com.nrlm.cbo.view.adapters.ShgDetailsReceiptsAdapter;
import com.nrlm.cbo.database.room.entity.Shg;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
import com.nrlm.cbo.view.adapters.ShgDetailsSavingAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecieptsActivity extends AppCompatActivity {
    @BindView(R.id.title)
    TextView title_activityTV;

  /*  @BindView(R.id.expandedImage)
    ImageView expandedImageIV;*/

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.reciept_Shg_RV)
    RecyclerView shgDetailsRV;

/*    @BindView(R.id.shg_member_savingRV)
    RecyclerView shg_member_savingRV;*/

    public Context context;
    private ShgDetailsReceiptsAdapter shgDetailsReceiptsAdapter;
    private ShgDataRepo shgDataRepo;
    private List<ShgEntityDataModel> shgEntityDataModelList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciepts);
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
                shgDetailsReceiptsAdapter = new ShgDetailsReceiptsAdapter(context, shgEntityDataModelList);
                shgDetailsRV.setLayoutManager(new LinearLayoutManager(context));
                shgDetailsRV.setAdapter(shgDetailsReceiptsAdapter);
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
                shgDetailsReceiptsAdapter.getFilter().filter(newText);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        AppUtils.getInstance().makeIntent(this,HomeActivity.class,true);
        super.onBackPressed();
    }
}