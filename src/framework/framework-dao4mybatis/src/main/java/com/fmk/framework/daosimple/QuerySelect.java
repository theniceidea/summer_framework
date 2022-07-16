package com.fmk.framework.daosimple;

import java.util.List;

public interface QuerySelect {

    String toSqlString();
    String toSqlString(int start, int limit);
    String toCountSqlString();

    List<Object> getParameters();
}
