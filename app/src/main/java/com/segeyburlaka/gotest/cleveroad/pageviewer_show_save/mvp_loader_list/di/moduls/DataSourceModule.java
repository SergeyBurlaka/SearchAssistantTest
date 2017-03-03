package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.FavoriteLocalContract;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.FavoriteLocalSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.GoogleSearchSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.GoogleSearchContract;

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
    public GoogleSearchContract provideSearchRemoteDataSource (){
        return new GoogleSearchSource();
    }

    @Provides
    @Singleton
    public FavoriteLocalContract provideSearchItemDataLocalSource (){
        return new FavoriteLocalSource();
    }
}
