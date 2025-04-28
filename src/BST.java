import java.util.Iterator;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size = 0;

    private class Node {
        private K key;
        private V val;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    public static class Entry<K, V> {
        private final K key;
        private final V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }

    public void put(K key, V val) {
        Node newNode = new Node(key, val);
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                current.val = val;
                return;
            }
        }

        if (key.compareTo(parent.key) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        size++;
    }
    public V get(K key) {
        Node current = root;
        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                return current.val;
            }
        }
        return null;
    }
    public void delete(K key) {
        Node parent = null;
        Node current = root;

        while (current != null && !current.key.equals(key)) {
            parent = current;
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return; // Not found
        }

        // Case 1: Node with only one child or no child
        if (current.left == null || current.right == null) {
            Node newCurr;
            if (current.left == null) {
                newCurr = current.right;
            } else {
                newCurr = current.left;
            }

            if (parent == null) {
                root = newCurr;
            } else if (current == parent.left) {
                parent.left = newCurr;
            } else {
                parent.right = newCurr;
            }
        } else {
            // Case 2: Node with two children
            Node p = null;
            Node temp;

            temp = current.right;
            while (temp.left != null) {
                p = temp;
                temp = temp.left;
            }

            if (p != null) {
                p.left = temp.right;
            } else {
                current.right = temp.right;
            }

            current.key = temp.key;
            current.val = temp.val;
        }
        size--;
    }
    public int size() {
        return size;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new Iterator<>() {
            private Stack<Node> stack = new Stack<>();
            private Node current = root;

            {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }

            public boolean hasNext() {
                return !stack.isEmpty();
            }

            public Entry<K, V> next() {
                Node node = stack.pop();
                Entry<K, V> entry = new Entry<>(node.key, node.val);
                Node right = node.right;
                while (right != null) {
                    stack.push(right);
                    right = right.left;
                }
                return entry;
            }
        };
    }

}
