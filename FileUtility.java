package com.demo.FileReader.Utility;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

public class FileUtility {

	
	
	
	public static void createPdf() throws IOException {
		//Creating PDF document object 
	      PDDocument document = new PDDocument();

	      for (int i=0; i<5; i++) {
	         
	         
	         if(i==0) {
	       //Creating a PDF Document
	         PDPage page = new PDPage();  
	        
	         PDPageContentStream contentStream = new PDPageContentStream(document, page); 
	         
	         //Begin the Content stream 
	         contentStream.beginText(); 
	          
	         //Setting the font to the Content stream
	         contentStream.setFont( PDType1Font.TIMES_ROMAN, 16 );
	          
	         //Setting the leading
	         contentStream.setLeading(14.5f);

	         //Setting the position for the line
	         contentStream.newLineAtOffset(25, 725);

	         String text1 = "This is an example of adding text to a page in the pdf document. we can add as many lines";
	         String text2 = "as we want like this using the ShowText()  method of the    ContentStream class";

	         //Adding text in the form of string
	         contentStream.showText(text1);
	         contentStream.newLine();
	         contentStream.showText(text2); 
	         //Ending the content stream
	         contentStream.endText();

	         document.addPage( page ); 
	         System.out.println("Content added");
	         //Closing the content stream
	         contentStream.close();
	         }else {
	        	//Creating a blank page 
		         PDPage blankPage = new PDPage();
		         
		         //Adding the blank page to the document
		         document.addPage( blankPage );
	         }
	      } 
	     
	      //Saving the document
	      
			document.save("C:/Amol/PdfBox_Examples/my_doc.pdf");
			//Closing the document
		      document.close();
		
	      System.out.println("PDF created");
	      
	      
	}
	
	public static void readPdfText(String pdfInputLocation,String outputLocation) throws IOException {
		File file = new File(pdfInputLocation);
	      PDDocument document = PDDocument.load(file);

	      //Instantiate PDFTextStripper class
	      PDFTextStripper pdfStripper = new PDFTextStripper();

	      //Retrieving text from PDF document
	      String text = pdfStripper.getText(document);
	      System.out.println(text);
	      
	    //Get the file reference
	      Path path = Paths.get(outputLocation);
	       
	      //Use try-with-resource to get auto-closeable writer instance
	      try (BufferedWriter writer = Files.newBufferedWriter(path)) 
	      {
	          writer.write(text);
	      }
	      //Closing the document
	      document.close();
	}
	
	public static void main(String[] args) {
		try {
			String pdfInputLocation="C:/Amol/PdfBox_Examples/my_doc.pdf";
			String outputLocationPath="C:/Amol/PdfBox_Examples/output1.txt";
//			FileUtility.createPdf();
			FileUtility.readPdfText(pdfInputLocation,outputLocationPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
