package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

/**
 * Created by Operator on 01.03.2017.
 */

public interface FavoriteLocalContract {

    SearchItem getFavoriteSearch( long id);

    Long saveFavorite(@NonNull SearchItem searchItem);

    List<SearchItem> getFavoriteCash();
}
