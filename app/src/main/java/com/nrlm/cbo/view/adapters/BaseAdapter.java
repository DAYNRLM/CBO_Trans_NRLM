package com.nrlm.cbo.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.MyViewHolder> {

    public int layout_id;
    protected List<?> dataList = new ArrayList<>();
    Context BASE_CONTEXT;
    public View itemview;

    public BaseAdapter(Context context) {
        this.BASE_CONTEXT = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout_id, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int position) {
        onBindViewHold(position, dataList.get(position));
        onBindViewWithHolder(position, dataList.get(position), viewHolder);
    }

    public abstract View getView(View view);

    @Override
    public int getItemCount() {
        return dataList.size() == 0 ? 0 : dataList.size();
    }

    public abstract void onBindViewHold(int position, Object itemView);

    public abstract void onBindViewWithHolder(int position, Object itemView, @NonNull MyViewHolder viewHolder);

    class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            getView(itemView);
        }

    }

    public <T extends View> T bind(int id) {
        return itemview.findViewById(id);
    }

}

