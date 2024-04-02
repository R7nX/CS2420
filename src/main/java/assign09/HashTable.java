package assign09;

import java.util.*;

public class HashTable<K, V> implements Map<K, V> {

    private LinkedList<MapEntry<K, V>>[] table;
    private int size;

    public HashTable(){
        table = new LinkedList[10];
        size = 0;
    }

    private int hash(K key){
        return Math.abs(key.hashCode() % table.length);
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++){
            if (table[i] != null)
                table[i].clear();
        }

        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        int index = hash(key);
        for (MapEntry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (LinkedList<MapEntry<K, V>> bucket : table) {
            for (MapEntry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
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
        for (MapEntry<K, V> entry : table[index]) {
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
        int index = hash(key);
        for (MapEntry<K, V> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        table[index].add(new MapEntry<>(key, value));
        size++;
        return value;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        Iterator<MapEntry<K, V>> iterator = table[index].iterator();
        while (iterator.hasNext()) {
            MapEntry<K, V> entry = iterator.next();
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                iterator.remove();
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
