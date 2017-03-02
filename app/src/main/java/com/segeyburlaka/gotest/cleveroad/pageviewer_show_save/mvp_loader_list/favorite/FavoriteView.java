package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.favorite;

import com.arellomobile.mvp.MvpView;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;

/**
 * Created by Operator on 01.03.2017.
 */

public interface FavoriteView extends MvpView {
    void showToast(String onResult);

    void swapAdapter(SearchItem data);
}
