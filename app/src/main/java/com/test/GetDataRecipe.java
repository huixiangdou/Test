package com.test;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.test.Interface.GetDataInterface;
import com.test.moel.Material;
import com.test.moel.Recipe;
import com.test.moel.Process;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/28.
 */

public class GetDataRecipe {

    private GetDataInterface getDataInterface;
    private ProgressBar progressBar;
    private Context context;
    private ArrayList<Recipe> recipes_ArrayList;
    private ArrayList<Process> processArrayList;
    private ArrayList<Material> materialArrayList;

    public GetDataRecipe(ProgressBar progressBar, Context context, GetDataInterface getDataInterface) {
        this.progressBar = progressBar;
        this.context = context;
        this.getDataInterface = getDataInterface;
    }

    public GetDataRecipe(GetDataInterface getDataInterface, Context context) {
        this.getDataInterface = getDataInterface;
        this.context = context;
    }

    public void getDatas(String sorc, String keyword, String value,int i) {
        OkGo.<String>get("http://jisusrecipe.market.alicloudapi.com/recipe/"+sorc)
                .tag(this)
                .params(keyword, value)
                .params("num", "8")
                .params("start", i)
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
//                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        JsonParser jp = new JsonParser();
                        JsonElement jsone = jp.parse(body);
                        JsonObject jo = jsone.getAsJsonObject();
                        JsonArray ja = jo.getAsJsonObject("result").getAsJsonArray("list");
                        recipes_ArrayList = new ArrayList<Recipe>();
                        for (int i = 0; i < ja.size(); i++){
                            JsonObject jsonObject = (JsonObject) ja.get(i);
                            JsonArray processJsonArray = jsonObject.getAsJsonArray("process");
                            JsonArray materialJsonArray = jsonObject.getAsJsonArray("material");

                            processArrayList = new ArrayList<Process>();
                            materialArrayList = new ArrayList<Material>();

                            for (int p =0; p < processJsonArray.size(); p++){
                                JsonElement process_element = (JsonObject) processJsonArray.get(p);
                                String pcontent = process_element.getAsJsonObject().get("pcontent").getAsString();
                                String pic = process_element.getAsJsonObject().get("pic").getAsString();
                                processArrayList.add(new Process(pcontent,pic));
                            }

                            for (int m =0; m < materialJsonArray.size(); m++){
                                JsonElement material_element = (JsonObject) materialJsonArray.get(m);
                                String amount = material_element.getAsJsonObject().get("amount").getAsString();
                                String mname = material_element.getAsJsonObject().get("mname").getAsString();
                                materialArrayList.add(new Material(amount,mname));
                            }

                            String preparetime = jsonObject.get("preparetime").getAsString();
                            String name = jsonObject.get("name").getAsString();
                            String id = jsonObject.get("id").getAsString();
                            String pic = jsonObject.get("pic").getAsString();
                            String tag = jsonObject.get("tag").getAsString();
                            String peoplenum = jsonObject.get("peoplenum").getAsString();
                            String content = jsonObject.get("content").getAsString();
                            String cookingtime = jsonObject.get("cookingtime").getAsString();
                            recipes_ArrayList.add(new Recipe(processArrayList,materialArrayList,name,id,pic,tag,peoplenum,content,cookingtime));
                            Log.d("titletitle",processArrayList.get(0).toString() + "");
                        }
                        getDataInterface.onSucess(recipes_ArrayList);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
//                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }
}
