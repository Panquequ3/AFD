package tarea_AFD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AFD {
	private String estado_ini; //q0
	//key: simbolo del Estado, value: objeto Estado
	private HashMap<String,Estado> conjunto_estados; //Q
	
	//constructor
	public AFD(String estado_ini, HashMap<String,Estado> conjunto_estados) {
		this.estado_ini = estado_ini;
		this.conjunto_estados = conjunto_estados;
	}
	

	//getters (porsiacaso)
	public String getEstado_ini() {
		return estado_ini;
	}


	public HashMap<String, Estado> getConjunto_estados() {
		return conjunto_estados;
	}
	
	//funciones
	public boolean lee_palabra(String palabra) {
		Estado aux = conjunto_estados.get(estado_ini);
		for(int i = 0; i < palabra.length(); i++) {
			String transicion = aux.LeerSimbolo(palabra.charAt(i)+"");
			//si la transicion lleva a un estado, entonces pasamos a ese estado
			if(transicion != ""){
				//System.out.println("el estado: " + aux.getValue() + " con " + palabra.charAt(i)+ " va al estado " +  transicion );
				aux = conjunto_estados.get(transicion);
			}
			else //si lleva a "" entonces no se puede continuar, la palabra no es aceptada
				return false;
		}
		if(aux.isFinal())
			return true;
		return false;
	}



	public static void main(String[] args) {
		//en teoria el input debiese ser como:
		String estado_inicial = "0";
		String[] estados_finales = {"1"};
		String transiciones = "(0,a,1)-(1,a,1)-(1,b,1)-(0,b,2)-(2,a,2)-(2,b,2)"; //desde el estado 0, con a, vamos al estado 1
		//pasando las transiciones a un arraylist
		String[] tran = transiciones.split("-");
		ArrayList<String> lista_transiciones = new ArrayList<>();
		for(String elemento : tran) {
			elemento = elemento.replace("(", "").replace(")", "");
			lista_transiciones.add(elemento);
		}
		
		String palabra = "baaaaaaaabbbbbaaabbbaabbabababababba";
		
		//llenando el AFD y creando los objetos Estado
		
		//key:simbolo del Estado, value: objeto Estado
		HashMap<String,Estado> estados = new HashMap<>();
		
		//Creando objetos Estado y añadiendolos al conjunto
		for(String transicion : lista_transiciones) {
			String[] partes = transicion.split(",");
			String simbolo = partes[0];
			//si es nulo, entonces no está el objeto, por ello lo crea y lo agrega
			if(estados.get(simbolo) == null) {
				int indice = Arrays.binarySearch(estados_finales, simbolo + "");
				boolean es_final = (indice >= 0) ? true : false; //indica si el estado simbolo es e. final o no
				Estado aux = new Estado(simbolo, es_final);
				for(String t : lista_transiciones) {
					String[] datos = t.split(",");
					if(simbolo.equals(datos[0])) {
						String sim = datos[2]; //estado 1, 2, etc
						String key = datos[1]; //a, b, etc
						aux.AgregarTransicion(key, sim);	
					}
						
				}
				estados.put(simbolo, aux);
					
			}
		}
		//creacion del AFD
		AFD afd = new AFD(estado_inicial, estados);
		System.out.println("el estado inicial del afd es: " + afd.getEstado_ini());
		
		if(afd.lee_palabra(palabra))
			System.out.println("fue aceptada :D");
		else
			System.out.println("no fue aceptada :C");
		
	}
}
