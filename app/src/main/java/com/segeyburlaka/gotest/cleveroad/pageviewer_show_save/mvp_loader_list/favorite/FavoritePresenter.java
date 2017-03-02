package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.favorite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.BasePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.GetFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import javax.inject.Inject;

/**
 * Created by Operator on 01.03.2017.
 */

@InjectViewState
public class FavoritePresenter  extends BasePresenter<FavoriteView> implements
        LoaderManager.LoaderCallbacks<SearchItem> {

    private final static int TASKS_QUERY = 1;

    @Inject
    Context context;

    private final LoaderManager mLoaderManager;

    private GetFavoriteLoader getItemLoader;
    private String TAG = "goCR";

    public FavoritePresenter (@NonNull LoaderManager loaderManager){
        this.mLoaderManager = loaderManager;
        SearchApp.getComponent().inject(this);
    }

    public void getNewFavorite(long id) {
        Log.d(TAG, "getNewFavorite = "+id);

        getItemLoader = new GetFavoriteLoader( id, context);
        mLoaderManager.initLoader((int)id, null, this);

    }

    @Override
    public Loader<SearchItem> onCreateLoader(int id, Bundle args) {
        return getItemLoader;
    }

    @Override
    public void onLoadFinished(Loader<SearchItem> loader, SearchItem data) {
        getViewState().showToast(data.getTitle());
        getViewState().swapAdapter(data);
    }

    @Override
    public void onLoaderReset(Loader<SearchItem> loader) {

    }
}


