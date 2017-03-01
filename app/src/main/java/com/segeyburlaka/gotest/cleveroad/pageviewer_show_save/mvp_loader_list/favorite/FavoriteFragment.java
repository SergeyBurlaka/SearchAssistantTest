package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.favorite;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.AddFavoriteEvent;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search.SearchFragment;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search.SearchResultAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
public class FavoriteFragment extends MvpAppCompatFragment implements FavoriteView{

    //Moxy usage for MVP
    @InjectPresenter
    FavoritePresenter favoritePresenter;

    @BindView(R.id.search_recyclerview)
    RecyclerView searchResultList;

    private SearchResultAdapter searchResultAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private String TAG = "goCR";
    private int i = 0;

    public static FavoriteFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        ButterKnife.bind(this, view);
        onCreateSearchResult ();
        return view;
    }

    private void onCreateSearchResult() {
        ArrayList<SearchItem> searchItems = new ArrayList<>();
        for (int i = 0; i<2;i++){
            searchItems.add(new SearchItem("number "+i, ""));
        }
        searchResultAdapter = new SearchResultAdapter(searchItems,mItemListener );

        //init recycler view list
        searchResultList.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        searchResultList.setLayoutManager(linearLayoutManager);

        searchResultList.setAdapter(searchResultAdapter);
    }

    SearchFragment.SearchItemListener mItemListener = new SearchFragment.SearchItemListener() {
        @Override
        public void onFavoriteCheck(SearchItem searchItem) {
        }

        @Override
        public void onFavoriteUnCheck(SearchItem searchItem) {

        }
    };

    @Override
    public void showToast(String onResult) {
        Toast.makeText(getActivity(), onResult, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AddFavoriteEvent event) {
        i++;
        Log.d(TAG, "onMessageEvent"+i);
        showToast("OnResult");
    };
}