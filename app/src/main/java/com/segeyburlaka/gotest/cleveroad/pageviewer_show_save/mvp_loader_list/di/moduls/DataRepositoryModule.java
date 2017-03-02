package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import android.support.annotation.NonNull;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItemRepository;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItemSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.local.SearchItemLocalContract;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.remote.SearchRemoteContract;

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
    public SearchItemRepository provideGithubService(@NonNull SearchRemoteContract remoteSearchItemSource,
                                                     @NonNull SearchItemLocalContract localSearchItemSource) {
        return new SearchItemRepository(remoteSearchItemSource, localSearchItemSource);
    }
}