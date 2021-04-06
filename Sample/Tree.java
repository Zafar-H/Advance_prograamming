import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tree<T> {
    public static final Logger logger = LogManager.getLogger( Tree.class );

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
    private String searchKey;

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

    public void displayTreeStructure(Node<T> current, String levelIdentifier) {
        logger.info(levelIdentifier+ "" +current.getValue());
        String key = current.getValue().toString();
        levelIdentifier = levelIdentifier+ " -----> ";
        String finalLevelIdentifier = levelIdentifier;
        current.getChildren().forEach((n) -> displayTreeStructure(n, finalLevelIdentifier));
    }

    public void displayTreeStructure() {
        displayTreeStructure(getRoot(), "");
    }

    public String search(Node<T> current, String key) {
        if(current.getChildren() != null)
        {
            for(Node<T> child : current.getChildren())
            {

            }
        }
        if(current.getValue().toString().equals(key))
        {
            return current.getValue().toString();
        }
        else
            return "Not found!!!";
    }

    public String search(String key) {
        //return key.getChildren().get(0).getValue();
        return  search(getRoot(), key);
    }

    public static void main(String[] args) throws IOException, ParseException {


    }
}

