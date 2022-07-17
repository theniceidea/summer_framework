package com.fmk.framework.valid;

public class FieldValid<P, V> {
    private P parent;
    private V value;
    private boolean success=true;
    private FieldValidSuccess fieldValidSuccess;
    public FieldValid(P parent, V value, FieldValidSuccess fieldValidSuccess){
        this.parent=parent;
        this.value=value;
        this.fieldValidSuccess=fieldValidSuccess;
    }
    public FieldValid<P, V> valid(IValidator<V> validator, String msg){
        validator.valid(this.value, msg);
        return this;
    }
//    public P __validBack(IValidator<V> validator, String msg){
//        validator.valid(this.value, msg);
//        return __back();
//    }
    public FieldValid<P, V> valid(IValidatorSuccess<V> validator){
        if(!success){
            return this;
        }
        success=validator.isValidSuccess(this.value);
        return this;
    }
//    public P __validBack(IValidatorSuccess<V> validator){
//        if(!success){
//            return this.parent;
//        }
//        success=validator.isValidSuccess(this.value);
//        return __back();
//    }
    public P back(){
        if(this.success){
            fieldValidSuccess.success();
        }
        return this.parent;
    }
}
