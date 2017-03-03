package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;
import java.util.List;

import javax.inject.Inject;


/**
 * Created by Operator on 28.02.2017.
 */
public class FavoriteLocalSource implements FavoriteLocalContract,ImageCashListener {

    @Inject
    SearchItemDao searchItemDao;

    @Inject
    Context context;

    private static final int FIRST_ITEM = 0;
    private SearchItem searchItem;

    public FavoriteLocalSource(){
        SearchApp.getComponent().inject(this);
    }
    @Override
    public SearchItem getFavoriteSearch( long id) {
        List<SearchItem> searchItemList =  searchItemDao.queryBuilder()
                .where(SearchItemDao.Properties.Id.eq(id))
                .orderAsc(SearchItemDao.Properties.Id)
                .list();
        return searchItemList.get(FIRST_ITEM);
    }

    public Long saveFavorite(@NonNull SearchItem searchItem) {
        this.searchItem = searchItem;
        new CashImageTask(searchItem.getImageLink(), this).execute();

        try {
            searchItemDao.insert(searchItem);
        } catch (Exception ex) {

        } catch (Error e) {

        }
        return searchItem.getId();
    }

    @Override
    public List<SearchItem> getFavoriteCash() {

        List<SearchItem> searchItemList =  searchItemDao.queryBuilder()
                .orderAsc(SearchItemDao.Properties.Date)
                .list();
        return searchItemList;
    }

    @Override
    public void onCashedImage(String root) {
        try {
            searchItem.setCashRootImage(root);
            searchItemDao.update(this.searchItem);
        } catch (Exception ex) {

        } catch (Error e) {

        }
    }
}
