package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

/**
 * Created by Operator on 01.03.2017.
 */

public interface GoogleSearchContract {
    List<SearchItem> getGoogleSearch(@NotNull String query, long firstItemID);
}
