package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.event.OnSearchEvent;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.SearchResultAdapter;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.favorite.GridSpacingItemDecoration;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static com.arlib.floatingsearchview.util.Util.dpToPx;

//Our class extending fragment
public class SearchFragment extends MvpAppCompatFragment implements SearchView{

    private static final String TAG = "goCR";
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private boolean loading = false;

    private String currentQuery  = "";

    //Moxy usage for MVP
    @InjectPresenter
    SearchPresenter searchPresenter;

    @InjectPresenter
    GoogleCustomsearchPresenter googleCustomsearchPresenter;

    @BindView(R.id.search_recyclerview)
    RecyclerView searchResultList;

    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;



    @ProvidePresenter
    SearchPresenter provideSearchPresenter() {
        return new SearchPresenter (getLoaderManager());
    }

    @ProvidePresenter
    GoogleCustomsearchPresenter provideGoogleCustomsearchPresenter() {
        return new GoogleCustomsearchPresenter (getLoaderManager());
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
        EventBus.getDefault().register(this);

        mPage = getArguments().getInt(ARG_PAGE);

    }




    private void onGoogleSearch (@NonNull String query){

        Log.d(TAG, " onGoogleSearch "+ searchResultAdapter.getItemCount());

        googleCustomsearchPresenter.getGoogleSearch(query, searchResultAdapter.getItemCount() + 1);
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

    //todo set adapter result here
    private void onCreateSearchResult() {

        Random r = new Random();


        ArrayList<SearchItem> searchItems = new ArrayList<>();
       /* for (int i = 0; i<100;i++){
            long number = lowerLimit+((long)(r.nextDouble()*(upperLimit-lowerLimit)));
            searchItems.add(new SearchItem(number,"number "+i, "", false, new Date()));
        }*/
        searchResultAdapter = new SearchResultAdapter(searchItems, mItemListener);

        //init recycler view list
        searchResultList.setHasFixedSize(true);

        final GridLayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        searchResultList.setLayoutManager(mLayoutManager);
        searchResultList.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        searchResultList.setItemAnimator(new DefaultItemAnimator());

        searchResultList.setAdapter(searchResultAdapter);


        //loading = false;
        searchResultList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) {
                  //  Log.d(TAG, " addOnScrollListener "+ dy );

                        visibleItemCount = mLayoutManager.getChildCount();
                        totalItemCount = mLayoutManager.getItemCount();

                        pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {

                       // Log.d(TAG, " addOnScrollListener___ "+ "summa = "+(visibleItemCount + pastVisibleItems)+" total " +totalItemCount);

                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {



                            loading = true;
                            Toast.makeText(getActivity(), "get new", Toast.LENGTH_SHORT).show();

                            onGoogleSearch (currentQuery);

                           // runAsyncTask(eText.getText().toString(), mGObjectAdapter.getItemCount() + 1);
                        }
                    }
                }
            }
        });



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

    @Override
    public void showToast() {
        Toast.makeText(getActivity(), "loader", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void swapGoogleResult(List<SearchItem> searchResults) {
        loading = false;
        searchResultAdapter.swapOnGoogleResult(searchResults);
    }



    public interface SearchItemListener {

        void onFavoriteCheck(SearchItem clickedTask);

        void onFavoriteUnCheck (SearchItem clickedTask);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(! EventBus.getDefault().isRegistered(SearchFragment.this)){
        EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(OnSearchEvent event) {
        this.currentQuery = event.getQuery();
        Log.d(TAG,"onEvent(OnSearchEvent event)" );

        searchResultAdapter.clear ();
        onGoogleSearch (event.getQuery());
    };
}
