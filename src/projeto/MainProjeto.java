package projeto;

import java.util.Scanner;

public class MainProjeto {
    public static void menu() {
        Scanner scan = new Scanner(System.in);
        String op, v, w;
        boolean existeGrafo = false;

        TGrafoND grafo = new TGrafoND(0);

        do {
            System.out.println("================= MENU DE OPÇÕES =================");
            System.out.println("  a. Ler dados do arquivo 'grafo.txt'");
            System.out.println("  b. Gravar dados no arquivo 'grafo.txt'");
            System.out.println("  c. Inserir vértice;");
            System.out.println("  d. Inserir aresta;");
            System.out.println("  e. Remover vértice;");
            System.out.println("  f. Remover aresta;");
            System.out.println("  g. Mostrar conteúdo do arquivo;");
            System.out.println("  h. Mostrar grafo;");
            System.out.println("  i. Apresentar conexidade;");
            System.out.println("  j. Encerrar a aplicação.");
            System.out.println("==================================================");

            System.out.print("\n> Selecione uma opção: ");
            op = scan.next();

            System.out.println();

            if(!existeGrafo && !op.equals("a") && !op.equals("j")) {
                System.out.println("> É preciso ler um grafo antes de realizar esta operação!\n");
                continue;
            }

            switch (op) {
                case "a":
                    if(grafo.lerGTGrafo("grafo.txt") == 1) {
                        System.out.println("> Grafo lido com sucesso!");
                        existeGrafo = true;
                    }
                    break;
                case "b":
                    // TODO: gravar dados no arquivo no mesmo formato da entrada
                    break;
                case "c":
                    // TODO: inserir vertices
                    break;
                case "d":
                    // TODO: validar se os vértices existem
                    System.out.print("> Insira o primeiro vértice: ");
                    v = scan.next();

                    System.out.print("> Insira o segundo vértice: ");
                    w = scan.next();

                    System.out.print("> Insira o peso da aresta: ");
                    float valor = scan.nextFloat();

                    grafo.insereAresta(v, w, valor);
                    break;
                case "e":
                    // TODO: validar se o vértice existe
                    System.out.print("> Insira o vértice: ");
                    v = scan.next();

                    grafo.removeVertice(v);
                    break;
                case "f":
                    // TODO: validar se os vértices existem
                    System.out.print("> Insira o primeiro vértice: ");
                    v = scan.next();

                    System.out.print("> Insira o segundo vértice: ");
                    w = scan.next();

                    grafo.removeAresta(v, w);
                    break;
                case "g":
                    // TODO: mostrar conteúdo do arquivo de forma visual
                    break;
                case "h":
                    grafo.show();
                    break;
                case "i":
                    System.out.println("> Conexidade do grafo: " + (grafo.verificaConexidade() == 1 ? "desconexo" : "conexo"));
                    break;
                case "j":
                    System.out.println("> Encerrando o programa...");
                    break;
            }

            System.out.println();
        } while(!op.equals("j"));
    }
}
