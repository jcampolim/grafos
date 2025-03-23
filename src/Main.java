import grafoMatriz.TGrafo;

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

        System.out.println("\nImprimindo percurso em profundidade do grafo G1:\n");
        grafo1.percursoEmProfundidade(1);

        System.out.println("\nImprimindo percurso em largura do grafo G1:\n");
        grafo1.percursoEmLargura(1);

        // Grafo usado de exemplo para explicar os percursos (A = 0, B = 1, C = 2, D = 3, E = 4, F = 5, G = 6 e H = 7)
        // Em profundidade, começando do V0 (a) ficou {a, b, d, h, e, c, f, g}, ou seja, {0, 1, 3, 7, 4, 2, 5, 6} 
        // Em largura começando do V0 (a) ficou {a, b, c, e, d, f, g, h}, ou seja, {0, 1, 2, 4, 3, 5, 6, 7} 

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

        System.out.println("\nImprimindo percurso em profundidade do grafo G2:\n");
        grafo2.percursoEmProfundidade(0);

        System.out.println("\nImprimindo percurso em largura do grafo G2:\n");
        grafo2.percursoEmLargura(0);
    }
}
