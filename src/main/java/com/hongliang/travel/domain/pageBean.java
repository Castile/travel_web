package com.hongliang.travel.domain;

import java.util.List;

/**
 * 分页
 * @author Hongliang Zhu
 * @create 2020-05-17 16:04
 */
public class pageBean<T> {

    private Integer totalCount; //  总记录数
    private Integer totalPage;  // 总页数
    private Integer pageSize; // 每页显示的条数
    private Integer currentPage; // 当前页码

    private List<T> list; // 每页显示的数据集合

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
