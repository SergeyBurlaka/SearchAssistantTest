package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.arellomobile.mvp.InjectViewState;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.BasePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.QueryGoogleLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import java.util.List;

import javax.inject.Inject;


/**
 * Created by Operator on 02.03.2017.
 */
@InjectViewState
public class GoogleCustomsearchPresenter extends BasePresenter<SearchView> implements LoaderManager.LoaderCallbacks<List<SearchItem>>{

    private static final int TASKS_QUERY = 1;
    @Inject
    Context context;

    private LoaderManager mLoaderManager;
    private QueryGoogleLoader queryGoogleLoader;

    public GoogleCustomsearchPresenter(@NonNull LoaderManager loaderManager){
        this.mLoaderManager = loaderManager;
        SearchApp.getComponent().inject(this);
    }

    public void getGoogleSearch(@NonNull String query,  int firstItemID) {
        queryGoogleLoader = new QueryGoogleLoader( query,firstItemID, context);
        Loader<SearchView>  loader = mLoaderManager.getLoader(TASKS_QUERY);

        if(loader!=null){
            mLoaderManager.restartLoader(TASKS_QUERY, null, this);
        } else{
            mLoaderManager.initLoader(TASKS_QUERY, null, this);
        }

    }

    @Override
    public Loader<List<SearchItem>> onCreateLoader(int id, Bundle args) {
        return queryGoogleLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<SearchItem>> loader, List<SearchItem> data) {
        getViewState().swapGoogleResult(data);
    }

    @Override
    public void onLoaderReset(Loader<List<SearchItem>> loader) {

    }
}