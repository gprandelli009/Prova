 package Prova1;

import java.io.FileWriter;
import java.util.Vector;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;


public class Write {
	public Write() {

	}
	public boolean write(String time ,String filename){
		System.out.println("Scrittura su file");
		XMLOutputFactory output = XMLOutputFactory.newInstance();
		XMLStreamWriter writer;
		try {
			writer = output.createXMLStreamWriter(new FileWriter(filename));

			writer.writeComment("data saved");
			//					writer.writeStartDocument("utf-8","1.0");
			writer.writeStartElement("root");
			for(int i: Calcolo.Problemi){
				writer.writeStartElement("problem");
				writer.writeAttribute("solution",""+Calcolo.soluzioni.get(i));
				writer.writeAttribute("id",""+Calcolo.Problemi.get(i));
				//						writer.writeStartElement("name");
				//						writer.writeCharacters("soluzione");
				//						writer.writeEndElement();
				//						writer.writeStartElement("surname");
				//						writer.writeCharacters(i.getSurname());
				//						writer.writeEndElement();
				writer.writeEndElement(); // End Problem
			}
			writer.writeStartElement("time");
			writer.writeCharacters(time);
			writer.writeEndElement(); //End time
			writer.writeEndElement(); // End root
			writer.writeEndDocument(); //End Document
			writer.flush();
			writer.close();
			System.out.println("End!");
		} catch (Exception e) {
			System.out.print("Vecchio, problema!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
