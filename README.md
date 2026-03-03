# PAOJ 2026

Proiect educațional pentru cursul **Programare Avansată pe Obiecte în Java**.

---

## FAQ — Cum rulez Java din terminal (pentru începători)

### 1. Am Java instalat?

```bash
java -version
javac -version
```

Dacă primești un număr de versiune (ex: `21.0.x`), ești pregătit.  
Dacă nu, descarcă JDK de la [adoptium.net](https://adoptium.net/) și instalează-l.

---

### 2. Compilez un fișier `.java`

```bash
javac NumeleFisierului.java
```

Se generează un fișier `NumeleFisierului.class` în același folder.

---

### 3. Rulez clasa compilată

```bash
java NumeleFisierului
```

> ⚠️ Fără extensia `.class` — scrii doar numele clasei.

---

### 4. Clasa are `package`? Compilez din rădăcina proiectului

Dacă fișierul conține `package com.pao.laboratory00;`, trebuie să lucrezi **din folderul `src/`**:

```bash
cd src
javac com/pao/laboratory00/Main.java
java com.pao.laboratory00.Main
```

> Calea la compilare folosește `/` (sau `\` pe Windows), iar la rulare folosești `.` (puncte).

---

### 5. Compilez mai multe fișiere dintr-un pachet

```bash
javac com/pao/laboratory01/model/Dog.java com/pao/laboratory01/Main.java
```

Sau compilezi tot pachetul dintr-o dată:

```bash
javac com/pao/laboratory01/**/*.java
```

---

### 6. Trimit date de la tastatură (Scanner)

Rulezi normal cu `java ...` și scrii în terminal când programul așteaptă input, apoi apeși **Enter**.

---

### 7. Rezumat rapid

| Pas | Comandă |
|-----|---------|
| Verificare Java | `java -version` |
| Compilare (fără pachet) | `javac Main.java` |
| Rulare (fără pachet) | `java Main` |
| Compilare (cu pachet, din `src/`) | `javac com/pao/lab00/Main.java` |
| Rulare (cu pachet, din `src/`) | `java com.pao.lab00.Main` |

---

## Laboratoare

| Laborator | Subiect |
|-----------|---------|
| [laboratory00](src/com/pao/laboratory00/Readme.md) | Primul program, array-uri, Scanner |
| [laboratory01](src/com/pao/laboratory01/Readme.md) | Clase, încapsulare, Singleton, Comparator |
