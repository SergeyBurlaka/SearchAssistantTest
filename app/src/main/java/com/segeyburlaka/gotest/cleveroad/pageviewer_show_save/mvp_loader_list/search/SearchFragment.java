package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.pojo.SearchItem;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

//Our class extending fragment
public class SearchFragment extends MvpAppCompatFragment implements SearchView{

    //Moxy usage for MVP
    @InjectPresenter
    SearchPresenter searchPresenter;

    @BindView(R.id.search_recyclerview)
    RecyclerView searchResultList;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;

    @ProvidePresenter
    SearchPresenter provideSearchPresenter() {
        return new SearchPresenter (getLoaderManager());
    }

    private SearchResultAdapter searchResultAdapter;
    private LinearLayoutManager linearLayoutManager;

    public static SearchFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        SearchFragment fragment = new SearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
        //Toast.makeText(getActivity(), searchPresenter.getName(), Toast.LENGTH_SHORT).show();
        //searchPresenter.setName ("Naruto");
        //searchPresenter.start( getLoaderManager());
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        ButterKnife.bind(this, view);
        onCreateSearchResult ();
        return view;
    }

    private void onCreateSearchResult() {
        ArrayList<SearchItem> searchItems = new ArrayList<>();
        for (int i = 0; i<100;i++){
            searchItems.add(new SearchItem((long)i,"number "+i, "", false, new Date()));
        }
        searchResultAdapter = new SearchResultAdapter(searchItems, mItemListener);

        //init recycler view list
        searchResultList.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(getActivity());

        searchResultList.setLayoutManager(linearLayoutManager);

        searchResultList.setAdapter(searchResultAdapter);
    }

    /**
     * Listener for clicks on tasks in the ListView.
     */
    SearchItemListener mItemListener = new SearchItemListener() {
        
        @Override
        public void onFavoriteCheck(SearchItem searchItem) {
            searchPresenter.saveToFavorite(searchItem);
        }

        @Override
        public void onFavoriteUnCheck(SearchItem searchItem) {
            searchPresenter.removeFromFavorite (searchItem);
        }
    };

    public interface SearchItemListener {

        void onFavoriteCheck(SearchItem clickedTask);

        void onFavoriteUnCheck (SearchItem clickedTask);
    }
}
