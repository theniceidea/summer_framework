package com.fmk.framework.excel;

import java.io.InputStream;

public class ExcelReadByStream extends ExcelReadMod {
    private InputStream inputStream;
    private boolean closeStream=true;

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public boolean isCloseStream() {
        return closeStream;
    }

    public void setCloseStream(boolean closeStream) {
        this.closeStream = closeStream;
    }
}
