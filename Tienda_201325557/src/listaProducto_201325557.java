import java.io.File;

import javax.swing.JOptionPane;


public class listaProducto_201325557 {
	File venta;
	{
	venta=new File("C:\\Users\\Jaime\\Desktop\\Proyecto\\VENTA.fct");

	}
	int[] arreglo={};

										//logica de la lista para ventas

		nodoProducto_201325557 ultimo;

		listaProducto_201325557(){
			ultimo=null;
		}
		//metodo para cuando la lista esta vacia
		boolean estaVacia(){
			return ultimo==null;
		}
		//metodo para insertar nodos
		public listaProducto_201325557 insertar(String elemento){
			nodoProducto_201325557 nuevo=new nodoProducto_201325557(elemento);
			if(ultimo!=null){
				nuevo.siguiente=ultimo.siguiente;
				ultimo.siguiente=nuevo;
			}
			ultimo=nuevo;
			return this;
		}
		//metodo para eliminar nodo de  la lista!
		boolean eliminar(String elemento){
			nodoProducto_201325557 actual;
			boolean encontrar=false;
			actual=ultimo;
			while(actual.siguiente!=ultimo && !encontrar){
				encontrar=(actual.siguiente.dato==elemento);
				if(!encontrar){
					 actual=actual.siguiente;
				}
			}
			encontrar=(actual.siguiente.dato==elemento);
			if(encontrar){
				nodoProducto_201325557 auxiliar=actual.siguiente;
				if(ultimo==ultimo.siguiente){
					ultimo=null;
				}
				else{
					if(auxiliar==ultimo){
						ultimo=actual;
					}
					actual.siguiente=auxiliar.siguiente;
				}
				auxiliar=null;
			}
			return encontrar==true;
		}
		
		//metodo para mostrar la lista
		public void mostrarLista(){
			nodoProducto_201325557 auxiliar=ultimo.siguiente;
			String cadena="";
			do{
				cadena=cadena+"["+auxiliar.dato+"]->";
				auxiliar=auxiliar.siguiente;
			}while(auxiliar!=ultimo.siguiente);
			JOptionPane.showMessageDialog(null, cadena,"mostrando la lista circular",JOptionPane.INFORMATION_MESSAGE);
			
		}
}
