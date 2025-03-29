package grafoMatriz;

import java.util.ArrayList;
import java.util.List;

public class MainMatriz {
    public static void exerciciosMatriz() {
        
        TGrafo g1 = new TGrafo(5);

        
        g1.insereA(1, 2, 20);
        g1.insereA(1, 3, 30);
        g1.insereA(1, 4, 50);

        g1.insereA(2, 1, 20);
        g1.insereA(2, 3, 40);
        g1.insereA(2, 4, 15);

        g1.insereA(3, 1, 30);
        g1.insereA(3, 2, 40);
        g1.insereA(3, 4, 15);

        g1.insereA(4, 1, 50);
        g1.insereA(4, 2, 15);
        g1.insereA(4, 3, 15);

        
        int origem = 3;
        int[] pred = g1.dijkstra(origem);

        // Exibe o vetor de predecessores
        System.out.println("Vetor de predecessores obtido com Dijkstra (origem " + origem + "):");
        for (int i = 0; i < pred.length; i++) {
            System.out.println("  Vértice " + i + " <- " + pred[i]);
        }
        System.out.println();

        // Reconstrução e exibição do caminho mínimo de 'origem' para cada vértice
        for (int destino = 0; destino < 5; destino++) {
            List<Integer> caminho = reconstruirCaminho(origem, destino, pred);
            if (caminho == null) {
                System.out.println("Não há caminho de " + origem + " para " + destino);
            } else {
                System.out.print("Caminho mínimo de " + origem + " para " + destino + ": ");
                for (int v : caminho) {
                    System.out.print(v + " ");
                }
                System.out.println();
            }
        }
    }

    // Método auxiliar para reconstruir o caminho s
    private static List<Integer> reconstruirCaminho(int origem, int destino, int[] pred) {
        List<Integer> caminho = new ArrayList<>();
        int atual = destino;
        
        
        while (atual != -1) {
            caminho.add(0, atual);  
            if (atual == origem) {
                break;
            }
            atual = pred[atual];
        }
        
        if (caminho.get(0) != origem) {
            return null;
        }
        
        return caminho;
    }
    
    public static void main(String[] args) {
        exerciciosMatriz();
    }
}
