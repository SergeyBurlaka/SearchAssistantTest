package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;
import java.util.Date;

/**
 * Created by Operator on 28.02.2017.
 */

@Entity(indexes = {
        @Index(value = "title, date DESC", unique = true)
})
public class SearchItem {

    //todo 22min/40 ----------------------> _add greendao anotations
    @Id
    private long id;

    @NotNull
    private String title = "";
    private String imageUri = "";
    private boolean isLiked;
    private java.util.Date date;

   @Generated(hash = 1717446595)
    public SearchItem() {
    }

    public SearchItem(SearchItem searchItem){
        this.id = searchItem.getId();
        this.title = searchItem.getTitle();
        this.imageUri = searchItem.imageUri;
        this.isLiked = searchItem.getLiked();
        this.date = searchItem.getDate();
    }

    @Generated(hash = 1260413104)
    public SearchItem(long id, @NotNull String title, String imageUri, boolean isLiked,
            java.util.Date date) {
        this.id = id;
        this.title = title;
        this.imageUri = imageUri;
        this.isLiked = isLiked;
        this.date = date;
    }
    
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
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

    public void setId(Long id) {
        this.id = id;
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
        hash = 53 * hash + (int)this.id;
        return hash;
    }


}
