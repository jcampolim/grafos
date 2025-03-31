package projeto;

import java.io.IOException;
import java.util.Scanner;

public class MainProjeto {
    public static void menu() throws IOException {
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

            while(!op.equals("a") && !op.equals("b") && !op.equals("c") && !op.equals("d") && !op.equals("e")
                    && !op.equals("f") && !op.equals("g") && !op.equals("h") && !op.equals("i") && !op.equals("j")) {

                System.out.print("\n> Opção inválida. Tente novamente: ");
                op = scan.next();
            }

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
                    grafo.escreverArquivo();
                    break;
                case "c":
                    System.out.print("> Insira o rótulo do vértice: ");
                    v = scan.next();

                    if(!grafo.verificaVertice(v)) {
                        grafo.insereVertice(v); 
                        System.out.println("> Vértice inserido com sucesso!");
                    } else {
                        System.out.println("> Vértice " + v + " já existe no grafo");
                    }
 
                    break;
                case "d":
                    System.out.print("> Insira o primeiro vértice: ");
                    v = scan.next();

                    System.out.print("> Insira o segundo vértice: ");
                    w = scan.next();

                    System.out.print("> Insira o peso da aresta: ");
                    float valor = scan.nextFloat();

                    if(grafo.verificaVertice(v) || grafo.verificaVertice(w)) {
                        grafo.insereAresta(v, w, valor);
                        System.out.println("> Aresta removida com sucesso!");
                    } else {
                        System.out.println("> Vértice " + (grafo.verificaVertice(v) ? v : w) + " inválido.");
                    }

                    break;
                case "e":
                    System.out.print("> Insira o vértice: ");
                    v = scan.next();

                    if(grafo.verificaVertice(v)) {
                        grafo.removeVertice(v);
                        System.out.println("> Vértice removido com sucesso!");
                    } else {
                        System.out.println("> Vértice " + v + " inválido");
                    }

                    break;
                case "f":
                    System.out.print("> Insira o primeiro vértice: ");
                    v = scan.next();

                    System.out.print("> Insira o segundo vértice: ");
                    w = scan.next();

                    if(grafo.verificaVertice(v) || grafo.verificaVertice(w)) {
                        grafo.removeAresta(v, w);
                        System.out.println("> Aresta removida com sucesso!");
                    } else {
                        System.out.println("> Vértice " + (grafo.verificaVertice(v) ? v : w) + " inválido.");
                    }

                    break;
                case "g":
                    grafo.createGraph();
                    System.out.println("> O grafo ficará disponível para visualização após a execução do programa.");
                    break;
                case "h":
                    grafo.show();
                    break;
                case "i":
                    System.out.println("> Conexidade do grafo: " + (grafo.verificaConexidade() == 1 ? "desconexo." : "conexo."));
                    break;
                case "j":
                    System.out.println("> Encerrando o programa...");
                    break;
            }

            System.out.println();
        } while(!op.equals("j"));
    }
}
