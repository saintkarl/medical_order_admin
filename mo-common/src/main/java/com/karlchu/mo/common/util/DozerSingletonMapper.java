package com.karlchu.mo.common.util;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;

/**
 * Created with IntelliJ IDEA.
 * User: hieu
 * Date: 11/16/13
 * Time: 5:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class DozerSingletonMapper extends DozerBeanMapper{
    private transient final Logger logger = Logger.getLogger(DozerSingletonMapper.class);
    private static DozerSingletonMapper instance;

    private DozerSingletonMapper() {
        super();
    }
    public static DozerSingletonMapper getInstance() {
        if(instance == null) {
            instance = new DozerSingletonMapper();
        }
        return instance;
    }
}
