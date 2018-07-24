package com.sunbaby.app.bean;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import java.io.File;
import java.io.Serializable;

/**
 * @author wangjingbo
 * @date 2018/7/23
 * describe
 */
public class ImageData implements Parcelable, Serializable {
    public String fileName;
    public String imgUrl;

    public ImageData(String fileName, String imgUrl) {

        this.fileName = fileName;
        this.imgUrl = imgUrl;
    }

    protected ImageData(Parcel in) {
        fileName = in.readString();
        imgUrl = in.readString();
    }

    public static final Creator<ImageData> CREATOR = new Creator<ImageData>() {
        @Override
        public ImageData createFromParcel(Parcel in) {
            return new ImageData(in);
        }

        @Override
        public ImageData[] newArray(int size) {
            return new ImageData[size];
        }
    };

    public boolean localFileExist() {
        if (TextUtils.isEmpty(fileName)) {
            return false;
        }

        File file = new File(fileName);
        boolean exits = file.exists();
        file = null;
        return exits;
    }

    public boolean hasRemoteImgUrl() {
        if (TextUtils.isEmpty(imgUrl)) {
            return false;
        }

        if (!imgUrl.startsWith("http://")) {
            return false;
        }

        return true;
    }

    /**
     * 判断两个文件名是否一致
     *
     * @param imgFile
     * @return
     */
    public boolean equalsFile(String imgFile) {
        if (TextUtils.isEmpty(fileName) || TextUtils.isEmpty(imgFile)) {
            return false;
        }
        return fileName.equals(imgFile);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(imgUrl);
    }

    @Override
    public String toString() {
        System.out.println("ImageData.fileName:" + fileName + ";ImageData.imgUrl:" + imgUrl);
        return "ImageData.fileName:" + fileName + ";ImageData.imgUrl:" + imgUrl;
    }
}
