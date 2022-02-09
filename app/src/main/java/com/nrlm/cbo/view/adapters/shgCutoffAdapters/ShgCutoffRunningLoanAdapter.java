package com.nrlm.cbo.view.adapters.shgCutoffAdapters;

import android.app.Application;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.nrlm.cbo.R;
import com.nrlm.cbo.Utils.AppSharedPreferences;
import com.nrlm.cbo.Utils.AppUtils;
import com.nrlm.cbo.database.room.entities.ShgLoansEntity;
import com.nrlm.cbo.view.interfaces.OnClickListnerForRunningInsurance;

import java.util.List;

public class ShgCutoffRunningLoanAdapter extends RecyclerView.Adapter<ShgCutoffRunningLoanAdapter.MyViewHolder> {
    List<ShgLoansEntity> shgLoansEntities;
    Context context;
    AppUtils appUtils;
    AppSharedPreferences appSharedPreferences;
    OnClickListnerForRunningInsurance mClickListner = null;

    public ShgCutoffRunningLoanAdapter(List<ShgLoansEntity> shgLoansEntities, Context context, Application application, OnClickListnerForRunningInsurance mClickListner ) {
        this.shgLoansEntities = shgLoansEntities;
        this.context = context;
        this.mClickListner =mClickListner;
        appUtils =AppUtils.getInstance();
        appSharedPreferences =AppSharedPreferences.getsharedprefInstances(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shg_cutoff_running_loan_adapter_view,parent,false);
        return new ShgCutoffRunningLoanAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String loanFrom =shgLoansEntities.get(position).loan_from_code;
        String finalData="";
        String bankDate ="";
        String intrestData ="";
        String overDueData ="";

        if(loanFrom.equalsIgnoreCase("SHG")){
            finalData= "<b>Loan From-: </b>  "+
                    shgLoansEntities.get(position).loan_from_code+
                    "<br />"+
                    "<b>Loan Type-:</b>  "+
                    shgLoansEntities.get(position).loan_type_code+
                    "<br />"+
                    "<b>Loan Number-: </b>  "+
                    shgLoansEntities.get(position).loan_Number_code;

            bankDate = "<b>Bank Name-:</b>  "+
                    shgLoansEntities.get(position).bank_name_code+
                    "<br />"+
                    "<b>Branch Name-:</b>  "+
                    shgLoansEntities.get(position).branch_name_code+
                    "<br />"+
                    "<b>IFSC Code-:</b>  "+
                    shgLoansEntities.get(position).bank_ifsc_code;

            intrestData="<b>Loan Sanction Date-:</b>  "+
                    shgLoansEntities.get(position).loan_sanction_date+
                    "<br />"+
                    "<b>Loan Sanction Amount-:</b>  "+
                    shgLoansEntities.get(position).loan_sanction_amount+
                    "<span style=\"color:#5898ad\"> &#8377; </span>"+
                    "<br />"+ "<br />"+
                    "<b>Loan Disburse Date-:</b>  "+
                    shgLoansEntities.get(position).loan_disburses_date+
                    "<br />"+
                    "<b>Loan Disburse Amount-:</b>  "+
                    shgLoansEntities.get(position).loan_disburses_amount+
                    "<span style=\"color:#5898ad\"> &#8377; </span>"+
                    "<br />"+ "<br />"+
                    "<b>Rate of Intrest(Yearly in %)-:</b>  "+
                    shgLoansEntities.get(position).loan_roi+
                    "<br />"+
                    "<b>Number of instalments-:</b>  "+
                    shgLoansEntities.get(position).number_of_instalment+
                    "<br />"+
                    "<b>Instalment Amount-:</b>  "+
                    shgLoansEntities.get(position).instalment_amount+
                    "<span style=\"color:#5898ad\"> &#8377; </span>"+
                    "<br />"+
                    "<b>Number of instalment Re-paid-:</b>  "+
                    shgLoansEntities.get(position).number_of_instalment_repaid;


            overDueData = "<b>Principal Paid-:</b>  "+
                    shgLoansEntities.get(position).principal_paid+
                    "<span style=\"color:#5898ad\"> &#8377; </span>"+
                    "<br />"+
                    "<b>Principal Overdue-:</b>  "+
                    shgLoansEntities.get(position).principal_overdue+
                    "<span style=\"color:#5898ad\"> &#8377; </span>"+
                    "<br />"+
                    "<b>Intrest Overdue-:</b>  "+
                    shgLoansEntities.get(position).intrest_overdue+
                    "<span style=\"color:#5898ad\"> &#8377; </span>"+
                    "<br />"+
                    "<b>Intrest Paid-:</b>  "+
                    shgLoansEntities.get(position).intrest_paid+
                    "<span style=\"color:#5898ad\"> &#8377; </span>";


        }else {

        }

        holder.loanDetailTv.setText(Html.fromHtml(finalData));
        holder.bankDetailTv.setText(Html.fromHtml(bankDate));
        holder.intrestDetailTv.setText(Html.fromHtml(intrestData));
        holder.overDueDetailTv.setText(Html.fromHtml(overDueData));


    }

    @Override
    public int getItemCount() {
        return shgLoansEntities.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView loanDetailTv,bankDetailTv,intrestDetailTv,overDueDetailTv;
        MaterialButton deleteBtn,editBtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            deleteBtn=itemView.findViewById(R.id.deleteBtn);
            editBtn=itemView.findViewById(R.id.editBtn);
            loanDetailTv=itemView.findViewById(R.id.loanDetailTv);
            bankDetailTv=itemView.findViewById(R.id.bankDetailTv);
            intrestDetailTv=itemView.findViewById(R.id.intrestDetailTv);
            overDueDetailTv=itemView.findViewById(R.id.overDueDetailTv);
        }
    }
}
