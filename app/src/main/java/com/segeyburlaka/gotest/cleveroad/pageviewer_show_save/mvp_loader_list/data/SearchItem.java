package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

/**
 * Created by Operator on 28.02.2017.
 */

public class SearchItem {

    private String title = "";
    private String imageUri = "";
    private boolean isLiked;

    public SearchItem(String title, String imageUri) {
        this.title = title;
        this.imageUri = imageUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }
}
