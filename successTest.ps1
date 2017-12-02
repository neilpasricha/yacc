$output;
$succCount=0;
$errCount=0;
for ($i=1; $i -le 9; $i++)
{
"Success"+$i+".foo:";
  $output=java SemanticChecker success/success0$i.foo;
  if($output -eq "success"){
  "fuck yea";
  $succCount=$succCount+1;
  }
  else{
  $output;
  $errCount=$errCount+1;
  }
  " ";
}

for ($i=10; $i -le 17; $i++)
{
  "Success"+$i+".foo";
  $output=java SemanticChecker success/success$i.foo;
  if($output -eq "success"){
  "fuck yea";
  $succCount=$succCount+1;
  }
  else{
  $output;
  $errCount=$errCount+1;
  }
  " ";
}

"Total success: " +$succCount;
"Total errors: " + $errCount;