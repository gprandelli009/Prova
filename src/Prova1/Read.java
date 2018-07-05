 package Prova1;

import java.io.FileInputStream;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Read {
	public Read() {

	}
	/**
	 * 
	 * @param filename
	 * https://docs.oracle.com/javase/tutorial/jaxp/stax/example.html
	 */
	public void explore(String filename) {
		try {

			XMLInputFactory xmlif=XMLInputFactory.newInstance();
			XMLStreamReader xmlr = xmlif.createXMLStreamReader(filename,
					new FileInputStream(filename));
			while(xmlr.hasNext()) {
				switch(xmlr.getEventType()) {
				case XMLStreamConstants.START_DOCUMENT:
					//System.out.println("Start Read Doc "+filename);
					break;
				case XMLStreamConstants.START_ELEMENT:
					//System.out.print(xmlr.getLocalName());
					Calcolo.scegli(xmlr.getLocalName(),xmlr.getAttributeValue(0));

					break;
				case XMLStreamConstants.NOTATION_DECLARATION:
					System.out.println("Inside "+xmlr.getText());
					break;	            	
				case XMLStreamConstants.CHARACTERS:
					if(xmlr.getText().trim().length()>0)
						System.out.println("-> "+xmlr.getText());
					break;
				case XMLStreamConstants.COMMENT:
					if(xmlr.getText().trim().length()>0)
						//System.out.println("-> "+xmlr.getText());
					break;
				default:
					break;
				}
				xmlr.next();

			}
		}catch(Exception e){
			System.err.println("Error detected");
			System.err.println(e.getMessage());
		}

		Calcolo.risolviProblemaUltimoLink();
		
	}
	


}