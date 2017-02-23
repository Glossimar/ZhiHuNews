package com.bignerdranch.android.zhihunews.JSONData;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by LENOVO on 2017/1/17.
 */

public class  TopicLin {
    private List<OthersClass> others;
    private List<?> subscribed;
    private int limit;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<OthersClass> getOthersClass() {
        return others;
    }

    public void setOthersClass(List<OthersClass> others) {
        this.others = others;
    }

    public List<?> getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(List<?> subscribedl) {
        this.subscribed= subscribedl;
    }

    public static class OthersClass{
        private int color;
        private String thumbnail;
        private String description;
        private int id;
        private String name;
        private int num=1;

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
