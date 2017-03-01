package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

/**
 * Created by Operator on 28.02.2017.
 */
public class GetFavoriteLoader extends  AsyncTaskLoader<SearchItem>
    implements DataRepository.TasksRepositoryObserver{

    private final SearchItem searchItem;

    private DataRepository mRepository;

    public GetFavoriteLoader(SearchItem searchItem, Context context) {
        super(context);
        this.searchItem = searchItem;
    }

    @Override
    public SearchItem loadInBackground() {
        return mRepository.getFavoriteSearch();
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
            // Deliver any previously loaded data immediately if available.
            if (mRepository.cachedSearchItems()) {
                deliverResult(mRepository.getCachedSearchItem());
            }

            // Begin monitoring the underlying data source.
            mRepository.addContentObserver(this);

            if (takeContentChanged() || !mRepository.cachedSearchItems()) {
                // When a change has  been delivered or the repository cache isn't available, we force
                // a load.
                forceLoad();
            }
        }

        @Override
        protected void onReset() {
            onStopLoading();
            mRepository.removeContentObserver(this);
        }

        @Override
        public void onTasksChanged() {
            if (isStarted()) {
                forceLoad();
            }
        }
}


