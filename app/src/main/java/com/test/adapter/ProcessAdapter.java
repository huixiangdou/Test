package com.test.adapter;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.model.Response;
import com.test.R;
import com.test.moel.Process;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/25.
 */

public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.ProcessHolder> {
    private ArrayList<Process> processArrayList;

    public ProcessAdapter(ArrayList<Process> processArrayList) {
        this.processArrayList = processArrayList;
    }

    @Override
    public ProcessHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.process_item,parent,false);
        ProcessHolder processHolder = new ProcessHolder(view);
        return processHolder;
    }

    @Override
    public void onBindViewHolder(final ProcessHolder holder, int position) {
        Process process = processArrayList.get(position);
        holder.pcotent.setText(process.getPcontent());

        OkGo.<Bitmap>get(process.getPic())
                .tag(this)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        holder.pic.setImageBitmap(response.body());
                    }
                });
    }

    @Override
    public int getItemCount() {
        return processArrayList.size();
    }
     public static class ProcessHolder extends RecyclerView.ViewHolder {
         private TextView pcotent;
         private ImageView pic;
         public ProcessHolder(View itemView) {
             super(itemView);

             pcotent = itemView.findViewById(R.id.process_pcontent_tv);
             pic = itemView.findViewById(R.id.process_pic_image);
         }
     }
}
