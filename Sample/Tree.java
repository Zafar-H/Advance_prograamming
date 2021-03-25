import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Tree<T> {
    public static final Logger logger = LogManager.getLogger( LinearArrayOperation_L2.class );

    public static class Node<T>{
        private T value;
        private ArrayList<Node<T>> children;
        private Tree<T> hostTree;
        private Node<T> parent;
        public T getValue() {
            return value;
        }
        public ArrayList<Node<T>> getChildren() {
            return children;
        }

        public Node(ArrayList<Node<T>> children, Tree<T> hostTree, T value, Node<T> parent) {
            this.children = children;
            this.hostTree = hostTree;
            this.value = value;
            this.parent = parent;
        }
    }
    private Node<T> root;

    public void addRoot(T value) {
        if (root == null) {
            root = new Node<>(new ArrayList<>(), this, value, null);
        }else {
            throw new IllegalArgumentException("Trying to add new root to a non empty tree!!!");
        }
    }

    public Node<T> addNode(Node<T> parent, T value) {
        if(parent == null) {
            throw new NullPointerException("Cannot add child to null parent!!!");
        }else if(parent.hostTree != this){
            throw new IllegalArgumentException("Parent node not a part of this tree!!!");
        }else {
            Node<T> newNode = new Node<>(new ArrayList<>(), this, value, parent);
            parent.getChildren().add(newNode);
            return newNode;
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    public static void main(String[] args)
    {
        Tree<String> tree = new Tree<>();
        tree.addRoot("form");

        //logger.info(tree);
        /*Node<String> nod1 = tree.getRoot();
        Node<String> nod1_1 = tree.addNode(nod1, "firstName");
            Node<String> nod1_1_1 = tree.addNode(nod11, "rules");
                Node<String> nod1_1_1_1 = tree.addNode(nod21, "r.01");
                    Node<String> nod1_1_1_1_1 = tree.addNode(nod211, "notEmpty");
                        Node<String> nod1_1_1_1_1_1 = tree.addNode(nod2111, "true");
                    Node<String> nod2111 = tree.addNode(nod211, "notEmpty");
                        Node<String> nod21111 = tree.addNode(nod2111, "true");
       *//* for (int i=0; i<level; i++) {
            logger.info(nod1.getChildren().get(i).getValue());
        }*//*

                Node<String> nod212 = tree.addNode(nod21, "r.02");
                Node<String> nod213 = tree.addNode(nod21, "r.03");
                Node<String> nod214 = tree.addNode(nod21, "r.04");*/

        //Node<String> nod6 = tree.addNode(nod2, "display");

       /* Node<String> nod3 = tree.addNode(nod1, "mobileNumber");
        Node<String> nod4 = tree.addNode(nod1, "email");*/




        for (int i=0; i<2; i++) {
           // logger.info(nod1.getChildren().get(i).getValue());
        }
    }
}

