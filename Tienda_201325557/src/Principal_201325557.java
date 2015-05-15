import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




public class Principal_201325557 {

	public static void main(String[] args) {
		
		try{ FileOutputStream ost=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\total.txt");}
		catch(IOException e){
			
		}
		
		Ventana_201325557 ventana=new Ventana_201325557();
		ventana.setVisible(true);
		
	
					
	}

}
