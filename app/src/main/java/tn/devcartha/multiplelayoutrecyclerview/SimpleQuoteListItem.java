package tn.devcartha.multiplelayoutrecyclerview;

/**
 * Created by Souhail on 7/22/2016.
 */
public class SimpleQuoteListItem extends ListItem{
    private int imageResId;
    private boolean favourite = false;

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
