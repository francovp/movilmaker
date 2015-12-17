package colecciones;

import java.util.ArrayList;

public class SuperColeccion {
	private ArrayList<Object> lista;
	private int actual;
	
	public ArrayList<Object> getLista() {
		return lista;
	}
	public void setLista(ArrayList<Object> lista) {
		this.lista = lista;
	}
	public int getActual() {
		return actual;
	}
	public void setActual(int actual) {
		this.actual = actual;
	}
	
	public void append(Object o){
		if (count()==0)
		{
			actual=1;
			lista.add(o);
			
		}
		else
		{
			lista.add(o);
			actual=count();
		}
	}
	
	public int count(){
		if(lista.size()!=0)
			return lista.size();
		else
			return 0;
	}

	public Object actualValue(){
		if(count()!= 0)
			return lista.get(actual);
		else
			return null;
	}
	
	public Object actualValue(int index){
		if(count()!= 0)
			return lista.get(index);
		else
			return null;
	}
    
	public void setPos(int i){
		if(i<=count())
			actual=i;
	}
	
	public Object removeValue(){
		return lista.remove(actual);
	}
}
