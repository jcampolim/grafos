// Enzo Guarnieri - 10410074
// Erika Borges Piaui - 10403716
// Júlia Campolim de Oste - 10408802

import grafoMatriz.TGrafo;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TGrafo grafo1 = new TGrafo(6);

        grafo1.insereA(0, 3, 1);
        grafo1.insereA(1, 0, 1);
        grafo1.insereA(1, 4, 1);
        grafo1.insereA(1, 5, 1);
        grafo1.insereA(2, 1, 1);
        grafo1.insereA(2, 3, 1);
        grafo1.insereA(3, 4, 1);
        grafo1.insereA(4, 3, 1);
        grafo1.insereA(5, 2, 1);
        grafo1.insereA(5, 4, 1);

        System.out.println("\n> Imprimindo percurso em profundidade do grafo G1:\n");
        List<Integer> percursoProfunidade1 = grafo1.percursoEmProfundidade(1);

        for(int v : percursoProfunidade1) System.out.println(v);

        System.out.println("\n> Imprimindo percurso em largura do grafo G1:\n");
        List<Integer> percursoLargura1 = grafo1.percursoEmLargura(1);

        for(int v : percursoLargura1) System.out.println(v);

        TGrafo grafo2 = new TGrafo(8);

        grafo2.insereA(0, 1, 1);
        grafo2.insereA(0, 2, 1);
        grafo2.insereA(0, 4, 1);
        grafo2.insereA(1, 3, 1);
        grafo2.insereA(1, 4, 1);
        grafo2.insereA(2, 5, 1);
        grafo2.insereA(2, 6, 1);
        grafo2.insereA(3, 7, 1);
        grafo2.insereA(4, 7, 1);
        grafo2.insereA(5, 4, 1);
        grafo2.insereA(5, 6, 1);
        grafo2.insereA(6, 7, 1);

        System.out.println("\n> Imprimindo percurso em profundidade do grafo G2: \n");
        List<Integer> percursoProfunidade2 = grafo2.percursoEmProfundidade(0);

        for(int v : percursoProfunidade2) System.out.println(v);

        System.out.println("\n> Imprimindo percurso em largura do grafo G2:\n");
        List<Integer> percursoLargura2 = grafo2.percursoEmLargura(0);

        for(int v : percursoLargura2) System.out.println(v); 
    }
}
