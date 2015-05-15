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
		
		
		Venta_201325557 listaVenta=new Venta_201325557();
		listaEmpleado_201325557 listaEmpleado=new listaEmpleado_201325557();
		listaProducto_201325557 listaProducto=new listaProducto_201325557();
		listaCliente_201325557 listaCliente=new listaCliente_201325557();
		
		setTitle("Datos De Reportes");
		setBounds(100, 100, 540, 372);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton AgregarVenta = new JButton("Agregar venta");
		AgregarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String elemento;
				try{
					File ventaDoc;
					{
					ventaDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.txt");
					}
					FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.txt");
					DataOutputStream ds=new DataOutputStream(os);
					elemento=JOptionPane.showInputDialog(null,"ingresa la venta a realizar de la forma:\n "
							+ "producto-fecha-cantidad-empleado-cliente","Ingresando venta ", JOptionPane.INFORMATION_MESSAGE);	
					listaVenta.insertar(elemento);
					String []campos;
					String []campos2;
					nodoVenta_201325557 auxiliar=listaVenta.ultimo.siguiente;
					String cadena="";
					do{
						cadena=cadena+auxiliar.dato+"]";
						auxiliar=auxiliar.siguiente;
					}
					while(auxiliar!=listaVenta.ultimo.siguiente);
					campos=cadena.split("]");
					for(int i=0;i<=campos.length-1;i++){
						campos2=campos[i].split("-");
						os.write((campos2[0]+"-").getBytes());
						os.write((campos2[1]+"-").getBytes());
						os.write((campos2[2]+"-").getBytes());
						os.write((campos2[3]+"-").getBytes());
						os.write((campos2[4]+"-"+"\r\n").getBytes());
					}
					
					os.close();
					ds.close();
					
					
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarVenta.setBounds(28, 98, 125, 26);
		getContentPane().add(AgregarVenta);
		
		JButton ReporteDeVentas = new JButton("Reporte de Facturacion");	
		ReporteDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 try{
			          File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			          FileInputStream isv=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.txt");
			          DataInputStream dsv=new DataInputStream(isv);
			          FileOutputStream is2v=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			          DataOutputStream os=new DataOutputStream(is2v);
			          String lineaV,lineaE,lineaP;
			          String[] camposV,camposE,camposP;
			          int total=0;
			          os.write(("<table BORDER=\"3\"ALIGN=\"center\">").getBytes());
			          os.write(("<TH COLSPAN=8 BGCOLOR=\"#6D8FFF\"> Reporte de ventas</TH>").getBytes());
			          os.write(("<TR>").getBytes());   
			          os.write(("<TD>Cod. Empleado</TD><TD>Empleado</TD><TD>Fecha</TD><TD>Cod. Producto</TD><TD>Producto</TD><TD>Cantidad</TD><TD>Precio</TD><TD>Sub.Total</TD>").getBytes());
			          os.write(("</TR>").getBytes());
			         while((lineaV=dsv.readLine())!=null){
			        	 camposV=lineaV.split("-");
			        	 os.write(("<TR>").getBytes()); 
			        	 os.write(("<TD>"+camposV[3]+"</TD>").getBytes());//1
			        	 FileInputStream ise=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.txt");
				          DataInputStream dse=new DataInputStream(ise);
			        		  while((lineaE=dse.readLine())!=null){
			        			  camposE=lineaE.split("-");
			        			  if(camposV[3].equals(camposE[0])){
			        				  os.write(("<TD>"+camposE[1]+" "+camposE[2]+"</TD>").getBytes());//2
			        				  break;
			        			  }
			        		  }
			        		  os.write(("<TD>"+camposV[1]+"</TD>").getBytes());//3
			        		  os.write(("<TD>"+camposV[0]+"</TD>").getBytes());//4
			        		  FileInputStream isp=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.txt");
					          DataInputStream dsp=new DataInputStream(isp);
			        		  while((lineaP=dsp.readLine())!=null){
			        			  camposP=lineaP.split("-");
			        			  if(camposV[0].equals(camposP[0])){
			        				  os.write(("<TD>"+camposP[1]+"</TD>").getBytes());//5
			        				  os.write(("<TD>"+camposV[2]+"</TD>").getBytes());//6
					        		  os.write(("<TD>"+camposP[2]+"</TD>").getBytes());//7
					        		  os.write(("<TD>"+(Integer.parseInt(camposP[2])*Integer.parseInt(camposV[2]))+"</TD>").getBytes());//8
					        		  total=total+(Integer.parseInt(camposP[2])*Integer.parseInt(camposV[2]));
					        		  break;
			        			  }
			        		  }
			        		  os.write(("</TR>").getBytes());			        		   
			          }
			         os.write(("<TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD></TD><TD>"+"TOTAL: "+total+"</TD>").getBytes());//3
			         os.write(("</table>").getBytes());
			         os.close();
			         is2v.close();
			         isv.close();
			         dsv.close();
			         Desktop.getDesktop().open(pagina);
			        }
				 catch(IOException e){
			           System.out.println(e);
			        }
			}
		});
		ReporteDeVentas.setBounds(216, 98, 227, 26);
		getContentPane().add(ReporteDeVentas);
		
		JButton NumeroDeVentas = new JButton("Numero de ventas por empleado");
		NumeroDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					 File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
					  FileInputStream ise=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.txt");
			          DataInputStream dse=new DataInputStream(ise);
			          FileOutputStream is2v=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			          DataOutputStream os=new DataOutputStream(is2v);
			          String lineaE,lineaV;
			          String [] camposE,camposV;
			          int ventasEmpleado=0;
			          os.write(("<table BORDER=\"3\"ALIGN=\"center\">").getBytes());
			          os.write(("<TH COLSPAN=8 BGCOLOR=\"#6D8FFF\"> Numero de ventas por empleado</TH>").getBytes());
			          os.write(("<TR>").getBytes());
			          os.write(("<TD>Cod. Empleado</TD><TD>Empleado</TD><TD>Ventas</TD>").getBytes());
			          os.write(("</TR>").getBytes());
			        
			          while((lineaE=dse.readLine())!=null){
			        	  FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.txt");
				          DataInputStream dsV=new DataInputStream(isV);
			        	  camposE=lineaE.split("-");
			        	  os.write(("<TR>").getBytes());
			        	  ventasEmpleado=0;
			        	  while((lineaV=dsV.readLine())!=null){
					        	 camposV=lineaV.split("-");
					        	 if( camposE[0].equals(camposV[3])){
					        		 ventasEmpleado=ventasEmpleado+1;
					        	 }
			        	  }
			        	  os.write(("<TR>").getBytes());
		        		  os.write(("<TD>"+camposE[0]+"</TD>").getBytes());//1
		        		  os.write(("<TD>"+camposE[1]+"</TD>").getBytes());//2
		        		  os.write(("<TD>"+ventasEmpleado+"</TD>").getBytes());//4
		        		  os.write(("</TR>").getBytes());
		        		 }
			          os.write(("</table>").getBytes());
			          Desktop.getDesktop().open(pagina);
			          is2v.close();
			          os.close();
			          dse.close();
			          ise.close();
					}
				catch(IOException e){
				}
			}
		});
		
		NumeroDeVentas.setBounds(216, 141, 227, 26);
		getContentPane().add(NumeroDeVentas);
		
		JButton VentasPorIntervalo = new JButton("Ventas por intervalo de fechas");
		VentasPorIntervalo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				String rangos;
				String [] fechas;
				int total = 0;
				rangos=(JOptionPane.showInputDialog(null,"ingresa el rango de fechas a buscar de la forma: dd.mm.aa-dd.mm.aa","Intervalos de fechas", JOptionPane.INFORMATION_MESSAGE));
				fechas=rangos.split("-");
				try{
					File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
				 FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.txt");
		         DataInputStream dsV=new DataInputStream(isV);
		         FileOutputStream is2v=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			     DataOutputStream os=new DataOutputStream(is2v);
		         String lineaV,producto,lineap;
		         String[] camposV,fechaI,fechaf,fechav,camposp;
		         fechaI=fechas[0].split("/");
		         fechaf=fechas[1].split("/");
		         os.write(("<table BORDER=\"3\"ALIGN=\"center\">").getBytes());
			     os.write(("<TH COLSPAN=8 BGCOLOR=\"#6D8FFF\"> Numero de compras por cliente</TH>").getBytes());
			     os.write(("<TR>").getBytes());
			     os.write(("<TD>Fecha</TD><TD>Producto</TD><TD>Cantidad</TD><TD>Precio</TD><TD>Sub-total</TD>").getBytes());
			     os.write(("</TR>").getBytes());
			     os.write(("<TR>").getBytes());
			     System.out.println(fechaI[0]);
		         while((lineaV=dsV.readLine())!=null){
		        	 camposV=lineaV.split("-");
		        	 fechav=camposV[1].split("/");
		        	 if(Integer.parseInt(fechaI[0])<=Integer.parseInt(fechav[0])&& Integer.parseInt(fechaI[1])<=Integer.parseInt(fechav[1])&&Integer.parseInt(fechaI[2])<=Integer.parseInt(fechav[2])
		        			&& Integer.parseInt(fechav[0])<=Integer.parseInt(fechaf[0])&&Integer.parseInt(fechav[1])<=Integer.parseInt(fechaf[1])&&Integer.parseInt(fechav[2])<=Integer.parseInt(fechaf[2]) ){
		        		  os.write(("<TD>"+camposV[1]+"</TD>").getBytes());//1
		        		  producto=camposV[0];
		        		  FileInputStream isp=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.txt");
		        		  DataInputStream dsp=new DataInputStream(isp);
		        		  while((lineap=dsp.readLine())!=null){
		        			  camposp=lineap.split("-");
		        			  if(producto.equals(camposp[0])){
		        				  producto=camposp[1]; 
		        				  os.write(("<TD>"+producto+"</TD>").getBytes());//2
				        		  os.write(("<TD>"+camposV[2]+"</TD>").getBytes());//3
				        		  os.write(("<TD>"+camposp[2]+"</TD>").getBytes());//4
				        		  os.write(("<TD>"+(Integer.parseInt(camposp[2])*Integer.parseInt(camposV[2]))+"</TD>").getBytes());//5
				        		  total=total+(Integer.parseInt(camposp[2])*Integer.parseInt(camposV[2]));
				        		  break;
		        			  }	 
		        		  }
		        		  os.write(("</TR>").getBytes());
		        	 }
		         }
		         os.write(("<TD></TD><TD></TD><TD></TD><TD></TD><TD>"+"TOTAL: "+total+"</TD>").getBytes());//3
		         os.write(("</table>").getBytes());
		         isV.close();
		         dsV.close();
		         os.close();
		         is2v.close();
		          Desktop.getDesktop().open(pagina);
				}
				catch(IOException e){					
				}
			}
		});
		VentasPorIntervalo.setBounds(216, 186, 227, 28);
		getContentPane().add(VentasPorIntervalo);
		
		JButton ComprasRealizadasPor = new JButton("Compras realizadas por clientes");
		ComprasRealizadasPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try{
					File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
					FileInputStream isc=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.txt");
					DataInputStream dsc=new DataInputStream(isc);
					FileOutputStream is2v=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			        DataOutputStream os=new DataOutputStream(is2v);
			        String lineaC,lineaV;
			        String [] camposC,camposV;
			        int comprasCliente;
			        os.write(("<table BORDER=\"3\"ALIGN=\"center\">").getBytes());
			        os.write(("<TH COLSPAN=8 BGCOLOR=\"#6D8FFF\"> Numero de compras por cliente</TH>").getBytes());
			        os.write(("<TR>").getBytes());
			        os.write(("<TD>Cod. Cliente</TD><TD>Cliente</TD><TD>Compras</TD>").getBytes());
			        os.write(("</TR>").getBytes());
			        while((lineaC=dsc.readLine())!=null){
			        	camposC=lineaC.split("-");
			        	 FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.txt");
				         DataInputStream dsV=new DataInputStream(isV);
				         os.write(("<TR>").getBytes());
			        	comprasCliente=0;
			        	while((lineaV=dsV.readLine())!=null){
				        	 camposV=lineaV.split("-");
				        	 if( camposC[0].equals(camposV[4])){
				        		 comprasCliente=comprasCliente+1;
				        	 }
			        	}
			        	 os.write(("<TR>").getBytes());
		        		  os.write(("<TD>"+camposC[0]+"</TD>").getBytes());//1
		        		  os.write(("<TD>"+camposC[1]+"</TD>").getBytes());//2
		        		  os.write(("<TD>"+comprasCliente+"</TD>").getBytes());//4
		        		  os.write(("</TR>").getBytes());
			        }
			        os.write(("</table>").getBytes());
			          Desktop.getDesktop().open(pagina);
			          is2v.close();
			          os.close();
			          dsc.close();
			          isc.close();
				}
				catch(IOException e){
					
				}
				
			}
		});
		ComprasRealizadasPor.setBounds(216, 225, 227, 28);
		getContentPane().add(ComprasRealizadasPor);
		
		JButton VerTodosLos = new JButton("Ver todos los reportes");
		VerTodosLos.setBounds(257, 275, 161, 23);
		getContentPane().add(VerTodosLos);
		
		JButton AgregarCliente = new JButton("Agregar cliente");
		AgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elemento;
				try{
					File clienteDoc;
					{
					clienteDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.txt");}
					FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.txt");
					DataOutputStream ds=new DataOutputStream(os);
					elemento=JOptionPane.showInputDialog(null,"ingrese los datos del cliente de la forma:\n "
							+ "Codigo-Nombre-Apellido-Nit","Ingresando cliente ", JOptionPane.INFORMATION_MESSAGE);	
					listaCliente.insertar(elemento);
					String []campos;
					String []campos2;
					nodoCliente_201325557 auxiliar=listaCliente.ultimo.siguiente;
					String cadena="";
					do{
						cadena=cadena+auxiliar.dato+"]";
						auxiliar=auxiliar.siguiente;
					}
					while(auxiliar!=listaCliente.ultimo.siguiente);

					campos=cadena.split("]");
					for(int i=0;i<=campos.length-1;i++){
						campos2=campos[i].split("-");
						os.write((campos2[0]+"-").getBytes());
						os.write((campos2[1]+"-").getBytes());
						os.write((campos2[2]+"-").getBytes());
					}
					os.close();
					ds.close();
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarCliente.setBounds(28, 187, 125, 26);
		getContentPane().add(AgregarCliente);
		
		JButton AgregarProducto = new JButton("Agregar producto");
		AgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elemento;
				try{
					File productoDoc;
					{
					productoDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.txt");}
					FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.txt");
					DataOutputStream ds=new DataOutputStream(os);
					elemento=JOptionPane.showInputDialog(null,"ingrese los datos del producto de la forma:\n "
							+ "Codigo-Descripcion-Precio","Ingresando producto ", JOptionPane.INFORMATION_MESSAGE);	
					listaProducto.insertar(elemento);
					String []campos;
					String []campos2;
					nodoProducto_201325557 auxiliar=listaProducto.ultimo.siguiente;
					String cadena="";
					do{
						cadena=cadena+auxiliar.dato+"]";
						auxiliar=auxiliar.siguiente;
						
					}
					while(auxiliar!=listaProducto.ultimo.siguiente);
					campos=cadena.split("]");
					for(int i=0;i<=campos.length-1;i++){
						campos2=campos[i].split("-");
						os.write((campos2[0]+"-").getBytes());
						os.write((campos2[1]+"-").getBytes());
						os.write((campos2[2]+"-"+"\r\n").getBytes());
					}
					os.close();
					ds.close();	
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarProducto.setBounds(28, 141, 125, 26);
		getContentPane().add(AgregarProducto);
		
		JButton AgregarEmpleado = new JButton("Agregar empleado");
		AgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elemento;
				try{
					File empleadoDoc;
					{
					empleadoDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.txt");}
					FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.txt");
					DataOutputStream ds=new DataOutputStream(os);
					elemento=JOptionPane.showInputDialog(null,"ingrese los datos del empleado de la forma:\n "
							+ "Codigo-Nombre-Apellido-Edad-Cargo","Ingresando Epleado ", JOptionPane.INFORMATION_MESSAGE);	
					listaEmpleado.insertar(elemento);
					String []campos;
					String []campos2;
					nodoEmpleado_201325557 auxiliar=listaEmpleado.ultimo.siguiente;
					String cadena="";
					do{
						cadena=cadena+auxiliar.dato+"]";
						auxiliar=auxiliar.siguiente;	
					}
					while(auxiliar!=listaEmpleado.ultimo.siguiente);
					campos=cadena.split("]");
					for(int i=0;i<=campos.length-1;i++){
						campos2=campos[i].split("-");
						os.write((campos2[0]+"-").getBytes());
						os.write((campos2[1]+"-").getBytes());
						os.write((campos2[2]+"-").getBytes());
						os.write((campos2[3]+"-").getBytes());
						os.write((campos2[4]+"-"+"\r\n").getBytes());
					}
					os.close();
					ds.close();
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarEmpleado.setBounds(28, 227, 125, 26);
		getContentPane().add(AgregarEmpleado);
		}
}
