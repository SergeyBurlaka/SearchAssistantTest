package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo;

/**
 * Created by Operator on 01.03.2017.
 */

public class AddFavoriteEvent {

    private long idSearchItem;

    public AddFavoriteEvent(long idSearchItem) {
        this.idSearchItem = idSearchItem;
    }

    public Long getIdSearchItem() {
        return idSearchItem;
    }

    public void setIdSearchItem(long idSearchItem) {
        this.idSearchItem = idSearchItem;
    }
}
