//package com.fmk.framework.foundation.excel;
//
//import cn.hutool.poi.excel.ExcelUtil;
//import cn.hutool.poi.excel.sax.handler.RowHandler;
//import com.fmk.framework.excel.*;
//import com.google.common.collect.ImmutableList;
//import com.fmk.framework.basic.ReflectUtil;
//import com.fmk.framework.excel.*;
//import jodd.util.StringUtil;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Service;
//import org.summerframework.model.SummerService;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//@Service
//@SummerService
//public class ExcelReadService {
//
//    public List<? extends ExcelRow> excelReadByPath(ExcelReadByPath mod){
//        if(mod.getHeadIndex()>=0){
//            List<ExcelRow> excelRows = readHasHeaderByPath(mod);
//            mod.setSummerResult(excelRows);
//            return excelRows;
//        }
//        List<ExcelRow> excelRows = readNoHeaderByPath(mod);
//        mod.setSummerResult(excelRows);
//        return excelRows;
//    }
//    public List<? extends ExcelRow> excelReadByStream(ExcelReadByStream mod){
//        try {
//            if (mod.getHeadIndex() >= 0) {
//                List<ExcelRow> excelRows = readHasHeaderByStream(mod);
//                mod.setSummerResult(excelRows);
//                return excelRows;
//            }
//            List<ExcelRow> excelRows = readNoHeaderByStream(mod);
//            mod.setSummerResult(excelRows);
//            return excelRows;
//        }finally {
//            if(mod.isCloseStream()) {
//                IOUtils.closeQuietly(mod.getInputStream());
//            }
//        }
//    }
//
//    private List<ExcelRow> readHasHeaderByPath(ExcelReadByPath mod){
//        HasHeaderRowHander hasHeaderRowHander = new HasHeaderRowHander(mod);
//        ExcelUtil.readBySax(mod.getPath(), mod.getSheetIndex(), hasHeaderRowHander);
//        return hasHeaderRowHander.getResultList();
//    }
//    private List<ExcelRow> readNoHeaderByPath(ExcelReadByPath mod){
//        NoHeaderRowHander noHeaderRowHander = new NoHeaderRowHander(mod);
//        ExcelUtil.readBySax(mod.getPath(), mod.getSheetIndex(), noHeaderRowHander);
//        return noHeaderRowHander.getResultList();
//    }
//
//    private List<ExcelRow> readHasHeaderByStream(ExcelReadByStream mod){
//        HasHeaderRowHander hasHeaderRowHander = new HasHeaderRowHander(mod);
//        ExcelUtil.readBySax(mod.getInputStream(), mod.getSheetIndex(), hasHeaderRowHander);
//        return hasHeaderRowHander.getResultList();
//    }
//    private List<ExcelRow> readNoHeaderByStream(ExcelReadByStream mod){
//        NoHeaderRowHander noHeaderRowHander = new NoHeaderRowHander(mod);
//        ExcelUtil.readBySax(mod.getInputStream(), mod.getSheetIndex(), noHeaderRowHander);
//        return noHeaderRowHander.getResultList();
//    }
//
//    static class HasHeaderRowHander implements RowHandler {
//        private HashMap<Integer, String> headerMap = new HashMap<>();
//        private HashMap<String, String> fieldMap;
//        private ExcelReadMod mod;
//        private List<ExcelRow> resultList = new ArrayList<>();
//
//        public HasHeaderRowHander(ExcelReadMod mod){
//            this.mod = mod;
//            this.fieldMap = getFieldMap(mod.getKls());
//        }
//        private HashMap<String, String> getFieldMap(Class<? extends ExcelRow> kls){
//            HashMap<String, String> resultMap = new HashMap<>();
//            ImmutableList<Field> fields = ReflectUtil.getAnnotationField(kls, Column.class);
//            fields.forEach(field -> {
//                Column fieldAnnotation = field.getAnnotation(Column.class);
//                if(StringUtils.isNotBlank(fieldAnnotation.name())){
//                    resultMap.put(fieldAnnotation.name(), field.getName());
//                }
//            });
//            return resultMap;
//        }
//
//        @Override
//        public void handle(int sheetIndex, int rowIndex, List<Object> list) {
//            if(mod.getStartIndex()>=0 && rowIndex < mod.getStartIndex()){
//                return;
//            }
//            if(mod.getEndIndex()>=0 && rowIndex > mod.getEndIndex()){
//                return;
//            }
//            if(rowIndex<mod.getHeadIndex()){
//                return;
//            }else if(rowIndex == mod.getHeadIndex()){
//                for(int i=0; i<list.size(); i++){
//                    String colName = StringUtil.toSafeString(list.get(i));
//                    if(StringUtils.isNotBlank(colName)){
//                        String fieldName = fieldMap.get(colName);
//                        if(StringUtils.isNotBlank(fieldName)) {
//                            headerMap.put(i, fieldName);
//                        }
//                    }
//                }
//            }else{
//                ExcelRow bean = ReflectUtil.newInstance(mod.getKls());
//                resultList.add(bean);
//                bean.setRowNum(rowIndex);
//                for(int i=0; i<list.size(); i++){
//                    Object value = list.get(i);
//                    String fieldName = headerMap.get(i);
//                    if(StringUtils.isBlank(fieldName)) {
//                        continue;
//                    }
//                    RuntimeException exception = ReflectUtil.setProperty(bean, fieldName, value);
//                    if(null != exception){
//                        bean.setErrMsg(exception.getClass().getName()+"; "+exception.getMessage());
//                    }
//                }
//            }
//        }
//
//        public List<ExcelRow> getResultList() {
//            return resultList;
//        }
//    }
//    static class NoHeaderRowHander implements RowHandler {
//        private HashMap<Integer, String> headerMap;
//        private ExcelReadMod mod;
//        private List<ExcelRow> resultList = new ArrayList<>();
//
//        public NoHeaderRowHander(ExcelReadMod mod){
//            this.mod = mod;
//            this.headerMap = getFieldMap(mod.getKls());
//        }
//        private HashMap<Integer, String> getFieldMap(Class<? extends ExcelRow> kls){
//            HashMap<Integer, String> resultMap = new HashMap<>();
//            ImmutableList<Field> fields = ReflectUtil.getAnnotationField(kls, Column.class);
//            fields.forEach(field -> {
//                Column fieldAnnotation = field.getAnnotation(Column.class);
//                if(fieldAnnotation.index()>=0){
//                    resultMap.put(fieldAnnotation.index(), field.getName());
//                }
//            });
//            return resultMap;
//        }
//
//        @Override
//        public void handle(int sheetIndex, int rowIndex, List<Object> list) {
//            if(mod.getStartIndex()>=0 && rowIndex < mod.getStartIndex()){
//                return;
//            }
//            if(mod.getEndIndex()>=0 && rowIndex > mod.getEndIndex()){
//                return;
//            }
//            ExcelRow bean = ReflectUtil.newInstance(mod.getKls());
//            resultList.add(bean);
//            bean.setRowNum(rowIndex);
//            for(int i=0; i<list.size(); i++){
//                Object value = list.get(i);
//                String fieldName = headerMap.get(i);
//                if(StringUtils.isBlank(fieldName)) {
//                    continue;
//                }
//                RuntimeException exception = ReflectUtil.setProperty(bean, fieldName, value);
//                if(null != exception){
//                    bean.setErrMsg(exception.getClass().getName()+"; "+exception.getMessage());
//                }
//            }
//        }
//
//        public List<ExcelRow> getResultList() {
//            return resultList;
//        }
//    }
//}
