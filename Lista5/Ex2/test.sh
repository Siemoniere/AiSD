#!/bin/bash

# Pętla dla wartości n od 0 do 1000, co 25
for n in $(seq 25 25 7500); do
    # Uruchamiamy proces Graph z parametrem n, przekazujemy do Prim, a potem do Tree
    result=$(java Graph $n | java Prim | java Tree)
    
    # Debugowanie: Wypisz wynik przed przetworzeniem
    # echo "$result"
    
    # Zbieramy interesujące dane: Najlepszy, Najgorszy, Średni max round
    najlepszy=$(echo "$result" | grep -oP 'Najlepszy \(najmniejszy\) max round: \K[0-9]+')
    najgorszy=$(echo "$result" | grep -oP 'Najgorszy \(największy\) max round: \K[0-9]+')
    sredni=$(echo "$result" | grep -oP 'Średni max round: \K[0-9.]+')

    # Wypisujemy wynik w formacie: n;najlepszy;najgorszy;średni
    echo "$n;$najlepszy;$najgorszy;$sredni"
done
