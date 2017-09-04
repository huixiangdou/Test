package com.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.R;
import com.test.moel.Material;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/25.
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialHolder> {
    private ArrayList<Material> materialArrayList;

    public MaterialAdapter(ArrayList<Material> materialArrayList) {
        this.materialArrayList = materialArrayList;
    }

    @Override
    public MaterialHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.material_item,parent,false);
        MaterialHolder materialHolder = new MaterialHolder(view);
        return materialHolder;
    }

    @Override
    public void onBindViewHolder(MaterialHolder holder, int position) {
        Material material = materialArrayList.get(position);
        holder.mname.setText(material.getMname());
        holder.amount.setText(material.getAmount());
    }

    @Override
    public int getItemCount() {
        return materialArrayList.size();
    }

    public static class MaterialHolder extends RecyclerView.ViewHolder {
        private TextView amount;
        private TextView mname;
        public MaterialHolder(View itemView) {
            super(itemView);
            amount = itemView.findViewById(R.id.material_amount_tv);
            mname = itemView.findViewById(R.id.material_mname_tv);
        }
    }
}
