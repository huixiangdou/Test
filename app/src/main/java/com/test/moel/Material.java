package com.test.moel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/8/24.
 */

public class Material implements Parcelable{
    private String amount;
    private String mname;

    public Material(String amount, String mname) {
        this.amount = amount;
        this.mname = mname;
    }

    protected Material(Parcel in) {
        amount = in.readString();
        mname = in.readString();
    }

    public static final Creator<Material> CREATOR = new Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel in) {
            return new Material(in);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    @Override
    public String toString() {
        return "Material{" +
                "amount='" + amount + '\'' +
                ", mname='" + mname + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(amount);
        parcel.writeString(mname);
    }
}
