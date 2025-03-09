package grafoLista;

public class MainLista {

	public static void main(String[] args) {
	    TGrafo g1 = new TGrafo(10);

		g1.insereA(0, 3);
		g1.insereA(1, 2);
		g1.insereA(3, 6);
		g1.insereA(3, 4);
		g1.insereA(4, 1);
		g1.insereA(4, 5);
		g1.insereA(4, 7);
		g1.insereA(5, 2);
		g1.insereA(5, 8);
		g1.insereA(6, 9);
		g1.insereA(7, 6);
		g1.insereA(7, 8);
		g1.insereA(8, 5);
		g1.insereA(9, 7);
		g1.insereA(9, 8);

		System.out.println("> Grafo G1 = (V1,A1) inserido, onde V1 = {0,1,2,3,4,5,6,7,8,9} e A1 = {(0,3),(1,2),(3,6),(3,4),(4,1),(4,5),(4,7),(5,2),(5,8),(6,9),(7,6),(7,8),(8,5),(9,7),(9,8)}.");
		System.out.println("  Também foram inseridos pesos nas arestas:\n");

		g1.show();

		TGrafo g2 = new TGrafo(10);

        g2.insereA(0, 3);
        g2.insereA(1, 2);
        g2.insereA(3, 6);
        g2.insereA(3, 4);
        g2.insereA(4, 1);
        g2.insereA(4, 5);
        g2.insereA(4, 7);
        g2.insereA(5, 2);
        g2.insereA(5, 8);
        g2.insereA(6, 9);
        g2.insereA(7, 6);
        g2.insereA(7, 8);
        g2.insereA(8, 5);
        g2.insereA(9, 7);
        g2.insereA(9, 8);

        System.out.println("\n> Grafo G2 inserido, onde V2 = {0,1,2,3,4,5,6,7,8,9} e A2 = {(0,3),(1,2),(3,6),(3,4),(4,1),(4,5),(4,7),(5,2),(5,8),(6,9),(7,6),(7,8),(8,5),(9,7),(9,8)}.");
        System.out.println("  Também foram inseridos pesos nas arestas:\n");

        g2.show();

		// g1.removeA(0,3);  // remove a aresta (0,3)

	    // System.out.print("\nDepois da remoção da aresta (0,3)\n");
	    // g1.show();

		System.out.println("\n> Testes:");

		System.out.println("  > 19) Método que calcula e retorna o grau de entrada de um vértice v de um grafo dirigido.");
		System.out.println("    - Grau de entrada do vértice 2 do grafo G1: " + g1.inDegree(2));
		System.out.println("    - Grau de entrada do vértice 4 do grafo G1: " + g1.inDegree(4));
		System.out.println("    - Grau de entrada do vértice 8 do grafo G1: " + g1.inDegree(8));

		System.out.println("\n  > 20) Método que calcula e retorna o grau de saída de um vértice v de um grafo dirigido.");
		System.out.println("    - Grau de entrada do vértice 2 do grafo G1: " + g1.outDegree(2));
		System.out.println("    - Grau de entrada do vértice 4 do grafo G1: " + g1.outDegree(4));
		System.out.println("    - Grau de entrada do vértice 8 do grafo G1: " + g1.outDegree(8));

		System.out.println("\n  > 21) Método que calcula e retorna o grau de um vértice v de um grafo dirigido.");
		System.out.println("    - Grau de entrada do vértice 2 do grafo G1: " + g1.degree(2));
		System.out.println("    - Grau de entrada do vértice 4 do grafo G1: " + g1.degree(4));
		System.out.println("    - Grau de entrada do vértice 8 do grafo G1: " + g1.degree(8));
		
		System.out.println("\n  > 22) Método que verifica iqualdade 2 grafos:");
        System.out.println("  - Os grafos G1 e G2 são iguais? " + g1.equals(g2));

		//TODO: Teste 23
		System.out.println("\n  > 23) ");

        System.out.println("\n  > 24) Método que inverte a ordem dos vértices adjacêntes na lista de adjacência:");
        g1.inveteOrdem(4); 

        System.out.println("\n  - Grafo G1 após a inversão da ordem de adjacência do vértice 4:");
        g1.show(); 

		System.out.println("\n  > 25) Método que retorna 1 se o vértice v for fonte.");
		System.out.println("    - Vértice 2 do grafo G1 é fonte? " + g1.verificaFonte(2));
		System.out.println("    - Vértice 0 do grafo G1 é fonte? " + g1.verificaFonte(0));

		System.out.println("\n  > 26) Método que retorna 1 se o vértice v for sorvedouro.");
		System.out.println("    - Vértice 2 do grafo G1 é sorvedouro? " + g1.verificaSorvedouro(2));
		System.out.println("    - Vértice 0 do grafo G1 é sorvedouro? " + g1.verificaSorvedouro(0));

		System.out.println("\n  > 27) Método que retorna 1 se o grafo G1 for simétrico.");
		System.out.println("    - Grafo G1 é simétrico? " + g1.verificaSimetria());

		/* System.out.println("\n  > 28) Método que lê um arquivo e constrói um grafo a partir dele.");
		System.out.println("    O arquivo lido contém o seguinte conteúdo: \n6\n8\n0 1\n0 5\n1 0\n1 5\n2 4\n3 1\n4 3\n3 5");

		TGrafo g3 = new TGrafo(0);              // inicialmente declaramos o grafo com um número de arestas qualquer,
												   // esse número será atualizado na execução do método

		if(g3.lerGTGrafo("grafo.txt") == 1) {
			System.out.println("    E gerou o seguinte grafo G3: ");
			g3.show();
		} */

		System.out.println("\n  > 29) Método que permite remover um vértice do grafo (não dirigido).");

		g2.removeVerticeND(9);

		System.out.println("    - Vértice 9 removido do grafo G2: \n");
		g2.show();
	    
		System.out.println("\n  > 30) Método que permite remover um vértice do grafo (dirigido).");

		g1.removeVertice(7);

		System.out.println("    - Vértice 7 removido do grafo G1: \n");
		g1.show();
		
	}

}
