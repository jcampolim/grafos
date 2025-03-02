package grafo;

public class Main {
	public static void main(String[] args) {
		TGrafo g = new TGrafo(4);

		// insere as arestas do grafo A={(0,1),(0,2),(2,1),(2,3),(1,3)}
		g.insereA(0,1, 1);
		g.insereA(0,2, 2);
		g.insereA(2,1, 3);
		g.insereA(2,3, 4);
		g.insereA(1,3, 5);


	
		// mostra o grafo preenchido
		g.show();



		System.out.println(g.degree(0));
		System.out.println(g.degree(1));
		System.out.println(g.degree(2));
		System.out.println(g.degree(3));

	}
}
