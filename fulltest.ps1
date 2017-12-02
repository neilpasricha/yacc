count=1
rm output1.txt
rm output2.txt
if [ $# -eq 1 ]; then
    for out in ../testcase/$1/*
    do
    if [ $(( $count % 2)) -eq 0 ]; then
        echo running test $out >> output1.txt
        java SemanticChecker $out >> output1.txt
    else
        echo running test $out >> output2.txt
        cat $out >> output2.txt
fi
    ((count ++))
    done
    diff -y output1.txt output2.txt
else
 java SemanticChecker $2
fi