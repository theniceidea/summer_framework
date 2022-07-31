package com.testcomp.entities0.bizdemo.summer_dev2;

import java.util.List;
/**
* demo table
*/
public interface IList extends List<Ds2demotable>{
    /**
    * id
    */
    List<Integer> idList();
    /**
    * 标题
    */
    List<String> titleList();
    /**
    * 类型
    */
    List<String> typeList();
    /**
    * 数量
    */
    List<Integer> numList();
    /**
    * 数量2
    */
    List<BigDecimal> num2List();
    /**
    * 数量3
    */
    List<Double> num3List();
    /**
    * delete status
    */
    List<Integer> deleteStatusList();
    /**
    * enable status
    */
    List<String> enableStatusList();
    /**
    * status
    */
    List<Integer> statusList();
    /**
    * status
    */
    List<String> status2List();
    /**
    * 创建时间
    */
    List<Timestamp> creationDateList();
}
