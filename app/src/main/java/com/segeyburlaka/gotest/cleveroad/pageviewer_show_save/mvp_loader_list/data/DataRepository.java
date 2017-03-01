package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import javax.inject.Singleton;

/**
 * Created by Operator on 28.02.2017.
 */
@Singleton
public  class DataRepository {

    public  SearchItem getFavoriteSearch(String mTaskId){
        return null;
    } ;

    public  boolean cachedSearchItems(){
        return false;
    };

    public  SearchItem getCachedSearchItem(SearchItem mTaskId){
        return mTaskId;
    };


    public  SearchItem saveFavoriteSearch(SearchItem searchItem){
        return searchItem;
    };

    public SearchItem getFavoriteSearch() {
        return null;
    }

    public SearchItem getCachedSearchItem() {
        return null;
    }


    //todo realization Observer
    public void addContentObserver(DataRepository.TasksRepositoryObserver tasksRepositoryObserver) {

    }

    public void removeContentObserver(DataRepository.TasksRepositoryObserver tasksRepositoryObserver) {

    }

    public interface TasksRepositoryObserver {
        void onTasksChanged();
    }
}
