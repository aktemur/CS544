#!/bin/bash

if [ "$#" -lt 1 ]; then
    echo "Usage: $0 '<.ll file>'" >&2
    exit 1
fi

filename=$1

opt -dot-cfg $filename
dot -Tpdf cfg."${filename%.*}".dot -o "${filename%.*}".pdf
rm *.dot
