# Zadanie

Do realizacji kart CRC można użyć kreatora online (można również tradycyjnie zrobić to
na kartkach). W przypadku UML można analogicznie skorzystać z kartki lub wykorzystać
edytor online lub oprogramowanie UMLet.
### 1. (CRC) Pewna firma poprosiła Cię o stworzenie modułu walki do ich najnowszej gry
### RPG. Twoim zadaniem jest przedstawienie modelu obiektowego takiego modułu.</br>
1. Przyjmij, że gra rozgrywa się w pewnej krainie, po której poruszają się dwie
postaci.</br>
2. W dowolnej chwili między dwiema postaciami może rozpocząć się walka (załóżmy,
że walka rozgrywana jest w systemie turowym). Zadawane obrażenia zależne są
od siły obu postaci. Wynik walki wpływa na doświadczenie obu postaci.</br>
⋅⋅* Każda postać ma swoją broń (jedną). Zakładamy, że każda broń może zadać
pewną ilość obrażeń. Rzeczywiste obrażenia zależne są jednak również od siły obu
postaci, ale też od elementu losowego - wartości wyrzuconej tradycyjną kostką.
Dodatkowo, każda postać ma szereg przedmiotów, m.in. takich, które może użyć
w swojej obronie (zbroja, tarcza).</br>
3. Obrażenia odniesione przez jedną z postaci można obliczyć z następującego wzoru:  
      dmgp1 = powerp2 ∗ random ∗ weaponp2 − armorp1 − shieldp1 (1)  
      dmgp1 - otrzymane obrażenia (w tym przypadku przez gracza 1)  
      powerp2 - siła gracza 2  
      random - współczynnik losowy  
      weaponp2 - siła broni gracza 2  
      armorp1 - zbroja gracza 1  
      shieldp1 - tarcza gracza 1.
4. Porządna gra RPG nie może też istnieć bez magii. Dlatego, oprócz walki na
broń, możliwa jest walka na czary. W przypadku walki na czary, konkretny czar
użyty w danym ruchu losowany jest przy użyciu kostki k20. Dostępność czarów
uzależniona jest od poziomu many danej postaci. Oczywiście poszczególne czary
różnią się swoją mocą.</br>
#### 2. (UML) Proszę przemyśleć i stworzyć ogólny diagram klas, który reprezentuje funkcjonalność modułu z Zadania 1.  
Należy kolejno:  
**(a)** Znaleźć możliwie dużo uogólnionych reprezentacji obiektów w systemie (aby potem można było zastosować mechanizmy dziedziczenia    itp.).    
**(b)** Zapisać metody, które powinny być implementowane przez dane klasy.  
**(c)** Wprowadzić stosowne powiązania pomiędzy klasami.
