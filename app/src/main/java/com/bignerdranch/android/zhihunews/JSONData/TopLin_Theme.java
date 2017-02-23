package com.bignerdranch.android.zhihunews.JSONData;

import java.util.List;

/**
 * Created by LENOVO on 2017/1/24.
 */

//http://news-at.zhihu.com/api/4/theme/+id
public class TopLin_Theme {
    private List<StoriesBean> stories;
    private List<EditorsBean> editors;
    private String description;
    private String background;
    private int color;
    private String name;
    private String image;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EditorsBean> getEditors() {
        return editors;
    }

    public void setEditors(List<EditorsBean> editors) {
        this.editors = editors;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    static public class StoriesBean{
        private int type;
        private int id;
        private String title;
        private List<String> images;

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {

            return type;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<String> getImages() {
            return images;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }
    static public class EditorsBean{
        private String url;
        private String bio;
        private int id;
        private String avatar;
        private String name;

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getBio() {
            return bio;
        }

        public void setBio(String bio) {
            this.bio = bio;
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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
