package com.fmk.framework.excel;

import com.fmk.framework.summer.BasicSummer;

import java.util.List;

public class ExcelReadMod extends BasicSummer<List<ExcelRow>> {
    private int headIndex=-1;
    private int startIndex=-1;
    private int endIndex=-1;
    private int sheetIndex=0;
    private Class<? extends ExcelRow> kls;

    public Class<? extends ExcelRow> getKls() {
        return kls;
    }

    public void setKls(Class<? extends ExcelRow> kls) {
        this.kls = kls;
    }

    public int getHeadIndex() {
        return headIndex;
    }

    public void setHeadIndex(int headIndex) {
        this.headIndex = headIndex;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
