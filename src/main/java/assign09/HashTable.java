package assign09;

import java.util.*;


public class HashTable<K, V> implements Map<K, V> {
    private static final double LOAD_FACTOR_THRESHOLD = 10.0;
    private int capacity = 10;
    private ArrayList<LinkedList<MapEntry<K, V>>> table;
    private int size;
    private int collisions;

    public HashTable() {
        table = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
        size = 0;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % table.size());
    }

    private void resizeTable() {
        double loadFactor = (double) size / capacity;
        if (loadFactor > LOAD_FACTOR_THRESHOLD) {
            int newCapacity = capacity * 2;
            ArrayList<LinkedList<MapEntry<K, V>>> newTable = new ArrayList<>(newCapacity);
            for (int i = 0; i < newCapacity; i++) {
                newTable.add(new LinkedList<>());
            }
            for (LinkedList<MapEntry<K, V>> bucket : table) {
                for (MapEntry<K, V> entry : bucket) {
                    int index = Math.abs(entry.getKey().hashCode() % newCapacity);
                    newTable.get(index).add(entry);
                }
            }
            table = newTable;
            capacity = newCapacity;
        }
    }

    @Override
    public void clear() {
        table.clear();
        for (int i = 0; i < capacity; i++) {
            table.add(new LinkedList<>());
        }
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null)
            return false;
        int index = hash(key);
        for (MapEntry<K, V> entry : table.get(index)) {
            if (entry.getKey() != null && entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        if (value == null)
            return false;
        for (LinkedList<MapEntry<K, V>> bucket : table) {
            for (MapEntry<K, V> entry : bucket) {
                if (entry != null && entry.getValue() != null && entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<MapEntry<K, V>> entries() {
        List<MapEntry<K, V>> entries = new LinkedList<>();
        for (LinkedList<MapEntry<K, V>> bucket : table) {
            entries.addAll(bucket);
        }
        return entries;
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        for (MapEntry<K, V> entry : table.get(index)) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public V put(K key, V value) {
        resizeTable();
        int index = hash(key);
        if (table.get(index) == null) { // if null create a linked list
            table.set(index, new LinkedList<>());
        } else {
            for (MapEntry<K, V> entry : table.get(index)) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.setValue(value);
                    return oldValue;
                }
                collisions++; // Increment collision count
            }
        }

        table.get(index).add(new MapEntry<>(key, value));
        size++;
        return value;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            return null;
        }
        int index = hash(key);
        Iterator<MapEntry<K, V>> iterator = table.get(index).iterator();
        while (iterator.hasNext()) {
            MapEntry<K, V> entry = iterator.next();
            if (key.equals(entry.getKey())) {
                iterator.remove();
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public int getCollisions(){
        return collisions;
    }

}
