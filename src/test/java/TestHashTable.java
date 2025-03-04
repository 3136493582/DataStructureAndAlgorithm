import org.example.HashTable.HashTable;


public class TestHashTable {
    public static void main(String[] args) {
        HashTable table = new HashTable();

        for (int i = 0; i < 200000; i++) {
            Object o = new Object();
            table.put(o.hashCode(),o,o);
        }

        table.print();
    }
}
