/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package download.manager;

/**
 *
 * @author Gusde
 */
import java.io.*;
import java.net.*;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JTextPane;

public class downloadFile extends Thread{
    private String download;
    private JProgressBar jprog;
    private JButton button1;
    private JTextPane pane;
    public downloadFile(String download, JProgressBar jprog, JButton button1, JTextPane pane) {
        this.download = download;
        this.jprog = jprog;
        this.button1 = button1;
        this.pane = pane;
                
    }
                
                public void run(){
                    downloadIt(download);
                }
		public boolean downloadIt(String download){ 
		 	InputStream in=null; 
		 	FileOutputStream fOut=null; 
		 	try {
				//URL remoteFile = new URL ("https://www.xotours.net/images/Photo/Europe/Barcelona.jpg");
				//URL remoteFile = new URL ("https://if.unram.ac.id/wp-content/uploads/2020/08/opentalk2-770x410.png");
				URL remoteFile = new URL (download);
                                int fileNameIndex = download.lastIndexOf("/");
                                String fileName = download.substring(fileNameIndex+1);
		 		URLConnection fileStream = remoteFile.openConnection(); 
                                System.out.println("flename "+ fileName);
		 		//membuka input dan output stream 
		 		fOut = new FileOutputStream(fileName);
		 		in = fileStream.getInputStream(); 
                                int fileSize = fileStream.getContentLength();
		 		//menyimpan file 
		 		int data; 
                                int count=0;
				while ((data=in.read())!=-1){ 
                                            fOut.write(data); 
                                            count= count + 1;
//                                            System.out.println("file size"+ fileSize);
                                            
                                            float progress = ((float)count/(float)fileSize) * 100;
//                                            System.out.println("progress" +  progress);
                                            this.jprog.setValue(Math.round(progress));
		 			} 
                                
		 	} 
			catch (Exception ex){ 
//		 		ex.printStackTrace();
                                return false;
		 	} 
		 	finally { 
		 		System.out.println("Saving...."); 
				try { 
		 			in.close();
					fOut.flush(); 
					fOut.close();
                                        this.jprog.setValue(100);
                                        this.button1.setText("Start Download");
                                        pane.setText("Download Selesai");
                                        return true;
		 		} 
		 		catch (Exception e){ 
//		 			e.printStackTrace();
                                        pane.setText("Download Gagal");
                                        this.button1.setText("Start Download");

                                        return false;
		 			}
		 		} 
                                
		 	}
            
	}
