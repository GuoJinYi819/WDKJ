package com.wd.tech.bean.qzjbean.consultationlist;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/20 18:55
 * @Description: 用途：完成特定功能
 */
public class ConResultBean {
    private long releaseTime;
    private String source;
    private String summary;
    private String thumbnail;
    private String title;
    private int whetherAdvertising;
    private int whetherCollection;
    private int share;
    private int collection;
    private int id;

    public int getWhetherCollection() {
        return whetherCollection;
    }

    public void setWhetherCollection(int whetherCollection) {
        this.whetherCollection = whetherCollection;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setWhetherAdvertising(int whetherAdvertising) {
        this.whetherAdvertising = whetherAdvertising;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public void setCollection(int collection) {
        this.collection = collection;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public String getSource() {
        return source;
    }

    public String getSummary() {
        return summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public int getWhetherAdvertising() {
        return whetherAdvertising;
    }

    public int getShare() {
        return share;
    }

    public int getCollection() {
        return collection;
    }
}
