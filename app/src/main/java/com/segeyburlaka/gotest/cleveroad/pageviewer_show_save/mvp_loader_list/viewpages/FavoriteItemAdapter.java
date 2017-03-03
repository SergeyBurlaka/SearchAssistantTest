package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search.SearchFragment;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Operator on 03.03.2017.
 */

public class FavoriteItemAdapter extends SearchResultAdapter {
    public FavoriteItemAdapter(List<SearchItem> searchItemList, SearchFragment.SearchItemListener mItemListener) {
        super(searchItemList, mItemListener);
    }


    public static class FavoriteListViewHolder extends SearchListViewHolder{

        FavoriteListViewHolder(View itemView) {
            super(itemView);
        }


         void loadPhoto(SearchItem searchItem) {
             String root ;

             if (isOnline()){
                 root = searchItem.getImageLink();
                 loadPicasso ( root);

             }else{
                 Toast.makeText(context, "no internet "+searchItem.getCashRootImage(), Toast.LENGTH_SHORT).show();
                 root = searchItem.getCashRootImage();
                 loadPicasso ( new File(root));
             }


        }

        private void loadPicasso(File file) {
            try {
                Picasso.with(context)
                        .load(file)
                        //.resize(115, 100)
                        //.centerCrop()
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .into(searchImg);
            }catch (IllegalArgumentException ex){
                //WORKAROUND/Picasso loading exception: Path must not be empty...
                //ok, so picasso try again!
                Picasso.with(context)
                        .load(R.drawable.image_placeholder)
                        //.resize(115, 100)
                        // .centerCrop()
                        .into(searchImg);
            }
        }

        private void loadPicasso(String root) {

            try {
                Picasso.with(context)
                        .load(root)
                        //.resize(115, 100)
                        //.centerCrop()
                        .placeholder(R.drawable.image_placeholder)
                        .error(R.drawable.image_placeholder)
                        .into(searchImg);
            }catch (IllegalArgumentException ex){
                //WORKAROUND/Picasso loading exception: Path must not be empty...
                //ok, so picasso try again!
                Picasso.with(context)
                        .load(R.drawable.image_placeholder)
                        //.resize(115, 100)
                        // .centerCrop()
                        .into(searchImg);
            }
        }

        public boolean isOnline() {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        }


    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder searchViewHolder, int position) {

        ((FavoriteListViewHolder)  searchViewHolder).title.setText(searchItemList.get(position).getTitle());
        ((FavoriteListViewHolder)  searchViewHolder).linkImageText.setText(searchItemList.get(position).getImageLink());
        ((FavoriteListViewHolder)  searchViewHolder).linkImageText.setLinkTextColor(Color.BLUE);
        ((FavoriteListViewHolder)  searchViewHolder).loadPhoto(searchItemList.get(position));
        ((FavoriteListViewHolder)  searchViewHolder).checkBoxULike.setVisibility(View.INVISIBLE);
        ((FavoriteListViewHolder)  searchViewHolder).setOnClickListener(searchItemList.get(position).getContextLink());

    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.serarch_result_item, viewGroup, false);
        vh = new FavoriteListViewHolder(v);
        return vh;

    }

    public void swapOnCash(List<SearchItem> data){
        searchItemList.clear();
        searchItemList.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {

            return VIEW_IMG;
    }
}
