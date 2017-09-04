package com.test.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.R;
import com.test.moel.Channel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/29.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelViewHolder> implements View.OnClickListener{
    private ArrayList<Channel> channelArrayList;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;

    public ChannelAdapter(ArrayList<Channel> channelArrayList) {
        this.channelArrayList = channelArrayList;
    }

    @Override
    public ChannelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.channel_itme,parent,false);
        view.setOnClickListener(this);
        ChannelViewHolder channelViewHolder = new ChannelViewHolder(view);
        return channelViewHolder;
    }

    @Override
    public void onBindViewHolder(ChannelViewHolder holder, int position) {
        Channel channel = channelArrayList.get(position);
        holder.channel_name.setText(channel.getName());
        holder.itemView.setTag(channel);
    }

    @Override
    public int getItemCount() {
        return channelArrayList.size();
    }

    public static class ChannelViewHolder extends RecyclerView.ViewHolder {
        private TextView channel_name;
        public ChannelViewHolder(View itemView) {
            super(itemView);
            channel_name = itemView.findViewById(R.id.channel_name);
        }
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null){
            onRecyclerViewItemClickListener.onItemClick(view,(Channel)view.getTag());
        }
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener listener){
        this.onRecyclerViewItemClickListener = listener;
    }

}
