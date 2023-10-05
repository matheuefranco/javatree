
package javatreesaula;

public class BinarySearchTree<T extends Comparable<T>>{
    Node raiz=null;
    public void add(T novoDado){
        Node<T> novoNo = 
                new Node<T>(novoDado);
        raiz = inserir(raiz, novoNo);
    }
    
    private Node<T> inserir
        (Node<T> raiz, Node<T> novo){
    
    }

    
}
