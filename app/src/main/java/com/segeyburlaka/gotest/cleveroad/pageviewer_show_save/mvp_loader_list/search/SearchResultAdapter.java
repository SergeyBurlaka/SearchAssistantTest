package com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.search;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.R;
import com.segeyburlaka.gotest.cleveroad.pageviewer_show_save.mvp_loader_list.data.SearchItem;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Operator on 28.02.2017.
 */

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchViewHolder> {

    List<SearchItem> searchItemList;
    SearchFragment.SearchItemListener mItemListener;

    public SearchResultAdapter(List<SearchItem> searchItemList,  SearchFragment.SearchItemListener mItemListener){
        this.mItemListener = mItemListener;
        this.searchItemList = searchItemList;
    }

    public static class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView cv;

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.thumbnail)
        ImageView searchImg;

        @BindView(R.id.checkbox_item)
        CheckBox checkBoxULike;

        SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
                    mItemListener.onFavoriteCheck(searchItem);
                }
            });
        }
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.serarch_result_item, viewGroup, false);
        SearchViewHolder pvh = new SearchViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder searchViewHolder, int position) {
        searchViewHolder.title.setText(searchItemList.get(position).getTitle());
        searchViewHolder.checkBoxULike.setChecked(searchItemList.get(position).getLiked());
        searchViewHolder.setOnCheckBoxListener(searchItemList.get(position), mItemListener);
        //searchViewHolder.personAge.setText(persons.get(i).age);
        //searchViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
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
