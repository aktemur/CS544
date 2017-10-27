#!/bin/bash

for name in licm*.ll
do
    filename="${name%.*}"
    echo "==== LICM test $filename ===="
    opt -load ../../build/licm/libCS544LICM.so -cs544licm -S "$filename".ll | tail -n +4 > opt_"$filename".ll
    diff "$filename".ll opt_"$filename".ll
    echo "++++++++++++++++++++++++++"
    echo ""
done


