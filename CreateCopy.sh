#!/bin/bash

#Source URL variables
arr[0]="399"
arr[1]="42"
arr[2]="40"

for i in {1..100}
do
j=$(($i % 10))
if [ $j -eq 0 ]
then
        j=10
fi
#j=$((i-k))
size=${#arr[@]}
index=$(($RANDOM % $size))
sed -e 's/$1/c'"$j"'/' -e 's/$2/c'"$i"'/' -e 's/$3/C'"$i"'/' -e 's/$4/'"${arr[$index]}"'/' args_service.json.org > args_service_$i.json
done
