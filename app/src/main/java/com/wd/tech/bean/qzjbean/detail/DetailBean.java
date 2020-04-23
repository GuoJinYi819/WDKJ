package com.wd.tech.bean.qzjbean.detail;

import java.io.Serializable;
import java.util.List;

/**
 * ClassName: WdDetroy
 *
 * @author 作者 : Quzijun
 * @version 创建时间：2020/4/23 20:08
 * @Description: 用途：完成特定功能
 */
public class DetailBean implements Serializable {

    /**
     * result : {"comment":10,"content":"<div id=\"content\" style=\"overflow-x: hidden; word-break: break-all;\">\r\n<p>　　双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。<\/p>\r\n<p>　　知名调研公司奥维云网(AVC)数据显示，今年三季度天猫精灵、百度、小米的市场份额分别为33.3%、33.1%、26.9%，天猫精灵继续保持国内第一的位置，百度在今年二季度超过小米之后，紧追第一不舍。<\/p>\r\n<center><img src=\"../../upload/2019-11/191106140480851.png\" alt=\"\" width=\"554\" height=\"327\"><\/center>\r\n<p>　　从2019年累计销量数据来看，三大品牌占据国内市场超过90%的市场份额，形成垄断局面，包括华为、叮咚在内的其他品牌几乎没有太大生存空间。三家当中，天猫精灵以34.2%的份额大幅领先。<\/p>\r\n<p>　　从产品布局来看，2019年三大品牌产品不断推出出新。天猫精灵在方糖的基础上迭代升级出方糖R，开辟天猫精灵IN糖、天猫精灵QUEEN智能美妆镜、天猫精灵CC和CCL带屏音箱等全新的产品系列;百度在屏幕音箱上发布了小度在家1S和小度在家1C，无屏音箱则是功能升级的小度1S和大金刚;小米也推出了首款带屏音箱\u2014\u2014小爱触屏音箱，同时带来了主打音质的小爱音箱HD、mini音箱升级版小爱音箱play和小爱音箱遥控版。<\/p>\r\n<p>　　智能音箱品类不断扩张背后，是为了满足不同用户群对产品的差异化需求。入门款音箱满足的是大多数消费者最基本的需求，而对工业设计、功能体验、音质要求更高的消费者，需要有更多的不同的产品。<\/p>\r\n<center><img src=\"../../upload/2019-11/191106140480852.png\" alt=\"\" width=\"530\" height=\"354\"><\/center>\r\n<p>　　与2018年价格战为主的市场不同，2019年三大品牌均在探索给用户带来的价值感。天猫精灵还推出糖粉计划，与哆啦A梦、百事可乐等IP和潮牌合作，满足年轻人潮化、定制化的需求。<\/p>\r\n<p><strong>特别提醒：<\/strong>本网内容转载自其他媒体，目的在于传递更多信息，并不代表本网赞同其观点。其原创性以及文中陈述文字和内容未经本站证实，对本文以及其中全部或者部分内容、文字的真实性、完整性、及时性本站不作任何保证或承诺，并请自行核实相关内容。本站不承担此类作品侵权行为的直接责任及连带责任。如若本网有任何内容侵犯您的权益，请及时<a href=\"http://www.itbear.com.cn/sp4.aspx\">联系我们<\/a>，本站将会在24小时内处理完毕。<\/p>\r\n<\/div>","id":65,"informationList":[{"id":61,"thumbnail":"http://img.zhiding.cn/5/140/li9bFzxtlSE4k.jpg?rand=136","title":"区块链如何帮助人们更方便搞定法律服务？"},{"id":30,"thumbnail":"https://img.huxiucdn.com/article/cover/201809/26/184331152829.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"李开复《华尔街日报》专栏：AI革命的人类前景"},{"id":10,"thumbnail":"https://img.huxiucdn.com/article/cover/201805/25/104319243667.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"腾讯须证明自己是一家科技公司"}],"integralCost":0,"plate":[{"id":1,"name":"电商消费"}],"praise":6,"readPower":1,"relatedArticles":"61,30,10","releaseTime":1572763883000,"share":28,"source":"ITBEAR科技资讯","summary":"双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。","thumbnail":"http://www.itbear.com.cn/upload/2019-11/191106140480852.png","title":"2019年Q3智能音箱数据发布，天猫精灵再次国内第一","whetherCollection":2,"whetherGreat":2,"yuanCost":0}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * comment : 10
         * content : <div id="content" style="overflow-x: hidden; word-break: break-all;">
         <p>　　双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。</p>
         <p>　　知名调研公司奥维云网(AVC)数据显示，今年三季度天猫精灵、百度、小米的市场份额分别为33.3%、33.1%、26.9%，天猫精灵继续保持国内第一的位置，百度在今年二季度超过小米之后，紧追第一不舍。</p>
         <center><img src="../../upload/2019-11/191106140480851.png" alt="" width="554" height="327"></center>
         <p>　　从2019年累计销量数据来看，三大品牌占据国内市场超过90%的市场份额，形成垄断局面，包括华为、叮咚在内的其他品牌几乎没有太大生存空间。三家当中，天猫精灵以34.2%的份额大幅领先。</p>
         <p>　　从产品布局来看，2019年三大品牌产品不断推出出新。天猫精灵在方糖的基础上迭代升级出方糖R，开辟天猫精灵IN糖、天猫精灵QUEEN智能美妆镜、天猫精灵CC和CCL带屏音箱等全新的产品系列;百度在屏幕音箱上发布了小度在家1S和小度在家1C，无屏音箱则是功能升级的小度1S和大金刚;小米也推出了首款带屏音箱——小爱触屏音箱，同时带来了主打音质的小爱音箱HD、mini音箱升级版小爱音箱play和小爱音箱遥控版。</p>
         <p>　　智能音箱品类不断扩张背后，是为了满足不同用户群对产品的差异化需求。入门款音箱满足的是大多数消费者最基本的需求，而对工业设计、功能体验、音质要求更高的消费者，需要有更多的不同的产品。</p>
         <center><img src="../../upload/2019-11/191106140480852.png" alt="" width="530" height="354"></center>
         <p>　　与2018年价格战为主的市场不同，2019年三大品牌均在探索给用户带来的价值感。天猫精灵还推出糖粉计划，与哆啦A梦、百事可乐等IP和潮牌合作，满足年轻人潮化、定制化的需求。</p>
         <p><strong>特别提醒：</strong>本网内容转载自其他媒体，目的在于传递更多信息，并不代表本网赞同其观点。其原创性以及文中陈述文字和内容未经本站证实，对本文以及其中全部或者部分内容、文字的真实性、完整性、及时性本站不作任何保证或承诺，并请自行核实相关内容。本站不承担此类作品侵权行为的直接责任及连带责任。如若本网有任何内容侵犯您的权益，请及时<a href="http://www.itbear.com.cn/sp4.aspx">联系我们</a>，本站将会在24小时内处理完毕。</p>
         </div>
         * id : 65
         * informationList : [{"id":61,"thumbnail":"http://img.zhiding.cn/5/140/li9bFzxtlSE4k.jpg?rand=136","title":"区块链如何帮助人们更方便搞定法律服务？"},{"id":30,"thumbnail":"https://img.huxiucdn.com/article/cover/201809/26/184331152829.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"李开复《华尔街日报》专栏：AI革命的人类前景"},{"id":10,"thumbnail":"https://img.huxiucdn.com/article/cover/201805/25/104319243667.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"腾讯须证明自己是一家科技公司"}]
         * integralCost : 0
         * plate : [{"id":1,"name":"电商消费"}]
         * praise : 6
         * readPower : 1
         * relatedArticles : 61,30,10
         * releaseTime : 1572763883000
         * share : 28
         * source : ITBEAR科技资讯
         * summary : 双11在即，国内三大智能音箱品牌均面向用户大幅让利。天猫精灵X1和小米AI音箱到手价格均降至199元，带屏音箱天猫精灵CCL和小度在家1S到手价格低至299元，竞争进入肉搏战。
         * thumbnail : http://www.itbear.com.cn/upload/2019-11/191106140480852.png
         * title : 2019年Q3智能音箱数据发布，天猫精灵再次国内第一
         * whetherCollection : 2
         * whetherGreat : 2
         * yuanCost : 0
         */

        private int comment;
        private String content;
        private int id;
        private int integralCost;
        private int praise;
        private int readPower;
        private String relatedArticles;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherCollection;
        private int whetherGreat;
        private int yuanCost;
        private List<InformationListBean> informationList;
        private List<PlateBean> plate;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIntegralCost() {
            return integralCost;
        }

        public void setIntegralCost(int integralCost) {
            this.integralCost = integralCost;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getReadPower() {
            return readPower;
        }

        public void setReadPower(int readPower) {
            this.readPower = readPower;
        }

        public String getRelatedArticles() {
            return relatedArticles;
        }

        public void setRelatedArticles(String relatedArticles) {
            this.relatedArticles = relatedArticles;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getYuanCost() {
            return yuanCost;
        }

        public void setYuanCost(int yuanCost) {
            this.yuanCost = yuanCost;
        }

        public List<InformationListBean> getInformationList() {
            return informationList;
        }

        public void setInformationList(List<InformationListBean> informationList) {
            this.informationList = informationList;
        }

        public List<PlateBean> getPlate() {
            return plate;
        }

        public void setPlate(List<PlateBean> plate) {
            this.plate = plate;
        }

        public static class InformationListBean {
            /**
             * id : 61
             * thumbnail : http://img.zhiding.cn/5/140/li9bFzxtlSE4k.jpg?rand=136
             * title : 区块链如何帮助人们更方便搞定法律服务？
             */

            private int id;
            private String thumbnail;
            private String title;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getThumbnail() {
                return thumbnail;
            }

            public void setThumbnail(String thumbnail) {
                this.thumbnail = thumbnail;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }

        public static class PlateBean {
            /**
             * id : 1
             * name : 电商消费
             */

            private int id;
            private String name;

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
}
