package com.example.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.EventListener;

public class ShoppingItemAdapter extends RecyclerView.Adapter<ShoppingItemAdapter.ViewHolder> implements Filterable {

    private ArrayList<ShoppingItem> mShoppingItemsData;
    private ArrayList<ShoppingItem> mShoppingItemsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    ShoppingItemAdapter(Context context, ArrayList<ShoppingItem> itemsData) {
        this.mShoppingItemsData = itemsData;
        this.mShoppingItemsDataAll = itemsData;
        this.mContext = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder( ShoppingItemAdapter.ViewHolder holder, int position) {
        ShoppingItem currentItem = mShoppingItemsData.get(position);

        holder.bindTo(currentItem);

        if(holder.getAbsoluteAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext,R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAbsoluteAdapterPosition();
        }

    }

    @Override
    public int getItemCount() {

        return mShoppingItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return shoppingFilter;
    }

    private Filter shoppingFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<ShoppingItem> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (charSequence == null || charSequence.length() == 0){
                results.count = mShoppingItemsDataAll.size();
                results.values = mShoppingItemsDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (ShoppingItem item:mShoppingItemsDataAll) {
                    if (item.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mShoppingItemsData = (ArrayList)filterResults.values;
            notifyDataSetChanged();

        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private ImageView mItemImage;
        private RatingBar mRatingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText =  itemView.findViewById(R.id.subTitle);
            mPriceText = itemView.findViewById(R.id.price);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mRatingBar = itemView.findViewById(R.id.ratingBar);


        }

        public void bindTo(ShoppingItem currentItem) {
            mTitleText.setText(currentItem.getName());
            mInfoText.setText(currentItem.getInfo());
            mPriceText.setText(currentItem.getPrice());
            mRatingBar.setRating(currentItem.getRatedInfo());


            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);
            itemView.findViewById(R.id.add_to_cart).setOnClickListener(view -> ((ShopListActivity)mContext).updateAlertIcon(currentItem));
            itemView.findViewById(R.id.delete).setOnClickListener(view -> ((ShopListActivity)mContext).deleteItem(currentItem));
        }
    }
}

