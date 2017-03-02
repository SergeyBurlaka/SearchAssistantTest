package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.remote;

import android.support.annotation.NonNull;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Operator on 01.03.2017.
 */

public class SearchItemRemoteSource implements SearchRemoteContract {


    @Override
    public SearchItem getFavoriteSearch(@NotNull Long id) {
        return null;
    }

    @Override
    public void saveFavorite(@NotNull SearchItem searchItem) {

    }
}
