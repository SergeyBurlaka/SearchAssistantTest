package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.support.annotation.NonNull;

import java.util.List;

import static dagger.internal.Preconditions.checkNotNull;

/**
 * Created by Operator on 28.02.2017.
 */

public  class DataRepository implements FavoriteLocalContract, GoogleSearchContract {

    private final GoogleSearchContract mTasksRemoteSearchItemSource;

    private final FavoriteLocalContract mTasksLocalSearchItemSource;

    public DataRepository(@NonNull GoogleSearchContract tasksRemoteSearchItemSource,
                          @NonNull FavoriteLocalContract tasksLocalSearchItemSource) {
        mTasksRemoteSearchItemSource = checkNotNull(tasksRemoteSearchItemSource);
        mTasksLocalSearchItemSource = checkNotNull(tasksLocalSearchItemSource);
    }

    @Override
    public SearchItem getFavoriteSearch( long id){
        return mTasksLocalSearchItemSource.getFavoriteSearch(id);
    }

    @Override
    public Long saveFavorite(@NonNull SearchItem searchItem){
            return mTasksLocalSearchItemSource.saveFavorite(searchItem);
    }

    @Override
    public List<SearchItem> getFavoriteCash() {
        return mTasksLocalSearchItemSource.getFavoriteCash();
    }


    @Override
    public List<SearchItem> getGoogleSearch(@NonNull String query, long firstItemID) {
        return mTasksRemoteSearchItemSource.getGoogleSearch(query, firstItemID);
    }

    //todo realizate Observer
    public void addContentObserver(DataRepository.TasksRepositoryObserver tasksRepositoryObserver) {

    }

    public void removeContentObserver(DataRepository.TasksRepositoryObserver tasksRepositoryObserver) {

    }

    public interface TasksRepositoryObserver {
        void onTasksChanged();
    }
}
