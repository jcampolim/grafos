package grafoMatriz;

public class MainMatriz {
	public static void main(String[] args) {
		TGrafo g = new TGrafo(4);

		// insere as arestas do grafo A={(0,1),(0,2),(2,1),(2,3),(1,3)}
		g.insereA(0,1, 1);
		g.insereA(0,2, 2);
		g.insereA(2,1, 3);
		g.insereA(2,3, 4);
		g.insereA(1,3, 5);

		// mostra o grafo preenchido
		g.show();

		System.out.println(g.degree(0));
		System.out.println(g.degree(1));
		System.out.println(g.degree(2));
		System.out.println(g.degree(3));

		System.out.println("Grau de saída do vértice 2: " + g.outDegree(2)); 

		System.out.println("Verificar Sorvedouro - Vértice 1" + g.verificaSorvedouro(1));

		g.removeVertice(2);
		System.out.print("\nDepois da remoção do vértice 2\n");
		g.show();  

		System.out.println("\nGrafo complementar:");
		TGrafo complemento = g.grafoComplementar();  
		complemento.show();  

		System.out.println("\nGrafo reduzido:");
		TGrafo reduzido = g.grafoReduzido();  
		reduzido.show(); 

	}
}
