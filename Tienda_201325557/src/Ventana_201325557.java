import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

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

	String empleados = null, cliente= null,venta= null,producto= null,rutacliente=null,rutaventa=null,ruta=null,rutaempleado=null,rutaproducto=null;
	public Ventana_201325557() {
		
		//"emp","fct","clt","prt"
		FileNameExtensionFilter filtro=new FileNameExtensionFilter("Carga de archivos","emp","clt","fct","prt");
		Venta_201325557 listaVenta=new Venta_201325557();
		listaEmpleado_201325557 listaEmpleado=new listaEmpleado_201325557();
		listaProducto_201325557 listaProducto=new listaProducto_201325557();
		listaCliente_201325557 listaCliente=new listaCliente_201325557();
		
		setTitle("Datos De Reportes");
		setBounds(100, 100, 671, 372);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton AgregarVenta = new JButton("Agregar venta");
		AgregarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String elemento;
				try{
					if(venta!=null){
						File ventaDoc;
						{
						ventaDoc=new File(rutaventa);
						}
						FileOutputStream os=new FileOutputStream(rutaventa);
						DataOutputStream ds=new DataOutputStream(os);
						elemento=JOptionPane.showInputDialog(null,"ingresa la venta a realizar de la forma:\n "
								+ "producto-fecha(dd/mm/aa)-cantidad-empleado-cliente","Ingresando venta ", JOptionPane.INFORMATION_MESSAGE);	
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
					}else{
						File ventaDoc;
						{
						ventaDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
						}
						FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
						DataOutputStream ds=new DataOutputStream(os);
						elemento=JOptionPane.showInputDialog(null,"ingresa la venta a realizar de la forma:\n "
								+ "producto-fecha(dd/mm/aa)-cantidad-empleado-cliente","Ingresando venta ", JOptionPane.INFORMATION_MESSAGE);	
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
			          if(venta!= null && producto!=null && empleados!=null){
			        	  File pagina=new File(ruta+"\\Pagina.HTML");
				          FileInputStream isv=new FileInputStream(rutaventa);
				          DataInputStream dsv=new DataInputStream(isv);
				          FileOutputStream is2v=new FileOutputStream(ruta+"\\Pagina.HTML");
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
				        	 FileInputStream ise=new FileInputStream(rutaempleado);
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
				        		  FileInputStream isp=new FileInputStream(rutaproducto);
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
			          }else {
			        	  File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
				          FileInputStream isv=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
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
				        	 FileInputStream ise=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.emp");
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
				        		  FileInputStream isp=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");
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
			        }
				 catch(IOException e){
			           System.out.println(e);
			        }
			}
		});
		ReporteDeVentas.setBounds(178, 98, 227, 26);
		getContentPane().add(ReporteDeVentas);
		
		JButton NumeroDeVentas = new JButton("Numero de ventas por empleado");
		NumeroDeVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(empleados!=null &&venta!=null){
						 File pagina=new File(ruta+"\\Pagina.HTML");
						  FileInputStream ise=new FileInputStream(rutaempleado);
				          DataInputStream dse=new DataInputStream(ise);
				          FileOutputStream is2v=new FileOutputStream(ruta+"\\Pagina.HTML");
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
				        	  FileInputStream isV=new FileInputStream(rutaventa);
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
					}else{
						 File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
						  FileInputStream ise=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.emp");
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
				        	  FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
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
					}
				catch(IOException e){
				}
			}
		});
		
		NumeroDeVentas.setBounds(178, 135, 227, 26);
		getContentPane().add(NumeroDeVentas);
		
		JButton VentasPorIntervalo = new JButton("Ventas por intervalo de fechas");
		VentasPorIntervalo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg) {
				String rangos;
				String [] fechas;
				int total = 0;
				rangos=(JOptionPane.showInputDialog(null,"ingresa el rango de fechas a buscar de la forma: dd/mm/aa-dd/mm/aa","Intervalos de fechas", JOptionPane.INFORMATION_MESSAGE));
				fechas=rangos.split("-");
				try{ if(venta!=null && producto!=null){
					File pagina=new File(ruta+"\\Pagina.HTML");
					 FileInputStream isV=new FileInputStream(rutaventa);
			         DataInputStream dsV=new DataInputStream(isV);
			         FileOutputStream is2v=new FileOutputStream(ruta +"\\Pagina.HTML");
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
				    // System.out.println(fechaI[0]);
			         while((lineaV=dsV.readLine())!=null){
			        	 camposV=lineaV.split("-");
			        	 fechav=camposV[1].split("/");
			        	 if(Integer.parseInt(fechaI[0])<=Integer.parseInt(fechav[0])&& Integer.parseInt(fechaI[1])<=Integer.parseInt(fechav[1])&&Integer.parseInt(fechaI[2])<=Integer.parseInt(fechav[2])
			        			&& Integer.parseInt(fechav[0])<=Integer.parseInt(fechaf[0])&&Integer.parseInt(fechav[1])<=Integer.parseInt(fechaf[1])&&Integer.parseInt(fechav[2])<=Integer.parseInt(fechaf[2]) ){
			        		  os.write(("<TD>"+camposV[1]+"</TD>").getBytes());//1
			        		  producto=camposV[0];
			        		  FileInputStream isp=new FileInputStream(rutaproducto);
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
				}else{
					File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
					 FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
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
				   //  System.out.println(fechaI[0]);
			         while((lineaV=dsV.readLine())!=null){
			        	 camposV=lineaV.split("-");
			        	 fechav=camposV[1].split("/");
			        	 if(Integer.parseInt(fechaI[0])<=Integer.parseInt(fechav[0])&& Integer.parseInt(fechaI[1])<=Integer.parseInt(fechav[1])&&Integer.parseInt(fechaI[2])<=Integer.parseInt(fechav[2])
			        			&& Integer.parseInt(fechav[0])<=Integer.parseInt(fechaf[0])&&Integer.parseInt(fechav[1])<=Integer.parseInt(fechaf[1])&&Integer.parseInt(fechav[2])<=Integer.parseInt(fechaf[2]) ){
			        		  os.write(("<TD>"+camposV[1]+"</TD>").getBytes());//1
			        		  producto=camposV[0];
			        		  FileInputStream isp=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");
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
					
				}
				catch(IOException e){					
				}
			}
		});
		VentasPorIntervalo.setBounds(178, 172, 227, 28);
		getContentPane().add(VentasPorIntervalo);
		
		JButton ComprasRealizadasPor = new JButton("Compras realizadas por clientes");
		ComprasRealizadasPor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				try{
					if(cliente!=null && venta!=null){
					
						File pagina=new File(ruta+"\\Pagina.HTML");
						
						FileInputStream isc=new FileInputStream(rutacliente);
						DataInputStream dsc=new DataInputStream(isc);
						FileOutputStream is2v=new FileOutputStream(ruta+"\\Pagina.HTML");
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
				        	 FileInputStream isV=new FileInputStream(rutaventa);
					         DataInputStream dsV=new DataInputStream(isV);
				        	comprasCliente=0;
				        	while((lineaV=dsV.readLine())!=null){
					        	 camposV=lineaV.split("-");
					        	 if(camposC[0].equals(camposV[4])){
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
					else{
						File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
						FileInputStream isc=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.clt");
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
				        	 FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
					         DataInputStream dsV=new DataInputStream(isV);
					        
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
					
				}
				catch(IOException e){
					
				}
				
			}
		});
		ComprasRealizadasPor.setBounds(178, 211, 227, 28);
		getContentPane().add(ComprasRealizadasPor);
		
		JButton cargaArchivos = new JButton("Cargar Archivos");
		cargaArchivos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				
				JFileChooser selector=new JFileChooser();
				selector.setFileFilter(filtro);
				selector.setMultiSelectionEnabled(true);
				int r=selector.showOpenDialog(null);
				if(r==JFileChooser.APPROVE_OPTION){
					File archivos[]=selector.getSelectedFiles();
					
					
					for(int i=0;i<archivos.length;i++){
						
						if(archivos[i].getName().equals("EMPLEADO.emp")){
							empleados=archivos[i].getParent()+archivos[i].getName();
							ruta=archivos[i].getParent();	
							rutaempleado=archivos[i].getAbsolutePath();
						}
						else if(archivos[i].getName().equals("PRODUCTO.prt")){
							producto=archivos[i].getParent()+archivos[i].getName();
							ruta=archivos[i].getParent();
							rutaproducto=archivos[i].getAbsolutePath();
						}
						else if(archivos[i].getName().equals("VENTA.fct")){
							venta=archivos[i].getParent()+archivos[i].getName();
							rutaventa=archivos[i].getAbsolutePath();
							ruta=archivos[i].getParent();	
						}
						else if(archivos[i].getName().equals("CLIENTE.clt")){
							cliente=archivos[i].getParent()+archivos[i].getName();
							rutacliente=archivos[i].getAbsolutePath();
							
							ruta=archivos[i].getParent();	
						}
						
					}
					
				}
		  
		
				
			}
		});
		cargaArchivos.setBounds(494, 311, 161, 23);
		getContentPane().add(cargaArchivos);
		
		JButton AgregarCliente = new JButton("Agregar cliente");
		AgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elemento;
				try{
					if(cliente!=null){
						File clienteDoc;
						{
						clienteDoc=new File(rutacliente);}
						FileOutputStream os=new FileOutputStream(rutacliente);
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
							os.write((campos2[2]+"-"+"\r\n").getBytes());
						}
						os.close();
						ds.close();
					}else{
						File clienteDoc;
						{
						clienteDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.clt");}
						FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.clt");
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
							os.write((campos2[2]+"-"+"\r\n").getBytes());
						}
						os.close();
						ds.close();
					}
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarCliente.setBounds(28, 178, 125, 26);
		getContentPane().add(AgregarCliente);
		
		JButton AgregarProducto = new JButton("Agregar producto");
		AgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String elemento;
				try{
					if(producto!=null){
						File productoDoc;
						{
						productoDoc=new File(rutaproducto);}
						FileOutputStream os=new FileOutputStream(rutaproducto);
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
					}else{
						File productoDoc;
						{
						productoDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");}
						FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");
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
					if(empleados!=null){
						File empleadoDoc;
						FileOutputStream os;
						{
						empleadoDoc=new File(rutaempleado);}
						if(empleadoDoc.exists()){
							os=new FileOutputStream(empleadoDoc);
						}
						else{
							 os=new FileOutputStream(rutaempleado);
						}
						
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
					else{
						File empleadoDoc;
						FileOutputStream os;
						{
						empleadoDoc=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.emp");}
						if(empleadoDoc.exists()){
							os=new FileOutputStream(empleadoDoc);
						}
						else{
							 os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.emp");
						}
						
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
				}
				catch(IOException e2){
					System.out.println("Error de archivo: "+e2);
				}
			}
		});
		AgregarEmpleado.setBounds(28, 215, 125, 26);
		getContentPane().add(AgregarEmpleado);
		
		JButton btnBuscarCliente = new JButton("Buscar Cliente");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(cliente!=null){
						String nombre,lineaC,mensaje;
						String[] camposC;
						nombre=(JOptionPane.showInputDialog(null,"ingresa el nombre del cliente a buscar","Buscador", JOptionPane.INFORMATION_MESSAGE));
						FileInputStream isc=new FileInputStream(rutacliente);
						DataInputStream dsc=new DataInputStream(isc);
						while((lineaC=dsc.readLine())!=null){
							camposC=lineaC.split("-");
							if (camposC[1].equals(nombre)){
							
							JOptionPane.showMessageDialog(null,"Codigo: "+camposC[0]+" Nombre: "+camposC[1]+" Apellido: "+camposC[2]+" Nit: "+camposC[3]);
							}
							
						}
					}else{
						String nombre,lineaC,mensaje;
						String[] camposC;
						nombre=(JOptionPane.showInputDialog(null,"ingresa el nombre del cliente a buscar","Buscador", JOptionPane.INFORMATION_MESSAGE));
						FileInputStream isc=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.clt");
						DataInputStream dsc=new DataInputStream(isc);
						while((lineaC=dsc.readLine())!=null){
							camposC=lineaC.split("-");
							if (camposC[1].equals(nombre)){
							
							JOptionPane.showMessageDialog(null,"Codigo: "+camposC[0]+" Nombre: "+camposC[1]+" Apellido: "+camposC[2]+" Nit: "+camposC[3]);
							}
							
						}
					}
				}
				catch(IOException e){
					
				}
			}
		});
		btnBuscarCliente.setBounds(416, 98, 140, 26);
		getContentPane().add(btnBuscarCliente);
		
		JButton btnBuscarEmpleado = new JButton("Buscar Empleado");
		btnBuscarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					if(empleados!=null){
						String nombre,lineaE,mensaje;
						String[] camposE;
						nombre=(JOptionPane.showInputDialog(null,"Ingresa el nombre del Empleado a buscar","Buscador", JOptionPane.INFORMATION_MESSAGE));
						FileInputStream ise=new FileInputStream(rutaempleado);
						DataInputStream dse=new DataInputStream(ise);
						while((lineaE=dse.readLine())!=null){
							camposE=lineaE.split("-");
							if (camposE[1].equals(nombre)){
							
							JOptionPane.showMessageDialog(null,"Codigo: "+camposE[0]+" Nombre: "+camposE[1]+" Apellido: "+camposE[2]+" Edad: "+camposE[3]+" Puesto"+camposE[3]);
							}
							
						}
					}else{
						String nombre,lineaE,mensaje;
						String[] camposE;
						nombre=(JOptionPane.showInputDialog(null,"Ingresa el nombre del Empleado a buscar","Buscador", JOptionPane.INFORMATION_MESSAGE));
						FileInputStream ise=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\EMPLEADO.emp");
						DataInputStream dse=new DataInputStream(ise);
						while((lineaE=dse.readLine())!=null){
							camposE=lineaE.split("-");
							if (camposE[1].equals(nombre)){
							
							JOptionPane.showMessageDialog(null,"Codigo: "+camposE[0]+" Nombre: "+camposE[1]+" Apellido: "+camposE[2]+" Edad: "+camposE[3]+" Puesto"+camposE[3]);
							}
							
						}
					}
				}
				catch(IOException e){
					
				}
			}
		});
		btnBuscarEmpleado.setBounds(415, 135, 141, 26);
		getContentPane().add(btnBuscarEmpleado);
		
		JButton btnBuscarProducto = new JButton("Buscar Producto");
		btnBuscarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					if(producto!=null){

						String codigo,lineaP,mensaje;
						String[] camposP;
						codigo=(JOptionPane.showInputDialog(null,"Ingresa el codigo del producto a buscar","Buscador", JOptionPane.INFORMATION_MESSAGE));
						FileInputStream isp=new FileInputStream(rutaproducto);
						DataInputStream dsp=new DataInputStream(isp);
						while((lineaP=dsp.readLine())!=null){
							camposP=lineaP.split("-");
							if (camposP[0].equals(codigo)){
							
							JOptionPane.showMessageDialog(null,"Codigo: "+camposP[0]+" Producto: "+camposP[1]+" Precio: "+camposP[2]);
							}
							
						}
					}else{

						String codigo,lineaP,mensaje;
						String[] camposP;
						codigo=(JOptionPane.showInputDialog(null,"Ingresa el codigo del producto a buscar","Buscador", JOptionPane.INFORMATION_MESSAGE));
						FileInputStream isp=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");
						DataInputStream dsp=new DataInputStream(isp);
						while((lineaP=dsp.readLine())!=null){
							camposP=lineaP.split("-");
							if (camposP[0].equals(codigo)){
							
							JOptionPane.showMessageDialog(null,"Codigo: "+camposP[0]+" Producto: "+camposP[1]+" Precio: "+camposP[2]);
							}
							
						}
					}
				}
				catch(IOException e2){
					
				}
			}
		});
		btnBuscarProducto.setBounds(415, 172, 141, 28);
		getContentPane().add(btnBuscarProducto);
		
		JButton btnEliminarCliente = new JButton("Eliminar Cliente");
		btnEliminarCliente.setBounds(28, 274, 125, 26);
		getContentPane().add(btnEliminarCliente);
		
		JButton btnEliminarProdcuto = new JButton("Eliminar producto");
		btnEliminarProdcuto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String codigo,lineaP,mensaje;
					String[] camposP;
					codigo=(JOptionPane.showInputDialog(null,"Ingresa el codigo del producto a eliminar","Buscador", JOptionPane.INFORMATION_MESSAGE));
					FileInputStream isp=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");
					DataInputStream dsp=new DataInputStream(isp);
					FileOutputStream os=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\PRODUCTO.prt");
					DataOutputStream ds=new DataOutputStream(os);
					while((lineaP=dsp.readLine())!=null){
						camposP=lineaP.split("-");
						if ((camposP[0].equals(codigo))){
						
							
						
						//JOptionPane.showMessageDialog(null,"Codigo: "+camposP[0]+" Producto: "+camposP[1]+" Precio: "+camposP[2]);
					//	for(int i=0;i<camposP.length;i++){
						//	camposP[i]=null;
						//}
						}
						else{
							os.write((lineaP+"\r\n").getBytes());
						}
						
					}
				}
				catch(IOException e2){
					
				}
			}
		});
		btnEliminarProdcuto.setBounds(178, 273, 141, 26);
		getContentPane().add(btnEliminarProdcuto);
		
		JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
		btnEliminarEmpleado.setBounds(85, 309, 147, 26);
		getContentPane().add(btnEliminarEmpleado);
		
		JButton btnReporteOrdenadoDe = new JButton("Reporte Ordenado de Ventas");
		btnReporteOrdenadoDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
					
			*/	
			}
		});
		btnReporteOrdenadoDe.setBounds(454, 0, 201, 23);
		getContentPane().add(btnReporteOrdenadoDe);
		
		JButton btnReporteOrdenadoDe_1 = new JButton("Reporte Ordenado de Clientes");
		btnReporteOrdenadoDe_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					File pagina=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
					FileInputStream isc=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.clt");
					DataInputStream dsc=new DataInputStream(isc);
					FileOutputStream is2v=new FileOutputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\Pagina.HTML");
			        DataOutputStream os=new DataOutputStream(is2v);
			        String lineaC,lineaV;
			        String [] camposC,camposV;
			        int comprasCliente=0, tmp = 0 ,aux = 0;
			        String lineaC2,lineaV2;
			        String [] camposC2,camposV2;
			        int comprasCliente2=0, tmp2 = 0 ,aux2 = 0;
			        
			        while((lineaC=dsc.readLine())!=null){
			        	
			        	camposC=lineaC.split("-");
			        	 FileInputStream isV=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
				         DataInputStream dsV=new DataInputStream(isV);
				       
			        	comprasCliente=0;
			        	while((lineaV=dsV.readLine())!=null){
				        	 camposV=lineaV.split("-");
				        	 if( camposC[0].equals(camposV[4])){
				        		 comprasCliente=comprasCliente+1;
				        	 }
			        	}
			        	aux=aux+1;
			        	
			        }
			        int [] bu=new int[aux];
			        FileInputStream isc2=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\CLIENTE.clt");
					DataInputStream dsc2=new DataInputStream(isc2);
			        while((lineaC2=dsc2.readLine())!=null){
			        	
			        	camposC2=lineaC2.split("-");
			        	 FileInputStream isV2=new FileInputStream("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");
				         DataInputStream dsV2=new DataInputStream(isV2);
				       
			        	comprasCliente2=0;
			        	while((lineaV2=dsV2.readLine())!=null){
				        	 camposV2=lineaV2.split("-");
				        	 if( camposC2[0].equals(camposV2[4])){
				        		 comprasCliente2=comprasCliente2+1;
				        	 }
			        	}
			        	tmp=tmp+1;
			        	 bu[tmp-1]=comprasCliente2;
			        }
		        	
			        int i, j, aux3;
			         for(i=0;i<bu.length-1;i++)
			              for(j=0;j<bu.length-i-1;j++)
			                   if(bu[j+1]<bu[j]){
			                      aux3=bu[j+1];
			                      bu[j+1]=bu[j];
			                      bu[j]=aux3;
			                   }
			         	os.write(("<table BORDER=\"3\"ALIGN=\"center\">").getBytes());
				        os.write(("<TH COLSPAN=8 BGCOLOR=\"#6D8FFF\"> Numero de compras en orden</TH>").getBytes());
				        os.write(("<TR>").getBytes());
				        os.write(("<TD>Compras</TD>").getBytes());
				        os.write(("</TR>").getBytes());
				        for(int i2=0;i2<bu.length;i2++){
				        os.write(("<TR>").getBytes());
				        os.write(("<TD>"+bu[i2]+"</TD>").getBytes());//1
				        
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
		btnReporteOrdenadoDe_1.setBounds(454, 32, 201, 23);
		getContentPane().add(btnReporteOrdenadoDe_1);
		}
}
