package com.test.adapter;

import android.content.Context;
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
import com.test.moel.Recipe;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/23.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> implements View.OnClickListener {
    private ArrayList<Recipe> recipeArrayList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public RecipeAdapter(ArrayList arrayList) {
        this.recipeArrayList = arrayList;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recipe_item,parent,false);
        RecipeHolder recipeHolder = new RecipeHolder(view);
        view.setOnClickListener(this);
        return recipeHolder;
    }

    @Override
    public void onBindViewHolder(final RecipeHolder holder, int position) {
        Recipe recipe = recipeArrayList.get(position);
        if (recipe.getPic().isEmpty()){
            recipe.setPic("http://api.jisuapi.com/recipes/upload/201708/23170003_38771.jpg");
        }

        OkGo.<Bitmap>get(recipe.getPic())
                .tag(this)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Response<Bitmap> response) {
                        holder.pic.setImageBitmap(response.body());
                    }
                });

        holder.cookingtime.setText(recipe.getCookingtime());
        holder.name.setText(recipe.getName());
        holder.peoplenum.setText(recipe.getPeoplenum());

        holder.itemView.setTag(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeArrayList.size();
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewItemClickListener != null){
            onRecyclerViewItemClickListener.onItemClick(view,(Recipe)view.getTag());
        }
    }

    public static class RecipeHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView peoplenum;
        private TextView cookingtime;
        private ImageView pic;
        public RecipeHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.recipe_name_tv);
            peoplenum = itemView.findViewById(R.id.recipe_peoplenum_tv);
            cookingtime = itemView.findViewById(R.id.recipe_cookingtime_tv);
            pic = itemView.findViewById(R.id.recipe_pic_image);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public void clear(){
        recipeArrayList.clear();
        notifyDataSetChanged();
    }
}
