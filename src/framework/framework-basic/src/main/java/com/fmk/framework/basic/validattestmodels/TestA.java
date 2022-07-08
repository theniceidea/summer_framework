package com.fmk.framework.basic.validattestmodels;

import com.fmk.framework.annotations.validation.*;
import com.fmk.framework.basic.ResourceType;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestA {

    @Blank(msg = "000000000023_请输入testa field1")
    private String field1;

    @AssertEnum(enumClass = ResourceType.class, extValue = "0", extDesc = "xxxx", msg = "000000000023_请输入testa field enum")
    private Integer fieldEnum;

    @DateAfter(msg = "000000000023_请输入testa timestamp")
    private Timestamp timestamp;

    @DateBefore(msg = "000000000023_请输入testa timestampBefore")
    private Timestamp timestampBefore;

    @DateRange(min = 1560493033536L, max=1560494233536L, msg = "000000000023_请输入testa timestampRange")
    private Timestamp timestampRange;

    @DateRangeNow(before = 1000*60L, after=1000*60L, msg = "000000000023_请输入testa timestampRangeNow")
    private Timestamp timestampRangeNow;

    @DateAfter(msg = "000000000023_请输入testa date")
    private Date dateAfter;

    @DateBefore(msg = "000000000023_请输入testa dateBefore")
    private Date dateBefore;

    @DateRange(min = 1560493033536L, max=1560494233536L, msg = "000000000023_请输入testa dateRange")
    private Date dateRange;

    @DateRangeNow(before = 1000*60L, after=1000*60L, msg = "000000000023_请输入testa dateRangeNow")
    private Date dateRangeNow;

    @DecimalSize(min = "10", max="1000", msg = "000000000023_请输入testa decimalRangeNow")
    private BigDecimal bigDecimal;

    @Size(min = 1, max = 100, msg = "000000000022_testa field2")
    private int field2;

    @Length(value = 10, msg = "000000000023_请输入testa length")
    private String length;

    @LengthRange(min = 2, max = 10, msg = "000000000023_请输入testa lengthRange")
    private String lengthRange;

    @NotNull(msg = "000000000023_请输入testa notnull")
    private String notnull;

    @Null(msg = "000000000023_请输入testa null")
    private String nullString;

    @Pattern(value = "\\d+", msg = "000000000023_请输入testa pattern")
    private String pattern;

    @ListNotEmpty(msg = "000000000024_testa field list")
    private List<TestB> list;

    @ListNotEmpty(msg = "000000000021_testa field map")
    private HashMap<String, TestB> map;

    @Follow(kls = TestB.class, field = TestB._field1, anno = NotBlank.class)
    private String followField;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }

    public List<TestB> getList() {
        return list;
    }

    public void setList(List<TestB> list) {
        this.list = list;
    }

    public HashMap<String, TestB> getMap() {
        return map;
    }

    public void setMap(HashMap<String, TestB> map) {
        this.map = map;
    }

    public Integer getFieldEnum() {
        return fieldEnum;
    }

    public void setFieldEnum(Integer fieldEnum) {
        this.fieldEnum = fieldEnum;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Timestamp getTimestampBefore() {
        return timestampBefore;
    }

    public void setTimestampBefore(Timestamp timestampBefore) {
        this.timestampBefore = timestampBefore;
    }

    public Timestamp getTimestampRange() {
        return timestampRange;
    }

    public void setTimestampRange(Timestamp timestampRange) {
        this.timestampRange = timestampRange;
    }

    public Timestamp getTimestampRangeNow() {
        return timestampRangeNow;
    }

    public void setTimestampRangeNow(Timestamp timestampRangeNow) {
        this.timestampRangeNow = timestampRangeNow;
    }

    public Date getDateBefore() {
        return dateBefore;
    }

    public void setDateBefore(Date dateBefore) {
        this.dateBefore = dateBefore;
    }

    public Date getDateRange() {
        return dateRange;
    }

    public void setDateRange(Date dateRange) {
        this.dateRange = dateRange;
    }

    public Date getDateRangeNow() {
        return dateRangeNow;
    }

    public void setDateRangeNow(Date dateRangeNow) {
        this.dateRangeNow = dateRangeNow;
    }

    public Date getDateAfter() {
        return dateAfter;
    }

    public void setDateAfter(Date dateAfter) {
        this.dateAfter = dateAfter;
    }

    public BigDecimal getBigDecimal() {
        return bigDecimal;
    }

    public void setBigDecimal(BigDecimal bigDecimal) {
        this.bigDecimal = bigDecimal;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getLengthRange() {
        return lengthRange;
    }

    public void setLengthRange(String lengthRange) {
        this.lengthRange = lengthRange;
    }

    public String getNotnull() {
        return notnull;
    }

    public void setNotnull(String notnull) {
        this.notnull = notnull;
    }

    public String getNullString() {
        return nullString;
    }

    public void setNullString(String nullString) {
        this.nullString = nullString;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getFollowField() {
        return followField;
    }

    public void setFollowField(String followField) {
        this.followField = followField;
    }
}
