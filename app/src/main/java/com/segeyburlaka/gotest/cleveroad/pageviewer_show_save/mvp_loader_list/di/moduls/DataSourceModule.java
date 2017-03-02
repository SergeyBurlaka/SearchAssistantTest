package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItemSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.local.SearchItemLocalContract;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.local.SearchItemLocalSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.remote.SearchItemRemoteSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.remote.SearchRemoteContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Operator on 01.03.2017.
 */
@Module
public class DataSourceModule {
    @Provides
    @Singleton
    public SearchRemoteContract provideSearchRemoteDataSource (){
        return new SearchItemRemoteSource();
    }

    @Provides
    @Singleton
    public SearchItemLocalContract provideSearchItemDataLocalSource (){
        return new SearchItemLocalSource();
    }
}
