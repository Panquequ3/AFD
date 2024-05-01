package tarea_AFD;

import java.util.HashMap;

public class Estado {
	private String value; 
	private HashMap<String,String> transiciones;
	private boolean estado_final;
	
	//Constructor de estado
	
	public Estado(String value, boolean esfinal) {
		this.value = value;
		this.estado_final = esfinal;
		this.transiciones = new HashMap<String,String>();
	}

	//Funcion para agregar las transiciones a un estado, no permitiendo
	//el ingreso repetido
	
	public void agregarTransicion(String simbolo, String estado) {
		if(!this.transiciones.containsKey(simbolo)) {
			this.transiciones.put(simbolo, estado);
		}
	}
	
	//Funcion para devolver el estado al que va con un simbolo
	
	public String leerSimbolo(String simbolo) {
		if(this.transiciones.containsKey(simbolo)) {
			return this.transiciones.get(simbolo);
		}
		return "";
	}
	
	//Estado para confirmar si un estado es final
	
	public boolean isFinal() {
		return this.estado_final;
	}

	//Getter del valor del estado
	
	public String getValue() {
		return value;
	}
	
}
