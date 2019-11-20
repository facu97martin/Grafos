package grafos;

public class Main {

	public static void main(String[] args) {

		double O = Grafo.INFINITO;

		double[][] matriz = { 
				{ O, 1, O, O, O },
				{ 3, O, 2, O, O },
				{ O, O, O, 3, O },
				{ O, O, O, O, 4 },
				{ O, 5, O, O, O },
				};

		String nodos[] = { "1", "2", "3", "4", "5" };
		Grafo grafo = new Grafo(matriz, nodos);

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (grafo.getValor(i, j) == O) {
					System.out.print("[-]");
				} else {

					System.out.print("[" + String.format("%1.0f", grafo.getValor(i, j)) + "]");
				}
			}
			System.out.println("");
		}
		
		System.out.println("-------------------");
		
		Grafo grafo2 = new Grafo(grafo.floyd(), nodos);
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (grafo2.getValor(i, j) == O) {
					System.out.print("[-]");
				} else {

					System.out.print("[" + String.format("%2.0f", grafo2.getValor(i, j)) + "]");
				}
			}
			System.out.println("");
		}

	}
}
