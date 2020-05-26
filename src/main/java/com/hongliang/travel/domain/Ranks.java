package com.hongliang.travel.domain;

/**
 * @author Hongliang Zhu
 * @create 2020-05-25 16:41
 */
public class Ranks {

    private int rid; // 路线id
    private int cnt; // 被收藏的次数

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }
}
