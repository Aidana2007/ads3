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
    }
}
