package com.test.moel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/24.
 */

public class Process implements Parcelable{
    private String pcontent;
    private String pic;

    public Process(String pcontent, String pic) {
        this.pcontent = pcontent;
        this.pic = pic;
    }

    protected Process(Parcel in) {
        pcontent = in.readString();
        pic = in.readString();
    }

    public static final Creator<Process> CREATOR = new Creator<Process>() {
        @Override
        public Process createFromParcel(Parcel in) {
            return new Process(in);
        }

        @Override
        public Process[] newArray(int size) {
            return new Process[size];
        }
    };

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Process{" +
                "pcontent='" + pcontent + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pcontent);
        parcel.writeString(pic);
    }
}
