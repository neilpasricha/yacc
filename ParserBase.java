import java.util.*;

/*
SEMINAR NOTES:
{$$=new List<String>():
    $$.add($1);} for param_list -> param

{List<str> t = (List<str>)$1);
    t.add($3); $$=t;








*/


@SuppressWarnings("unchecked")
public class ParserBase 
{
    public  int   linenum;
    Env env = new Env(null);
   public static ArrayList<Object> pList = new ArrayList();
   public static ArrayList<String> expList = new ArrayList();
   public HashMap<Object,Object> paramNum = new HashMap();
   public HashMap<Object, String> varsParams = new HashMap();
   public HashMap<Object,Object> actualParams = new HashMap();

    Object CallDeclList(Object decl_list, Object decl){
        ((ArrayList<Object>)decl_list).add(decl);
        return decl_list;
    }

    Object CallProgram(Object decl_list, Object main_decl)
    {
        return "CallProgram";
    }
    Object CallMain(Object compound_stmt)
    {
        return "CallMain";
    }
    Object CallStmtListRec(Object stmt_list, Object stmt)
    {
        ((ArrayList<Object>)stmt_list).add(stmt);
        return stmt_list;
    }
    Object CallStmtListEps()
    {
        return new ArrayList<Object>();
    }
    Object CallStmtCompound(Object compound_stmt)
    {   
        //System.out.println("CallStmtCompound: " +((String)compound_stmt));
        return compound_stmt;
    }
    Object CallStmtExpr(Object expr_stmt)
    {
        return "CallStmtExpr";
    }
    Object CallStmtPrint(Object print_stmt) throws Exception
    {
        

       // if(){
         //    System.out.println("Error: try to assign wrong type to "+ID+" at line "+linenum);
            //throw new Exception();
       // }
        String x = ((String)print_stmt);
         //System.out.println(x);
        if(x.equals("null")){
            
           System.out.println("Error(PRINT_STMT): try to assign wrong type to "+print_stmt+" at line "+linenum);
           throw new Exception();
            
        }
       
        
        return print_stmt;
    }

    Object CallStmtIf(Object if_stmt)throws Exception
    {
        String x = ((String)if_stmt);

        if(!x.equals("bool")){
            System.out.println("Error(CALL IF STMT): try to assign wrong type to "+if_stmt+" at line "+linenum);
            System.out.println((String)if_stmt);
            throw new Exception();
        }
        return if_stmt;
    }

    Object CallStmtWhile(Object while_stmt)throws Exception
    {

         String x = ((String)while_stmt);
        if(!x.equals("bool")){
            System.out.println("Error(CALL WHILE STMT): try to assign wrong type to "+while_stmt+" at line "+linenum);
            throw new Exception();
        }
        

        return "CallStmtWhile";
    }
    Object CallStmtReturn(Object return_stmt)
    {
        return "CallStmtReturn";
    }

    Object CallReturn()
    {
        return "CallStmtReturn";
    }

    Object CallArgsList(Object arg_list, String expr){
       // ((ArrayList<String>)arg_list).add(expr);
      /*  if(arg_list instanceof String){

            System.out.println("arg_list: " +arg_list + ";expr: " +expr);
            return expr;
        }
        else{
            ((ArrayList<Object>)arg_list).add(expr);
            System.out.println("arg_list: " +arg_list + ";expr: " +expr);
            return arg_list;
        }*/

        ((ArrayList<String>)arg_list).add(expr);
         return arg_list;

    }

    
    Object CallArgsListExpr(String expr){
      ArrayList<String> argz = new ArrayList();
        argz.add(expr);
        //System.out.println("CallParamListParam: " +(String)param);
        return argz;
    }

    Object CallArgs(Object arg_list){
        return arg_list;
    }

    Object CallExprStmtSemi(){
        return "semi";
    }

    /* Object CallParamList(Object param_list, Object param) {
        
       try{
            ((ArrayList<Object>)param_list).add(param);
            System.out.println("triedWorked; here is param_list: -- " +param_list);

            return param_list;
        }
        catch(Exception e){
            System.out.println("just a string");
        }
        ArrayList<Object> a = new ArrayList();
        a. add(param);

        System.out.println("DIDNOTWORK(param_list): Here is a: " + a);
        return a;
    }*/
//Use param_list as List<String>
     Object CallParamList(Object param_list, Object param) {
       /* if(param_list instanceof String){

           // System.out.println("param_list: " +param_list + ";param: " +param);
            return param;
        }
        else{
            ((ArrayList<Object>)param_list).add(param);
          //  System.out.println("param_list: " +param_list + ";param: " +param);
            return param_list;
        }*/

         ((ArrayList<String>)param_list).add((String)param);
         return param_list;

     }

    Object CallParamListParam(Object param){

        ArrayList<String> paramz = new ArrayList();
        paramz.add((String)param);
        //System.out.println("CallParamListParam: " +(String)param);
        return paramz;
    }

    Object CallParam(Object type_spec){
        //System.out.println("CallParam: " +type_spec);
        return type_spec;
    }

    Object CallWhileStmt(Object expr, Object stmt)throws Exception{
      //  System.out.println("While: expr: " +expr + "; stmt: " +stmt);
if(expr.equals("bool")){
    //System.out.println("fuck");
    return expr;
}
else{
    System.out.println("Error(CALL WHILE STMT2): Not a boolean in the if statement");
    throw new Exception();
}
    }

    Object CallIfStmt(Object expr, Object stmt1, Object stmt2)throws Exception{
        
//System.out.println("If: expr: " +expr + "; stmt1: " +stmt1 +"; stmt2: " +stmt2);
if(expr.equals("bool")){
   // System.out.println("fuck");
    return expr;
}
else{
    System.out.println("Error(CALL IF STMT2): Not a boolean in the if statement");
    throw new Exception();
}
        
    }

   

    /*
    Object CallIDArgsParens(String ID, Object args) throws Exception
    {
        // 1. expr will have expr type
        // 2. get type of ID
        // 3. compare the ID_type and expr_type
        // 4. if their types are same, then OK
        // 5. if different, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        String ID_type = (String)(env.Get(ID));
        if(ID_type == "args")
            return "CallIDArgsParens "+ID+" "+"args";
        else
        {
            System.out.println("Error: try to assign wrong type to "+ID+" at line "+linenum);
            throw new Exception();
        }
        //return args;
    }*/
    String CallIDParensArgs(String ID, Object args) throws Exception
    {
      // System.out.println("IDparensArgs test: ID: " +ID + "; args: " +args);
       //System.out.println("argsSize: " + ((ArrayList<Object>)args).size());

        if(paramNum.containsKey(ID)){
        //System.out.println("ID: " +ID +"; actualParams.get(ID): "+actualParams.get(ID));
        //String x = (String)((ArrayList<Object>)args.get(0));
       // System.out.println(actualParams.entrySet());

         if(((ArrayList<Object>)args).size()>0){
                Object x = ((ArrayList<Object>)args).get(0);
                String y = ((String)x);
                String z = ((String)actualParams.get(ID));
                if(!y.equals(z)){
                    System.out.println("Error(CALL ID PARENSARGS): Incorrect type of arguments for " +ID+ " at line "+linenum);
         throw new Exception();
                }
                
                
              }   

        else{
            actualParams.put(ID,"");
        }
        //System.out.println("FUCK: " +(ArrayList<Object>)args);
       //System.out.println("ParamNum: " +(int)paramNum.get(ID)+"argsSize: " + ((ArrayList<Object>)args).size());
       if((int)paramNum.get(ID)==((ArrayList<Object>)args).size()){
        
                return (String)(env.Get(ID));
        }
        else{
            System.out.println("Error(CALL ID PARENS ARGS part 2): Incorrect number of arguments for " +ID+ " at line "+linenum);
         throw new Exception();
        }
            
       }

        
    

    else{
        System.out.println("Undefined(CALLIDPARENS3) " +ID + " is used at line " +linenum);
         throw new Exception();
    }
}


    Object CallExprStmtIdAssignExpr(String ID, String expr) throws Exception
    {
        // 1. expr will have expr type
        // 2. get type of ID
        // 3. compare the ID_type and expr_type
        // 4. if their types are same, then OK
        // 5. if different, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        String ID_type = (String)(env.Get(ID));
        //System.out.println("testing: ID: " +ID +"; expr: "  );
       //System.out.println("exprtest: " +expr + "; ID: " +ID);
      //  System.out.println("ID: " +ID + "; Id_type: " + ID_type+"; expr: " +expr);
        varsParams.put(ID,"var");
        if(ID_type == expr)

            return "CallExprStmtIdAssignExpr "+ID+" "+expr;
        else
        {
            System.out.println("Error(313): try to assign wrong type to "+ID+" at line "+linenum);
            throw new Exception();
        }
    }

    /*Object CallExprStmtIdAssignExpr(String ID, String expr) throws Exception
    {
        // 1. expr will have expr type
        // 2. get type of ID
        // 3. compare the ID_type and expr_type
        // 4. if their types are same, then OK
        // 5. if different, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        return ID + expr;
    }*/


    Object CallCompoundStmtBegin()
    {
        env= new Env(env);
        return "CallCompoundStmtBegin";
    }
    Object CallCompoundStmtRest(Object begin, Object local_decls, Object stmt_list)
    {
        env=env.previous;
        return "stmt_list";
    }
    Object CallLocalDeclsRec(Object local_decls, Object local_decl)
    {
        ((ArrayList<String>)local_decls).add((String)local_decl);
        return local_decls;
    }
    Object CallLocalDeclsEps()
    {
        return new ArrayList<Object>();
    }
    Object CallLocalDecl(Object type_spec, String ID)
    {
        // You should re-write this function since this is a simplified version
        switch((String)type_spec)
        {
            case "CallTypeInt" : env.Put(ID, "int" ); break;
            case "CallTypeBool": env.Put(ID, "bool"); break;
             case "CallTypeFloat": env.Put(ID, "float"); break;
        }
        return "CallLocalDecl "+type_spec+" "+ID;
    }
    Object CallTypeInt()
    {
        return "CallTypeInt";
    }
    Object CallTypeBool()
    {
        return "CallTypeBool";
    }
    Object CallTypeFloat()
    {
        return "CallTypeFloat";
    }
    Object CallPrintExpr(String expr)
    {
        // dont need to ckeck semantic error here
       // System.out.println("print: " +expr);
        return expr;
    }
    


    String CallExprAdd(String expr1, String expr2) throws Exception
    {
        // 1. expr1 has type of expr1
        // 2. expr2 has type of expr2
        // 3. if both of their types are int, then (int + int)->int, return "int"
        // 4. if not, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        if(expr1 == "int" && expr2 == "int")
            return "int";
        if(expr1 == "float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "float" && expr2 == "int"){
            return "float";
        }

        if(expr1 == "int" && expr2 == "float"){
            return "float";
        }




        else
        {
            System.out.println("(ADD)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

String CallExprMod(String expr1, String expr2) throws Exception
    {
        // 1. expr1 has type of expr1
        // 2. expr2 has type of expr2
        // 3. if both of their types are int, then (int + int)->int, return "int"
        // 4. if not, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        if(expr1 == "int" && expr2 == "int")
            return "int";
        if(expr1 == "float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "float" && expr2 == "int"){
            return "float";
        }

        if(expr1 == "int" && expr2 == "float"){
            return "float";
        }


        else
        {
            System.out.println("(MOD)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }
String CallExprMinus(String expr1, String expr2) throws Exception
    {
        // 1. expr1 has type of expr1
        // 2. expr2 has type of expr2
        // 3. if both of their types are int, then (int + int)->int, return "int"
        // 4. if not, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        if(expr1 == "int" && expr2 == "int")
            return "int";
        if(expr1 == "float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "float" && expr2 == "int"){
            return "float";
        }

        if(expr1 == "int" && expr2 == "float"){
            return "float";
        }

        else
        {
            System.out.println("(MINUS)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }


String CallExprDiv(String expr1, String expr2) throws Exception
    {
        // 1. expr1 has type of expr1
        // 2. expr2 has type of expr2
        // 3. if both of their types are int, then (int + int)->int, return "int"
        // 4. if not, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        if(expr1 == "int" && expr2 == "int")
            return "int";
        if(expr1 == "float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "float" && expr2 == "int"){
            return "float";
        }


        if(expr1 == "int" && expr2 == "float"){
            return "float";
        }

        else
        {
            System.out.println("(DIV)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }
    String CallExprMul(String expr1, String expr2) throws Exception
    {
        // 1. expr1 has type of expr1
        // 2. expr2 has type of expr2
        // 3. if both of their types are int, then (int * int)->int, return "int"
        // 4. if not, then print error message, and throw exception to stop parsing
        // You should re-write this function since this is a simplified version
        if(expr1 == "int" && expr2 == "int")

            return "int";

        if(expr1 == "float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "float" && expr2 == "int"){
            return "float";
        }

        if(expr1 == "int" && expr2 == "float"){
            return "float";
        }

        else
        {
            System.out.println("(MUL)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }
    String CallExprParen(String expr) throws Exception
    {
        return expr;
    }

    String CallExprOr(String expr1, String expr2) throws Exception
    {
        if(expr1=="bool" && expr2 == "bool"){
            return "bool";
        }

        else
        {
            System.out.println("(OR)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

    String CallExprAnd(String expr1, String expr2) throws Exception
    {
        if(expr1=="bool" && expr2 == "bool"){
            return "bool";
        }

        else
        {
            System.out.println("(AND)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }
String CallExprNot(String expr1, String expr2) throws Exception
    {
        if(expr1=="bool" && expr2 == "bool"){
            return "bool";
        }

        else
        {
            System.out.println("(NOT)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

    String CallExprEQ(String expr1, String expr2) throws Exception
    {
        if(expr1=="int" && expr2 == "int"){
            return "int";
        }

        if(expr1=="float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "bool" && expr2=="bool"){
            return "bool";
        }


        else
        {
            System.out.println("(EQ)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

String CallExprNEQ(String expr1, String expr2) throws Exception
    {
        if(expr1=="int" && expr2 == "int"){
            return "int";
        }

        if(expr1=="float" && expr2 == "float"){
            return "float";
        }

        if(expr1 == "bool" && expr2=="bool"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "int"){
            return "float";
        }

        if(expr1=="int" && expr2 == "float"){
            return "float";
        }

        else
        {
            System.out.println("(NEQ)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

String CallExprGTHAN(String expr1, String expr2) throws Exception
    {
        if(expr1=="int" && expr2 == "int"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "float"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "int"){
            return "float";
        }

        if(expr1=="int" && expr2 == "float"){
            return "float";
        }
        


        else
        {
            System.out.println("(GTHAN)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

String CallExprLTHAN(String expr1, String expr2) throws Exception
    {
        if(expr1=="int" && expr2 == "int"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "float"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "int"){
            return "float";
        }

        if(expr1=="int" && expr2 == "float"){
            return "float";
        }
        


        else
        {
            System.out.println("(LTHAN)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }

String CallExprGEQ(String expr1, String expr2) throws Exception
    {
        if(expr1=="int" && expr2 == "int"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "float"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "int"){
            return "float";
        }

        if(expr1=="int" && expr2 == "float"){
            return "float";
        }
        


        else
        {
            System.out.println("(GEQ)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }
String CallExprLEQ(String expr1, String expr2) throws Exception
    {
        if(expr1=="int" && expr2 == "int"){
            return "bool";
        }

        if(expr1=="float" && expr2 == "float"){
            return "bool";
        }


        if(expr1=="float" && expr2 == "int"){
            return "float";
        }

        if(expr1=="int" && expr2 == "float"){
            return "float";
        }
        

        else
        {
            System.out.println("(LEQ)Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }



    String CallExprID(String ID) throws Exception
    {
        // 1. in symbol table, env[ID] has its type in string, such as "int" or "BOOL"
        // 2. so return env[ID]
        // You should re-write this function since this is a simplified version
        // For example, if ID is function name, you should show error message.

        //String x = (String)(env.Get(ID));
        if(env.Get(ID)==null){

         System.out.println("(ID)Error: Expression is invalid:  " +ID+ " at line "+linenum);
         throw new Exception();
        }

        return (String)(env.Get(ID));
    }
    String CallExprNum(Object num)
    {
        // in this semantic checker, you can return type of value

        return "int";
    }
    String CallExprTrue()
    {
        // in this semantic checker, you can return type of value
        return "bool";
    }
    String CallExprFalse()
    {
        // in this semantic checker, you can return type of value
        return "bool";
    }

    String CallExprReal(Object real){
        return "float";
    }

    Object CallArgsEps()
    {
        return new ArrayList<String>();
    }

    Object CallDeclListEps()
    {
        return new ArrayList<Object>();
    }
    Object CallParamsEps()
    {
        return new ArrayList<Object>();
    }

    Object CallDecl(Object fun_decl){
        return fun_decl;
    }

    Object CallFunDecl(Object type_spec, String ID, Object params)
    {
        // You should re-write this function since this is a simplified version
       // System.out.println("fuckNa type_spec: " + type_spec);
       //System.out.println("; ID: " + ID + "; params: " +params);
       // System.out.println("test: " + ((ArrayList<Object>)params).size());
        paramNum.put(ID,((ArrayList<Object>)params).size());

        //actualParams.put(ID, params);
        varsParams.put(ID,"func");
        //System.out.println(paramNum.entrySet());
        if(((ArrayList<Object>)params).size()>0){
                Object x = ((ArrayList<Object>)params).get(0);
                String y = ((String)x);
                if(y.equals("CallTypeInt")){
                    actualParams.put(ID, "int");
                }
               else if(y.equals("CallTypeBool")){
                    actualParams.put(ID, "bool");
                }
               else if(y.equals("CallTypeFloat")){
                    actualParams.put(ID, "float");
                }
               
              }   
        else{
            actualParams.put(ID,"");
        }

        switch((String)type_spec)
        {
            case "CallTypeInt" : env.Put(ID, "int" );  break;
            case "CallTypeBool": env.Put(ID, "bool"); break;
            case "CallTypeFloat": env.Put(ID, "float"); break;
        }
        //System.out.println(actualParams.entrySet());
        return "CallFunDecl "+type_spec+" "+ID+params;
    }
    Object CallParams(Object param_list){
        //System.out.println("CallParams:" +param_list);
        return param_list;
    }


}
