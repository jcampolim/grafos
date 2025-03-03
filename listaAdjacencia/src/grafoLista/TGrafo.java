package grafoLista;

import java.util.ArrayList;
import java.util.List;

class TNo {
	public int w;      // vértice que é adjacente ao elemento da lista
	public TNo prox;
}

public class TGrafo {
	
	private final int n;             // quantidade de vértices
	private	int m;                   // quantidade de arestas
	private TNo[] adj;               // vetor onde cada entrada guarda o início de uma lista
	
	public TGrafo(int n) {
	    this.n = n;
	    this.m = 0;
	    TNo[] aux = new TNo[n];
	    
		for(int i = 0; i < n; i++)
			aux[i]=null;

	    this.adj = aux;
	}

	// insere uma aresta v->w no grafo, onde v e w são distintos, positivos e menores que n
	public void insereA(int v, int w) {
	    TNo novoNo;

	    TNo no = adj[v];
	    TNo ant = null;

		// anda na lista para chegar ao final
	    while(no != null){
	        if(w == no.w)
	            return;
	        ant = no;
	        no = no.prox;
	    }

	    // cria o novo No para guardar w
	    novoNo = new TNo();
	    novoNo.w = w;
	    novoNo.prox = no;

	    // atualiza a lista
	    if(ant == null) {
	        adj[v] = novoNo;      // insere no inicio
	    } else {
			ant.prox = novoNo;    // insere no final
		}

	    m++;	
	}

	// remove a aresta v->w, onde v e w são distintos, positivos e menores que n
	public void removeA(int v, int w) {
	    // obtém o início da lista do vértice v
	    TNo no = adj[v];
	    TNo ant = null;

		System.out.println(no.w != w);

	    // percorre a lista do vértice v procurando w (se adjacente)
	    while(no != null && no.w != w) {
			ant = no;

			if(no.prox == null) break;

			no = no.prox;
	    }

		if(no != null && ant == null) {
			if(no.prox == null) {
				adj[v] = null;
			} else {
				adj[v] = adj[v].prox;
			}

			m--;

			return;
		}

	    // se w é adjacente, remove da lista de v
	    if(no != null) {
			ant.prox = no.prox;
	    	no = null;
	    	m--;
		}
	}

	// TODO: 19 - calcula o grau de entrada de um vértice
	public int inDegree(int v) {
		return 0;
	}

	// TODO: 20 - calcula o grau de saída de um vértice
	public int outDegree(int v) {
		return 0;
	}

	// 21 - calcula o grau de um vértice
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	// 24 - inverte a ordem dos vértices adjacêntes na lista de adjacência
	public void inveteOrdem(int v) {
		List<Integer> nosAdjacentes = new ArrayList<>();

		TNo proxNo = adj[v];

		// percorre a lista de nós, removendo-os e armazenando-os
		while(proxNo != null) {
			nosAdjacentes.add(proxNo.w);
			removeA(v, proxNo.w);

			proxNo = proxNo.prox;
		}

		// adiciona os nós na lista na ordem inversa
		for(int i = nosAdjacentes.size() - 1; i >= 0; i--) {
			insereA(v, nosAdjacentes.get(i));
		}
	}

	// apresenta o grafo contendo número de vértices, arestas e a lista de adjacência obtida
	public void show() {
	    System.out.println("n: " + n);
	    System.out.println("m: " + m);

	    for(int i = 0; i < n; i++){
	    	System.out.print("\n" + i + ": ");

	        // percorre a lista na posição i do vetor
	        TNo no = adj[i];
	        while( no != null ){
	        	System.out.print(no.w + " ");
	            no = no.prox;
	        }
	    }

	    System.out.print("\n\nfim da impressao do grafo.\n");
	}
}