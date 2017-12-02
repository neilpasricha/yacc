
for ($i=1; $i -le 9; $i++)
{
 
  java SemanticChecker error/error0$i"a".foo;
}

for ($i=10; $i -le 17; $i++)
{
  "Error"+$i+"a.foo";
  java SemanticChecker error/error$i"a".foo;
}

"FINISHED ALL 'a' TESTS"
