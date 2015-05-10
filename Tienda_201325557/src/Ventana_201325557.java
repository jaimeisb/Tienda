import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Ventana_201325557 extends JFrame{

	
	public Ventana_201325557() {
		
		Venta_201325557 lista=new Venta_201325557();
		
		setTitle("Datos De Reportes");
		setBounds(100, 100, 1000, 475);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton AgregarVenta = new JButton("Agregar venta");
		AgregarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				String elemento;
				
				
				try{
					File pagina;
					{
					pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");}
					
					
					FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
					DataOutputStream ds=new DataOutputStream(os);
					elemento=JOptionPane.showInputDialog(null,"ingresa la venta a realizar de la forma:\n "
							+ "producto-fecha-cantidad-empleado-cliente","Ingresando venta ", JOptionPane.INFORMATION_MESSAGE);						os.write(1);
					lista.insertar(elemento);
					String []campos;
					String []campos2;
					
					
					nodoVenta auxiliar=lista.ultimo.siguiente;
					String cadena="";
					do{
						cadena=cadena+auxiliar.dato+"]";
						auxiliar=auxiliar.siguiente;
						
					}while(auxiliar!=lista.ultimo.siguiente);
				
					campos=cadena.split("]");
					
					for(int i=0;i<=campos.length;i++){
						campos2=campos[i].split("-");
						os.write(("Producto: "+campos2[0]).getBytes());
						os.write(("  Fecha: "+campos2[1]).getBytes());
						os.write(("  Cantidad: "+campos2[2]).getBytes());
						os.write(("  Empleado: "+campos2[3]).getBytes());
						os.write(("  Cliente: "+campos2[4]+"\r\n").getBytes());
						
					}
					
					os.close();
					ds.close();
					
					
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarVenta.setBounds(285, 244, 125, 26);
		getContentPane().add(AgregarVenta);
		
		JButton ReporteDeVentas = new JButton("Reporte de ventas");	
		ReporteDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try{
			          File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			          String linea;
			          String [] campos;
			          FileInputStream is=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
			          DataInputStream ds=new DataInputStream(is);
			          FileOutputStream is2=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			          DataOutputStream os=new DataOutputStream(is2);
			          while((linea=ds.readLine())!=null){
			        	  campos=linea.split("\r\n");
			        	  for(int j=0;j<campos.length;j++){
			        	  os.write((campos[j]).getBytes());
			        	  os.write(("<br>").getBytes());
			        	
			        	  }
			          }
			          is.close();
			          ds.close();
			          Desktop.getDesktop().open(pagina);
			              
			           
			        }catch(IOException e){
			            e.printStackTrace();
			        }
				
				
			}
		});
		ReporteDeVentas.setBounds(486, 244, 227, 28);
		getContentPane().add(ReporteDeVentas);
		
		JButton NumeroDeVentas = new JButton("Numero de ventas por empleado");
		NumeroDeVentas.setBounds(486, 283, 227, 28);
		getContentPane().add(NumeroDeVentas);
		
		JButton VentasPorIntervalo = new JButton("Ventas por intervalo de fechas");
		VentasPorIntervalo.setBounds(486, 322, 227, 28);
		getContentPane().add(VentasPorIntervalo);
		
		JButton ComprasRealizadasPor = new JButton("Compras realizadas por clientes");
		ComprasRealizadasPor.setBounds(486, 361, 227, 28);
		getContentPane().add(ComprasRealizadasPor);
		
		JButton VerTodosLos = new JButton("Ver todos los reportes");
		VerTodosLos.setBounds(755, 364, 161, 23);
		getContentPane().add(VerTodosLos);
		
		JButton AgregarCliente = new JButton("Agregar cliente");
		AgregarCliente.setBounds(285, 281, 125, 29);
		getContentPane().add(AgregarCliente);
		
		JButton AgregarProducto = new JButton("Agregar producto");
		AgregarProducto.setBounds(285, 325, 125, 23);
		getContentPane().add(AgregarProducto);
		
		JButton AgregarEmpleado = new JButton("Agregar empleado");
		AgregarEmpleado.setBounds(285, 364, 125, 23);
		getContentPane().add(AgregarEmpleado);
		}
}
