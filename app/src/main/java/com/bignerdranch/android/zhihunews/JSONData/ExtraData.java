package com.bignerdranch.android.zhihunews.JSONData;

/**
 * Created by LENOVO on 2017/2/2.
 */

public class ExtraData {
    /**
     * long_comments : 48
     * popularity : 2851
     * short_comments : 251
     * comments : 299
     */

    private int long_comments;
    private int popularity;
    private int short_comments;
    private int comments;

    public void setLong_comments(int long_comments) {
        this.long_comments = long_comments;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public void setShort_comments(int short_comments) {
        this.short_comments = short_comments;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public int getLong_comments() {
        return long_comments;
    }

    public int getPopularity() {
        return popularity;
    }

    public int getShort_comments() {
        return short_comments;
    }

    public int getComments() {
        return comments;
    }
}
