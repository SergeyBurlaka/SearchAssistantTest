package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Operator on 01.03.2017.
 */
@Module
public class DataRepositoryModule {
    @Provides
    @Singleton
    public DataRepository provideGithubService() {
        return new DataRepository();
    }
}