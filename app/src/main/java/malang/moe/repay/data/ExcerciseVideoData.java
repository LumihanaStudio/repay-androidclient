package malang.moe.repay.data;

/**
 * Created by kotohana5706 on 2015. 11. 21..
 * Copyright by Sunrin Internet High School EDCAN
 * All rights reversed.
 */
public class ExcerciseVideoData {
    public String imageLink;
    public String title;
    public String description;
    public String date;
    public String channelTitle;
    public String videoId;
    public ExcerciseVideoData(String imageLink, String title, String description, String channelTitle, String videoId) {
        this.imageLink = imageLink;
        this.title = title;
        this.description = description;
        this.channelTitle = channelTitle;
        this.videoId = videoId;
    }
}
