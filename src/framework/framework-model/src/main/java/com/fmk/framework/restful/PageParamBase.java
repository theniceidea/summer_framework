package com.fmk.framework.restful;

import com.fmk.framework.annotations.SkipDoc;
import com.fmk.framework.summer.BasicSummer;

public class PageParamBase<T> extends BasicSummer<T> {

    @SkipDoc
    private int start;

    @SkipDoc
    private int limit;


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
