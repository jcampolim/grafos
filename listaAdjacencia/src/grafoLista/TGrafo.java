package grafoLista;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class TNo {
	public int w;      // vértice que é adjacente ao elemento da lista
	public TNo prox;
}

public class TGrafo {
	
	private int n;             		 // quantidade de vértices
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

	// insere uma aresta v -> w no grafo, onde v e w são distintos, positivos e menores que n
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

	// remove a aresta v -> w, onde v e w são distintos, positivos e menores que n
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


	// 19 - calcula o grau de entrada de um vértice
	public int inDegree(int v) {
		int grau = 0;
		for(int i = 0; i < n; i++) {
			TNo no = adj[i];

			while (no != null) {
				if (no.w == v) grau++;
				no = no.prox;
			}
		}

		return grau;
	}
	
	// 20 - calcula o grau de saída de um vértice
	public int outDegree(int v) {
		int grau = 0;
		TNo no = adj[v];

		while (no != null) {
			grau++;
			no = no.prox;
		}

    	return grau;
	}

	// 21 - calcula o grau de um vértice
	public int degree(int v) {
		return inDegree(v) + outDegree(v);
	}

	// 22 - verifica iqualdade 2 grafos
	public boolean equals(TGrafo outroGrafo) {
        if (this.n != outroGrafo.n || this.m != outroGrafo.m) return false;

		List<Integer> lista1 = new ArrayList<>();
        List<Integer> lista2 = new ArrayList<>();

        // Percorre cada lista de adjacência
        for (int i = 0; i < this.n; i++) {
            TNo no1 = this.adj[i];
            TNo no2 = outroGrafo.adj[i];

            while (no1 != null) {
                lista1.add(no1.w);
                no1 = no1.prox;
            }

            while (no2 != null) {
                lista2.add(no2.w);
                no2 = no2.prox;
            }

            Collections.sort(lista1);
            Collections.sort(lista2);
        }
		return (lista1.equals(lista2));   
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

	// verifica se a aresta v -> w existe
	private boolean verificaAresta(int v, int w) {
		TNo no = adj[v];
		
		while(no != null) {
			if(no.w == w) return true;
			no = no.prox;
		}
		
		return false;
	}

	// 25 - verifica se um vértice é fonte
	public int verificaFonte(int v){
		return (outDegree(v) > 0 && inDegree(v) == 0) ? 1 : 0;
	}

	// 26 - verifica se o vértice é sorvedouro
	public int verificaSorvedouro(int v) {
		return (inDegree(v) > 0 && outDegree(v) == 0) ? 1 : 0;
	}
	
	// 27 - verifica se o grafo é simétrico
	public int verificaSimetria() {
		for(int i = 0; i < n; i++) {
			TNo no = adj[i];
			
			while(no != null) {
				if(!verificaAresta(no.w, i)) return 0;
				no = no.prox;
			}
		}
		return 1;
	}

	// 28 - constrói grafo a partir de arquivo
	public Integer lerGTGrafo(String file){
		try {
            Scanner scanner = new Scanner(new File(file));
            this.n = scanner.nextInt();                // número de vértices
			int arestasLidas = scanner.nextInt();      // número de arestas

            while (scanner.hasNextInt()) {
                int origem = scanner.nextInt();
                int destino = scanner.nextInt();
				this.insereA(origem, destino);
            }

            scanner.close();

			// verifica erro: número de arestas n e quantidade de linhas no arquivo diferente
			if(arestasLidas != m) return 0;
            return 1;

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + file);
            return null;
        }	
	}

	// 29 - remove vértice do grafo não direcionado
	public void removeVerticeND(int v) {
		while (adj[v] != null) {
			removeA(v, adj[v].w);
		}

		for (int i = 0; i < n; i++) {
			if (i != v && verificaAresta(i, v)) {
				removeA(i, v);
			}
		}

		adj[v] = null;
	}

	// 30 - remove um vértice do grafo (removendo as arestas associadas)
	public void removeVertice(int v) {
		for(int i = 0; i < n; i++) {
			if(i != v && verificaAresta(i, v)) {
				removeA(i, v);
			}
		}
		
		adj[v] = null;
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