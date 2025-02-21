## Exercícios

19\) Escreva um método “int inDegree(int v)” que calcula e retorna o grau de entrada de um vértice v de um grafo dirigido fazendo uso da lista de adjacência. 

20\) Escreva o método "int outDegree(int v)" que calcula o grau de saída de v em grafo dirigido. fazendo uso da lista de adjacência.

21\) Fazer o método "int degree(int v)" que calcula o grau do vértice de um grafo dirigido fazendo uso da lista de adjacência. 

22\) Escreva um método que decida se dois grafos direcionados são iguais. O método deve ser implementado para a classe TGrafo faz uso da lista de adjacência.

23\) Escreva um método que converta uma representação de um grafo em outra. Por exemplo, converta um grafo armazenado em matriz de adjacência em uma lista de adjacência.

24\) Escreva um método que receba um grafo armazenado em lista de adjacência e inverta a lista de adjacência de todos os vértices do grafo.  Por exemplo, se os 4 vizinhos de um certo vértice u aparecem na lista adj[u] na ordem v, w, x, y, então depois da aplicação do método a lista deve conter os mesmos vértices na ordem y, x, w, v. Obs.: Vizinhos são todos os vértices ligados ao vértice u.

25\) Escreva um método que receba um grafo e um vértice como parâmetro e retorne 1 se vértice for uma fonte (grau de saída maior que zero e grau de entrada igual a 0), ou 0 caso contrário. O método deve ser implementado para a classe TGrafo como lista de adjacência.

26\) Escreva um método que receba um grafo e um vértice como parâmetro, retorne 1 se vértice for um sorvedouro (grau de entrada maior que zero e grau de saída igual a 0), ou 0 caso contrário. O método deve ser implementado para a classe TGrafo que utiliza lista de adjacência.

27\) Escreva um método que receba um grafo dirigido como parâmetro e retorna 1 se o grafo for simétrico e 0 caso contrário. O método deve ser implementado  para a classe TGrafo que utiliza lista de adjacência.

28\) Um grafo pode ser armazenado em um arquivo com o seguinte formato:

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

Onde na primeira linha contém um inteiro V (vértice), na segunda contém um inteiro A (arestas) e nas demais linha contém dois inteiros pertencentes ao intervalo 0..V-1. 
Se interpretarmos cada linha do arquivo como uma aresta, podemos dizer que o arquivo define um grafo com vértices 0..V-1. Escreva um método que receba um nome de arquivo com o formato acima e construa a representação de  lista de adjacência do grafo. 

29\) Fazer um método que permita remover um vértice do Grafo (não dirigido). Não se esqueça de remover as arestas associadas.

30\) Fazer um método que permita remover um vértice do Grafo (dirigido). Não se esqueça de remover as arestas associadas.

31\) Fazer um método que verifique se o grafo (dirigido ou não) é completo.
