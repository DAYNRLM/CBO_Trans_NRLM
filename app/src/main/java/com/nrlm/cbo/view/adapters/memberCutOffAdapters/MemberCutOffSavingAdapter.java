package com.nrlm.cbo.view.adapters.memberCutOffAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.nrlm.cbo.R;
import com.nrlm.cbo.database.room.entities.MasterMemberSavingEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MemberCutOffSavingAdapter extends RecyclerView.Adapter<MemberCutOffSavingAdapter.MyViewHolder> {
    Context context;
    ArrayList<String> masterMemberSavingType;
    MaterialButton addBt;
    ArrayAdapter<String> savingType;
    String selectedSavingType;
    List<ArrayList<String>> outerList=new ArrayList<ArrayList<String>>();
    int count=1;
    public MemberCutOffSavingAdapter(Context context, ArrayList<String> masterMemberSavingType, MaterialButton addBt)
    {
        this.context=context;
        //this.masterMemberSavingType=masterMemberSavingType;
        this.addBt=addBt;
        this.outerList.add(masterMemberSavingType);
    }
    @NonNull
    @Override
    public MemberCutOffSavingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_cutoff_saving_custom_lay,parent,false);

        return new MemberCutOffSavingAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberCutOffSavingAdapter.MyViewHolder holder, int position) {
        for(int i=0;i<outerList.size();i++) {
            for(int j=0;j<outerList.get(i).size();j++) {
              //  savingType = new ArrayAdapter<>(context, R.layout.spinner_textview, masterMemberSavingType);
                savingType = new ArrayAdapter<>(context, R.layout.spinner_textview, outerList.get(i));
                holder.savingtypeSp.setAdapter(savingType);
                //holder.sno.setText("" + count);
            }
        }

  holder.savingtypeSp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectedSavingType=outerList.get(position).get(position);
      }
  });
        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                removeSavingType(selectedSavingType);
               // refreshAdapter();
               // savingType=new ArrayAdapter<>(context,R.layout.spinner_textview,masterMemberSavingType);
              //  holder.savingtypeSp.setAdapter(savingType);
            }
        });
    }
    private void refreshAdapter() {
              notifyDataSetChanged();
    }

    private void removeSavingType(String selectedSavingType) {
        this.selectedSavingType=selectedSavingType;
        ArrayList<String> tempData=new ArrayList<>();
        for(int i=0;i<outerList.size();i++) {
            tempData=outerList.get(i);

            for (int j = 0; j < outerList.get(i).size(); j++) {
                if (selectedSavingType.equalsIgnoreCase(tempData.get(j))) {
                    tempData.remove(j);

                }
            }
        }
        this.outerList.add(tempData);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return outerList.size();
    }
    public class MyViewHolder  extends RecyclerView.ViewHolder{
       // @BindView(R.id.snoTv)
      //  TextView sno;
        @BindView(R.id.savingtypeSp)
        AutoCompleteTextView savingtypeSp;
        @BindView(R.id.saving_amount)
        TextInputEditText savingamount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
