package com.sunbaby.app.common.file;

import android.os.Environment;
import android.text.format.Time;

import java.io.File;

/**
 * @author wangjingbo
 * @date 2018/7/9
 * describe
 */
public class ImageFolder {
    public ImageFolder() {
    }

    public static File getTempImageName() {
        Time t = new Time("GMT+8");
        t.setToNow();
        int year = t.year;
        int month = t.month;
        int day = t.monthDay;
        int hour = t.hour;
        int minute = t.minute;
        int second = t.second;
        String filename = "" + year + month + day + hour + minute + second;
        String sdcard = Environment.getExternalStorageDirectory().getPath();
        if (sdcard.startsWith("/storage/emulated/0")) {
            sdcard = sdcard.replace("/storage/emulated/0", "/sdcard");
        }

        File welcomeimgFlord = new File(sdcard + "/Photo/tempImage");
        if (!welcomeimgFlord.exists()) {
            welcomeimgFlord.mkdirs();
        }

        File tempImage = new File(welcomeimgFlord, "temp" + filename + ".jpg");
        return tempImage;
    }
}
