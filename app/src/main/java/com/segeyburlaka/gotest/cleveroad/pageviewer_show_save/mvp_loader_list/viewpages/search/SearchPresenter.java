package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.BasePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.event.AddFavoriteEvent;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.SaveFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
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
    private String name ="";

    public SearchPresenter (@NonNull LoaderManager loaderManager){
        this.mLoaderManager = loaderManager;
        SearchApp.getComponent().inject(this);
    }

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
        return tasksLoader;
    }

    @Override
    public void onLoadFinished(final Loader<Long> loader, final Long idSearcItem) {
        sendNotify(idSearcItem);
    }

    @Override
    public void onLoaderReset(Loader<Long> loader) {

    }

    public void saveToFavorite(SearchItem searchItem) {
        tasksLoader = new SaveFavoriteLoader( searchItem, context);

        Loader<SearchView>  loader = mLoaderManager.getLoader((int)searchItem.getId());
        if(loader!=null){
            mLoaderManager.restartLoader((int)searchItem.getId(), null, this);
        } else{
            mLoaderManager.initLoader((int)searchItem.getId(), null, this);
        }
    }

    public void removeFromFavorite(SearchItem searchItem) {
    }
}
