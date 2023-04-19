import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

// TODO test all methods

/**
 * My own implementation of a HashMap
 * @param <K> Key
 * @param <V> Value
 */
public class MyHashMap<K, V> {

    private Entry<K, V>[] entries;
    private int size = 0;
    private int capacity = 509; // a prime number


    /**
     * Entry class, with a LinkedList feature when collision happens
     * @param <K> Key of the entry
     * @param <V> Value of the entry
     */
    static class Entry<K, V> {
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
     * @param capacity Number, how many buckets are created
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
        this.size = map.size;
    }

    /**
     * Removes all of the mappings from this map.
     */
    public void clear() {
        this.entries = new Entry[capacity];
        size = 0;
    }

    /**
     *
     * @return Returns a shallow copy of this HashMap instance.
     */
    public Object clone() {
        return this;
    }

    /**
     *
     * @param key That should be searched for
     * @return Returns true, when the MyHashMap contains the given key, false otherwise
     */
    public boolean containsKey(Object key) {
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                if(current.key.equals(key)) return true;
                current = current.next;
            }
        }
        return false;
    }

    /**
     *
     * @param value That should be searched for
     * @return Returns true, when the MyHashMap contains the given value, false otherwise
     */
    public boolean containsValue(Object value) {
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                if(current.value.equals(value)) return true;
                current = current.next;
            }
        }
        return false;
    }

    /**
     *
     * @return a Set of all Entry instances in the MyHashMap
     */
    public Set<MyHashMap.Entry<K, V>> entrySet() {
        Set<MyHashMap.Entry<K, V>> set = new HashSet<>();
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                set.add(current);
                current = current.next;
            }
        }
        return set;
    }

    /**
     *
     * @param key that should be searched for
     * @return the value connected to the given key, or null if the key isn't in the MyHashFunction
     */
    public V get(Object key) {
        Entry<K, V> current = entries[hash(key)];
        while(current != null) {
            if(current.key.equals(key)) return current.value;
            current = current.next;
        }
        return null;
    }

    /**
     *
     * @param key Key that should be searched for
     * @param defaultValue Value that is returned, if the given key couldn't be found in the MyHashMap
     * @return the value connected to the given key, or the defaultValue, if the key couldn't be found in the
     * MyHashMap
     */
    public V getOrDefault(Object key, V defaultValue) {
        return entries[hash(key)] != null ? get(key) : defaultValue;
    }

    /**
     *
     * @return true, if the MyHashMap is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     *
     * @return Returns a Set of keys for the current instance
     */
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                set.add(current.key);
                current = current.next;
            }
        }
        return set;
    }

    /**
     *
     * @param key Key that should be added or edited
     * @param value Value that should be added or edited
     * @return Returns the value connected to the given key
     */
    public V put(K key, V value) {
        int hash = hash(key);
        if(entries[hash] != null) { // If there is already an Entry in the current bucket
            Entry<K, V> current = entries[hash];
            while(current != null) {
                if(current.key.equals(key)) { // There is already this key, so we replace the value
                    current.value = value;
                    return value;
                }
                if(current.next != null) {
                    current = current.next;
                } else { // End of the linked list, so we add a new Entry
                    current.next = new Entry<>(key, value, null);
                    size++;
                    return value;
                }
            }
        } else { // Else there is no Entry in the current bucket
            entries[hash] = new Entry<>(key, value, null);
            size++;
        }
        return entries[hash].value;
    }

    /**
     *
     * @param map Instance of a MyHashMap which elements are added to the current instance of MyHashMap
     */
    public void putAll(MyHashMap<? extends K, ? extends V> map) {
        for(Entry<? extends K, ? extends V> entry : map.entries) {
            if(entry != null)
                put(entry.key, entry.value);
        }
    }

    /**
     *
     * @param key Key that should be added if it is not in the current instance of MyHashMap
     * @param value Value that should be added if it is not in the current instance of MyHashMap
     * @return Returns the value connected to the given key, if absent
     */
    public V putIfAbsent(K key, V value) {
        int hash = hash(key);
        if(!containsKey(key)) {
            put(key, value);
        }
        return entries[hash].value;
    }

    /**
     * Removes the instance of Entry if it is concluded in the current instance of MyHashMap
     * @param key Key that should be searched for
     */
    public void remove(Object key) {
        int hash = hash(key);
        if(!containsKey(key)) return;
        Entry<K, V> current = entries[hash];
        // First Element has to be removed
        if(current.key.equals(key)) {
            entries[hash] = current.next;
            size--;
            return;
        }
        // Added this line because intellij says current is always null???
        current = entries[hash];
        while(current != null) {
            if(current.next.key.equals(key)) { // key was found
                current.next = current.next.next;
                size--;
                return;
            }
            current = current.next;
        }
    }

    /**
     *
     * @param key Key that should be searched for
     * @param value Value that should be searched for
     * @return Return true if the given key with the connected value was successfully found in the current instance
     * of MyHashMap and was removed from it, false otherwise
     */
    public boolean remove(Object key, Object value) {
        int hash = hash(key);
        if(!containsKey(key)) return false;
        Entry<K, V> current = entries[hash];
        // First Element has to be removed
        if(current.key.equals(key)) {
            entries[hash] = current.next;
            size--;
        }
        // Added this line because intellij says current is always null???
        current = entries[hash];
        while(current != null) {
            if(current.next == null) break;
            if(current.next.key.equals(key) && current.next.value.equals(value)) { // key with given value was found
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Replaces the current value connected to the given key with the given value
     * @param key Key that should be searched for
     * @param value Value that should be replaced with
     * @return Return the value connected to the given key
     */
    public V replace(K key, V value) {
        if(!containsKey(key)) return null;
        return put(key, value);
    }

    /**
     * Replaces the current value connected to the given key with the newValue, if the current value is equal to
     * the oldValue
     * @param key Key that should be found
     * @param oldValue Value that should be found
     * @param newValue New value that should be replaced with
     * @return Return true if the given key and oldValue could be found connected together in the current instance
     * of MyHashMap, false otherwise
     */
    public boolean replace(K key, V oldValue, V newValue) {
        if(!containsKey(key)) return false;
        Entry<K, V> current = entries[hash(key)];
        while(current != null) {
            if(current.key.equals(key)) { // key was found
                if(current.value.equals(oldValue)) { // and it had the oldValue, so we replace
                    current.value = newValue;
                    return true;
                } else { // but it didn't have the oldValue
                    break;
                }
            }
            current = current.next;
        }
        return false;
    }

    public int size() {
        return size;
    }

    /**
     *
     * @return Returns a set of the values in the current instance of the MyHashMap
     */
    public Collection<V> values() {
        Collection<V> set = new HashSet<>();
        for(Entry<K, V> entry : entries) {
            Entry<K, V> current = entry;
            while(current != null) {
                set.add(current.value);
                current = current.next;
            }
        }
        return set;
    }

    // HELPER METHODS //

    /**
     *
     * @param o the given Object
     * @return Returns a hashCode for the given object
     */
    private int hash(Object o) {
        // Multiplies 4 chars of the current object to get a hashcode
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