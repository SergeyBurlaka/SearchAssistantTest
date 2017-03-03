package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data;

import android.content.Context;

import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import javax.inject.Inject;

import static com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.util.AppConstant.RandomGenerate.lowerLimit;
import static com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.util.AppConstant.RandomGenerate.upperLimit;

/**
 * Created by Operator on 01.03.2017.
 */

public class GoogleSearchSource implements GoogleSearchContract {

    @Inject
    Context context;

    @Inject
    Customsearch.Builder googleCustomSearch;

    private final static String SEARCH = "Search";
    private final static String IMAGE = "image";
    private Customsearch.Cse.List list;
    private String NOT_EXIST ="";

    public GoogleSearchSource(){
            SearchApp.getComponent().inject(this);
        }

    @Override
    public List<SearchItem> getGoogleSearch(@NonNull String query, final long firstItemID){
        googleCustomSearch.setApplicationName(SEARCH);

        try {
            list = googleCustomSearch.build().cse().list(query);
            list.setKey( context.getResources().getString(R.string.google_api_key));
            list.setCx(context.getResources().getString(R.string.search_engine_id));
            list.setSearchType(IMAGE);
            list.setImgSize("xxlarge");

            return onGoogleResult(list.execute());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<SearchItem> onGoogleResult(Search results) {
        Random r = new Random();
        List<SearchItem> objects = new ArrayList<>();
        if (results.getItems() == null) return objects;
            for (Result res : results.getItems()) {
                long number = lowerLimit+((long)(r.nextDouble()*(upperLimit-lowerLimit)));

                if (res != null)
                    objects.add(new SearchItem(
                            number,
                            res.getTitle(),
                            res.getImage().getThumbnailLink(),
                            res.getImage().getContextLink(),
                            NOT_EXIST,
                            false,
                            new Date()
                    ));
            }
        return objects;
    }
}
