package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.event;

/**
 * Created by Operator on 03.03.2017.
 */

public class OnSearchEvent {

    private String query;

    public OnSearchEvent(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
