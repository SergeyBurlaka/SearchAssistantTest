package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Operator on 02.03.2017.
 */

public class QueryGoogleLoader extends AsyncTaskLoader<List<SearchItem>>
        implements DataRepository.TasksRepositoryObserver {

    @Inject
    DataRepository dataRepository;

    private final String query;
    private final int firstItemID;

    public QueryGoogleLoader(@NonNull String query, int firstItemID, Context context) {
        super(context);
        SearchApp.getComponent().inject(this);
        this.query = query;
        this.firstItemID = firstItemID;
    }



    @Override
    public List<SearchItem> loadInBackground() {
        long firstItemID = this.firstItemID;
       return dataRepository.getGoogleSearch(query,firstItemID);
    }

    /*@Override
    public void deliverResult(List<SearchItem> searchItemList) {
        if (isReset()) {
            return;
        }

        if (isStarted()) {
            super.deliverResult(searchItemList);
        }
    }*/

    @Override
    protected void onStartLoading() {
        // Begin monitoring the underlying data source.
       // dataRepository.addContentObserver(this);
      forceLoad();
    }

    @Override
    protected void onReset() {
        onStopLoading();
        //dataRepository.removeContentObserver(this);
    }

    @Override
    public void onTasksChanged() {
       if (isStarted()) {
           forceLoad();
    }
    }
}
