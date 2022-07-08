package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

/**
 * @author larry
 * @date 2019/2/20
 */
public class GetCurrentRequestNumber extends BasicSummer<Integer> implements LocalSummer {

    public static int s(){
        GetCurrentRequestNumber summer = new GetCurrentRequestNumber();
        return summer.sum();
    }

}
