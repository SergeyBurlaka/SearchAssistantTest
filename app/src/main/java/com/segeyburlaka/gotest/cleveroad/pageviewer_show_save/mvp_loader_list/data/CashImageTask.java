package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;

import javax.inject.Inject;

/**
 * Created by Operator on 03.03.2017.
 */

public class CashImageTask extends AsyncTask<Void, Void, Void> {

    @Inject
    Context context;

    private String root;

    private ImageCashListener imageCashListener;

    public CashImageTask(String root ,ImageCashListener imageCashListener){
      this.imageCashListener = imageCashListener;
        SearchApp.getComponent().inject(this);
        this.root = root;
    }

    @Override
    protected Void doInBackground(Void... params) {
        onCashImage(root);
        return null;
    }

    private void onCashImage(String imageLink) {
       saveImage( download_Image(imageLink));
    }

    private Bitmap download_Image(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
        }
        return bm;
    }

    private void saveImage(Bitmap finalBitmap) {

        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/saved_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-"+ n +".jpg";
        File file = new File (myDir, fname);
        if (file.exists ()) file.delete ();

        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        imageCashListener.onCashedImage(file.getAbsolutePath());
    }
}