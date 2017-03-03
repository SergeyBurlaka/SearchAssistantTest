package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Operator on 03.03.2017.
 */

public class QueryCashFavoriteLoader extends AsyncTaskLoader<List<SearchItem>>
        implements DataRepository.TasksRepositoryObserver {

    @Inject
    DataRepository dataRepository;



    public QueryCashFavoriteLoader ( Context context) {
        super(context);
        SearchApp.getComponent().inject(this);
    }

    @Override
    public List<SearchItem> loadInBackground() {
        return dataRepository.getFavoriteCash();
    }

    @Override
    public void deliverResult(List<SearchItem> data) {
        if (isReset()) {
            return;
        }

        if (isStarted()) {
            super.deliverResult(data);
        }
    }

    @Override
    protected void onStartLoading() {
        // Begin monitoring the underlying data source.
        dataRepository.addContentObserver(this);
        forceLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        dataRepository.removeContentObserver(this);
    }

    @Override
    public void onTasksChanged() {
        if (isStarted()) {
            forceLoad();
        }
    }
}
