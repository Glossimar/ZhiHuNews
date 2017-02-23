package com.bignerdranch.android.zhihunews.JSONData;

import java.util.List;

/**
 * Created by LENOVO on 2017/1/31.
 */

public class MainData {
    public String date;
    public List<Stories> stories;
    public List<Top_Stories> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Stories> getStories() {
        return stories;
    }

    public void setStories(List<Stories> stories) {
        this.stories = stories;
    }

    public List<Top_Stories> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<Top_Stories> top_stories) {
        this.top_stories = top_stories;
    }

    static public  class Stories{
        private List<String> images;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
    static public class Top_Stories{
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
