package grafoLista;

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
	    
		for(int i = 0; i< n; i++)
			aux[i]=null;

	    this.adj = aux;
	}

	// insere uma aresta v->w no grafo, onde v e w são distintos, positivos e menores que n
	public void insereA( int v, int w) {
	    TNo novoNo;

	    TNo no = adj[v];
	    TNo ant = null;

		// anda na lista para chegar ao final
	    while(no != null && w >= no.w){
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
	    if(ant == null){
	        adj[v] = novoNo;      // insere no inicio
	    } else {
			ant.prox = novoNo;    // insere no final
		}

	    m++;	
	}

	// remove a aresta v->w, onde v e w são distintos, positivos e menores que n
	public void removeA( int v, int w) {
	    // obtém o início da lista do vértice v
	    TNo no = adj[v];
	    TNo ant = null;

	    // percorre a lista do vértice v procurando w (se adjacente)
	    while( no != null && no.w != w ){
			ant = no;
			no = no.prox;
	    }

	    // se w é adjacente, remove da lista de v
	    if (no != null){
			if (ant != null) {
				ant.prox = no.prox;
			}
			no = null;
	    	m--;
		}	
	}

	// apresenta o grafo contendo número de vértices, arestas e a lista de adjacência obtida
	public void show() {
	    System.out.println("n: " + n);
	    System.out.println("m: " + m + "\n");

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