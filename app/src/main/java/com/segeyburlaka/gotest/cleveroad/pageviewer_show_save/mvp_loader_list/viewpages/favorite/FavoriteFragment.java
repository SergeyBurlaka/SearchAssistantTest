package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.event.AddFavoriteEvent;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.FavoriteItemAdapter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search.SearchFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.arlib.floatingsearchview.util.Util.dpToPx;

/**
 * Created by Belal on 2/3/2016.
 */

//Our class extending fragment
public class FavoriteFragment extends MvpAppCompatFragment implements FavoriteView{

    //Moxy usage for MVP
    @InjectPresenter
    FavoritePresenter favoritePresenter;

    @InjectPresenter
    QueryCashFavoritePresenter queryCashFavoritePresenter;

    @BindView(R.id.search_recyclerview)
    RecyclerView searchResultList;

    private FavoriteItemAdapter searchResultAdapter;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

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

    @ProvidePresenter
    FavoritePresenter provideFavoritePresenter() {
        return new FavoritePresenter (getLoaderManager());
    }

    @ProvidePresenter
    QueryCashFavoritePresenter provideQueryCashFavoritePresenter() {
        return new QueryCashFavoritePresenter (getLoaderManager());
    }

    private void onCreateSearchResult() {
        ArrayList<SearchItem> searchItems = new ArrayList<>();

        searchResultAdapter = new FavoriteItemAdapter(searchItems,mItemListener );
        searchResultList.setHasFixedSize(true);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        searchResultList.setLayoutManager(mLayoutManager);
        searchResultList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        searchResultList.setItemAnimator(new DefaultItemAnimator());
        searchResultList.setAdapter(searchResultAdapter);
        queryCashFavoritePresenter.getFavoriteFromCash();
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
    public void swapAdapter(SearchItem data) {
        searchResultAdapter.swap(data);
    }

    @Override
    public void swapAdapterFavoriteCash(List<SearchItem> data) {
        searchResultAdapter.swapOnCash(data);
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
        favoritePresenter.getNewFavorite(event.getIdSearchItem());
    };
}