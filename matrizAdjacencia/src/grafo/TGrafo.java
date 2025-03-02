package grafo;

public class TGrafo {

	private final int n;            // quantidade de vértices
	private	int m;                  // quantidade de arestas
	private	float adj[][];          //matriz de adjacência

	private final float INF = Float.MAX_VALUE;     // define o valor do infinito para grafos ponderados

	public TGrafo(int n) {
	    this.n = n;
	    this.m = 0;                // inicialmente não há arestas
	    this.adj = new float [n][n];

	    // inicia a matriz com zeros
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				this.adj[i][j] = INF;
			}
		}
	}

	// insere uma aresta no Grafo tal que v é adjacente a w
	public void insereA(int v, int w, float valor) {
	    if(adj[v][w] == INF) {        // verifica se não temos aresta
	        adj[v][w] = valor;
	        m++;
	    }
	}
	
	// remove uma aresta v->w do Grafo	
	public void removeA(int v, int w) {
	    if(adj[v][w] != INF){        // verifica se temos a aresta
	        adj[v][w] = INF;
	        m--;
	    }
	}

	// TODO: 1 - calcula o grau de entrada de um vértice
	public int inDegree(int v) {
		int degree = 0;
		for(int i=0; i<this.n; i++){
			if(this.adj[i][v] != INF){
				degree++;
			}
		}
		return degree;
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
				if(adj[i][w] == INF) System.out.print("Adj[" + i + "," + w + "]= INF ");
				else System.out.print("Adj[" + i + "," + w + "]= " + adj[i][w] + " ");
			}
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}
}