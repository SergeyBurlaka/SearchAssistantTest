package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite;

import com.arellomobile.mvp.MvpView;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;

import java.util.List;

/**
 * Created by Operator on 01.03.2017.
 */

public interface FavoriteView extends MvpView {

    void swapAdapter(SearchItem data);

    void swapAdapterFavoriteCash(List<SearchItem> data);
}
