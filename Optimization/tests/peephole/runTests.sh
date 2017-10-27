#!/bin/bash

for name in peephole*.ll
do
    filename="${name%.*}"
    echo "==== Peephole test $filename ===="
    opt -load ../../build/peephole/libCS544Peephole.so -cs544peephole -S "$filename".ll | tail -n +4 > opt_"$filename".ll
    diff "$filename".ll opt_"$filename".ll
    echo "++++++++++++++++++++++++++"
    echo ""
done


