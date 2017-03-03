package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search.SearchFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Operator on 28.02.2017.
 */

public class FavoriteLocalSource implements FavoriteLocalContract,ImageCashListener {

    private static final String TAG = "goCR";
    @Inject
    SearchItemDao searchItemDao;

    @Inject
    Context context;

    private static final int FIRST_ITEM = 0;
    private ImageCashListener imageCashListener;
    private SearchItem searchItem;



    public FavoriteLocalSource(){
        SearchApp.getComponent().inject(this);
    }


    @Override
    public SearchItem getFavoriteSearch( long id) {
        List<SearchItem> searchItemList =  searchItemDao.queryBuilder()
                .where(SearchItemDao.Properties.Id.eq(id))
                .orderAsc(SearchItemDao.Properties.Id)
                .list();
        return searchItemList.get(FIRST_ITEM);

    }


    public Long saveFavorite(@NonNull SearchItem searchItem) {


        this.searchItem = searchItem;
        new CashImageTask(searchItem.getImageLink(), this).execute();

        try {
            searchItemDao.insert(searchItem);
        } catch (Exception ex) {
            // Do your handling here
        } catch (Error e) {
            // and here
        }
        Log.d(TAG,"myKey ....."+searchItemDao.getKey(searchItem));

        return searchItem.getId();

    }


    //todo $$$-> get from cash////
    @Override
    public List<SearchItem> getFavoriteCash() {

        List<SearchItem> searchItemList =  searchItemDao.queryBuilder()
                .orderAsc(SearchItemDao.Properties.Date)
                .list();
        return searchItemList;
    }


    //todo 3) save image in CD
    @Override
    public void onCashedImage(String root) {
        try {
            searchItem.setCashRootImage(root);
            searchItemDao.update(this.searchItem);
        } catch (Exception ex) {
            // Do your handling here
        } catch (Error e) {
            // and here
        }

    }


}
