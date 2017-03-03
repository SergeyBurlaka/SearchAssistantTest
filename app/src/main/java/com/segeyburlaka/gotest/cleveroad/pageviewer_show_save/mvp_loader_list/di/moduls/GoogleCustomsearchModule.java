package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.moduls;

import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.customsearch.Customsearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Operator on 02.03.2017.
 */

@Module
public class GoogleCustomsearchModule {

    @Provides
    @Singleton
    public  Customsearch.Builder provideCustomsearch (){
        return new Customsearch.Builder(new NetHttpTransport(), new JacksonFactory(), null);
    }
}
