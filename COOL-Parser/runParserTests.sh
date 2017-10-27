#!/bin/bash


mkdir -p parserOutputs

FILES=`ls parserInputs/*.cl`

for file in $FILES
do
    base=`basename $file`
    echo "# "$base
    java -cp ./out/production/CS544-COOL-Parser/:./libs/obf-scanner.jar:$CLASSPATH cool.Main parserInputs/$base &> parserOutputs/$base
    echo "--------------"
    cat parserInputs/$base
    echo "--------------"
    cat parserOutputs/$base
    echo "==============================================="
done

