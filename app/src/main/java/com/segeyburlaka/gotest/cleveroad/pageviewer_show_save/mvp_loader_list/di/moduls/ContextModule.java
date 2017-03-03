package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


/**
 * Created by Operator on 01.03.2017.
 */
@Module
public class ContextModule {

    private Context mContext;

    public ContextModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mContext;
    }
}