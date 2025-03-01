package grafo;

public class TGrafo {

	private	int n;          // quantidade de vértices
	private	int m;          // quantidade de arestas
	private	int adj[][];    //matriz de adjacência

	public TGrafo(int n) {
	    this.n = n;
	    this.m = 0;         // inicialmente não há arestas
	    this.adj = new int [n][n];

	    // inicia a matriz com zeros
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				this.adj[i][j] = 0;
			}
		}
	}

	// insere uma aresta no Grafo tal que v é adjacente a w
	public void insereA(int v, int w) {
	    if(adj[v][w] == 0 ) {        // verifica se não temos aresta
	        adj[v][w] = 1;
	        m++;
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    if(adj[v][w] == 1 ){        // verifica se temos a aresta
	        adj[v][w] = 0;
	        m--;
	    }
	}

	// TODO: 1 - calcula o grau de entrada de um vértice
	public int inDegree(int v) {
		return 0;
	}

	// TODO: 2 - calcula o grau de saída de um vértice
	public int outDegree(int v) {
		return 0;
	}

	// 3 - calcula o grau de um vértice
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	// 6 - verifica se o grafo é simétrico
	public int verificaSimetria() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= i; j++) {
				if(adj[i][j] != adj[j][i]) {
					return 0;
				}
			}
		}

		return 1;
	}

	// apresenta o grafo contendo número de vértices, arestas e a matriz de adjacência obtida
	public void show() {
	    System.out.println("n: " + n);
	    System.out.println("m: " + m);

	    for(int i = 0; i < n; i++){
	    	System.out.print("\n");

	        for(int w = 0; w < n; w++) {
				if(adj[i][w] == 1) System.out.print("Adj[" + i + "," + w + "]= 1" + " ");
				else System.out.print("Adj[" + i + "," + w + "]= 0" + " ");
			}
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}
}