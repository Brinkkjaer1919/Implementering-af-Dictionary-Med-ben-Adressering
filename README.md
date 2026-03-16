# Implementering-af-Dictionary-Med-ben-Adressering
Aflevering af "Implementering af Dictionary med Åben Adressering".


Denne opgave implementerer interfacet Dictionary<K,V> ved hjælp af en hash-tabel.
Dictionaryen bruger et array af Entry<K,V> objekter til at gemme elementerne.
Hver Entry indeholder en key og en value.
Variablen size holder styr på hvor mange elementer der er gemt løbende.

Implementerede metoder
put(K key, V value) – indsætter nyt Entry eller overskriver en eksisterende værdi.
get(K key) – returnerer værdien der hører til en given nøgle.
remove(K key) – fjerner et Entry og markerer pladsen som DELETED.
size() – returnerer antallet af elementer i dictionaryen.
isEmpty() – returnerer true hvis dictionaryen ikke indeholder nogen elementer.
