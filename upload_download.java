import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.NullOutputStream;


class upload_download extends Thread {

    private String filePrefix, operation;
    private byte[] buffer;

    public upload_download(String filePrefix, String operation, byte[] buffer) {
        this.filePrefix = filePrefix;
        this.operation = operation;
        this.buffer = buffer;
    }

    private upload_download() {
        //return null;
    }

    @Override
    public void run() {

        try {
        
        	while(true)
        	{
            if (operation == "Upload")
            {                                   
                 try{
 		               
                	 int iteration = 0;
                	 switch(filePrefix)
                	 {
                	 case "newfile100KB":
                		 iteration = 1;
                		 break;
                	 case "newfile1MB":
                		 iteration = 10;
                		 break;
                	 case "newfile10MB":
                		 iteration = 100;
                		 break;
                	 case "newfile100MB":
                		 iteration = 1000;
                		 break;
                	 case "newfile1000MB":
                		 iteration = 10000;
                		 break;
                	 case "newfile10000MB":
                		 iteration = 100000;
                		 break;
                	 case "newfile20000MB":
                		 iteration = 200000;
                		 break;
                	 }             		 

                	 int i = 1;
                	 while(true) {
		        	        File target_File = new File("<file_directory>"+ filePrefix + "_" + i);
		        	        System.out.println(" Thread ID " + this.getId() + " Thread Started : " + System.currentTimeMillis());
		        	        FileOutputStream stream = new FileOutputStream(target_File, true);
		        	        double start = System.currentTimeMillis();
		        	        for (int k=1; k <=iteration; k++) {
		        	        	stream.write(buffer);
		        	        }
		        	        stream.close();
		        	        double end = System.currentTimeMillis();
		        		    double total = end - start;
		        		    System.out.println("Thread ID " + this.getId() + " Thread Ended : " + end );
		        		    System.out.println("Thread ID " + this.getId() + " Time taken to " + operation + " " + filePrefix + " " + total);
		        		    i++;
                	 }
		            	}catch(Exception e){
		        		e.printStackTrace();
		        	    }
                           
            }
            else
            {
            	try{
		               
               	 int totalfiles = 0;
               	 
               	 switch(filePrefix)
               	 {
               	 case "file100KB":
               		 totalfiles = 72000;
               		 break;
               	 case "file1MB":
               		 totalfiles = 16000;
               		 break;
               	 case "file10MB":
               		 totalfiles = 8000;
               		 break;
               	 case "file100MB":
               		 totalfiles = 4000;
               		 break;
               	 case "file1000MB":
               		 totalfiles = 100;
               		 break;
               	 case "file10000MB":
               		 totalfiles = 20;
               		 break;
               	 case "file20000MB":
               		 totalfiles = 10;
               		 break;
               	 }
                
                System.out.println("starting DOWNLOAD thread ******************");
                for (int j=1; j<=totalfiles; j++) {
                	File source_file = new File("<file_directory>"+ filePrefix + "_" + j);
                    System.out.println(source_file);                    
                    System.out.println(" Thread ID " + this.getId() + " Thread Started : " + System.currentTimeMillis());
                                      
                    int bufferSize = 4 * 1024;
                    InputStream instream = new BufferedInputStream(
                    		new FileInputStream(source_file), bufferSize
                    	);
                    
                    OutputStream nullStream = new NullOutputStream();
                    double start = System.currentTimeMillis();
                    IOUtils.copy(instream, nullStream);
                    IOUtils.closeQuietly(nullStream);
                   
                    IOUtils.closeQuietly(instream);
                   
                    double end = System.currentTimeMillis();
                    System.out.println(" Thread ID " + this.getId() + " Thread Ended : " + end );
                    double total = end - start;
                    System.out.println( "Thread ID " + this.getId() + " Time taken to " + operation + " " + filePrefix + " " + total);
                }               
            }catch (Exception e) {
                e.printStackTrace();
            }
            }

        } 
        	
        }catch (Exception e) {
            e.printStackTrace();
        }
        

    }

/* Main method to invoke multiple threads */
    public static void main(String[] args) throws IOException 
    	{
    	final byte[] empty = null;
    	/* Load a byte array with 100KB junk as a buffer, to be used for uploads */	
    	InputStream inputstream = new FileInputStream("<Path_to_file_of_100KB>/<file_name>");
        byte[] buffer100K = IOUtils.toByteArray(inputstream);
        IOUtils.closeQuietly(inputstream);
        
        
    	
      /* Start individual threads for upload/download for each file size */         
        new upload_download("newfile100KB", "Upload", buffer100K).start();
        new upload_download("file100KB", "Download", empty).start();
        new upload_download("newfile1MB", "Upload", buffer100K).start();
        new upload_download("file1MB", "Download", empty).start();
        new cecworkupload_downloadload("newfile10MB", "Upload", buffer100K).start();
        new upload_download("file10MB", "Download", empty).start();
        new upload_download("newfile100MB", "Upload", buffer100K).start();
        new upload_download("file100MB", "Download", empty).start();
        new upload_download("newfile1000MB", "Upload", buffer100K).start();
        new upload_download("file1000MB", "Download", empty).start();
        new upload_download("newfile10000MB", "Upload", buffer100K).start();
        new upload_download("file10000MB", "Download", empty).start();
        new upload_download("newfile20000MB", "Upload", buffer100K).start();
        new upload_download("file20000MB", "Download", empty).start();
    }

}
