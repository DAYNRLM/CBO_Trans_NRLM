package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nrlm.cbo.R;
import com.nrlm.cbo.database.room.entities.ShgMemberDataEntity;
import com.nrlm.cbo.database.room.entity.ShgMember;

import java.util.List;

public class ShgMemberVerificationAdapter extends RecyclerView.Adapter<ShgMemberVerificationAdapter.MyViewHolder> {
    List<ShgMemberDataEntity> shgMembersDataListItem;
    Context context;

    public ShgMemberVerificationAdapter(List<ShgMemberDataEntity> shgMembersDataListItem, Context context) {
        this.shgMembersDataListItem = shgMembersDataListItem;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_verification_custom_layout, parent, false);
        return new ShgMemberVerificationAdapter.MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name =shgMembersDataListItem.get(position).memberName+"\n Line number 2 \n Line number 3";

        String details ="<h1>"+shgMembersDataListItem.get(position).memberName+ "</h1>"+
        "<p><u>FatherName/HusbandName:</u></p>"+
                "<br />"+shgMembersDataListItem.get(position).shgMemberCode;

        String finalData ="<b>"+shgMembersDataListItem.get(position).memberName+"</b>"+
                " Member Code ("+"<b>"+shgMembersDataListItem.get(position).shgMemberCode+"</b>"+")"  +"<br />"+
                "<u>"+
                "<span style=\"color:#4fc3f7\">WO/DO-: </span>"+
                "</u>"+
                "Sumit Sing phogat";

        String memberDetail= "<b> Leader-:</b>"+
                "Member"+"<br />"+
                "<b> Gender-:</b>"+
                "Female"+"<br />"+
                "<b>Social Category-:</b>"+
                "OBC"+"<br />"+
                "<b>PIP category-:</b>"+
                "Poor";
        String subCategory= "<b> Disability-:</b>"+
                "No"+"<br />"+
                "<b> Religion-:</b>"+
                "Hindu"+"<br />"+
                "<b>APL/BPL-:</b>"+
                "APL";

        String pipCategory= "<b>PIP category-:</b>"+
                "Poor";

        //+"<span style=\"color:red;\"> --- </span>"+
        //"FatherName/HusbandName:
       /* +shgMembersDataListItem.get(position).getShgMemberCode()+"<br />" +
                "<p style=\"font-size:11px;\">This is a paragraph.</p>";*/

       /* String memberDetail= "<p> <b> Leader-:</b>"+
                "Member"+"<br />"+
                "<b> Gender-:</b>"+
                "Female"+"<br />"+
                "<b>Social Category-:</b>"+
                "OBC"+
                "</p>";*/

        String s = "<p>Add the <span class=\"ingredient\">basil</span>, <span class=\"ingredient\">pine nuts</span> and <span class=\"ingredient\">garlic</span> to a blender and blend into a paste.</p>";



        String name5 =" <![CDATA[\n" +
                "        <h1>Main Title</h1>\n" +
                "        <h2>A sub-title</h2>\n" +
                "        <p>This is some html. Look, here\\'s an <u>underline</u>.</p>\n" +
                "        <p>Look, this is <em>emphasized.</em> And here\\'s some <b>bold</b>.</p>\n" +
                "        <p>This is a UL list:\n" +
                "        <ul>\n" +
                "        <li>One</li>\n" +
                "        <li>Two</li>\n" +
                "        <li>Three</li>\n" +
                "        </ul>\n" +
                "        <p>This is an OL list:\n" +
                "        <ol>\n" +
                "        <li>One</li>\n" +
                "        <li>Two</li>\n" +
                "        <li>Three</li>\n" +
                "        </ol>\n" +
                "        ]]>";
        String name6 =" <h1>Main Title</h1>" +
                "<h2>A sub-title</h2>" +
                "<p>This is some html. Look, here\'s an" +
                " <u>underline</u>.</p>";
        String name7="<html>" +
                "<head>" +
                "<style>" +
                "  h1 {color:#73cbc3;}" +
                "  p {color:#f44336;}" +
                "</style>" +
                "</head>" +
                "<body>" +
                "" +
                "<h1>A heading</h1>" +
                "<p>A paragraph.</p>" +
                "" +
                "</body>" +
                "</html>";
        String name8="<html>" +
                "<body>" +
                "" +
                "<h1 style=\"color:blue;\">This is a header</h1>" +
                "<p style=\"color:green;\">This is a paragraph.</p>" +
                "" +
                "</body>" +
                "</html>";

        holder.shgMember_Tv.setText(Html.fromHtml(finalData));
        holder.memberDetail_Tv.setText(Html.fromHtml(memberDetail));
        holder.memberSubCategory_Tv.setText(Html.fromHtml(subCategory));


    }

    @Override
    public int getItemCount() {
        return shgMembersDataListItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shgMember_Tv,memberDetail_Tv,memberSubCategory_Tv;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shgMember_Tv=itemView.findViewById(R.id.shgMember_Tv);
            memberDetail_Tv=itemView.findViewById(R.id.memberDetail_Tv);
            memberSubCategory_Tv=itemView.findViewById(R.id.memberSubCategory_Tv);

        }
    }
}
