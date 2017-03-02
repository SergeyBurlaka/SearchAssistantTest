package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItemRepository;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import javax.inject.Inject;

/**
 * Created by Operator on 01.03.2017.
 */

public class SaveFavoriteLoader extends AsyncTaskLoader<Long>
        implements SearchItemRepository.TasksRepositoryObserver {

    @Inject
    SearchItemRepository dataRepository;

    private final SearchItem searchItem;
    private String TAG = "goCR";

    public SaveFavoriteLoader(SearchItem searchItem, Context context) {
        super(context);
        SearchApp.getComponent().inject(this);
        this.searchItem = searchItem;
    }

    @Override
    public Long loadInBackground() {

        Log.d(TAG, "loadInBackground()");
        dataRepository.saveFavorite(searchItem);

        return searchItem.getId();
    }

    @Override
    protected void onStartLoading() {

        forceLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
    }

    @Override
    public void onTasksChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
