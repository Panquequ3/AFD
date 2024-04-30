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
			Estado aux = new Estado(simbolo, true);
			estados.put(simbolo,aux);
			for(String t : lista_transiciones) {
				String[] datos = t.split(",");
				if(simbolo.equals(datos[0])) {
					aux.AgregarTransicion(datos[1],datos[2]);	
					//datos[0]:estado 1, 2, etc
					//datos[2]:a, b, etc
				}		
			}
		}
		
		//si el estado inicial no es tambien estado final, entonces se agrega
		if(estados.containsKey(estado_inicial)) {
			Estado aux = new Estado(estado_inicial, false);
			estados.put(estado_inicial,aux);
			for(String t : lista_transiciones) {
				String[] datos = t.split(",");
				if(estado_inicial.equals(datos[0])) {
					aux.AgregarTransicion(datos[1],datos[2]);	
				}
					
			}
		}
		//2: añadir los estados restantes a partir de las transiciones:
		for(String transicion : lista_transiciones) {
			String[] partes = transicion.split(",");
			String simbolo = partes[0];
			//si es nulo, entonces no está el objeto, por ello lo crea y lo agrega
			if(estados.get(simbolo) == null) {
				Estado aux = new Estado(simbolo, false);
				for(String t : lista_transiciones) {
					String[] datos = t.split(",");
					if(simbolo.equals(datos[0])) {
						aux.AgregarTransicion(datos[1],datos[2]);	
					}
						
				}
				estados.put(simbolo, aux);
					
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
