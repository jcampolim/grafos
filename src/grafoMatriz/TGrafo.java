// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste - 10408802

package grafoMatriz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

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
	
	// remove uma aresta v -> w do TGrafo
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

            this.n = scanner.nextInt();             // número de vértices
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

			// verifica erro: número de arestas n e quantidade de linhas no arquivo diferente
			if(arestasLidas != m) return 0;
            return 1;

        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + file);
            return 0;
        }	
	}

	// 11 - remove vértice do grafo direcionado
	public void removeVertice(int v) {
		// remove os arcos do vértice v
        for (int i = 0; i < n; i++) {
			if (adj[v][i] != INF) {
				adj[v][i] = INF;
				m--;
			}

			if (adj[i][v] != INF) {
				adj[i][v] = INF;
				m--;
			}
    	}

		// remove a linha e coluna do vértice v na matriz de adjacência
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
			for(int j = 0; j < n; j++){
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

    	if (R == 0 || verificaDesconexo(reduzido)) return 0;   // grafo desconexo
    	if (R == 1) return 3;                                  // grafo fortemente conexo

    	// verifica se há caminho de i -> j e de j -> j (semi-fortemente conexo)
    	for (int i = 0; i < R; i++) {
        	for (int j = 0; j < R; j++) {
            	if (i != j) {
					// se não tiver caminho em algum dos sentidos, é simplemente conexo
                	if(!existeCaminho(this, i, j) && !existeCaminho(this, j, i)) {
						return 1;
					}
            	}
        	}
    	}
    	return 2;
	}

	// verifica se há caminho de 'inicio' até 'fim' num grafo direcionado
	private boolean existeCaminho(TGrafo g, int inicio, int fim) {
		boolean[] visitado = new boolean[g.n];
		return buscaCaminho(g, inicio, fim, visitado);
	}

	// faz uma busca de profundidade no grafo para verificar se é possível chegar de um vértice a outro
	private boolean buscaCaminho(TGrafo grafo, int atual, int fim, boolean[] visitado) {
		if (atual == fim) return true;

		visitado[atual] = true;

		for (int i = 0; i < grafo.n; i++) {
			if(grafo.adj[atual][i] != grafo.INF && !visitado[i]) {
				if(buscaCaminho(grafo, i, fim, visitado)) return true;
			}
		}

		return false;
	}

	private boolean verificaDesconexo(TGrafo grafo) {
		// transforma o grafo direcionado em não direcionado, para verificar se ele é conexo ou desconexo
		TGrafoND grafoND = new TGrafoND(grafo.n);

		for (int i = 0; i < grafo.n; i++) {
			for (int j = i + 1; j < grafo.n; j++) {
				if (grafo.adj[i][j] != grafo.INF || grafo.adj[j][i] != grafo.INF) {
					grafoND.insereA(i, j);
				}
			}
		}

		// verifica conexidade: 0 - conexo ou 1 - não conexo/desconexo
		return !(grafoND.verificaConexidade() == 0);
	}

	// 17 - retorna o grafo reduzido de um grafo direcionado
	public TGrafo grafoReduzido() {
		List<List<Integer>> particoes = new ArrayList<>();

		// encontra as partições do grafo original
		for (int i = 0; i < this.n; i++) {
			boolean encontrado = false; // verifica se o vértice já está em alguma partição

			for (List<Integer> particao : particoes) {
				if (particao.contains(i)) {
					encontrado = true;
					break;
				}
			}

			if (!encontrado) {
				particoes.add(encontraComponentes(i));
			}
		}

		// cria um grafo reduzido, onde cada partição é um vértice e adiciona os arcos entre as partições
		TGrafo reduzido = new TGrafo(particoes.size());

		for(int i = 0; i < particoes.size(); i++) {
			for(int j = 0; j < particoes.size(); j++) {
				if(particoes.get(i) == particoes.get(j)) continue;

				if(existeCaminho(this, particoes.get(i).get(0), particoes.get(j).get(0))) {
					reduzido.insereA(i, j, 1);
				}
			}
		}

		return reduzido;
	}

	// busca o fecho transitivo direto e indireto e faz a intersecção entre eles para encontrar componentes fortemente ligadas
	public List<Integer> encontraComponentes(int vertice) {
		List<Integer> fechoTransitivoDireto = new ArrayList<>();
		List<Integer> fechoTransitivoIndireto = new ArrayList<>();

		for(int i = 0; i < this.n; i++) {
			if(existeCaminho(this, vertice, i)) {
				fechoTransitivoDireto.add(i);
			}

			if(existeCaminho(this, i, vertice)) {
				fechoTransitivoIndireto.add(i);
			}
		}

		List<Integer> interseccao = new ArrayList<>();

		for(int i : fechoTransitivoDireto) {
			for(int j : fechoTransitivoIndireto) {
				if(i == j) {
					interseccao.add(i);
					break;
				}
			}
		}

		return interseccao;
	}

	// retorna o percurso em profundidade do grafo iniciando pelo vértice vInicio
	public List<Integer> percursoEmProfundidade(int vInicio) {
		int qtdVerticesMarcados = 0, n, m;
		int[] verticesMarcados = new int[this.n];

		Stack<Integer> pilha = new Stack<>();
		List<Integer> visitado = new ArrayList<>();

		qtdVerticesMarcados = marcarVertice(verticesMarcados, qtdVerticesMarcados, vInicio);
		pilha.add(vInicio);
		visitado.add(vInicio);

		while(!pilha.empty()) {
			n = pilha.pop();
			m = proxAdjacente(verticesMarcados, n);

			while(m != -1) {
				visitado.add(m);
				pilha.add(n);
				qtdVerticesMarcados = marcarVertice(verticesMarcados, qtdVerticesMarcados, m);
				n = m;
				m = proxAdjacente(verticesMarcados, n);
			}
		}

		return visitado;
	}

	// retorna o percurso em largura do grafo iniciando pelo vértice vInicio
	public List<Integer> percursoEmLargura(int vInicio) {
		int qtdVerticesMarcados = 0;
		int[] verticesMarcados = new int[this.n]; 

		Queue<Integer> fila = new LinkedList<>();
		List<Integer> visitado = new ArrayList<>();  

		qtdVerticesMarcados = marcarVertice(verticesMarcados, qtdVerticesMarcados, vInicio);
		fila.add(vInicio);
		visitado.add(vInicio);

		while (!fila.isEmpty()) {
			int n = fila.poll();

			int m;
			while ((m = proxAdjacente(verticesMarcados, n)) != -1) {
				if (!visitado.contains(m)) {
					visitado.add(m);  
					fila.add(m);
					qtdVerticesMarcados = marcarVertice(verticesMarcados, qtdVerticesMarcados, m);
				}
			}
		}

		return visitado;
	}

	// marca o vértice como visitado e retorna a quantidade de vértices marcados
	private int marcarVertice(int[] verticesMarcados, int qtdVerticesMarcados, int v) {
		verticesMarcados[v] = 1;
		return qtdVerticesMarcados + 1;
	}

	// procura o próximo vértice adjacente a n que não esteja marcado
	private int proxAdjacente(int[] verticesMarcados, int n) {
		for(int i = 0; i < this.n; i++) {
			if(adj[n][i] != INF && verticesMarcados[i] == 0) {
				return i;
			}
		}
		return -1;
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