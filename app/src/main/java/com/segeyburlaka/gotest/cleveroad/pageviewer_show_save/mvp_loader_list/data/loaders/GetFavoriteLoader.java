package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import javax.inject.Inject;

/**
 * Created by Operator on 28.02.2017.
 */
public class GetFavoriteLoader extends  AsyncTaskLoader<SearchItem>
    implements DataRepository.TasksRepositoryObserver {

    @Inject
    DataRepository dataRepository;

    private final Long idSearchItem;

    public GetFavoriteLoader(Long idSearchItem, Context context) {
        super(context);
        SearchApp.getComponent().inject(this);
        this.idSearchItem = idSearchItem;
    }

    @Override
    public SearchItem loadInBackground() {
        return dataRepository.getFavoriteSearch(idSearchItem);
    }

    @Override
        public void deliverResult(SearchItem data) {
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


