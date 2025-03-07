package grafoMatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TGrafo {

	private  int n;            // quantidade de vértices
	private	int m;                  // quantidade de arestas
	private float[][] adj;          //matriz de adjacência

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
		for(int i=0; i<this.n; i++){
			if(this.adj[i][v] != INF){
				degree++;
			}
		}
		return degree;
	}

	// 2 - calcula o grau de saída de um vértice
	public int outDegree(int v) {
		return 0;
	}

	// 3 - calcula o grau de um vértice
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	// 4 - verifica se um vértice é fonte
	
	public int verificaFonte(int v){
		return (outDegree(v) > 0 && inDegree(v) == 0)? 1 : 0; 
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

	// 7 - constroi grafo apartir de file
	public Integer lerGTGrafo(String file){
		try {
            Scanner scanner = new Scanner(new File(file));
            this.n = scanner.nextInt(); // Numero de vértices
            this.m = scanner.nextInt(); // Numero de arestas
		    this.adj = new float [n][n];
			int arestasLidas = 0;

			 // Inicializa a matriz com INF
			 for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					this.adj[i][j] = INF;
				}
			}
		    
            while (scanner.hasNextInt()) {
				arestasLidas++;
                int origem = scanner.nextInt();
                int destino = scanner.nextInt();
				this.insereA(origem, destino, 0);
            }

            scanner.close();
			// Verificar erro: numero de arestas n e quantidade de linhas no arquivo diferente
			if(arestasLidas != m) return 0;
            return 1;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + file);
            return null;
        }	
	}


	// 13) Fazer um método que verifique e retorne e o grafo (dirigido) é completo.
    public boolean verificaCompleto(){
        for(int i = 0; i < n; i++){
			for(int j=0; j < n; j++){
				if(this.adj[i][j] == INF && i != j) return false;
			}
		}
		return true;
    }


	// 16) Método que retorna a categoria de conexidade para um grafo direcionado 
	public int categoriaConexidade() {
    	
    	TGrafo reduzido = this;
    	int R = reduzido.n;  // número de vértices no grafo reduzido

    	// Caso especial: grafo vazio 
    	if (R == 0)
        	return 0;
    	if (R == 1)
        	return 3; 

    	// Verifica unilateralidade (C2):
    	for (int i = 0; i < R; i++) {
        	for (int j = 0; j < R; j++) {
            	if (i != j) {
                	if (!(existeCaminho(reduzido, i, j) || existeCaminho(reduzido, j, i))) {
                    	// Se para algum par não houver caminho em nenhuma direção,
                    	// não é unilateral; passa para a verificação de conectividade fraca.
                    	return verificaConectividadeFraca(reduzido);
                	}
            	}
        	}
    	}
    	return 2;  // Se para todo par há caminho em ao menos uma direção, o grafo é unilateral (C2)
	}

	// Verifica se há caminho de 'inicio' até 'fim' num grafo direcionado
	private boolean existeCaminho(TGrafo g, int inicio, int fim) {
    	boolean[] visited = new boolean[g.n];
    	return DFSPath(g, inicio, fim, visited);
	}
	private boolean DFSPath(TGrafo g, int current, int target, boolean[] visited) {
    	if (current == target)
        	return true;
    	visited[current] = true;
    	for (int i = 0; i < g.n; i++) {
        	if (g.adj[current][i] != g.INF && !visited[i]) {
            	if (DFSPath(g, i, target, visited))
            	    return true;
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
		// O método verificaConexidade() da classe TGrafoND retorna 0 se o grafo for conectado ou 1 se estiver desconexo.
		int conex = grafoND.verificaConexidade();
		return (conex == 0) ? 1 : 0;
	}



	// apresenta o grafo contendo número de vértices, arestas e a matriz de adjacência obtida
	public void show() {
	    System.out.println("n: " + n);
	    System.out.println("m: " + m);

	    for(int i = 0; i < n; i++){
	    	System.out.print("\n");

	        for(int w = 0; w < n; w++) {
				if(adj[i][w] == INF) System.out.print("Adj[" + i + "," + w + "] = INF ");
				else System.out.print("Adj[" + i + "," + w + "] = " + adj[i][w] + " ");
			}
	    }
	    System.out.println("\n\nfim da impressao do grafo." );
	}
}