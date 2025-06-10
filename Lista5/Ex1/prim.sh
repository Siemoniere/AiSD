#!/bin/bash
for n in $(seq 0 25 5000); do
    sum=0
    count=0
    for i in {1..5}; do
        execution_time=$(java Graph $n | java Prim 2>&1 | grep -oP "Execution time: \K[\d.]+")
        if [[ -n "$execution_time" ]]; then
            sum=$(echo "$sum + $execution_time" | bc)
            count=$((count+1))
        fi
    done
    if [[ $count -gt 0 ]]; then
        avg=$(echo "scale=4; $sum / $count" | bc)
        echo "$n ; $avg"
    else
        echo "$n ;"
    fi
done