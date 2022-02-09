package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AllDataList;
import com.nrlm.cbo.database.room.entity.ShgMember;
import com.nrlm.cbo.model.dummyModels.ShgMemberPojo;

import java.util.ArrayList;
import java.util.List;

public class MemberAdapterForAttedance extends BaseAdapter {
    Context context;
    TextView memberNametv, memberCodetv;
    CheckBox memberCheckBox, selectAll, unselectAll;
    String shgCode;
    boolean[] shgMemberDataValue;
    List<ShgMember> data;


    public MemberAdapterForAttedance(Context context, List<ShgMemberPojo> shgmemberdataList, String shgCode, CheckBox selectAll, CheckBox unselectAll) {
        super(context);
        this.context = context;
        this.selectAll = selectAll;
        this.shgCode = shgCode;
        this.shgMemberDataValue = shgMemberDataValue;
        this.unselectAll = unselectAll;
        dataList = shgmemberdataList;
        // this.data=shgmemberdataList;
        layout_id = R.layout.common_member_custom_layout;
    }

    @Override
    public View getView(View view) {
        memberNametv = view.findViewById(R.id.memberNametv);
        memberCodetv = view.findViewById(R.id.memberCodetv);
        memberCheckBox = (CheckBox) view.findViewById(R.id.memberCheckBox);
        return view;
    }

    @Override
    public void onBindViewHold(int position, Object itemView) {

    }

    @Override
    public void onBindViewWithHolder(int position, Object itemView, @NonNull MyViewHolder viewHolder) {
        ShgMemberPojo ad = (ShgMemberPojo) itemView;
        memberNametv.setText(ad.getShgMemberName());
        memberCodetv.setText(ad.getShgMemberCode());
        memberCheckBox.setTag(ad.getPosition());

        memberCheckBox.setChecked(ad.isMemberCb());


        selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v.findViewById(R.id.select_all_CB);
                if (checkBox.isChecked()) {

                    refreshAdpter();


                } else {
                    refreshAdpter1();
                }


            }
        });


    }

    private void refreshAdpter() {
        List<ShgMember> shgmemberdataList = AllDataList.getInstance(context).getmemberWithShg(shgCode);
        int count = 0;
        List<ShgMemberPojo> shgMemberPojos = new ArrayList<>();
        for (ShgMember shgMember : shgmemberdataList) {
            ShgMemberPojo shgMemberPojo = new ShgMemberPojo();

            shgMemberPojo.setShgCode(shgMember.getShgCode());
            shgMemberPojo.setShgMemberCode(shgMember.getShgMemberCode());
            shgMemberPojo.setShgMemberName(shgMember.getShgMemberName());
            shgMemberPojo.setMemberCb(true);
            shgMemberPojo.setPosition(count);

            count++;
            shgMemberPojos.add(shgMemberPojo);

        }
        dataList.clear();
        dataList = shgMemberPojos;

        notifyDataSetChanged();

    }

    private void refreshAdpter1() {
        List<ShgMember> shgmemberdataList = AllDataList.getInstance(context).getmemberWithShg(shgCode);
        int count = 0;
        List<ShgMemberPojo> shgMemberPojos = new ArrayList<>();
        for (ShgMember shgMember : shgmemberdataList) {
            ShgMemberPojo shgMemberPojo = new ShgMemberPojo();

            shgMemberPojo.setShgCode(shgMember.getShgCode());
            shgMemberPojo.setShgMemberCode(shgMember.getShgMemberCode());
            shgMemberPojo.setShgMemberName(shgMember.getShgMemberName());
            shgMemberPojo.setMemberCb(false);
            shgMemberPojo.setPosition(count);

            count++;
            shgMemberPojos.add(shgMemberPojo);

        }
        dataList.clear();
        dataList = shgMemberPojos;

        notifyDataSetChanged();

    }

}
