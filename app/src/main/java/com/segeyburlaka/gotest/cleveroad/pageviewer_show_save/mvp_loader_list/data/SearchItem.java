package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;


/**
 * Created by Operator on 28.02.2017.
 */

@Entity(indexes = {
        @Index(value = "title, date DESC", unique = true)
})
public class SearchItem {

    @Id
    private long id;

    @NotNull
    private String title = "";

    private String imageLink = "";
    private String contextLink ="";
    private String cashRootImage = "";
    private boolean isLiked;
    private java.util.Date date;

    public SearchItem(@NotNull SearchItem searchItem) {
        this.id = searchItem.getId();
        this.title = searchItem.getTitle();
        this.imageLink = searchItem.imageLink;
        this.isLiked = searchItem.getLiked();
        this.date = searchItem.getDate();
        this.contextLink = searchItem.contextLink;
        this.cashRootImage = searchItem.cashRootImage;
    }

    @Generated(hash = 654981595)
    public SearchItem(long id, @NotNull String title, String imageLink, String contextLink, String cashRootImage,
            boolean isLiked, java.util.Date date) {
        this.id = id;
        this.title = title;
        this.imageLink = imageLink;
        this.contextLink = contextLink;
        this.cashRootImage = cashRootImage;
        this.isLiked = isLiked;
        this.date = date;
    }

    @Generated(hash = 1717446595)
    public SearchItem() {
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NotNull String title) {
        this.title = title;
    }

    public Boolean getLiked() {
        return isLiked;
    }

    public void setLiked(Boolean liked) {
        isLiked = liked;
    }

    public long getId() {
        return this.id;
    }

    public boolean getIsLiked() {
        return this.isLiked;
    }

    public void setIsLiked(boolean isLiked) {
        this.isLiked = isLiked;
    }

    public java.util.Date getDate() {
        return this.date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!SearchItem.class.isAssignableFrom(obj.getClass())) {
            return false;
        }
        final SearchItem other = (SearchItem) obj;
        if ((this.title == null) ? (other.title != null) : !this.title.equals(other.title)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.title != null ? this.title.hashCode() : 0);
        return hash;
    }

    public String getContextLink() {
        return contextLink;
    }

    public void setContextLink(String contextLink) {
        this.contextLink = contextLink;
    }

    public String getCashRootImage() {
        return cashRootImage;
    }

    public void setCashRootImage(String cashRootImage) {
        this.cashRootImage = cashRootImage;
    }
}
