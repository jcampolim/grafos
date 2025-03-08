package grafoMatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TGrafo {

	private  int n;                 // quantidade de vértices
	private	int m;                  // quantidade de arestas
	private float[][] adj;          //matriz de adjacência

	private final float INF = Float.MAX_VALUE;     // define o valor do infinito para grafos ponderados

	public TGrafo(int n) {
	    this.n = n;
	    this.m = 0;                 // inicialmente não há arestas
	    this.adj = new float [n][n];

	    // inicia a matriz com zeros
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				this.adj[i][j] = INF;
			}
		}
	}

	// insere uma aresta no TGrafo tal que v é adjacente a w
	public void insereA(int v, int w, float valor) {
	    if(adj[v][w] == INF) {        // verifica se não temos aresta
	        adj[v][w] = valor;
	        m++;
	    }
	}
	
	// remove uma aresta v->w do TGrafo
	public void removeA(int v, int w) {
	    if(adj[v][w] != INF){        // verifica se temos a aresta
	        adj[v][w] = INF;
	        m--;
	    }
	}

	// 1 - calcula o grau de entrada de um vértice
	public int inDegree(int v) {
		int degree = 0;

		for(int i = 0; i < this.n; i++){
			if(this.adj[i][v] != INF){
				degree++;
			}
		}

		return degree;
	}

	// 2 - calcula o grau de saída de um vértice
	public int outDegree(int v) {
		int degree = 0;

		for (int i = 0; i < this.n; i++) {
			if (this.adj[v][i] != INF) {
				degree++;
			}
		}

		return degree;
	}

	// 3 - calcula o grau de um vértice
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	// 4 - verifica se um vértice é fonte
	public int verificaFonte(int v){
		return (outDegree(v) > 0 && inDegree(v) == 0) ? 1 : 0;
	}
	
	// 5 - verifica se o vértice é sorvedouro
	public int verificaSorvedouro(int v) {
		return (inDegree(v) > 0 && outDegree(v) == 0) ? 1 : 0;
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

	// 7 - constrói grafo a partir de arquivo
	public int lerGTGrafo(String file){
		try {
            Scanner scanner = new Scanner(new File(file));

            this.n = scanner.nextInt();   // número de vértices
			int arestasLidas = scanner.nextInt();   // número de arestas

		    this.adj = new float [n][n];
			this.m = 0;

			 // inicializa a matriz com INF
			 for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					this.adj[i][j] = INF;
				}
			}
		    
            while(scanner.hasNextInt()) {
                int origem = scanner.nextInt();
                int destino = scanner.nextInt();

				this.insereA(origem, destino, 0);
            }

            scanner.close();

			// Verificar erro: numero de arestas n e quantidade de linhas no arquivo diferente
			if(arestasLidas != m) return 0;
            return 1;

        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + file);
            return 0;
        }	
	}

	// 11 - remove vértice do grafo direcionado
	public void removeVertice(int v) {
        for (int i = 0; i < n; i++) {
			if (adj[v][i] != INF) {
				adj[v][i] = INF;
				m--;
			}
    	}

		for (int i = 0; i < n; i++) {
			if (adj[i][v] != INF) {
				adj[i][v] = INF;
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

	// 13 - fazer um método que verifique e retorne e o grafo (dirigido) é completo.
    public boolean verificaCompleto(){
        for(int i = 0; i < n; i++){
			for(int j=0; j < n; j++){
				if(this.adj[i][j] == INF && i != j) return false;
			}
		}
		return true;
    }

	// 14 - retorna o grafo complementar de um grafo dirigido
	public TGrafo grafoComplementar() {
		TGrafo complemento = new TGrafo(this.n);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i != j && adj[i][j] == INF) {  
					complemento.insereA(i, j, 0);
				}
			}
		}

		return complemento;
	}

	// 16 - método que retorna a categoria de conexidade para um grafo direcionado
	public int verificaConexidade() {
    	
    	TGrafo reduzido = this.grafoReduzido();
    	int R = reduzido.n;  // número de vértices no grafo reduzido

    	// caso especial: grafo vazio
    	if (R == 0) return 0;
    	if (R == 1) return 3;

    	// verifica unilateralidade (C2):
    	for (int i = 0; i < R; i++) {
        	for (int j = 0; j < R; j++) {
            	if (i != j) {
                	if (!(existeCaminho(reduzido, i, j) || existeCaminho(reduzido, j, i))) {
                    	// se para algum par não houver caminho em nenhuma direção,
                    	// não é unilateral; passa para a verificação de conectividade fraca.
                    	return verificaConectividadeFraca(reduzido);
                	}
            	}
        	}
    	}
    	return 2;  // Se para todo par há caminho em ao menos uma direção, o grafo é unilateral (C2)
	}

	// 17 - retorna o grafo reduzido de um grafo direcionado
	public TGrafo grafoReduzido() {
		TGrafo reduzido = new TGrafo(this.n);

		boolean[] verticeAtivo = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (adj[i][j] != INF || adj[j][i] != INF) {
					verticeAtivo[i] = true;
					verticeAtivo[j] = true;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			if (verticeAtivo[i]) { 
				for (int j = 0; j < n; j++) {
					if (verticeAtivo[j] && adj[i][j] != INF) reduzido.insereA(i, j, adj[i][j]);
				}
			}
		}

		return reduzido;
	}


	// Verifica se há caminho de 'inicio' até 'fim' num grafo direcionado
	private boolean existeCaminho(TGrafo g, int inicio, int fim) {
    	boolean[] visited = new boolean[g.n];
    	return DFSPath(g, inicio, fim, visited);
	}

	private boolean DFSPath(TGrafo g, int current, int target, boolean[] visited) {
    	if (current == target) return true;

    	visited[current] = true;

    	for (int i = 0; i < g.n; i++) {
        	if (g.adj[current][i] != g.INF && !visited[i]) {
            	if (DFSPath(g, i, target, visited)) return true;
        	}
    	}

    	return false;
	}

	private int verificaConectividadeFraca(TGrafo g) {
		TGrafoND grafoND = new TGrafoND(g.n);

		for (int i = 0; i < g.n; i++) {
			for (int j = i + 1; j < g.n; j++) {
				if (g.adj[i][j] != g.INF || g.adj[j][i] != g.INF) {
					grafoND.insereA(i, j);
				}
			}
		}

		// o método verificaConexidade() da classe TGrafoND retorna 0 se o grafo for conectado ou 1 se estiver desconexo.
		int conex = grafoND.verificaConexidade();
		return (conex == 0) ? 1 : 0;
	}

	// apresenta o grafo contendo número de vértices, arestas e a matriz de adjacência obtida
	public void show() {
		System.out.println("> Início da impressão do grafo (n = " + n + " e m = " + m + "): ");
	    for(int i = 0; i < n; i++){
	    	System.out.println();

	        for(int w = 0; w < n; w++) {
				if(adj[i][w] == INF) System.out.print("Adj[" + i + "," + w + "] = INF ");
				else System.out.print("Adj[" + i + "," + w + "] = " + adj[i][w] + " ");
			}
	    }
	    System.out.println("\n\n> Fim da impressão do grafo.");
	}
}