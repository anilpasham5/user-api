package com.api.tss.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class StoreImage {

	 @SuppressWarnings("resource")
	public static void storeImage(File sourceFile, File destFile)
	            throws IOException {
	        if (!destFile.exists()) {
	            destFile.createNewFile();
	        }

	        FileChannel source = null;
	        FileChannel destination = null;
	        try {
	            source = new FileInputStream(sourceFile).getChannel();
	            destination = new FileOutputStream(destFile).getChannel();

	            // previous code: destination.transferFrom(source, 0, source.size());
	            // to avoid infinite loops, should be:
	            long count = 0;
	            long size = source.size();
	            while ((count += destination.transferFrom(source, count, size
	                    - count)) < size)
	                ;
	        } finally {
	            if (source != null) {
	                source.close();
	            }
	            if (destination != null) {
	                destination.close();
	            }
	        }
	    }

	    public static void copyImage(String inputPath,String outputPath) {
	       try {
	        File sourceFile = new File(inputPath);
	        File destFile = new File(outputPath);
	        storeImage(sourceFile,destFile);
	        } catch (IOException ex) {
	           ex.printStackTrace();
	         }
	    }
	}

