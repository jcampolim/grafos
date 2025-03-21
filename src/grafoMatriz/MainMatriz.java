// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste - 10408802

package grafoMatriz;

public class MainMatriz {
	public static void exerciciosMatriz() {
		TGrafo g1 = new TGrafo(10);

		g1.insereA(0, 3, 1);
		g1.insereA(1, 0, 2);
		g1.insereA(1, 2, 2);
		g1.insereA(3, 6, 3);
		g1.insereA(3, 4, 4);
		g1.insereA(4, 1, 5);
		g1.insereA(4, 5, 6);
		g1.insereA(4, 7, 7);
		g1.insereA(5, 2, 8);
		g1.insereA(5, 8, 9);
		g1.insereA(6, 9, 10);
		g1.insereA(7, 6, 11);
		g1.insereA(7, 8, 12);
		g1.insereA(8, 5, 13);
		g1.insereA(9, 7, 14);
		g1.insereA(9, 8, 15);

		System.out.println("> Grafo G1 = (V1,A1) inserido, onde V1 = {0,1,2,3,4,5,6,7,8,9} e A1 = {(0,3),(1,2),(3,6),(3,4),(4,1),(4,5),(4,7),(5,2),(5,8),(6,9),(7,6),(7,8),(8,5),(9,7),(9,8)}.");
		System.out.println("  Também foram inseridos pesos nas arestas:\n");

		g1.show();

		System.out.println("\n> Testes:");

		System.out.println("  > 1) Método que calcula e retorna o grau de entrada de um vértice v de um grafo dirigido.");
		System.out.println("    - Grau de entrada do vértice 2 do grafo G1: " + g1.inDegree(2));
		System.out.println("    - Grau de entrada do vértice 4 do grafo G1: " + g1.inDegree(4));
		System.out.println("    - Grau de entrada do vértice 8 do grafo G1: " + g1.inDegree(8));

		System.out.println("\n  > 2) Método que calcula e retorna o grau de saída de um vértice v de um grafo dirigido.");
		System.out.println("    - Grau de entrada do vértice 2 do grafo G1: " + g1.outDegree(2));
		System.out.println("    - Grau de entrada do vértice 4 do grafo G1: " + g1.outDegree(4));
		System.out.println("    - Grau de entrada do vértice 8 do grafo G1: " + g1.outDegree(8));

		System.out.println("\n  > 3) Método que calcula e retorna o grau de um vértice v de um grafo dirigido.");
		System.out.println("    - Grau de entrada do vértice 2 do grafo G1: " + g1.degree(2));
		System.out.println("    - Grau de entrada do vértice 4 do grafo G1: " + g1.degree(4));
		System.out.println("    - Grau de entrada do vértice 8 do grafo G1: " + g1.degree(8));

		System.out.println("\n  > 4) Método que retorna 1 se o vértice v for fonte.");
		System.out.println("    - Vértice 2 do grafo G1 é fonte? " + g1.verificaFonte(2));
		System.out.println("    - Vértice 0 do grafo G1 é fonte? " + g1.verificaFonte(0));

		System.out.println("\n  > 5) Método que retorna 1 se o vértice v for sorvedouro.");
		System.out.println("    - Vértice 2 do grafo G1 é sorvedouro? " + g1.verificaSorvedouro(2));
		System.out.println("    - Vértice 0 do grafo G1 é sorvedouro? " + g1.verificaSorvedouro(0));

		System.out.println("\n  > 6) Método que retorna 1 se o grafo G1 for simétrico.");
		System.out.println("    - Grafo G1 é simétrico? " + g1.verificaSimetria());

		System.out.println("\n  > 7) Método que lê um arquivo e constrói um grafo a partir dele.");
		System.out.println("    O arquivo lido contém o seguinte conteúdo: \n6\n8\n0 1\n0 5\n1 0\n1 5\n2 4\n3 1\n4 3\n3 5");

		TGrafo g2 = new TGrafo(0);              // inicialmente declaramos o grafo com um número de arestas qualquer,
												   // esse número será atualizado na execução do método

		if(g2.lerGTGrafo("grafo.txt") == 1) {
			System.out.println("    E gerou o seguinte grafo G2: ");
			g2.show();
		}

		System.out.println("\n  > 8) Criar uma classe para representar grafos não-dirigidos utilizando matriz de adjacência.");

		TGrafoND g3 = new TGrafoND(8);

		g3.insereA(0, 1);
		g3.insereA(0, 3);
		g3.insereA(0, 4);
		g3.insereA(1, 2);
		g3.insereA(1, 5);
		g3.insereA(2, 3);
		g3.insereA(2, 6);
		g3.insereA(3, 7);
		g3.insereA(4, 5);
		g3.insereA(4, 7);
		g3.insereA(5, 6);
		g3.insereA(6, 7);

		System.out.println("    Grafo G3 = (V3,A3) inserido com as arestas: A = {{0,1},{0,3},{0,4},{1,2},{1,5},{2,3},{2,6},{3,7},{4,5},{4,7},{5,6},{6,7}}");
		g3.show();

		System.out.println("\n  > 9) Método que calcula e retorna o grau de um vértice v de um grafo não dirigido.");
		System.out.println("    - Grau de entrada do vértice 4 do grafo G3: " + g3.degree(4));
		System.out.println("    - Grau de entrada do vértice 2 do grafo G3: " + g3.degree(2));
		System.out.println("    - Grau de entrada do vértice 7 do grafo G3: " + g3.degree(7));

		System.out.println("\n  > 10) Modifique a classe TGrafo e os métodos correspondentes para permitir a criação de um grafo direcionado rotulado nas arestas");

		System.out.println("\n  > 11) Método que permite remover uma areta do grafo (dirigido ou não dirigido).");

		g1.removeVertice(7);
		g3.removeVertice(1);

		System.out.println("    - Vértice 7 removido do grafo G1: \n");
		g1.show();

		System.out.println("\n    - Vértice 1 removido do grafo G3: \n");
		g3.show();

		System.out.println("\n  > 12) Método que verifica se o grafo não dirigido é completo.");
		System.out.println("    - Grafo G3 é completo? " + g3.verificaCompleto());

		System.out.println("\n  > 13) Método que verifica se o grafo dirigido é completo.");
		System.out.println("    - Grafo G1 é completo? " + g1.verificaCompleto());

		System.out.println("\n  > 14) Método que retorna o complemento (grafo complementar) de um grafo (dirigido ou não) na forma de uma matriz de adjacência.");
		System.out.println("    - Complemento do grafo G1: \n");
		g1.grafoComplementar().show();

		System.out.println("\n    - Complemento do grafo G3: \n");
		g3.grafoComplementar().show();

		System.out.println("\n  > 15) Método que retorna o tipo de conexidade de um grafo não direcionado (0 - conexo ou 1 - não conexo/desconexo).");
		System.out.println("    - Conexidade do grafo G3: " + g3.verificaConexidade());

		System.out.println("\n  > 16) Método que retorna o tipo de conexidade de um grafo direcionado (3 – C3, 2 – C2, 1 – C1 ou 0 – C0).");
		System.out.println("    - Conexidade do grafo G1: " + g1.verificaConexidade());

		System.out.println("\n  > 17) Método que retorne o grafo reduzido de um grafo direcionado no formato de uma matriz de adjacência.");
		System.out.println("    - Grafo reduzido do grafo G1: \n");
		g1.grafoReduzido().show();

	}
}
