package p3.OpgaverAfleveringer.ImplementeringafDictionaryMedAabenAdressering;


public class DictionaryOpenAddressing<K, V> implements Dictionary<K, V> {
    private Entry<K, V>[] table; // --> Opretter en array tabel der holder på "Entry" Objekter.
    private final Entry<K,V> DELETED = new Entry<>(null,null);
    private int size;
    private static final int n = 13;

    public DictionaryOpenAddressing() {
        this.table = (Entry<K, V>[]) new Entry[n];
        this.size = 0;
    }

    @Override
    public V put(K key, V value) {

        int index = Math.abs(key.hashCode()) % n;

        if (table[index] == null) {
            Entry<K, V> Entry = new Entry<>(key, value); // --> Opretter nyt entry
            table[index] = Entry; // --> Placere nyt Entry på rette index i tabel.
            size++;
        } else if (table[index].key.equals(key)) {
            Entry<K, V> oldEntry = table[index]; // --> gemmer den gamle værdi
            Entry<K, V> Entry = new Entry<>(key, value); // --> Opretter nyt entry
            table[index] = Entry; // --> Placere nyt Entry på rette index i tabel
            return oldEntry.value;
        } else if (table[index] != null && !table[index].key.equals(key)) {
            while (table[index] != null && !table[index].key.equals(key)) {
                int temp = (index + 1) % n;
                index = temp;
            }
            if (table[index] == null) {
                Entry<K, V> Entry = new Entry<>(key, value); // --> Opretter nyt entry
                table[index] = Entry; // --> Placere nyt Entry på rette index i tabel.
                size++;
            } else if (table[index].key.equals(key)) {
                Entry<K, V> oldEntry = table[index]; // --> gemmer den gamle værdi
                Entry<K, V> Entry = new Entry<>(key, value); // --> Opretter nyt entry
                table[index] = Entry; // --> Placere nyt Entry på rette index i tabel
                return oldEntry.value;
            }
        }
        return null;
    } //Færdig


    @Override
    public V get(K key) {

        int index = Math.abs(key.hashCode()) % n;

        while (table[index] != null) { // --> undersøger om plads har et objekt
            if (table[index].key.equals(key)) { // --> undersøger om key vi har er lig med key på index
                return table[index].value; // --> Retunere værdi hvis det er det rigtige objekt
            } else {
                int temp = (index + 1) % n; //->
                index = temp;

                 /*if (table[index].key.equals(key)){
                     return table[index].value;*/
            }
        }
        return null;
    } //Færdig

    @Override
    public int size() {
        return size;
    } //Færdig

    @Override
    public boolean isEmpty() {
        return size == 0;
    } //Færdig

    @Override
    public V remove(K key) {

        int index = Math.abs(key.hashCode()) % n;

        while (table[index] != null){
            if (table[index].key.equals(key)){
                Entry<K,V> deletedEntry = new Entry<>(key,table[index].value);
                table[index] = DELETED;
                size--;
                return deletedEntry.value;
            } else {
                int temp = (index + 1) % n; //->
                index = temp;
            }
        }


        return null;
    }

    private static class Entry<K, V> { // hjælpe klasse der bruges til at skabe entries.
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    } //Færdig
}
