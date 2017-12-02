import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class ParserBase
{
    public  int   linenum;
    Env env = new Env(null);


    Object CallProgram(Object main_decl)
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
        return "CallStmtCompound";
    }
    Object CallStmtExpr(Object expr_stmt)
    {
        return "CallStmtExpr";
    }
    Object CallStmtPrint(Object print_stmt)
    {
        return "CallStmtPrint";
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
        if(ID_type == expr)
            return "CallExprStmtIdAssignExpr "+ID+" "+expr;
        else
        {
            System.out.println("Error: try to assign wrong type to "+ID+" at line "+linenum);
            throw new Exception();
        }
    }
    Object CallCompoundStmtBegin()
    {
        return "CallCompoundStmtBegin";
    }
    Object CallCompoundStmtRest(Object begin, Object local_decls, Object stmt_list)
    {
        return "CallCompoundStmtRest";
    }
    Object CallLocalDeclsRec(Object local_decls, Object local_decl)
    {
        ((ArrayList<String>)local_decls).add((String)local_decl);
        return local_decls;
    }
    Object CallLocalDeclsEps()
    {
        return new ArrayList<String>();
    }
    Object CallLocalDecl(Object type_spec, String ID)
    {
        // You should re-write this function since this is a simplified version
        switch((String)type_spec)
        {
            case "CallTypeInt" : env.Put(ID, "int" ); break;
            case "CallTypeBool": env.Put(ID, "bool"); break;
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
    Object CallPrintExpr(String expr)
    {
        // dont need to ckeck semantic error here
        return "CallPrintExpr "+expr;
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
        else
        {
            System.out.println("Error: unsupported operation at line "+linenum);
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
        else
        {
            System.out.println("Error: unsupported operation at line "+linenum);
            throw new Exception();
        }
    }
    String CallExprParen(String expr)
    {
        return expr;
    }
    String CallExprId(String ID)
    {
        // 1. in symbol table, env[ID] has its type in string, such as "int" or "bool"
        // 2. so return env[ID]
        // You should re-write this function since this is a simplified version
        // For example, if ID is function name, you should show error message.
        return (String)(env.Get(ID));
    }
    String CallExprNum(String NUM)
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
}
