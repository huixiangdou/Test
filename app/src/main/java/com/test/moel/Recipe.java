package com.test.moel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/24.
 */

public class Recipe implements Parcelable {
    private ArrayList<Process> processArrayList;
    private ArrayList<Material> materialArrayList;
    private String name;
    private String id;
    private String pic;
    private String tag;
    private String peoplenum;
    private String content;
    private String cookingtime;

    public Recipe(ArrayList<Process> processArrayList, ArrayList<Material> materialArrayList, String name, String id, String pic, String tag, String peoplenum, String content, String cookingtime) {
        this.processArrayList = processArrayList;
        this.materialArrayList = materialArrayList;
        this.name = name;
        this.id = id;
        this.pic = pic;
        this.tag = tag;
        this.peoplenum = peoplenum;
        this.content = content;
        this.cookingtime = cookingtime;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public ArrayList<Process> getProcessArrayList() {
        return processArrayList;
    }

    public void setProcessArrayList(ArrayList<Process> processArrayList) {
        this.processArrayList = processArrayList;
    }

    public ArrayList<Material> getMaterialArrayList() {
        return materialArrayList;
    }

    public void setMaterialArrayList(ArrayList<Material> materialArrayList) {
        this.materialArrayList = materialArrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPeoplenum() {
        return peoplenum;
    }

    public void setpeoplenum(String peoplenum) {
        this.peoplenum = peoplenum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCookingtime() {
        return cookingtime;
    }

    public void setCookingtime(String cookingtime) {
        this.cookingtime = cookingtime;
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "processArrayList=" + processArrayList +
                ", materialArrayList=" + materialArrayList +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", pic='" + pic + '\'' +
                ", tag='" + tag + '\'' +
                ", peoplenum='" + peoplenum + '\'' +
                ", content='" + content + '\'' +
                ", cookingtime='" + cookingtime + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }
    protected Recipe(Parcel in) {
        processArrayList = in.readArrayList(Process.class.getClassLoader());
        materialArrayList = in.readArrayList(Material.class.getClassLoader());
        name = in.readString();
        id = in.readString();
        pic = in.readString();
        tag = in.readString();
        peoplenum = in.readString();
        content = in.readString();
        cookingtime = in.readString();
    }
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(processArrayList);
        parcel.writeList(materialArrayList);
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeString(pic);
        parcel.writeString(tag);
        parcel.writeString(peoplenum);
        parcel.writeString(content);
        parcel.writeString(cookingtime);
    }
}
