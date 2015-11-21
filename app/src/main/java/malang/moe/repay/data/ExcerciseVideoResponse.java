package malang.moe.repay.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class ExcerciseVideoResponse {
    public String nextPageToken;
    public String prevPageToken;
    public Info pageInfo;
    public List<Items> items;
    public ExcerciseVideoResponse(String nextPageToken, String prevPageToken, Info pageInfo, List<Items> items){
        this.nextPageToken =nextPageToken;
        this.prevPageToken = prevPageToken;
        this.pageInfo = pageInfo;
        this.items = items;
    }

    class Info {
        public int totalResults;
        public int resultsPerPage;

        public Info(int totalResults, int resultsPerPage) {
            this.totalResults = totalResults;
            this.resultsPerPage = resultsPerPage;
        }
    }

    public class Items {
        public String kind;
        public ID id;
        public Snippet snippet;

        public Items(String kind, ID id, Snippet snippet){
            this.kind = kind;
            this.id = id;
            this.snippet = snippet;
        }
        public class Snippet {
            public String publishedAt;
            public String title;
            public String description;
            public Thumbnails thumbnails;
            public String channelTitle;
            public Snippet(String publishedAt, String title, String description, Thumbnails thumbnails, String channelTitle){
                this.publishedAt = publishedAt;
                this.title = title;
                this.thumbnails = thumbnails;
                this.description = description;
                this.channelTitle = channelTitle;
            }
            public class Thumbnails {
                @SerializedName("default")
                public Def def;
                public Mid medium;
                public High high;
                public Thumbnails(Def def, Mid medium, High high){
                    this.def = def;
                    this.medium = medium;
                    this.high = high;
                }
                public class Def {
                    public String url;

                    public Def(String url) {
                        this.url = url;
                    }
                }
                public class Mid{
                    public String url;
                    public Mid(String url){
                        this.url = url;
                    }
                }
                public class High{
                    public String url;
                    public High(String url){
                        this.url = url;
                    }
                }
            }
        }

        public class ID {
            public String kind;
            public String videoId;

            public ID(String kind, String videoId) {
                this.kind = kind;
                this.videoId = videoId;
            }
        }
    }
}
