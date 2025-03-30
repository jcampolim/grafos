package projeto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TGrafoND {

    private	int n;            // quantidade de vértices
    private	int m;            // quantidade de arestas
    private float[][] adj;    //matriz de adjacência
    private List<String> rotulos;                  // vetor que armazena os valores dos vértices

    private final float INF = Float.MAX_VALUE;     // define o valor do infinito para grafos ponderados

    public TGrafoND(int n) {
        this.n = n;
        this.m = 0;         // inicialmente não há arestas
        this.adj = new float [n][n];

        this.rotulos = new ArrayList<>();

        // inicia a matriz com zeros
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                this.adj[i][j] = INF;
            }
        }
    }

    // insere uma aresta no TGrafo tal que v é adjacente a w e w é adjacente a v
    public void insereAresta(String rotuloV, String rotuloW, float valor) {
        int v = rotulos.indexOf(rotuloV);
        int w = rotulos.indexOf(rotuloW);

        if(adj[v][w] == INF) {         // verifica se não temos aresta
            adj[v][w] = valor;
            adj[w][v] = valor;
            this.m += 2;
        }
    }

    // remove uma aresta v->w/w->v do TGrafo
    public void removeAresta(String rotuloV, String rotuloW) {
        int v = rotulos.indexOf(rotuloV);
        int w = rotulos.indexOf(rotuloW);

        if(adj[v][w] != INF) {        // verifica se temos a aresta
            adj[v][w] = INF;
            adj[w][v] = INF;
            this.m--;
            this.m--;
        }
    }

    // remove vértice do grafo não direcionado
    public void removeVertice(String vertice) {
        int v = rotulos.indexOf(vertice);
        rotulos.remove(v);

        // remove as arestas do vértice v
        for (int i = 0; i < n; i++) {
            if (adj[v][i] != INF) {
                adj[v][i] = INF;
                adj[i][v] = INF;
                this.m--;
                this.m--;
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

    // retorna o tipo de conexidade
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
            if(grafo.adj[atual][i] != INF && !visitado[i]) {
                if(buscaCaminho(grafo, i, fim, visitado)) return true;
            }
        }

        return false;
    }

    public int lerGTGrafo(String file){
        try {
            Scanner scanner = new Scanner(new File(file));

            int tipoDoGrafo = scanner.nextInt();

            this.n = scanner.nextInt();             // número de vértices

            for(int i = 0; i < n; i++) {
                String aux = scanner.next();
                this.rotulos.add(aux);
            }

            int arestasLidas = scanner.nextInt();   // número de arestas

            this.adj = new float [n][n];
            this.m = 0;

            // inicializa a matriz com INF
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    this.adj[i][j] = INF;
                    this.adj[j][i] = INF;
                }
            }

            while(scanner.hasNextInt()) {
                String origem = scanner.next();
                String destino = scanner.next();

                float valor = scanner.nextFloat();

                this.insereAresta(origem, destino, valor);
            }

            scanner.close();

            // verifica erro: número de arestas n e quantidade de linhas no arquivo diferente
            if(arestasLidas == m) return 1;

            System.out.println("> Arquivo em formato inválido");
            return 0;

        } catch(FileNotFoundException e) {
            System.out.println("> Arquivo não encontrado: " + file);
            return 0;
        } catch(Exception e) {
            System.out.println("> Arquivo em formato inválido");
            return 0;
        }
    }

    public void escreverArquivo() {
        try {
            FileWriter fileWriter = new FileWriter("grafo.txt");

            fileWriter.write("2" + "\n"); // tipo do grafo
            fileWriter.write(this.n + "\n");  // quantidade de vértices

            for(int i = 0; i < n; i++) {
                fileWriter.write(rotulos.get(i) + "\n");
            }

            fileWriter.write(this.m + "\n");  // quantidade de arestas

            for(int i = 0; i < n; i++) {
                for(int j = 0; j < i; j++) {
                    if(adj[i][j] != INF) {
                        fileWriter.write(rotulos.get(i) + " " + rotulos.get(j) + " " + adj[i][j] + "\n");
                    }
                }
            }

            fileWriter.close();
            System.out.println("> Dados gravados com sucesso");
        } catch (IOException e) {
            System.out.println("> Não foi possível gravar dados no arquivo");
        }
    }

    public boolean verificaVertice(String rotulo) {
        return this.rotulos.contains(rotulo);
    }

    public void show() {
        System.out.println("> Início da impressão do grafo (n = " + n + " e m = " + m + "): ");
        for(int i = 0; i < n; i++){
            System.out.println();

            for(int w = 0; w < n; w++) {
                if(adj[i][w] == INF) System.out.print("Adj[" + i + "," + w + "] = INF ");
                else System.out.print("Adj[" + i + "," + w + "] = " + adj[i][w] + " ");
            }
        }

        System.out.println("\n\n> Fim da impressao do grafo." );
    }
}
