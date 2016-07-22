package tn.devcartha.multiplelayoutrecyclerview;

/**
 * Created by Souhail on 7/22/2016.
 */
public class CardQuoteListItem extends ListItem{
    private int imageResId;
    private String subTitle;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
