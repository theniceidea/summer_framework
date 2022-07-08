package com.fmk.framework.basic;

import com.fmk.framework.logger.Logger;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class ByteUtil {
	
	private static Logger LOG = Logger.getLogger(ByteUtil.class);
	
	public static byte[] decompress(byte[] data) {
	    ByteArrayOutputStream out = null;
	    ByteArrayInputStream in = null;
	    GZIPInputStream input = null;
        try {
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(data);
            input = new GZIPInputStream(in);
            IOUtils.copy(input, out);
            byte[] unzip = out.toByteArray();
            return unzip;
        } catch (Exception e) {
            //throw new RuntimeException(e);
            throw new RuntimeException(e.getMessage());
        }finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(input);
        }
    }
	
	
	 public static byte[] compress(byte[] data) {  
	        if (data == null || data.length == 0) {  
	            return null;  
	        }  
	        ByteArrayOutputStream out = null;
	        GZIPOutputStream gzip = null;
	        try {
	            out = new ByteArrayOutputStream();
	            gzip = new GZIPOutputStream(out);  
	            gzip.write(data);  
	            gzip.close();  
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }finally {
                IOUtils.closeQuietly(out);
                IOUtils.closeQuietly(gzip);
            }
            return out.toByteArray();
	    }
}
