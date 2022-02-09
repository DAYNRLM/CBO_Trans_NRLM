package com.nrlm.cbo.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.repositories.ShgmemberRepo;
import com.nrlm.cbo.view.Activities.AttendanceSelectLocationActivity;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.model.dummyModels.ShgMemberPojo;

import java.util.ArrayList;
import java.util.List;

import spencerstudios.com.ezdialoglib.EZDialog;
import spencerstudios.com.ezdialoglib.EZDialogListener;

public class ShgMemberPojoAdapter extends RecyclerView.Adapter<ShgMemberPojoAdapter.ViewHoalder> {

    Context context;
    TextView memberNametv, memberCodetv,countTv,submitBt;
    CheckBox memberCheckBox, selectAll, unselectAll;
    String shgCode;
    boolean[] shgMemberDataValue;
    List<ShgMemberPojo> shgmemberdataList;
    List<ShgMemberPojo> selectedMembers;
    public ShgMemberPojoAdapter(Context context, List<ShgMemberPojo> shgmemberdataList, String shgCode, CheckBox selectAll,TextView count,TextView submitBt) {
        this.context = context;
        this.selectAll = selectAll;
        this.shgCode = shgCode;
        this.countTv=count;
        this.shgMemberDataValue = shgMemberDataValue;
        this.shgmemberdataList = shgmemberdataList;
        this.selectedMembers=new ArrayList<>();
        this.submitBt=submitBt;
    }

    @NonNull
    @Override
    public ShgMemberPojoAdapter.ViewHoalder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.common_member_custom_layout, parent, false);
        return new ViewHoalder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShgMemberPojoAdapter.ViewHoalder holder, int position) {    //Rendering member details over the recyclerview
        holder.memberNametv.setText(shgmemberdataList.get(position).getShgMemberName());
        holder.memberCodetv.setText(shgmemberdataList.get(position).getShgMemberCode());
        holder.memberCheckBox.setTag(shgmemberdataList.get(position).getPosition());
        holder.memberCheckBox.setChecked(shgmemberdataList.get(position).isMemberCb());
        holder.memberCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox=(CheckBox) v;
                  if (checkBox.isChecked())
                  {
                      selectedMembers.add(shgmemberdataList.get(position));
                      countTv.setText(""+selectedMembers.size());
                      AppUtils.getInstance().showLog("Added"+shgmemberdataList.get(position).getShgMemberName(),ShgMemberPojoAdapter.class);
                  }else {

                     // selectedMembers.remove(selectedMembers.remove(shgmemberdataList.get(position)));
                      selectedMembers.remove(shgmemberdataList.get(position));
                      countTv.setText(""+selectedMembers.size());
                      AppUtils.getInstance().showLog("Deleted"+shgmemberdataList.get(position).getShgMemberCode(),ShgMemberPojoAdapter.class);
                  }

            }
        });
        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.select_all_CB);
                if (checkBox.isChecked()) {
                    refreshAdpterSelectAll();                 //this method will invoked when user click on selectAll checkbox
                } else {
                    refreshAdpterDeselectAll();               //this method will call when user uncheck the checkbox as above mentioned
                }
            }
        });

        submitBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    double selectedMember=selectedMembers.size();
                    double totalMember=shgmemberdataList.size();

                    int selectionPercentage= (int) Math.ceil((selectedMember/totalMember*100));

                    if(selectionPercentage>=60)
                    {
                        new EZDialog.Builder(context)
                                .setTitle("Alert.")
                                .setMessage("You have successfully marked your attendance")
                                .setPositiveBtnText("ok")
                                .setCancelableOnTouchOutside(false)
                                .OnPositiveClicked(new EZDialogListener() {
                                    @Override
                                    public void OnClick() {
                                         AppUtils.getInstance().makeIntent(context, AttendanceSelectLocationActivity.class,true);

                                    }
                                })
                                .build();

                    }else
                    {
                        new EZDialog.Builder(context)
                                .setTitle("Alert.")
                                .setMessage("Selection of members should be 60% or more")
                                .setPositiveBtnText("ok")
                                .setCancelableOnTouchOutside(false)
                                .OnPositiveClicked(new EZDialogListener() {
                                    @Override
                                    public void OnClick() {


                                    }
                                })
                                .build();
                    }

                }catch (Exception e)
                {

                    AppUtils.getInstance().showLog(""+e,ShgMemberPojoAdapter.class);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return shgmemberdataList.size();
    }

    public class ViewHoalder extends RecyclerView.ViewHolder {
        TextView memberNametv, memberCodetv;
        CheckBox memberCheckBox;

        public ViewHoalder(@NonNull View itemView) {
            super(itemView);
            memberNametv = itemView.findViewById(R.id.memberNametv);
            memberCodetv = itemView.findViewById(R.id.memberCodetv);
            memberCheckBox = (CheckBox) itemView.findViewById(R.id.memberCheckBox);
        }
    }

    private void refreshAdpterSelectAll() {                        //Work on the select all check box it will select all the members
      //  List<ShgMember> shgmemberdataList = AllDataList.getInstance(context).getmemberWithShg(shgCode);
        List<ShgMemberDataEntity> shgmemberdataList = new ShgmemberRepo(((Activity)context).getApplication()).getAllMemberData(shgCode);
        int count = 0;
        List<ShgMemberPojo> shgMemberPojos = new ArrayList<>();
        for (ShgMemberDataEntity shgMember : shgmemberdataList) {
            ShgMemberPojo shgMemberPojo = new ShgMemberPojo();
            shgMemberPojo.setShgCode(shgMember.shgCode);
            shgMemberPojo.setShgMemberCode(shgMember.shgMemberCode);
            shgMemberPojo.setShgMemberName(shgMember.memberName);
            shgMemberPojo.setMemberCb(true);
            shgMemberPojo.setPosition(count);
            count++;
            shgMemberPojos.add(shgMemberPojo);
            selectedMembers.add(shgMemberPojo);
        }
        countTv.setText(""+selectedMembers.size());
        this.shgmemberdataList = shgMemberPojos;
        notifyDataSetChanged();
    }

    private void refreshAdpterDeselectAll() {             // work on the deselect all the member for the attendance on checkbox click
        //List<ShgMember> shgmemberdataList = AllDataList.getInstance(context).getmemberWithShg(shgCode);
        List<ShgMemberDataEntity> shgmemberdataList = new ShgmemberRepo(((Activity)context).getApplication()).getAllMemberData(shgCode);
        int count = 0;
        List<ShgMemberPojo> shgMemberPojos = new ArrayList<>();
        for (ShgMemberDataEntity shgMember : shgmemberdataList) {
            ShgMemberPojo shgMemberPojo = new ShgMemberPojo();
            shgMemberPojo.setShgCode(shgMember.shgCode);
            shgMemberPojo.setShgMemberCode(shgMember.shgMemberCode);
            shgMemberPojo.setShgMemberName(shgMember.memberName);
            shgMemberPojo.setMemberCb(false);
            shgMemberPojo.setPosition(count);
            count++;
            shgMemberPojos.add(shgMemberPojo);
        }
        selectedMembers.clear();
        countTv.setText(""+selectedMembers.size());

        this.shgmemberdataList = shgMemberPojos;
        notifyDataSetChanged();

    }

}
