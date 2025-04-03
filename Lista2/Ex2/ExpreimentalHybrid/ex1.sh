#!/bin/bash

n=10000
repeats=10
outfile="prog_threshold.csv"

echo "threshold;avg_cmp;avg_swap" > "$outfile"

for t in $(seq 5 5 50); do
    total_cmp=0
    total_swap=0

    for ((i = 1; i <= repeats; i++)); do
        data=$(java DataGenerator l $n)
        input=$(echo "$data" | sed "1a$t")

        output=$(echo "$input" | java Main)
        cmp=$(echo "$output" | grep "Compares" | awk '{print $2}')
        swap=$(echo "$output" | grep "Swaps" | awk '{print $2}')

        total_cmp=$((total_cmp + cmp))
        total_swap=$((total_swap + swap))
    done

    avg_cmp=$((total_cmp / repeats))
    avg_swap=$((total_swap / repeats))
    echo "$t;$avg_cmp;$avg_swap" >> "$outfile"
    echo "threshold=$t done (avg_cmp=$avg_cmp, avg_swap=$avg_swap)"
done
