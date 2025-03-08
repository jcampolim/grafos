package grafoMatriz;

public class TGrafoND {
    
    private	int n;          // quantidade de vértices
	private	int m;          // quantidade de arestas
	private int[][] adj;    //matriz de adjacência

	public TGrafoND(int n) {
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

	// insere uma aresta no TGrafo tal que v é adjacente a w e w é adjacente a v
	public void insereA(int v, int w) {
	    if(adj[v][w] == 0) {         // verifica se não temos aresta
	        adj[v][w] = 1;
            adj[w][v] = 1;
	        m++;
	    }
	}
	
	// remove uma aresta v->w/w->v do TGrafo
	public void removeA(int v, int w) {
	    if(adj[v][w] == 1) {        // verifica se temos a aresta
	        adj[v][w] = 0;
            adj[w][v] = 0;
	        m--;
	    }
	}

    // 9 - calcula o grau de um vértice
    public int degree(int v) {
        int degree = 0;

        for(int i = 0; i < n; i++) {
            if(adj[v][i] == 1) degree++;
        }

        return degree;
    }

    // 11 - remove vértice do grafo não direcionado
    public void removeVertice(int v) {
        for (int i = 0; i < n; i++) {
            if (adj[v][i] == 1) {
                adj[v][i] = 0;
                adj[i][v] = 0;
                m--;
            }
        }

        for (int i = v; i < n - 1; i++) {
            for (int j = 0; j < n; j++) {
                adj[i][j] = adj[i + 1][j];
                adj[j][i] = adj[j][i + 1];
            }
        }

        n--;
    }

    // 12 - verifica se o grafo é completo
    public boolean verificaCompleto() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(adj[i][j] == 0) return false;
            }
        }

        return true;
    }

    // 14 - retorna o grafo complementar de um grafo não dirigido 
    public TGrafoND grafoComplementar() {
        TGrafoND complemento = new TGrafoND(this.n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && adj[i][j] == 0) {  
                    complemento.insereA(i, j);
                }
            }
        }

        return complemento;
    }

    // 15 - retorna o tipo de conexidade
    public int verificaConexidade() {
        for(int i = 0; i < n; i++) {
            boolean desconexo = true;

            for(int j = 0; j < n; j++) {
                if(adj[i][j] == 1) {
                    desconexo = false;
                    break;
                }
            }

            if(desconexo) return 1;
        }

        return 0;
    }

    public void show() {
        System.out.println("n: " + n);
        System.out.println("m: " + m);

        for(int i = 0; i < n; i++){
            System.out.print("\n");

            for(int w = 0; w < n; w++) {
                if(adj[i][w] == 0) System.out.print("Adj[" + i + "," + w + "] = 0 ");
                else System.out.print("Adj[" + i + "," + w + "] = 1 ");
            }
        }
        System.out.println("\n\nfim da impressao do grafo." );
    }
}
