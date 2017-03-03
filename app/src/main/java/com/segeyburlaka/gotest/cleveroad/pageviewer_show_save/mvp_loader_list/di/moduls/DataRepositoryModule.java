package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import android.support.annotation.NonNull;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.FavoriteLocalContract;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.GoogleSearchContract;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Operator on 01.03.2017.
 */
@Module (includes = {DataSourceModule.class})
public class DataRepositoryModule {
    @Provides
    @Singleton
    public DataRepository provideGithubService(@NonNull GoogleSearchContract remoteSearchItemSource,
                                               @NonNull FavoriteLocalContract localSearchItemSource) {
        return new DataRepository(remoteSearchItemSource, localSearchItemSource);
    }
}