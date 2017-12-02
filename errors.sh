#!/usr/bin/env bash
rm -rf *class
rm -rf Lex.java
echo creating lexer...
nohup java -jar jflex-1.6.1.jar Lexer.flex 
echo compiling....
./yacc.linux -Jthrows="Exception" -Jextends=ParserBase -Jnorun -J ./Parser.y
javac *.java -Xdiags:verbose
echo running...
count=1
rm output1.txt
rm output2.txt
if [ $# -eq 1 ]; then
    for out in ./error/*
    do
    if [ $(( $count % 2)) -eq 0 ]; then
        echo running test $out >> output2.txt
        cat $out >> output2.txt
    else
        echo running test $out >> output1.txt
        java SemanticChecker $out >> output1.txt
        
fi
    ((count ++))
    done
    diff -y output1.txt output2.txt
else
 java SemanticChecker $2
fi