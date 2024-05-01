package tarea_AFD;

import java.util.ArrayList;
import java.util.HashMap;

public class AFD {
	private String estado_inicial; //q0
	//key: simbolo del Estado, value: objeto Estado
	private HashMap<String,Estado> estados; //Q
	
	//constructor
	public AFD(String estado_ini, String est_finales, String transiciones) {
		this.estado_inicial = estado_ini;
		String[] tran = transiciones.split("-");
		ArrayList<String> lista_transiciones = new ArrayList<>();
		for(String elemento : tran) {
			elemento = elemento.replace("(", "").replace(")", "");
			lista_transiciones.add(elemento);
		}

		String[] estados_finales = est_finales.split(",");
		
		//key:simbolo del Estado, value: objeto Estado
		this.estados = new HashMap<>();
		
		//Creando objetos Estado y añadiendolos al conjunto
		for(String simbolo : estados_finales) {
			estados.put(simbolo, new Estado(simbolo, true));
		}
		
		if(!estados.containsKey(estado_inicial)) {
			estados.put(estado_inicial,new Estado(estado_inicial, false));
		}
		for(String transicion : lista_transiciones) {
			String[] partes = transicion.split(",");
			String simbolo = partes[0];
			if(estados.containsKey(simbolo)) {
				estados.get(simbolo).agregarTransicion(partes[1],partes[2]);
			}
			else {
				estados.put(simbolo,new Estado(simbolo,false));
			}
			
			if(!estados.containsKey(partes[2])) {
				estados.put(partes[2],new Estado(partes[2],false));
			}
		}
	}
	

	//getters de las variables estado_inicial y los estados
	public String getEstado_inicial() {
		return estado_inicial;
	}


	public HashMap<String, Estado> getEstados() {
		return estados;
	}
	
	//funciones
	public boolean lee_palabra(String palabra) {
		Estado aux = estados.get(estado_inicial);
		for(int i = 0; i < palabra.length(); i++) {
			String transicion = aux.leerSimbolo(palabra.charAt(i)+"");
			if(transicion != ""){
				aux = estados.get(transicion);
			}
			else //si lleva a "" entonces no se puede continuar, la palabra no es aceptada
				return false;
		}
		return aux.isFinal();
		
	}
	
	//public static void main(String[] args) {}
}
