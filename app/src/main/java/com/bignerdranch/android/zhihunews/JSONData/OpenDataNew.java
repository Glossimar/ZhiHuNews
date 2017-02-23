package com.bignerdranch.android.zhihunews.JSONData;

import java.util.List;

/**
 * Created by LENOVO on 2017/2/20.
 */

public class OpenDataNew {
    /**
     * creatives : [{"url":"https://pic4.zhimg.com/v2-d68cefa1320e27b6fc2bd0fdaec760b7.jpg","text":"Alessio Lin","start_time":1487594632,"impression_tracks":["https://sugar.zhihu.com/track?vs=1&ai=3240&ut=&cg=2&ts=1487594632.1&si=39f51b41c2e64a92929c8fa0849da482&lu=0&hn=ad-engine.ad-engine.aa87fe3d&at=impression&pf=PC&az=11&sg=951a78347ee7f6a26aa9e276eb9078ff"],"type":0,"id":"3240"}]
     */

    private List<CreativesEntity> creatives;

    public void setCreatives(List<CreativesEntity> creatives) {
        this.creatives = creatives;
    }

    public List<CreativesEntity> getCreatives() {
        return creatives;
    }

    public static class CreativesEntity {
        /**
         * url : https://pic4.zhimg.com/v2-d68cefa1320e27b6fc2bd0fdaec760b7.jpg
         * text : Alessio Lin
         * start_time : 1487594632
         * impression_tracks : ["https://sugar.zhihu.com/track?vs=1&ai=3240&ut=&cg=2&ts=1487594632.1&si=39f51b41c2e64a92929c8fa0849da482&lu=0&hn=ad-engine.ad-engine.aa87fe3d&at=impression&pf=PC&az=11&sg=951a78347ee7f6a26aa9e276eb9078ff"]
         * type : 0
         * id : 3240
         */

        private String url;
        private String text;
        private int start_time;
        private int type;
        private String id;
        private List<String> impression_tracks;

        public void setUrl(String url) {
            this.url = url;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setImpression_tracks(List<String> impression_tracks) {
            this.impression_tracks = impression_tracks;
        }

        public String getUrl() {
            return url;
        }

        public String getText() {
            return text;
        }

        public int getStart_time() {
            return start_time;
        }

        public int getType() {
            return type;
        }

        public String getId() {
            return id;
        }

        public List<String> getImpression_tracks() {
            return impression_tracks;
        }
    }
}
