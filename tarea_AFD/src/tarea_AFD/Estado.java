package tarea_AFD;

import java.util.HashMap;

public class Estado {
	private String value; 
	private HashMap<String,String> Transiciones;
	private boolean estado_final;
	
	public Estado(String value, boolean esfinal) {
		this.value = value;
		this.estado_final = esfinal;
		this.Transiciones = new HashMap<String,String>();
	}

	public void AgregarTransicion(String simbolo, String estado) {
		if(!this.Transiciones.containsKey(simbolo)) {
			this.Transiciones.put(simbolo, estado);
		}
	}
	
	public String LeerSimbolo(String simbolo) {
		if(this.Transiciones.containsKey(simbolo)) {
			return this.Transiciones.get(simbolo);
		}
		return ""; //Tiene que analizarlo
	}
	
	public boolean isFinal() {
		return this.estado_final;
	}

	public String getValue() {
		return value;
	}
	
}
