sapper
======
- (done) Board Generation
- (done) Simply GUI project
- (done) Simply Saper game
- (done) HomeScreen
- (in progress) Save/Load game
- (done) Flag a field
- (done) Lose event
- (done) Mine and Time counters
- (in progress) Users
- (in progress) Game stats

TO DO
- Trophes

======
// Koło 26.11.2013
rozdzielić pakiety
refaktoryzacja kodu
nieużywane konstruktory
klasa powinna mieć jedną przycznkę do zmiany!!!
Board może przyjmować jako parametr generator lub zadane wartości
w kl Bridge: funkcja changeFieldFlagStatus();
GuiOptions: WTF! nieczytelne
Gettery i Settery i w ogóle cokolwiek piszemy tylko i wyłącznie wtedy, kiedy tego potrzebujemy.

Diagram programu
Rozbić Boarda i w ogóle inne klasy na mniejsze kawałki.
Board powinien przechowywać tylko stan planszy gry, łącznie z setterami i getterami.
Nie optymalizować programu na początku, bo później może to ograniczać rozwój programu.
Board zrefaktorować tak, żeby  Board był odpowiedzialny za Board i nic więcej.
Stworzyć nową klasę Generator do Boarda.

Field rozbić na trzy klasy: mina, nie-mina-pusta, nie-mina-pełna
Np: Field będzie interfejsem, który będzie implementowany przez trzy powyższe klasy.
W Field można wrzucić metodę click(), która zwraca enuma: np Mine lub liczbę min (jak w Bridge'u).

**********
GUI:
Options wrzucić do modela(z MVC) (Board i te inne) do np klasy Settings, która będzie przechowywała parametry gry.
Zastanowić się, czy Settings powinny być przekazywane przez Bridge'a.

Controler - odpowiedzialny za przygotowanie danych przekazywanych do widoku.
Wskazówka: jak najwięcej logiki powinno być w Modelu. W Controllerze jak najmniej.

Zadanie na 3. grudnia:
1. Refaktoryzacja modelu i controllera, jeśli starczy czasu to gui, ale tylko jeśli starczy czasu.
2. Wszystko powinno być przetestowane (testy jednostkowe). -> wykorzystać mocka (?) jeśli się uda, biblioteka mockito
3. Poczytać trochę o Springu.
4. Ktoś robi commit -> co najmniej 2 code review -> dopiero wtedy Łukasz wrzuca na mastera















