/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kgtUtility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author Lipari
 */
public class ConvertPdftoTxt{
    
    public static void convert(String filePath,String fileDest){
        //the pdf file
	File input = new File(filePath);
        // The text file where you are going to store the extracted data
	filePath=input.getName();
        filePath = filePath.replace(".pdf", ".txt");
	File output = new File(fileDest+File.separator+filePath); 
	PDDocument pd = null;
	BufferedWriter wr = null;
	try {
		pd = PDDocument.load(input);
		PDFTextStripper stripper = new PDFTextStripper();
		wr = new BufferedWriter(new OutputStreamWriter
				(new FileOutputStream(output)));
		stripper.writeText(pd, wr);
	}
	catch(IOException e){
		System.out.println(e.getMessage());
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
	finally {
	/*
	 * Close streams
	 */
		if (pd != null) {
			try {
				pd.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		//I use close() to flush the stream.
		try {
			if(wr!=null)
				wr.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
    }	
}
