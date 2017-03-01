package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.BasePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.AddFavoriteEvent;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SaveFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

/**
 * Created by Operator on 28.02.2017.
 */

@InjectViewState
public class SearchPresenter extends BasePresenter<SearchView> implements
        LoaderManager.LoaderCallbacks<String> {

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

    public void start(@NonNull LoaderManager loaderManager) {
        mLoaderManager = loaderManager;
        tasksLoader = new SaveFavoriteLoader( new SearchItem("",""),context );
        mLoaderManager.initLoader(TASKS_QUERY, null, this);
    }

    //send notification with eventbus to other presenter
    public void sendNotify (){
        EventBus.getDefault().post(new AddFavoriteEvent());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        return tasksLoader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
       // Log.d(TAG, "onLoadFinished");
        // This solution will leak memory!  Don't use!!!
        /*Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "send notify--->");
                sendNotify();
            }
        }, 2000);*/
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    public void saveToFavorite(SearchItem searchItem) {
        tasksLoader = new SaveFavoriteLoader( searchItem, context);
        mLoaderManager.initLoader(TASKS_QUERY, null, this);
    }

    public void removeFromFavorite(SearchItem searchItem) {
    }
}
