package tarea_AFD;

import java.util.HashMap;

public class Estado {
	private String value; 
	private HashMap<String,String> Transiciones;
	private boolean estado_final;
	
	//Constructor de estado
	
	public Estado(String value, boolean esfinal) {
		this.value = value;
		this.estado_final = esfinal;
		this.Transiciones = new HashMap<String,String>();
	}

	//Funcion para agregar las transiciones a un estado, no permitiendo
	//el ingreso repetido
	
	public void AgregarTransicion(String simbolo, String estado) {
		if(!this.Transiciones.containsKey(simbolo)) {
			this.Transiciones.put(simbolo, estado);
		}
	}
	
	//Funcion para devolver el estado al que va con un simbolo
	
	public String LeerSimbolo(String simbolo) {
		if(this.Transiciones.containsKey(simbolo)) {
			return this.Transiciones.get(simbolo);
		}
		return ""; //Tiene que analizarlo pa que no acepte llaves vacias
	}
	
	//Estado para confirmar si un estado es final
	
	public boolean isFinal() {
		return this.estado_final;
	}

	//Solo esta esta funcion porque me lo pide :p
	
	public String getValue() {
		return value;
	}
	
}
