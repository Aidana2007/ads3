import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>();

        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            MyTestingClass key = new MyTestingClass(random.nextInt(100000), "Name" + i);
            Student value = new Student("Student" + i, 18 + random.nextInt(10));
            table.put(key, value);
        }

        for (int i = 0; i < table.getM(); i++) {
            int count = 0;
            MyHashTable.HashNode<MyTestingClass, Student> current = table.getChainArray()[i];
            while (current != null) {
                count++;
                current = ((MyHashTable.HashNode<MyTestingClass, Student>) current).next;
            }
            System.out.println("Bucket " + i + ": " + count + " elements");
        }
        BST<Integer, String> tree = new BST<>();
        tree.put(5, "Five");
        tree.put(3, "Three");
        tree.put(7, "Seven");
        tree.put(1, "One");

        for (var elem : tree) {
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
    }
}
