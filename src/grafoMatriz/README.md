## Exercícios

1\) Escreva um método "int inDegree(int v)" que calcula e retorna o grau de entrada de um vértice v de um grafo dirigido. O método deve ser implementado na classe TGrafo da matriz de adjacência.
Obs.: grau de entrada é o total de arestas que chegam o vértice v.

2\)	Escreva o método "int outDegree(int v)" que calcula o grau de saída de v em grafo dirigido. O método deve ser implementado na classe TGrafo que usa matriz de adjacência.
Obs.: Grau de saída de v é o total de arestas que saem do vértice v.

3\)	Fazer o método "int degree(int v)" que calcula o grau do vértice de um grafo dirigido. O método deve ser implementado na classe TGrafo que usa matriz de adjacência.

4\)	Escreva um método para um grafo direcionado que recebe um vértice como parâmetro e retorne 1 se vértice for uma fonte (grau de saída maior que zero e grau de entrada igual a 0), ou 0 caso contrário. O método deve ser implementado para a classe TGrafo como matriz de adjacência.

5\)	Escreva um método para um grafo direcionado que recebe um vértice como parâmetro, retorne 1 se vértice for um sorvedouro (grau de entrada maior que zero e grau de saída igual a 0), ou 0 caso contrário. O método deve ser implementado para a classe TGrafo que utiliza matriz de adjacência.

6\)	Escreva um método que receba um grafo dirigido como parâmetro e retorna 1 se o grafo for simétrico e 0 caso contrário. O método deve ser implementado  para a classe TGrafo que utiliza matriz de adjacência.

7\)	Um grafo pode ser armazenado em um arquivo com o seguinte formato:

6
8
0 1 
0 5 
1 0 
1 5 
2 4 
3 1
4 3
3 5

Onde na primeira linha contém um inteiro V (vértice), na segunda contém um inteiro A (arestas) e nas demais linha contém dois inteiros pertencentes ao intervalo 0..V-1. Se interpretarmos cada linha do arquivo como uma aresta, podemos dizer que o arquivo define um grafo com vértices 0..V-1. 
Escreva um método que receba um nome de arquivo com o formato acima e construa a representação do grafo como matriz de adjacência. 

8\)	Criar uma outra classe TGrafoND e modifique as funções insereA, removeA e show para representar um grafo não-dirigido utilizando matriz de adjacência.

9\)	Fazer o método "int degree(int v)" que calcula o grau do vértice de um grafo não-dirigido. O método deve ser implementado na classe TGrafoND que usa matriz de adjacência.

10\) Modifique a classe TGrafo e os métodos correspondentes para permitir a criação de um grafo direcionado rotulado (valor float) nas arestas. Por exemplio, em C++, para representar o infinito utilize float a = std::numeric_limits<float>::infinity().

11\) Fazer um método que permita remover um vértice do Grafo (não dirigido e dirigido). Não se esqueça de remover as arestas associadas.

12\) Fazer um método que verifique e retorne se o grafo (não dirigido) é completo.

13\) Fazer um método que verifique e retorne e o grafo (dirigido) é completo.

14\) Fazer um método que retorne o complemento (grafo complementar) de um grafo (dirigido ou não) na forma de uma matriz de adjacência.

15\) Fazer um método que retorne o tipo de conexidade de um grafo não direcionado (0 - conexo ou 1 - não conexo – desconexo).

16\) Fazer um método que retorne a categoria de conexidade para um grafo direcionado (3 – C3, 2 – C2, 1 – C1 ou 0 – c0).

17\) Faze um método que retorne o grafo reduzido de um grafo direcionado no formato de uma matriz de adjacência.

18\) Modifique a classe TGrafo e os métodos correspondentes para permitir a criação de um grafo direcionado rotulado (valor float) nas arestas.
