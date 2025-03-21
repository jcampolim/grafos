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

        grafo1.percursoEmProfundidade(1);
    }
}
