package tn.devcartha.multiplelayoutrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Souhail on 7/22/2016.
 */
public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.QuotesHolder> {


    private final int SIMPLE = 0, CARD = 1;

    private List<ListItem> listData;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
        void onSecondaryIconClick(int p);
    }

    public QuotesAdapter(List<ListItem> listData, Context c) {
        inflater = LayoutInflater.from(c);
        this.listData = listData;
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    @Override
    public int getItemViewType(int position) {
        if (listData.get(position) instanceof SimpleQuoteListItem) {
            return SIMPLE;
        } else if (listData.get(position) instanceof CardQuoteListItem) {
            return CARD;
        }
        return -1;
    }

    @Override
    public QuotesAdapter.QuotesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_item, parent, false);
        QuotesHolder viewHolder;

        switch (viewType) {
            case SIMPLE:
                View v1 = inflater.inflate(R.layout.list_item, parent, false);
                viewHolder = new SimpleQuotesHolder(v1);
                break;
            case CARD:
                View v2 = inflater.inflate(R.layout.card_item, parent, false);
                viewHolder = new CardQuotesHolder(v2);
                break;
            default: viewHolder = new SimpleQuotesHolder(view);
        }
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(QuotesHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SIMPLE:
                SimpleQuotesHolder simpleHolder = (SimpleQuotesHolder) holder;
                configureSimpleView(simpleHolder, position);
                break;
            case CARD:
                CardQuotesHolder cardHolder = (CardQuotesHolder) holder;
                configureCardView(cardHolder,position);
                break;
        }
    }

    private void configureSimpleView(SimpleQuotesHolder simpleHolder, int position) {
        SimpleQuoteListItem item = (SimpleQuoteListItem)listData.get(position);
        simpleHolder.title.setText(item.getTitle());
        simpleHolder.subTitle.setText(item.getSubTitle());
        if (item.isFavourite()){
            simpleHolder.secondaryIcon.setImageResource(R.drawable.ic_star_black_24dp);
        } else {
            simpleHolder.secondaryIcon.setImageResource(R.drawable.ic_star_border_black_24dp);
        }
    }

    private void configureCardView(CardQuotesHolder cardHolder, int position) {
        CardQuoteListItem item = (CardQuoteListItem)listData.get(position);
        cardHolder.title.setText(item.getTitle());
        cardHolder.subTitle.setText(item.getSubTitle());
    }

    public void setListData(ArrayList<ListItem> exerciseList) {
        this.listData.clear();
        this.listData.addAll(exerciseList);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    class QuotesHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public QuotesHolder(View view) {
            super(view);
        }

        @Override
        public void onClick(View v) {
        }
    }

    class SimpleQuotesHolder extends QuotesHolder implements View.OnClickListener {

        ImageView thumbnail;
        ImageView secondaryIcon;
        TextView title;
        TextView subTitle;
        View container;

        public SimpleQuotesHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView) itemView.findViewById(R.id.im_item_icon);
            secondaryIcon = (ImageView) itemView.findViewById(R.id.im_item_icon_secondary);
            secondaryIcon.setOnClickListener(this);
            subTitle = (TextView) itemView.findViewById(R.id.lbl_item_sub_title);
            title = (TextView) itemView.findViewById(R.id.lbl_item_text);
            container = (View) itemView.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.cont_item_root) {
                itemClickCallback.onItemClick(getAdapterPosition());
            } else {
                itemClickCallback.onSecondaryIconClick(getAdapterPosition());
            }
        }
    }

    class CardQuotesHolder extends QuotesHolder implements View.OnClickListener {

        ImageView thumbnail;
        TextView title;
        TextView subTitle;
        View container;
        Button load;

        public CardQuotesHolder(View itemView) {
            super(itemView);
            thumbnail = (ImageView)itemView.findViewById(R.id.im_item_icon);
            subTitle = (TextView)itemView.findViewById(R.id.lbl_item_sub_title);
            title = (TextView)itemView.findViewById(R.id.lbl_item_text);
            container = (View)itemView.findViewById(R.id.cont_item_root);
            load = (Button)itemView.findViewById(R.id.btn_card_load);
            load.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickCallback.onItemClick(getAdapterPosition());
        }
    }
}
