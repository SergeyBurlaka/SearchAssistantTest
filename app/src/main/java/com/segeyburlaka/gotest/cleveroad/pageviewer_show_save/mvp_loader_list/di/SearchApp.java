package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di;

import android.app.Application;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.ContextModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.DataRepositoryModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.GoogleCustomsearchModule;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls.GreenDaoModule;

/**
 * Created by Operator on 01.03.2017.
 */
public class SearchApp extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    public static AppComponent getComponent(){
        return component;
    }

    protected AppComponent buildComponent(){
        //init DI in application
        return component = DaggerAppComponent
                .builder()
                .contextModule(new ContextModule(this))
                .greenDaoModule(new GreenDaoModule(this))
                .dataRepositoryModule(new DataRepositoryModule())
                .googleCustomsearchModule(new GoogleCustomsearchModule())
                .build();
    }
}
