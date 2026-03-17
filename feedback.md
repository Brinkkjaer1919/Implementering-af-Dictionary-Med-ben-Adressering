# Feedback på Implementering af Dictionary med Åben Adressering

Hej Kasper,

Godt arbejde med din aflevering! Du har fat i de grundlæggende koncepter omkring hash-tabeller og linear probing. Her er en gennemgang af din løsning med fokus på både det, der fungerer godt, og de steder, hvor der er plads til forbedring.

### Positive punkter
*   **Korrekt brug af Generics:** Du implementerer `Dictionary<K, V>` korrekt med generiske typer.
*   **Entry-klasse:** Din brug af en privat indre klasse `Entry` er den helt rigtige arkitektoniske beslutning i Java.
*   **Håndtering af negative hash-koder:** Du bruger `Math.abs(key.hashCode())`, hvilket løser problemet med negative indeks – flot!
*   **Sentinel value:** Du har implementeret en `DELETED` markør, som er kritisk for at åben adressering fungerer korrekt ved sletning.

### Områder til forbedring

#### 1. NullPointerException ved DELETED (Kritisk)
I dine `put`, `get` og `remove` metoder bruger du `table[index].key.equals(key)`.
Da din `DELETED` entry er defineret som `new Entry<>(null, null)`, vil `table[index].key` være `null`, når du rammer en slettet plads. I Java kan man ikke kalde `.equals()` på `null`. Det vil resultere i en `NullPointerException`.

**Løsning:** Du skal tjekke om pladsen er `DELETED` før du sammenligner nøgler, eller bruge `Objects.equals(table[index].key, key)`. Alternativt kan du tjekke `if (table[index] != DELETED && table[index].key.equals(key))`.

#### 2. Risiko for uendelige løkker
I dine `while`-løkker (f.eks. i `get` og `put`) stopper du kun, hvis du finder en `null` plads. Hvis din tabel bliver 100% fuld (og der ikke er nogen `null` værdier tilbage, kun aktive elementer eller `DELETED` markører), vil dine løkker køre for evigt.

**Tip:** I en rigtig implementering ville man udvide tabellen (resizing) før den bliver fuld, eller tælle antallet af forsøg for at undgå at køre hele tabellen rundt uden stop.

#### 3. Logik i `put`
Din `put` metode er lidt kompleks med mange `if-else` grene. Du har faktisk tre forskellige steder, hvor du gør næsten det samme. Du kan gøre koden meget mere læsbar ved at have én `while`-løkke, der finder den korrekte plads (enten en ledig plads eller pladsen med den eksisterende nøgle), og derefter udfører logikken.

#### 4. Genbrug af DELETED pladser
Når du kører `put`, leder du efter den første ledige plads. En optimering ville være at genbruge en `DELETED` plads til den nye indsættelse, hvis nøglen ikke findes andre steder i tabellen.

### Opsummering
Din forståelse for linear probing er god, og din brug af `DELETED` markøren viser, at du har forstået udfordringen ved sletning. Hvis du fikser `NullPointerException` fejlen ved sammenligning af nøgler, vil din implementering være meget robust.

Flot arbejde!
