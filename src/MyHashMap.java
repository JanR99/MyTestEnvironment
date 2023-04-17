import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MyHashMap<K, V> {

    private Entry<K, V>[] entries;
    private int size = 0;
    private int capacity = 500;


    /**
     * Entry class, with a LinkedList feature when collision happens
     * @param <K> Key of the entry
     * @param <V> Value of the entry
     */
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    /**
     * Default constructor
     */
    public MyHashMap() {
        entries = new Entry[capacity];
    }

    /**
     * Constructor with given capacity
     * @param capacity How much buckets are created
     */
    public MyHashMap(int capacity) {
        this.capacity = capacity;
        entries = new Entry[capacity];
    }

    /**
     * Constructor which clones the given MyHashMap
     * @param map That should be cloned
     */
    public MyHashMap(MyHashMap<K, V> map) {
        this.entries = map.entries;
        this.capacity = map.capacity;
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        this.entries = new Entry[capacity];
        size = 0;
    }

    /**
     * Return a shallow copy of this HashMap instance.
     * @return
     */
    public Object clone() {
        return this;
    }

    /**
     *
     * @param key That should be searched for
     * @return Returns true, when the MyHashMap contains the given value, false otherwise
     */
    public boolean containsKey(Object key) {
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                if(current.key == key) return true;
                current = current.next;
            }
        }
        return false;
    }

    public boolean containsValue(Object value) {
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                if(current.value == value) return true;
                current = current.next;
            }
        }
        return false;
    }

    public Set<MyHashMap.Entry<K, V>> entrySet() {
        Set<MyHashMap.Entry<K, V>> set = new HashSet();
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                set.add(current);
                current = current.next;
            }
        }
        return set;
    }

    public V get(Object key) {
        int hash = hash(key);
        Entry<K, V> current = entries[hash];
        while(current != null) {
            if(current.key == key) return current.value;
            current = current.next;
        }
        return null;
    }

    public V getOrDeffault(Object key, V defaultValue) {
        int hash = hash(key);
        return entries[hash] != null ? entries[hash].value : defaultValue;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(Entry<K, V> entry : entries) {
            if(entry != null)
                set.add(entry.key);
        }
        return set;
    }

    public V put(K key, V value) {
        int hash = hash(key);
        if(entries[hash] != null) {
            entries[hash].value = value;
        } else {
            entries[hash] = new Entry<>(key, value, null);
            size++;
        }
        return entries[hash].value;
    }

    public void putAll(MyHashMap<? extends K, ? extends V> map) {
        for(Entry<? extends K, ? extends V> entry : map.entries) {
            put(entry.key, entry.value);
        }
    }

    public V putIfAbsent(K key, V value) {
        int hash = hash(key);
        if(entries[hash] == null) {
            put(key, value);
        }
        return entries[hash].value;
    }

    public void remove(Object key) {
        int hash = hash(key);

    }

    public boolean remove(Object key, Object value) {

        return false;
    }

    public V replace(K key, V value) {

        return null;
    }

    public boolean replace(K key, V oldValue, V newValue) {

        return false;
    }

    public int size() {
        return size;
    }

    public Collection<V> values() {
        Collection<V> set = new HashSet<>();
        for(Entry<K, V> entry : entries) {
            if(entry != null)
                set.add(entry.value);
        }
        return set;
    }



    // HELPER METHODS //
    private int hash(Object o) {
        String s = o.toString();
        if(s.length() >= 2) {
            return (s.charAt(0) * s.charAt(1) * s.charAt(s.length() - 2) * s.charAt(s.length() - 1)) % capacity;
        }else if(s.length() == 1) {
            return ((int) Math.pow(s.charAt(0), 4)) % capacity;
        } else {
            return 0;
        }
    }
}