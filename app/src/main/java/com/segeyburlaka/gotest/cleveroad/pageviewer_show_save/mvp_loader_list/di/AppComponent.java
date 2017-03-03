package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di;


import android.content.Context;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.CashImageTask;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DataRepository;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.GetFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.QueryCashFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.QueryGoogleLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.loaders.SaveFavoriteLoader;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.FavoriteLocalSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.GoogleSearchSource;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.ContextModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.DataRepositoryModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.GoogleCustomsearchModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.GreenDaoModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite.FavoritePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite.GridSpacingItemDecoration;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite.QueryCashFavoritePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search.GoogleCustomsearchPresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search.SearchPresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.SearchResultAdapter;

import javax.inject.Singleton;


import dagger.Component;

/**
 * Created by Operator on 01.03.2017.
 */

@Singleton
@Component(modules = {
        ContextModule.class,DataRepositoryModule.class,
        GreenDaoModule.class, GoogleCustomsearchModule.class })

public interface AppComponent {

    Context getContext();
    DataRepository getDataRepo();

    void inject(SearchPresenter searchPresenter);
    void inject(FavoritePresenter favoritePresenter);
    void inject(GoogleCustomsearchPresenter googleCustomsearchPresenter);
    void inject (QueryCashFavoritePresenter queryCashFavoritePresenter);

    void inject(SaveFavoriteLoader saveFavoriteLoader);
    void inject(GetFavoriteLoader saveFavoriteLoader);
    void inject(QueryGoogleLoader queryGoogleLoader);
    void inject(QueryCashFavoriteLoader queryCashFavoriteLoader);

    void inject(FavoriteLocalSource favoriteLocalSource);
    void inject (GoogleSearchSource searchItemRemoteSource);

    //SearchResultAdapter
    void inject (SearchResultAdapter.SearchListViewHolder searchViewHolder);
    void inject (GridSpacingItemDecoration gridSpacingItemDecoration);

    // CashImageTask
    void inject (CashImageTask cashImageTask);
}
