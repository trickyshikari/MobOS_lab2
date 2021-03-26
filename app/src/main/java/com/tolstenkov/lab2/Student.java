package com.tolstenkov.lab2;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    String name = "";
    String email = "";
    String phone = "";

    protected Student(Parcel in) {
        name = in.readString();
        email = in.readString();
        phone = in.readString();
    }

    public Student() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(phone);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}