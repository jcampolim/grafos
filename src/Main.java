// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste - 10408802

import grafoMatriz.TGrafoND;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n  > Inserindo grafo G1 (disponibilizado no material de aula)...");

        TGrafoND g1 = new TGrafoND(9);

        g1.insereA(0, 1, 4);
        g1.insereA(0, 5, 5);
        g1.insereA(1, 2, 7);
        g1.insereA(1, 5, 3);
        g1.insereA(2, 3, 5);
        g1.insereA(2, 6, 6);
        g1.insereA(3, 4, 3);
        g1.insereA(4, 6, 2);
        g1.insereA(4, 7, 4);
        g1.insereA(5, 6, 7);
        g1.insereA(5, 8, 5);
        g1.insereA(6, 7, 6);
        g1.insereA(7, 8, 8);

        g1.show();

        System.out.println("\n  > Gerando árvore de custo mínimo de G1...");

        TGrafoND arvoreCustoMinimo1 = g1.getArvoreCustoMinimo();

        System.out.println("\n  > Custo mínimo obtido: " + arvoreCustoMinimo1.getTotalArestas());
        System.out.println("\n  > Imprimindo matriz de adjacência da árvore de custo mínimo obtida...");

        arvoreCustoMinimo1.show();

        System.out.println("\n  > Inserindo grafo G2 (atividade de aula)...");

        TGrafoND g2 = new TGrafoND(6);

        g2.insereA(0, 1, 6);
        g2.insereA(0, 4, 8);
        g2.insereA(0, 2, 15);
        g2.insereA(1, 2, 20);
        g2.insereA(1, 4, 10);
        g2.insereA(1, 5, 8);
        g2.insereA(2, 3, 9);
        g2.insereA(3, 5, 7);
        g2.insereA(4, 5, 5);

        g2.show();

        System.out.println("\n  > Gerando árvore de custo mínimo de G2...");

        TGrafoND arvoreCustoMinimo2 = g2.getArvoreCustoMinimo();

        System.out.println("\n  > Custo mínimo obtido: " + arvoreCustoMinimo2.getTotalArestas());
        System.out.println("\n  > Imprimindo matriz de adjacência da árvore de custo mínimo obtida...");

        arvoreCustoMinimo2.show();
    }

}
