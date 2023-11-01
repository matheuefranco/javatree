
package javatreesaula;

import javax.swing.JTextArea;

public class AVLTree<T extends Comparable<T>>{
    Node raiz=null;
    int cont;
    
    public int altura(Node raiz) {
            int altEsq, altDir;
            if (raiz == null) {
                return -1;
            }
            altEsq = altura(raiz.esquerda);
            altDir = altura(raiz.direita);
            if (altDir > altEsq)
                return altDir + 1;
            else
                return altEsq + 1;
           
    }// fim funcao altura
    public Node<T> rotacaoDir(Node<T> raiz){
        Node<T> nova = raiz.esquerda;
        raiz.esquerda = nova.direita;
        nova.direita = raiz;
        //atualizar fator de balanceamento
        raiz.fb = altura(raiz.direita) - altura(raiz.esquerda);
        nova.fb = altura(nova.direita) - altura(nova.esquerda);
        System.out.println("Rotacao Direita -->");
        return nova;
    }
    
    public Node<T> rotacaoEsq(Node<T> raiz){
            Node<T> nova = raiz.direita;
            raiz.direita = nova.esquerda;
            nova.esquerda = raiz;
            // atualizar os fb
            raiz.fb = altura(raiz.direita) - altura(raiz.esquerda);
            nova.fb = altura(nova.direita) - altura(nova.esquerda);
            System.out.println("Rotacao Esquerda -->");
            return nova;
    }
            
    public int add(T novoDado){
        Node<T> novoNo = new Node<T>(novoDado);
        cont=0;
        raiz = inserir(raiz, novoNo);
        return cont;
    }
    
    private Node<T> inserir(Node<T> raiz, Node<T> novo){
           cont++;   
           if(raiz == null)
                return novo;
            
            if(novo.dado.compareTo(raiz.dado)>=0)
                raiz.direita = inserir(raiz.direita, novo);
            else
                raiz.esquerda = inserir(raiz.esquerda, novo);
            // atualizar o fb
            raiz.fb = altura(raiz.direita) - altura(raiz.esquerda);
            switch(raiz.fb){
                case 2: if(raiz.direita.fb>=0) // rot esquerda
                            raiz = rotacaoEsq(raiz);
                        else{
                            raiz.direita = rotacaoDir(raiz.direita);
                            raiz = rotacaoEsq(raiz);
                        }
                break;
                case -2: if(raiz.esquerda.fb <=0) // rot direitra
                            raiz = rotacaoDir(raiz); // simples
                        else{
                            raiz.esquerda = rotacaoEsq(raiz.esquerda);
                            raiz = rotacaoDir(raiz);
                        }      
                break;
            }             
           
            return raiz; // retorna a raiz atualizada
    }
    
    //--------------------------------------
    // Busca
    public T buscar(T dadoBusca){
        return (T)buscar(raiz,dadoBusca);
    }
    private T buscar(Node<T> raiz, T dadoBusca){
        if(raiz==null)
          return null;
        if(raiz.dado.equals(dadoBusca))
            return raiz.dado;
        if(dadoBusca.compareTo(raiz.dado)<0)
            return buscar(raiz.esquerda, dadoBusca);
        else
            return buscar(raiz.direita,dadoBusca);
    }
    //--------------------------------------
    
    public void preOrder(){
        preOrder(raiz);
    }
    private void preOrder(Node<T> raiz){
        if(raiz!=null){
            System.out.print(raiz.dado+", ");
            preOrder(raiz.esquerda);
            preOrder(raiz.direita);
        }// fim 
    }
    
     public void preOrder(JTextArea listMostraDados){
        preOrder(raiz, listMostraDados);
        listMostraDados.append("\n");
    }
    private void preOrder(Node<T> raiz, JTextArea listMostraDados){
        if(raiz!=null){
            listMostraDados.append(raiz.dado+" |");
            preOrder(raiz.esquerda, listMostraDados);
            preOrder(raiz.direita, listMostraDados);
        }// fim 
    }
    
    public void inOrder(){
        inOrder(raiz);
    }
    private void inOrder(Node<T> raiz){
        if(raiz!=null){
            inOrder(raiz.esquerda);
            System.out.print(raiz.dado+", ");
            inOrder(raiz.direita);
        }// fim 
    }
    public void postOrder(){
        postOrder(raiz);
    }
    private void postOrder(Node<T> raiz){
        if(raiz!=null){
            postOrder(raiz.esquerda);
            postOrder(raiz.direita);
            System.out.print(raiz.dado+", ");
        }// fim 
    }
    //----------------------
       
    public Node<T> removeNode(Node<T> raiz) {
        Node<T> nova, pai;
        if(raiz.direita==null){
            nova = raiz.esquerda;
            return nova;
        }
        // percorrer até achar o menor da direita
        pai = raiz; nova= raiz.direita;
        while(nova.esquerda!=null){
		pai=nova;
		nova = nova.esquerda;
	}// fim while
        
        // reorganizar os ponteiros
	if(pai!=raiz){
		pai.esquerda = nova.direita;
		nova.direita = raiz.direita;
	}
        
        nova.esquerda = raiz.esquerda;
        System.out.println("Retornando nova raiz:"+nova.dado);
	return nova;
}

public void remove(T dadoRemover) {
    raiz = remove(this.raiz, dadoRemover);
}

private Node<T> remove(Node<T> raiz, T dadoRemover) {
    	if(raiz==null){
                System.out.println("Não encontrado - :(");
		return null;
        }
	if(raiz.dado.equals(dadoRemover)){
                System.out.println("Encontrado - removendo");
		return removeNode(raiz);
        }
	if(dadoRemover.compareTo(raiz.dado)<0)
		raiz.esquerda = remove(raiz.esquerda,dadoRemover);
	else
	    raiz.direita = remove(raiz.direita,dadoRemover);
	
        // atualizar o fb
            raiz.fb = altura(raiz.direita) - altura(raiz.esquerda);
            switch(raiz.fb){
                case 2: if(raiz.direita.fb>=0) // rot esquerda
                            raiz = rotacaoEsq(raiz);
                        else{
                            raiz.direita = rotacaoDir(raiz.direita);
                            raiz = rotacaoEsq(raiz);
                        }
                break;
                case -2: if(raiz.esquerda.fb <=0) // rot direitra
                            raiz = rotacaoDir(raiz); // simples
                        else{
                            raiz.esquerda = rotacaoEsq(raiz.esquerda);
                            raiz = rotacaoDir(raiz);
                        }      
                break;
            }             
            
        return raiz;
}// fim buscaRemove

    
}
