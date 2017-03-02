package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di;


import android.content.Context;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItemRepository;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.GetFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.SaveFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.local.SearchItemLocalSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.ContextModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.DataRepositoryModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.GreenDaoModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.favorite.FavoritePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search.SearchPresenter;

import javax.inject.Singleton;


import dagger.Component;

/**
 * Created by Operator on 01.03.2017.
 */

@Singleton
@Component(modules = {ContextModule.class,DataRepositoryModule.class, GreenDaoModule.class })
public interface AppComponent {

    Context getContext();
    SearchItemRepository getDataRepo();

    void inject(SearchPresenter searchPresenter);
    void inject(FavoritePresenter favoritePresenter);

    void inject(SearchItemLocalSource searchItemLocalSource);
    void inject(SaveFavoriteLoader saveFavoriteLoader);
    void inject(GetFavoriteLoader saveFavoriteLoader);

}
