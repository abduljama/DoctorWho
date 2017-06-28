package com.abduljama.doctorwho;

/**
 * Created by abduljama on 1/31/17.
 */

public class ExploreModel {
     String  categoryname;
    int category_id ;
    int  thumbnail;


    public ExploreModel(String categoryname, int thumbnail) {

        this.categoryname = categoryname;
        this.thumbnail = thumbnail;
    }


    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
