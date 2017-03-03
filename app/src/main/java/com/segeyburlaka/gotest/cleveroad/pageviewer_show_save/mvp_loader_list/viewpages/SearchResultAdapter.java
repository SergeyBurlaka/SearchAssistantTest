package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.di.SearchApp;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.viewpages.search.SearchFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Operator on 28.02.2017.
 */

public class SearchResultAdapter extends RecyclerView.Adapter {


    List<SearchItem> searchItemList;

     final int VIEW_PROG = 0;
     final int VIEW_IMG = 1;

    SearchFragment.SearchItemListener mItemListener;

    public SearchResultAdapter(List<SearchItem> searchItemList,  SearchFragment.SearchItemListener mItemListener){

        this.mItemListener = mItemListener;
        this.searchItemList = searchItemList;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {


        public SearchViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class SearchListViewHolder extends RecyclerView.ViewHolder {

        @Inject
        Context context;

        @BindView(R.id.link_image)
        TextView linkImageText;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.thumbnail)
        ImageView searchImg;

        @BindView(R.id.item_relative_view)
        RelativeLayout item_relative_view;

        @BindView(R.id.checkbox_item)
        CheckBox checkBoxULike;

        SearchListViewHolder(View itemView) {
            super(itemView);
            SearchApp.getComponent().inject(this);
            ButterKnife.bind(this, itemView);


        }

        void setOnClickListener (final String URL){

           searchImg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(Intent.ACTION_VIEW);
                   i.setData(Uri.parse(URL));
                   i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   context.startActivity(i);
               }
           });



       }

        private void setOnCheckBoxListener(
                final SearchItem searchItem,
                final SearchFragment.SearchItemListener mItemListener
        ){
            //on user like-click
            checkBoxULike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    searchItem.setLiked(cb.isChecked());

                    if(cb.isChecked()){
                    //With the aim of rid of links from List
                    mItemListener.onFavoriteCheck(new SearchItem(searchItem));
                    }
                }
            });
        }


        void loadPhoto(String path) {
            try {
                Picasso.with(context)
                        .load(path)
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
    }

    public void swap(SearchItem data){
        if(searchItemList.contains(data)) return;
        searchItemList.add(data);
        notifyDataSetChanged();
    }



    public void swapOnGoogleResult(List <SearchItem> data){
       //searchItemList.clear();
        searchItemList.addAll(data);
        notifyDataSetChanged();
    }

    public void clear (){
        searchItemList.clear();
        notifyDataSetChanged();
    }

    @Override
    public  RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder vh;

        if (viewType == VIEW_IMG) {

            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.serarch_result_item, viewGroup, false);
            vh = new SearchListViewHolder(v);

        } else {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_loading_item, viewGroup, false);

            vh = new ProgressViewHolder(v);
        }
        return vh;

    }

    @Override
    public void onBindViewHolder( RecyclerView.ViewHolder searchViewHolder, int position) {
        if (searchViewHolder instanceof SearchListViewHolder) {
        ((SearchListViewHolder)  searchViewHolder).title.setText(searchItemList.get(position).getTitle());
        ((SearchListViewHolder)  searchViewHolder).linkImageText.setText(searchItemList.get(position).getImageLink());
        ((SearchListViewHolder)  searchViewHolder).linkImageText.setLinkTextColor(Color.BLUE);
        ((SearchListViewHolder)  searchViewHolder).loadPhoto(searchItemList.get(position).getImageLink());

        ((SearchListViewHolder)  searchViewHolder).checkBoxULike.setChecked(searchItemList.get(position).getLiked());
        ((SearchListViewHolder)  searchViewHolder).setOnCheckBoxListener(searchItemList.get(position), mItemListener);
         ((SearchListViewHolder)searchViewHolder).setOnClickListener(searchItemList.get(position).getContextLink());

        } else {
            ((ProgressViewHolder) searchViewHolder).progressBar.setIndeterminate(true);
        }

    }


    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public ProgressViewHolder(View v) {
            super(v);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar1);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if ( searchItemList.size()>position+1) {
                return VIEW_IMG;

        } else {
            return VIEW_PROG;
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return searchItemList.size();
    }
}
