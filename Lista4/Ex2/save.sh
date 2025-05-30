#!/bin/bash

signs=("r" "l")

echo "Test,sign,n,avg_cmp,max_cmp,avg_point,max_point,avg_height,max_height"

for sign in "${signs[@]}"
do
  for (( n=10000; n<=100000; n+=10000 ))
  do
    total_avg_cmp=0
    total_max_cmp=0
    total_avg_point=0
    total_max_point=0
    total_avg_height=0
    total_max_height=0

    for (( i=1; i<=3; i++ ))
    do
      output=$(java -Xss16m DataGenerator "$sign" "$n" | java -Xss16m PrinterTest)

      # Porównania
      cmps=($(echo "$output" | grep -oP 'Porównania: \K[0-9]+'))
      sum_cmp=0
      max_cmp=0
      for c in "${cmps[@]}"; do
        sum_cmp=$((sum_cmp + c))
        if (( c > max_cmp )); then max_cmp=$c; fi
      done
      count_cmp=${#cmps[@]}
      avg_cmp=0
      if (( count_cmp > 0 )); then
        avg_cmp=$((sum_cmp / count_cmp))
      fi

      # Operacje na wskaźnikach
      points=($(echo "$output" | grep -oP 'Operacje na wskaźnikach: \K[0-9]+'))
      sum_point=0
      max_point=0
      for p in "${points[@]}"; do
        sum_point=$((sum_point + p))
        if (( p > max_point )); then max_point=$p; fi
      done
      count_point=${#points[@]}
      avg_point=0
      if (( count_point > 0 )); then
        avg_point=$((sum_point / count_point))
      fi

      # Wysokość drzewa
      heights=($(echo "$output" | grep -oP 'Wysokość drzewa: \K[0-9]+'))
      sum_height=0
      max_height=0
      for h in "${heights[@]}"; do
        sum_height=$((sum_height + h))
        if (( h > max_height )); then max_height=$h; fi
      done
      count_height=${#heights[@]}
      avg_height=0
      if (( count_height > 0 )); then
        avg_height=$((sum_height / count_height))
      fi

      # Sumujemy wartości z kolejnych powtórzeń
      total_avg_cmp=$((total_avg_cmp + avg_cmp))
      if (( max_cmp > total_max_cmp )); then total_max_cmp=$max_cmp; fi

      total_avg_point=$((total_avg_point + avg_point))
      if (( max_point > total_max_point )); then total_max_point=$max_point; fi

      total_avg_height=$((total_avg_height + avg_height))
      if (( max_height > total_max_height )); then total_max_height=$max_height; fi
    done

    # Obliczamy średnią z 20 powtórzeń
    total_avg_cmp=$((total_avg_cmp / 3))
    total_avg_point=$((total_avg_point / 3))
    total_avg_height=$((total_avg_height / 3))

    echo "$sign;$n;$total_avg_cmp;$total_max_cmp;$total_avg_point;$total_max_point;$total_avg_height;$total_max_height"
  done
done
