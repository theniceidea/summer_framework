package com.testcomp.entities0.bizdemo.summer_dev2;

import java.util.List;
import java.math.BigDecimal;
import java.sql.Timestamp;
/**
* demo table
*/
public interface IDs2demotableList extends List<Ds2demotable>{
    /**
    * id
    */
    List<Integer> idList();
    /**
    * id
    */
    List<Integer> idList_skipNull();
    /**
    * 标题
    */
    List<String> titleList();
    /**
    * 标题
    */
    List<String> titleList_skipNull();
    /**
    * 标题
    */
    List<String> titleList_skipBlank();
    /**
    * 类型
    */
    List<String> typeList();
    /**
    * 类型
    */
    List<String> typeList_skipNull();
    /**
    * 类型
    */
    List<String> typeList_skipBlank();
    /**
    * 数量
    */
    List<Integer> numList();
    /**
    * 数量
    */
    List<Integer> numList_skipNull();
    /**
    * 数量2
    */
    List<BigDecimal> num2List();
    /**
    * 数量2
    */
    List<BigDecimal> num2List_skipNull();
    /**
    * 数量3
    */
    List<Double> num3List();
    /**
    * 数量3
    */
    List<Double> num3List_skipNull();
    /**
    * delete status
    */
    List<Integer> deleteStatusList();
    /**
    * delete status
    */
    List<Integer> deleteStatusList_skipNull();
    /**
    * enable status
    */
    List<String> enableStatusList();
    /**
    * enable status
    */
    List<String> enableStatusList_skipNull();
    /**
    * enable status
    */
    List<String> enableStatusList_skipBlank();
    /**
    * status
    */
    List<Integer> statusList();
    /**
    * status
    */
    List<Integer> statusList_skipNull();
    /**
    * status
    */
    List<String> status2List();
    /**
    * status
    */
    List<String> status2List_skipNull();
    /**
    * status
    */
    List<String> status2List_skipBlank();
    /**
    * 创建时间
    */
    List<Timestamp> creationDateList();
    /**
    * 创建时间
    */
    List<Timestamp> creationDateList_skipNull();
}
