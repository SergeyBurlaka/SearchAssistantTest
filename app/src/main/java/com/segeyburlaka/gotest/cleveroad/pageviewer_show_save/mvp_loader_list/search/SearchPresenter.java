package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.BasePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.AddFavoriteEvent;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.SaveFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by Operator on 28.02.2017.
 */

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchView> implements
        LoaderManager.LoaderCallbacks<Long> {

    @Inject
    Context context;

    private LoaderManager mLoaderManager;
    private SaveFavoriteLoader tasksLoader;

    private String TAG = "goCR";
    private String name ="";
    private final static int TASKS_QUERY = 1;

    public SearchPresenter (@NonNull LoaderManager loaderManager){
        this.mLoaderManager = loaderManager;
        SearchApp.getComponent().inject(this);
    }

    /*public void start(@NonNull LoaderManager loaderManager) {
        mLoaderManager = loaderManager;
        tasksLoader = new SaveFavoriteLoader(new SearchItem(1l,"",""),context );
        mLoaderManager.initLoader(TASKS_QUERY, null, this);
    }*/

    //send notification with eventbus to other presenter
    public void sendNotify (Long id){
        EventBus.getDefault().post(new AddFavoriteEvent(id));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Loader<Long> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader ");
        return tasksLoader;
    }

    @Override
    public void onLoadFinished(final Loader<Long> loader, final Long idSearcItem) {
        // Log.d(TAG, "onLoadFinished");
        sendNotify(idSearcItem);
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "send notify--->");
                sendNotify(idSearcItem);
            }
        }, 2000);*/
    }


    @Override
    public void onLoaderReset(Loader<Long> loader) {

    }

    public void saveToFavorite(SearchItem searchItem) {
        Log.d(TAG, "saveToFavorite "+searchItem.getId());

        tasksLoader = new SaveFavoriteLoader( searchItem, context);


        mLoaderManager.initLoader((int)searchItem.getId(), null, this);
    }

    public void removeFromFavorite(SearchItem searchItem) {
    }
}
