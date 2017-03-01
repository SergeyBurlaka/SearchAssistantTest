package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di;


import android.content.Context;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SaveFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.ContextModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.DataRepositoryModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search.SearchPresenter;

import javax.inject.Singleton;


import dagger.Component;

/**
 * Created by Operator on 01.03.2017.
 */

@Singleton
@Component(modules = {ContextModule.class,DataRepositoryModule.class })
public interface AppComponent {

   Context getContext();
   DataRepository getDataRepo();

   void inject(SearchPresenter searchPresenter);
   void inject(SaveFavoriteLoader saveFavoriteLoader);
}
