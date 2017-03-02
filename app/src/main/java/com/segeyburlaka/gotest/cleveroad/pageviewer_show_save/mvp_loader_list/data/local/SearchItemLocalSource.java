package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.local;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItemDao;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import org.greenrobot.greendao.annotation.NotNull;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Operator on 28.02.2017.
 */

public class SearchItemLocalSource implements SearchItemLocalContract {

    @Inject
    SearchItemDao searchItemDao;

    @Inject
    Context context;

    private static final int FIRST_ITEM = 0;

    public SearchItemLocalSource (){
        SearchApp.getComponent().inject(this);
    }


    @Override
    public SearchItem getFavoriteSearch(@NotNull Long id) {
        List<SearchItem> searchItemList =  searchItemDao.queryBuilder()
                .where(SearchItemDao.Properties.Id.eq(id))
                .orderAsc(SearchItemDao.Properties.Id)
                .list();
        return searchItemList.get(FIRST_ITEM);

    }


    @Override
    public void saveFavorite(@NotNull SearchItem searchItem) {
        try {
            searchItemDao.insert(searchItem);
        } catch (Exception ex) {
            // Do your handling here
        } catch (Error e) {
            // and here
        }


        Log.d("DaoExample", "Inserted new note, ID: " + searchItem.getTitle());

    }

    //todo 11/2:50----------------------------------> 1) add entety
    //todo 4min/3h ------------------------------------> 2) get entety

    //todo 3) save image in CD

}
