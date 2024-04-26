package tarea_AFD;

import java.util.HashMap;

public class AFD {
	private char estado_ini; //q0
	private HashMap<String,Estado> conjunto_estados; //Q
	
	//constructor
	public AFD(String palabra_entrada, char estado_ini) {
		super();
		this.estado_ini = estado_ini;
		conjunto_estados = new HashMap<String, Estado>();
	}
	

	//getters (porsiacaso)
	public char getEstado_ini() {
		return estado_ini;
	}


	public HashMap<String, Estado> getConjunto_estados() {
		return conjunto_estados;
	}
	
	//funciones
	public boolean lee_palabra() {
		return true;
	}



	public static void main(String[] args) {
		System.out.print("Bruja");
	}
}
