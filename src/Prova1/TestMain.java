 package Prova1;

public class TestMain {

	public static void main(String[] args) {
		double tempoInizio=System.currentTimeMillis();
		Read read_xml=new Read();
		read_xml.explore("input_8.1.F.xml");
		Calcolo.Iterazioni();
		System.out.println(Calcolo.soluzioni);
		double tempoFine=System.currentTimeMillis();
		double tempoImpiegato=(tempoFine/1000)-(tempoInizio/1000);
		String tempoImpiegatoString=String.valueOf(tempoImpiegato);
		System.out.println((tempoFine/1000)-(tempoInizio/1000));
		System.out.println(tempoImpiegatoString);
		Write write_xml=new Write();
		try {
			write_xml.write(tempoImpiegatoString,"output_8.1.F.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
