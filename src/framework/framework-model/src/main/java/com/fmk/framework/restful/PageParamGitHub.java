package com.fmk.framework.restful;

public class PageParamGitHub<T> extends PageParamBase<T> {

    private int page;

    private int perPage;

    public void pagesize2startlimit(){
        if(perPage<=0) perPage=10;
        if(page<1) page=1;
        setStart((page-1)*perPage);
        setLimit(perPage);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
