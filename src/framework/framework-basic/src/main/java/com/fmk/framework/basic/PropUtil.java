package com.fmk.framework.basic;

import com.fmk.framework.logger.Logger;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropUtil {
    private static final Logger logger = Logger.getLogger(PropUtil.class);

    public static Properties fromResource(String resourcePath){
        Properties prop = new Properties();
        String properties = StringUtil.fromResource(resourcePath);
        StringReader reader = null;
        try {
            reader = new StringReader(properties);
            prop.load(reader);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            IOUtils.closeQuietly(reader);
        }
        return prop;
    }
}
