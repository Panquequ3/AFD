package tarea_AFD;

import java.util.ArrayList;
import java.util.Arrays;
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
		//pasando estados_finales a un array
		String[] estados_finales = est_finales.split(",");
		
		
		
		//llenando el AFD y creando los objetos Estado
		
		//key:simbolo del Estado, value: objeto Estado
		this.estados = new HashMap<>();
		
		//Creando objetos Estado y añadiendolos al conjunto
		//1: añadir estados iniciales y finales inmediatamente
		for(String simbolo : estados_finales) {
			estados.put(simbolo, new Estado(simbolo, true));
		}
		
		//si el estado inicial no es tambien estado final, entonces se agrega
		if(estados.containsKey(estado_inicial)) {
			estados.put(estado_inicial,new Estado(estado_inicial, false));
		}
		//2: añadir los estados restantes a partir de las transiciones:
		for(String transicion : lista_transiciones) {
			String[] partes = transicion.split(",");
			String simbolo = partes[0];
			if(estados.containsKey(simbolo)) {
				estados.get(simbolo).AgregarTransicion(partes[1],partes[2]); //porsiaca
			}
			else {
				estados.put(simbolo,new Estado(simbolo,false));
			}
			
			if(!estados.containsKey(partes[2])) {
				estados.put(partes[2],new Estado(partes[2],false));
			}
		}
	}
	

	//getters (porsiacaso)
	public String getEstado_inicial() {
		return estado_inicial;
	}


	public HashMap<String, Estado> getConjunto_estados() {
		return estados;
	}
	
	public void llenaEstados() {}
	
	
	//funciones
	public boolean lee_palabra(String palabra) {
		Estado aux = estados.get(estado_inicial);
		for(int i = 0; i < palabra.length(); i++) {
			String transicion = aux.LeerSimbolo(palabra.charAt(i)+"");
			//si la transicion lleva a un estado, entonces pasamos a ese estado
			if(transicion != ""){
				//System.out.println("el estado: " + aux.getValue() + " con " + palabra.charAt(i)+ " va al estado " +  transicion );
				aux = estados.get(transicion);
			}
			else //si lleva a "" entonces no se puede continuar, la palabra no es aceptada
				return false;
		}
		if(aux.isFinal())
			return true;
		return false;
	}



	public static void main(String[] args) {}
}
