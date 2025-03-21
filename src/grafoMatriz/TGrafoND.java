// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste - 10408802

package grafoMatriz;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
        // remove as arestas do vértice v
        for (int i = 0; i < n; i++) {
            if (adj[v][i] == 1) {
                adj[v][i] = 0;
                adj[i][v] = 0;
                m--;
            }
        }

        // remove a linha e a coluna do vértice na matriz de adjacência
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
        // verifica se existe um caminho para todo par de vértice
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if (!existeCaminho(this, i, j)) {
                    return 1;
                }
            }
        }

        return 0;
    }

    private boolean existeCaminho(TGrafoND g, int inicio, int fim) {
        boolean[] visitado = new boolean[g.n];
        return buscaCaminho(g, inicio, fim, visitado);
    }

    // faz uma busca de profundidade no grafo para verificar se é possível chegar de um vértice a outro
    private boolean buscaCaminho(TGrafoND grafo, int atual, int fim, boolean[] visitado) {
        if (atual == fim) return true;

        visitado[atual] = true;

        for (int i = 0; i < grafo.n; i++) {
            if(grafo.adj[atual][i] != 0 && !visitado[i]) {
                if(buscaCaminho(grafo, i, fim, visitado)) return true;
            }
        }

        return false;
    }

    public void percursoEmProfundidade(int vInicio) {
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

        for(int i : visitado) {
            System.out.println(i);
        }
    }

    private int marcarVertice(int[] verticesMarcados, int qtdVerticesMarcados, int v) {
        verticesMarcados[v] = 1;
        return qtdVerticesMarcados + 1;
    }

    private int proxAdjacente(int[] verticesMarcados, int n) {
        for(int i = 0; i < this.n; i++) {
            if(adj[n][i] == 1 && verticesMarcados[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    public void show() {
        System.out.println("> Início da impressão do grafo (n = " + n + " e m = " + m + "): ");

        for(int i = 0; i < n; i++){
            System.out.print("\n");

            for(int w = 0; w < n; w++) {
                if(adj[i][w] == 0) System.out.print("Adj[" + i + "," + w + "] = 0 ");
                else System.out.print("Adj[" + i + "," + w + "] = 1 ");
            }
        }

        System.out.println("\n\n> Fim da impressao do grafo." );
    }
}
