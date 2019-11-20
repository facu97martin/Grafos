package grafos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class Grafo {

	public static final double INFINITO = Double.MAX_VALUE;

	private double[][] matriz;
	private String[] nodos;
	private Map<String, Integer> mapaNodos;

	public double getValor(int i, int j) {
		return this.matriz[i][j];
	}

	public Grafo(double[][] matriz, String[] vecNodos) {

		this.matriz = matriz;

		this.nodos = new String[matriz.length];

		this.mapaNodos = new HashMap<String, Integer>();

		int i = 0;

		for (String nodo : vecNodos) {

			nodos[i] = nodo;
			mapaNodos.put(nodo, i++);
		}
	}

	public void recorrerBFS(String nodo) {

		int cantNodos = this.nodos.length;
		boolean listaNodos[] = new boolean[cantNodos];

		Queue<String> cola = new LinkedList<String>();
		cola.add(nodo);

		while (!cola.isEmpty()) {

			String nodoActual = cola.poll();
			int indiceNodo = mapaNodos.get(nodoActual);
			if (listaNodos[indiceNodo] == false) {
				listaNodos[indiceNodo] = true;
				for (int j = 0; j < cantNodos; j++) {
					if (matriz[indiceNodo][j] < Grafo.INFINITO && listaNodos[j] == false) {
						cola.add(nodos[j]);
					}
				}
				procesoRecorrida(nodoActual);
			}
		}
	}

	public void recorrerDFS(String nodo) { // dfs es igual que bfs, pero cambiando la cola por la pila

		int cantNodos = this.nodos.length;
		boolean listaNodos[] = new boolean[cantNodos];

		Stack<String> pila = new Stack<String>();
		pila.push(nodo);

		while (!pila.isEmpty()) {

			String nodoActual = pila.pop();
			int indiceNodo = mapaNodos.get(nodoActual);
			if (listaNodos[indiceNodo] == false) {
				listaNodos[indiceNodo] = true;
				for (int j = 0; j < cantNodos; j++) {
					if (matriz[indiceNodo][j] < Grafo.INFINITO && listaNodos[j] == false) {
						pila.push(nodos[j]);
					}
				}
				procesoRecorrida(nodoActual);
			}
		}
	}

	private void procesoRecorrida(String nodo) {
		System.out.println("Procesando: " + nodo);
	}

	/*
	 * @Override public Double[] dijkstraCostos(String nodo) {
	 * 
	 * int cantNodos = this.nodos.length; Double[] vecCostos = new
	 * Double[cantNodos]; int indiceNodo = mapaNodos.get(nodo);
	 * 
	 * vecCostos = this.matriz[indiceNodo];
	 * 
	 * PriorityQueue<String> cola = new PriorityQueue<String>();
	 * 
	 * 
	 * 
	 * return null; }
	 */
	public String[] dijkstraCamino(String nodo) {
		// TODO Auto-generated method stub
		return null;
	}

	public Double[] dijkstraCostos(String nodoSolucion) {

		int n = matriz.length;

		Double[] vecCostos = new Double[n];

		int indiceNodo = mapaNodos.get(nodoSolucion);

		for (int i = 0; i < n; i++) {
			vecCostos[i] = matriz[indiceNodo][i];
		}

		List<String> solucion = new ArrayList<String>();
		List<String> universoMenosSolucion = new ArrayList<String>();

		solucion.add(nodoSolucion);

		for (String nodo : nodos) {
			if (nodo != nodoSolucion) {
				universoMenosSolucion.add(nodo);
			}
		}

		while (!universoMenosSolucion.isEmpty()) {

			int w = hallarMenor(vecCostos, universoMenosSolucion);

			for (String nodo : universoMenosSolucion) {

				vecCostos[mapaNodos.get(nodo)] = Math.min(vecCostos[mapaNodos.get(nodo)],
						vecCostos[w] + matriz[w][mapaNodos.get(nodo)]);

			}

			solucion.add(nodos[w]);
			universoMenosSolucion.remove(nodos[w]);
		}

		return vecCostos;
	}

	private int hallarMenor(Double[] vecCostos, List<String> universoMenosSolucion) {

		Double menor = null;
		int indiceMenor = 0;
		boolean primero = true;

		for (String nodo : universoMenosSolucion) {

			double menorAux = vecCostos[mapaNodos.get(nodo)];

			if (primero || menorAux < menor) {
				menor = menorAux;
				indiceMenor = mapaNodos.get(nodo);
				primero = false;
			}
		}

		return indiceMenor;
	}

	public double[][] floyd() {

		int cantNodos = this.nodos.length;
		double[][] matrizFloyd = new double[cantNodos][cantNodos];

		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				matrizFloyd[i][j] = this.matriz[i][j];
			}
		}

		for (int k = 0; k < cantNodos; k++) {
			for (int i = 0; i < cantNodos; i++) {
				for (int j = 0; j < cantNodos; j++) {
					if (k != i && k != j) {
						matrizFloyd[i][j] = Math.min(matrizFloyd[i][j], matrizFloyd[i][k] + matrizFloyd[k][j]);
					}
				}
			}
		}

		return matrizFloyd;
	}

	public boolean[][] warshall() {

		int cantNodos = this.nodos.length;
		boolean[][] matrizWarshall = new boolean[cantNodos][cantNodos];

		for (int i = 0; i < cantNodos; i++) {
			for (int j = 0; j < cantNodos; j++) {
				matrizWarshall[i][j] = this.matriz[i][j] < Grafo.INFINITO;
			}
		}

		for (int k = 0; k < cantNodos; k++) {
			for (int i = 0; i < cantNodos; i++) {
				for (int j = 0; j < cantNodos; j++) {
					if (k != i && k != j && i != j) {
						matrizWarshall[i][j] = (matrizWarshall[i][j]) || (matrizWarshall[i][k] && matrizWarshall[k][j]);
					}
				}
			}
		}

		return matrizWarshall;
	}

	public Grafo kruskall() {

		return null;
	}

	public Grafo prim() {

		PriorityQueue<String> cola = new PriorityQueue<String>();
		 

		return null;
	}

}
