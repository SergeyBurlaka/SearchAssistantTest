package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.support.annotation.NonNull;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;

import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by Operator on 28.02.2017.
 */

public interface SearchItemSource {


    SearchItem getFavoriteSearch(@NotNull Long id);

    void saveFavorite(@NotNull SearchItem searchItem);
    //todo add basic method //for data


}
