package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.content.Context;
import android.os.Handler;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import javax.inject.Inject;

/**
 * Created by Operator on 01.03.2017.
 */

public class SaveFavoriteLoader extends AsyncTaskLoader<String>
        implements DataRepository.TasksRepositoryObserver{

    @Inject
    DataRepository dataRepository;

    private final SearchItem searchItem;
    private String TAG = "goCR";

    public SaveFavoriteLoader(SearchItem searchItem, Context context) {
        super(context);
        SearchApp.getComponent().inject(this);
        this.searchItem = searchItem;
    }

    @Override
    public String loadInBackground() {
       // Log.d(TAG, "loadInBackground()");
       // return mRepository.saveFavoriteSearch(searchItem);
        return "NEO";
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
