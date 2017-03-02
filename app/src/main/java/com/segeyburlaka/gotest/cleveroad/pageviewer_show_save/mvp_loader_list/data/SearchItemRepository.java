package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.support.annotation.NonNull;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;

import org.greenrobot.greendao.annotation.NotNull;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Operator on 28.02.2017.
 */

public  class SearchItemRepository implements SearchItemSource {

    private final SearchItemSource mTasksRemoteSearchItemSource;

    private final SearchItemSource mTasksLocalSearchItemSource;

    public SearchItemRepository(@NonNull SearchItemSource tasksRemoteSearchItemSource,
                                @NonNull SearchItemSource tasksLocalSearchItemSource) {
        mTasksRemoteSearchItemSource = checkNotNull(tasksRemoteSearchItemSource);
        mTasksLocalSearchItemSource = checkNotNull(tasksLocalSearchItemSource);
    }

    @Override
    public SearchItem getFavoriteSearch(@NotNull Long id){
        return mTasksLocalSearchItemSource.getFavoriteSearch(id);
    }

    @Override
    public void saveFavorite(@NotNull SearchItem searchItem){
            mTasksLocalSearchItemSource.saveFavorite(searchItem);
    }

    //todo realization Observer
    public void addContentObserver(SearchItemRepository.TasksRepositoryObserver tasksRepositoryObserver) {

    }

    public void removeContentObserver(SearchItemRepository.TasksRepositoryObserver tasksRepositoryObserver) {

    }

    public interface TasksRepositoryObserver {
        void onTasksChanged();
    }
}
