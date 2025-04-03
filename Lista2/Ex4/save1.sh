#!/bin/bash

plik="wyniki1.csv"
echo "k;n;cmp;swap" > $plik

for k in 1 10 100; do
    for n in 10 20 30 40 50; do
        if [ $k -eq 1 ]; then
            output=$(java DataGenerator l $n | java Main)
            cmp=$(echo "$output" | grep "Compares" | awk '{print $2}')
            swap=$(echo "$output" | grep "Swaps" | awk '{print $2}')
            echo "$k;$n;$cmp;$swap" >> $plik
        else
            total_cmp=0
            total_swap=0
            for i in $(seq 1 $k); do
                output=$(java DataGenerator l $n | java Main)
                cmp=$(echo "$output" | grep "Compares" | awk '{print $2}')
                swap=$(echo "$output" | grep "Swaps" | awk '{print $2}')
                total_cmp=$((total_cmp + cmp))
                total_swap=$((total_swap + swap))
            done
            avg_cmp=$((total_cmp / k))
            avg_swap=$((total_swap / k))
            echo "$k;$n;$avg_cmp;$avg_swap" >> $plik
        fi
        echo "$k $n"
    done
done
