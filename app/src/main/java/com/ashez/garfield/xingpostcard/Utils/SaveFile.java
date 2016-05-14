package com.ashez.garfield.xingpostcard.Utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;


/**
 * Created by hugeterry(http://hugeterry.cn)
 * Date: 16/5/14 15:07
 */
public class SaveFile {
    public static void toSaveFile(Bitmap bitmap) {
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
    }

    public static void scanPhotos(String filePath, Context context) {
        Intent intent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(new File(filePath));
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

}
