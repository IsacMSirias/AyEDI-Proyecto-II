Evaluación de expresiones matemáticas
====================================

## Introducción y descripción de la problemática

El proyecto presente consta en el diseño solución e implementación de una interfaz gráfica web, sockets multicliente y el uso de un arbol de expresion binaria, el cual al ser una estructura de datos, ayudaá a almacenar informacion necesaria que posteriormente se utilizará para dar un resultado espererado en el proyecto, en este caso se trata de la realización de una calculadora web, que tendra como principal objetivo resolver operaciones matemáticas compuestas escritas por el usuario; el mismo será desarrollado en el lenguaje de programación Java(jdk 16.0), pero además de esto al tener que implementar una interfaz web, se utilizarán otros recursos progrmacionales, como html y css.

Dicho app tendra un  historial por el cual se podrá acceder a las operaciones y resultados digitados por los usuarios que agregen problemas matemáticos a ella.

Al ser una app multicliente, esta permitirá que varias personas puedan usar la aplicacion al mismo tiempo y en distintos lugares segun su dirección IP.

Para la resolución de las operaciones matemáticas escritas por el usuario se deberá implementar un algoritmo que evalue el arbol en notación postfija.

## Diagrama de clases

![DiagramUML](https://user-images.githubusercontent.com/83249731/139214353-38f41c4d-d071-429a-bbeb-00e001a0fe35.png)
 

## Árbol de expresión binaria.

Como estructura de base de datos se utilizó un arbol de expresion binaria el cual es es una estructura de datos en la cual cada nodo puede tener un hijo izquierdo y un hijo derecho. No pueden tener más de dos hijos (de ahí el nombre "binario"). Si algún hijo tiene como referencia a null, es decir que no almacena ningún dato, entonces este es llamado un nodo externo. En el caso contrario el hijo es llamado un nodo interno. Esto con el fin de poder recorrelo para que se pueda calcular la expresión que el usuario debe de ingresar.

## Descripción de los algoritmos utilizados

* **Postfijo**

        public static String postfijo(String cadena){
                pila.clear();
                String postorderExpression = "";
                char caracterEvaluado;
                boolean temp=true;
                char UltimoCaracter =' ';

                for (int i=0;i<cadena.length();i++){ //se itera cada caracter de la expresión entrante
                    caracterEvaluado = cadena.charAt(i);

                    if (Character.isDigit(caracterEvaluado)) {
                        postorderExpression = postorderExpression + Character.toString(caracterEvaluado);
                        UltimoCaracter = caracterEvaluado;
                        temp=true;
                        continue;
                    }

                    switch(caracterEvaluado) {
                        case ')':
                            postorderExpression = postorderExpression+" "+ pila.pop();
                            UltimoCaracter = caracterEvaluado;
                            temp=false;
                            break;
                        default:

                            if (!Character.toString(caracterEvaluado).equals("(") & !Character.isDigit(caracterEvaluado)) {
                                pila.push(Character.toString(caracterEvaluado));
                                if (temp){
                                    postorderExpression = postorderExpression +" ";
                                }
                                break;
                            }
                            break;
                    }
                }
                if (Character.isDigit(UltimoCaracter)){
                    postorderExpression=postorderExpression +" "+ pila.pop();
                    return postorderExpression;
                }
                return postorderExpression;
            }


    * Consiste en tomar una expresión de carácter infijo (enviada por el usuario) y transformarla en 
carácter postfijo, el cual se caracteriza por no tener paréntesis y los operadores y operandos se ordenan de 
forma distinta, por lo general los operadores se colocan después de los operandos, de manera que se 
identifique la operación o relación que existe entre ambos. Posteriormente esta expresión (postija) será
leída por el árbol de expresión binaria.

* **Verificar**

        public boolean verificar(String expression){
                char caracter;
                int parentesis = 0;
                boolean ExpresionCorrecta = true;
                for (int i=0; i<expression.length();i++){
                    caracter = expression.charAt(i);
                    if (Character.toString(caracter).equals("(") ||Character.toString(caracter).equals(")")){
                        parentesis++;
                        continue;
                    }
                    switch (caracter) {
                        case '+':
                            break;
                        case '-':
                            break;
                        case '*':
                            break;
                        case '/':
                            break;
                        case '%':
                            break;
                        default:
                            if (Character.isDigit(caracter)) {
                                break;
                            }
                            ExpresionCorrecta = false;

                            i = expression.length();
                            break;
                    }
                }
                if ((parentesis%2)==0 && ExpresionCorrecta){
                    return false;
                }
                return true;
            }


  * Método boolean que recibe como argumento la expresión infija del usuario y verifica carácter por carácter si la expresión el usuario es apta para realizar el cálculo mediante el árbol de expresión binaria. Si la expresión está escrita erróneamente (caracteres no válidos, colocación incorrecta de paréntesis) se obtendrá como resultado un mensaje que indicará que la expresión ingresada está incorrecta, sin embargo si la expresión al ser evaluada en este método no se encuentran errores, permitirá su envió y ejecución en el árbol de expresión binaria.

 
* **ConstructorTree**

        public NodeTree constructorTree(String postorderExpression) {
                String TempNum ="";
                Stack<NodeTree> pila = new Stack<>();

                String caracterEvaluado;

                for (int i=0; i<postorderExpression.length();i++) {
                    caracterEvaluado = Character.toString(postorderExpression.charAt(i));

                    if (caracterEvaluado.equals(" ")) {
                        pila.add(new NodeTree(TempNum));
                        TempNum="";
                        continue;
                    }
                    if (isOperator(caracterEvaluado)) {

                        // retira los dos últimos nodos o elementos de 'x' y 'y' de la pila
                        NodeTree right = pila.pop();
                        NodeTree left = pila.pop();

                        NodeTree node = new NodeTree(caracterEvaluado, left, right);
                        pila.add(node);

                    }
                    else if (caracterEvaluado.equals(" ")) {

                        pila.add(new NodeTree(TempNum));
                        TempNum="";
                        continue;
                    }
                    else {
                        TempNum = TempNum + caracterEvaluado;
                    }
                }
                return pila.peek();
            }

  * Este método consiste en tomar una expresión postfija y crear un árbol de expresión binaria de carácter postfijo a base de esa expresión, de tal manera que se itera sobre la expresión carácter por carácter. Si el carácter iterado es un operando, se crea un nuevo nodo conteniendo el dato y su puntero se inserta en una pila, por otro lado si el carácter iterado es un operador, se extraen de la pila dos punteros (nodo izquierdo y nodo derecho) a dos árboles y se forma un nuevo árbol cuya raíz es el operador y cuyos hijos son el nodo izquierdo y el nodo derecho. Al final de la construcción del árbol, un puntero al árbol completo permanece en la pila (nodo) de forma que siempre haya una referencia a la raíz del árbol de expresión.

   
* **Resultado**

        public double Resultado(NodeTree root) {
                double respuesta = 0; // resultado que se retorna en cada subárbol

                // se cumple al tener como raiz a un operando o nodo hoja
                if (root.left==null && root.right==null){
                    return Double.parseDouble(root.data); // se retorna el operando o número contenido en el nodo hoja
                }

                // se realiza recursividad de pila para cálcular cada subárbol del árbol principal
                //  para luego cálcular el hijo izquierdo y derecho de la raiz principal
                switch (root.data){
                    // para cada caso se llama recursivamente a los hijos de cada subárbol
                    //  de manera que se obtengan los nodos hojas para ser cálculados.
                    case "+":
                        respuesta = Resultado(root.left) + Resultado(root.right);
                        break;
                    case "-":
                        respuesta = Resultado(root.left) - Resultado(root.right);
                        break;
                    case "*":
                        respuesta = Resultado(root.left) * Resultado(root.right);
                        break;
                    case "/":
                        respuesta = Resultado(root.left) / Resultado(root.right);
                        break;
                    case "%":
                        respuesta = Resultado(root.left) % Resultado(root.right);
                        break;
                }
                return respuesta;
            }
        }

  * Este método recibe como parámetro el nodo de la raíz del árbol de expresión, el cual luego recorre de forma recursiva con carácter postfijo; es decir, leerá la raíz de cada nodo interno (operadores), sin embargo no los escribirá hasta encontrar y escribir el nodo hoja izquierdo que contiene a un operando del subárbol evaluado (operador). Luego de escribir el operador se busca y se escribe el nodo hoja derecho que contiene al operando faltante para realizar el cálculo, de modo que ese resultado del cálculo se transformará en el operando de los nodos hijos del árbol de nivel anterior y de esta forma se hará con cada subárbol del árbol de expresión.

##  Problemas encontrados

Aunque la mayoría de los problemas presentes en el proyecto se dieron por parte del código y su resolución, tuvimos inconvenientes con github, dado que en ciertos casos habían conflictos presentes en los merge quue se realizaban.

Una de las complicaciones mas grandes que se presentaron a la hora de realizar el proyecto fue el uso de diferentes aplicaciones git, dado que a la hora de hacer un commid o un merge, no agregaba, sino que sobreescribia la información del proyecto.

Gracias a la realización de un proyecto con Maven, dado que agregaba todas las dependencias necesarias para que su utilización fuera mas sencilla.

El problema mas gande que tuvimos es que en una primera instancia usarmos para la interfaz web codigo manejado por JavaScript, que a pesar de ser excelente para manejar interfaces, se necesitaba pasar información de este código a codigo Java, que anque si se puede realizar, es un poco complicado de hacer, por lo que se procedió a exportar la interfaz gráfica a un xhtml, manejado con el framework PrimeFaces, esto fue muy sencillo ya qué este framework ofrece gran variedad de templates, por lo que escogimos uno y la interfáz gráfica salió sola, solo nos tuvimos que preocupar por el manejo de la información entre cliente y Servidor.

## Conclusiones

**1.** Al realizar la documentación interna utilizando la herramienta de JavaDocs, se reconoce la importancia de hacerla lo mas clara y lo más detallada posible, ya que es necesario para el entendimiento de las demás personas a la hora de leer el código realizado, teniendo una vista de los constructores, variables y métodos presentes en cada clase.

**2.** Se concluye el proyecto con la idea de que al crear algo grande a como lo es una aplicación con multiples clientes, con cosas relativamente básicas y sin tener muchos recursos o mucho conocimiento de programación, se pueden lograr cosas muy provechosas e impresionantes como las obtenidas en este proyecto.

**3.** En la elaboración de un proyecto se da la oportunidad de adquirir nuevos conocimientos, nuevas formas de pensar y desarrollar la lógica computacional y de programación, con el fin de poder realizar a futuro, proyectos aún más avanzados y con más eficiencia mayor.

