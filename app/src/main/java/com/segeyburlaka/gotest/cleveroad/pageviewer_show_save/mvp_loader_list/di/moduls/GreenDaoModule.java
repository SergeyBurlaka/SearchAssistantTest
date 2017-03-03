package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import android.content.Context;


import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DaoMaster;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.DaoSession;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItemDao;

import org.greenrobot.greendao.database.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;




/**
 * Created by Operator on 01.03.2017.
 */


@Module
public class GreenDaoModule {

    private Context mContext;

    public GreenDaoModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @Singleton
    public DaoSession provideDaoSession (){
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mContext, "notes-db");
        Database db = helper.getWritableDb();
        return new DaoMaster(db).newSession();
    }

    @Provides
    @Singleton
    public SearchItemDao provideSearchItemDao (DaoSession daoSession){
        return  daoSession.getSearchItemDao();
    }
}
