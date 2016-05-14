package com.ashez.garfield.xingpostcard.Utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 2016/5/14 15:07
 * OverWritten by Garfield
 * Date: 5/15/2016 04:01
 */
public class SaveFile {
    public static String toSaveFile(Bitmap bitmap){
        File sdCardDir = Environment.getExternalStorageDirectory();
        String strPath = "/StarMark/starmark" + System.currentTimeMillis()
                + ".jpg";
        File file = new File(sdCardDir, strPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream os = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }

    public static String toSaveFile(Bitmap bitmap,String dir){
        File sdCardDir = Environment.getExternalStorageDirectory();
        String strPath = "/StarMark/" + dir + System.currentTimeMillis()
                + ".jpg";
        File file = new File(sdCardDir, strPath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream os = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.getAbsolutePath();
    }
    public static void scanPhotos(String filePath, Context context) {
        Intent intent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

}
