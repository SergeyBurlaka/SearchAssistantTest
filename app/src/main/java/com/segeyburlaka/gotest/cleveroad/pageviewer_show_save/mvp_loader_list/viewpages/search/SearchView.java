package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search;

import com.arellomobile.mvp.MvpView;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;

import java.util.List;

/**
 * Created by Operator on 28.02.2017.
 */

public interface SearchView  extends MvpView {

    void swapGoogleResult(List<SearchItem> searchResults);
}
