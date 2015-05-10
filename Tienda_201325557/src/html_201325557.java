
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class html_201325557 {
	/*char[] arreglo={'a','b'};{
		//este escribe en el documento
	try{
		FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
		for(int i=0;i<arreglo.length;i++)
			os.write(arreglo[i]);
			
		os.close();
	}
	catch(IOException e){
		System.out.println("Error de archivo: "+e);
	}

}*/
	// este lee en consola lo que tiene el documento
{
try{
	String linea;
	String [] campos;
	FileInputStream is=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
	DataInputStream ds=new DataInputStream(is);
	while((linea=ds.readLine())!=null){
		campos =linea.split("");
		System.out.println(campos[0]+campos[1]);
	}
		ds.close();
		is.close();
	}
catch(IOException e){
	System.out.println("Error de archivo: "+e);
}
}

	
}
