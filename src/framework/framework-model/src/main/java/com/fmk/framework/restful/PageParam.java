package com.fmk.framework.restful;

public class PageParam<T> extends PageParamBase<T> {

    private int page;

    private int size;

    public void pagesize2startlimit(){
        if(page<1) page=1;
        setStart((page-1)*size);
        setLimit(size);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
