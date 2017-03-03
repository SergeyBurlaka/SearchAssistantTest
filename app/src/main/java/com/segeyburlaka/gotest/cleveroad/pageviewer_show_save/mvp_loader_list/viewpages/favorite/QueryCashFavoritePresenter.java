package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.QueryCashFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Operator on 03.03.2017.
 */

@InjectViewState
public class QueryCashFavoritePresenter extends BasePresenter<FavoriteView> implements
        LoaderManager.LoaderCallbacks<List<SearchItem>> {

private final static int TASKS_QUERY = 1;

@Inject
Context context;

private final LoaderManager mLoaderManager;

private QueryCashFavoriteLoader getItemCashLoader;
private String TAG = "goCR";

    public QueryCashFavoritePresenter (@NonNull LoaderManager loaderManager){
        this.mLoaderManager = loaderManager;
        SearchApp.getComponent().inject(this);
        }

    public void getFavoriteFromCash() {
        getItemCashLoader = new QueryCashFavoriteLoader( context);
        mLoaderManager.initLoader(TASKS_QUERY, null, this);
        }

    @Override
    public Loader<List<SearchItem>> onCreateLoader(int id, Bundle args) {
        return getItemCashLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<SearchItem>> loader, List<SearchItem> data) {

        getViewState().swapAdapterFavoriteCash( data);
    }

    @Override
    public void onLoaderReset(Loader<List<SearchItem>> loader) {

    }


}
