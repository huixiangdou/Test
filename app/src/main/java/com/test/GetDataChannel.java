package com.test;

import android.content.Context;
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
import com.test.moel.Channel;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/29.
 */

public class GetDataChannel {
    private GetDataInterface getDataInterface;
    private ProgressBar progressBar;
    private Context context;
    private ArrayList<Channel> caipuClasses_ArrayList;

    public GetDataChannel(ProgressBar progressBar, Context context, GetDataInterface getDataInterface) {
        this.getDataInterface = getDataInterface;
        this.progressBar = progressBar;
        this.context = context;
    }

    public GetDataChannel(GetDataInterface getDataInterface, Context context) {
        this.getDataInterface = getDataInterface;
        this.context = context;
    }

    public void getDataClass(){
        OkGo.<String>get("http://jisusrecipe.market.alicloudapi.com/recipe/class")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        String body = response.body();
                        JsonParser jsonParser = new JsonParser();
                        JsonElement jsonElement = jsonParser.parse(body);
                        JsonObject jsonObject = jsonElement.getAsJsonObject();
                        JsonArray jsonArray = jsonObject.getAsJsonArray("result");
                        JsonArray jsonArray1 = jsonArray.get(0).getAsJsonObject().getAsJsonArray("list");

                        caipuClasses_ArrayList = new ArrayList<Channel>();

                        for (int i = 0; i<jsonArray1.size();i++){
                            JsonObject jsonObject1 = jsonArray1.get(i).getAsJsonObject();
                            String classid = jsonObject1.get("classid").getAsString();
                            String name = jsonObject1.get("name").getAsString();
                            String parentid = jsonObject1.get("parentid").getAsString();
                            caipuClasses_ArrayList.add(new Channel(classid,name,parentid));
                        }
                        getDataInterface.onSucess(caipuClasses_ArrayList);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }
}
