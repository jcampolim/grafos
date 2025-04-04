// Enzo Guarnieri, 10410074
// Erika Borges Piaui, 10403716
// Júlia Campolim de Oste, 10408802

package projeto;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxFastOrganicLayout;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.view.mxGraph;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
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
            this.m++;
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
        }
    }

    // insere vértice no grafo não direcionado
    public void insereVertice(String rotulo) {

        rotulos.add(rotulo);
        n++;

        float[][] atualizarAdj = new float[n][n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                atualizarAdj[i][j] = adj[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            atualizarAdj[i][n - 1] = INF;
            atualizarAdj[n - 1][i] = INF;
        }

        adj = atualizarAdj;
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

            while(scanner.hasNext()) {
                String origem = scanner.next();
                String destino = scanner.next();

                float valor = scanner.nextFloat();

                if(!verificaVertice(origem) || !verificaVertice(destino)) {
                    System.out.println("> Vértice " + (verificaVertice(origem) ? destino : origem) + " não encontrado.");
                    return 0;
                }

                this.insereAresta(origem, destino, valor);
            }

            scanner.close();

            // verifica erro: número de arestas n e quantidade de linhas no arquivo diferente
            if(arestasLidas == m) return 1;

            System.out.println("> Arquivo em formato inválido.");
            return 0;

        } catch(FileNotFoundException e) {
            System.out.println("> Arquivo não encontrado: " + file);
            return 0;
        } catch(Exception e) {
            System.out.println("> Arquivo em formato inválido.");
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
            System.out.println("> Dados gravados com sucesso!");
        } catch (IOException e) {
            System.out.println("> Não foi possível gravar dados no arquivo.");
        }
    }

    public boolean verificaVertice(String rotulo) {
        return this.rotulos.contains(rotulo);
    }

    public void mostrarGrafo() throws IOException {
        mxGraph grafo = new mxGraph();
        Object parent = grafo.getDefaultParent();

        // iniciando a edição do grafo
        grafo.getModel().beginUpdate();

        try {
            // adicionando os vértices no mxGraph
            Object[] vertices = new Object[this.n];
            for (int i = 0; i < this.n; i++) {
                if(rotulos.get(i).startsWith("E")) {
			        vertices[i] = grafo.insertVertex(parent, null, rotulos.get(i), 0, 0, 80, 30, "shape=ellipse;fillColor=#CD82F5;strokeColor=#000000;rounded=1;whiteSpace=wrap;html=1;fontColor=#000000;");
		        } else if(rotulos.get(i).startsWith("P")) {
			        vertices[i] = grafo.insertVertex(parent, null, rotulos.get(i), 0, 0, 80, 30, "shape=ellipse;fillColor=#FAB06B;strokeColor=#000000;rounded=1;whiteSpace=wrap;html=1;fontColor=#000000;");
		        } else {
		    	    vertices[i] = grafo.insertVertex(parent, null, rotulos.get(i), 0, 0, 80, 30, "shape=ellipse;fillColor=#5FBF5C;strokeColor=#000000;rounded=1;whiteSpace=wrap;html=1;fontColor=#000000;");
		        }
            }

            // adicionando as arestas no mxGraph com base na matriz de adjacência
            for (int i = 0; i < this.n; i++) {
                for (int j = i + 1; j < this.n; j++) {
                    if (adj[i][j] != INF) {
                        grafo.insertEdge(parent, null, adj[i][j], vertices[i], vertices[j], "edgeStyle=orthogonalEdge;rounded=0;orthogonalLoop=1;exitDx=0;exitDy=0;endArrow=none;strokeColor=#000000;fontColor=#000000;");
                    }
                }
            }

            // opções para melhorar o layout do grafo
            mxFastOrganicLayout layout = new mxFastOrganicLayout(grafo);

            layout.setForceConstant(200);
            layout.execute(parent);
        } finally {
            // finaliza a edição do grafo
            grafo.getModel().endUpdate();
        }

        BufferedImage image = mxCellRenderer.createBufferedImage(grafo, null, 5, Color.WHITE, true, null);

        File file = new File("grafo.png");
        ImageIO.write(image, "PNG", file);

        System.out.println("> Imagem do grafo gerada com sucesso!");
    }

    public void exibirVertices() {
        System.out.println("> Início da impressão dos vértices (n = " + n + "): ");

        for(String v : this.rotulos) {
            System.out.println(v);
        }

        System.out.println("> Fim da impressão dos vértices.");
    }

    public void exibirArestas() {
        System.out.println("> Início da impressão das arestas (m = " + m + "): ");
        final int n = this.n;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < i; j++) {
                if(adj[i][j] != INF) {
                    System.out.println(this.rotulos.get(i) + " -- " + this.rotulos.get(j) + " = " + this.adj[i][j]);
                }
            }
        }

        System.out.println("> Fim da impressão das arestas.");
    }

    public void show() {
        System.out.println("> Início da impressão do grafo (n = " + n + " e m = " + m + "): ");
        for(int i = 0; i < n; i++){
            System.out.println();

            for(int w = 0; w < n; w++) {
                if(adj[i][w] == INF) System.out.print("Adj[" + rotulos.get(i) + "," + rotulos.get(w) + "] = INF ");
                else System.out.print("Adj[" + rotulos.get(i) + "," + rotulos.get(w) + "] = " + adj[i][w] + " ");
            }
        }

        System.out.println("\n\n> Fim da impressao do grafo." );
    }
}
