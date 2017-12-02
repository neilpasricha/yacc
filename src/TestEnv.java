public class TestEnv {
    public static int total = 0;
    public static void Assert(boolean condition, String testname)
    {
        if(condition)
        {
            total += 5;
            System.out.println("Succeed (5 points) in " + testname);
        }
        else
            System.out.println("Fail (0 point) in " + testname);
    }

    public static void main(String[] args) throws Exception
    {
        Env B0 = new Env(null);
        Object INT   = "int";
        Object FLOAT = "float";
        Object BOOL  = "bool";
        total = 0;

        System.out.println("symbol table B0 test");
        Assert(B0.Get("w") == null,"determining that the symbol 'w' is not in symbol table");

        // add ('abc', 1)
        B0.Put("w", INT);
        Assert(B0.Get("w") == INT ,"finding the    int symbol 'w' in symbol table");

        System.out.println();
        System.out.println("symbol table B1 test");
        Env B1 = new Env(B0);
        B1.Put("x", INT);
        B1.Put("y", INT);
        Assert(B1.Get("w") == INT ,"finding the   int symbol 'w' in symbol table");
        Assert(B1.Get("x") == INT ,"finding the   int symbol 'x' in symbol table");
        Assert(B1.Get("y") == INT ,"finding the   int symbol 'y' in symbol table");
        Assert(B1.Get("z") == null,"determining that the symbol 'z' is not in symbol table");

        System.out.println();
        System.out.println("symbol table B2 test");
        Env B2 = new Env(B1);
        B1.Put("w", FLOAT);
        B1.Put("y", BOOL );
        B1.Put("z", INT  );
        Assert(B1.Get("w") == FLOAT,"finding the float symbol 'w' in symbol table");
        Assert(B1.Get("x") == INT  ,"finding the   int symbol 'x' in symbol table");
        Assert(B1.Get("y") == BOOL ,"finding the  bool symbol 'y' in symbol table");
        Assert(B1.Get("z") == INT  ,"finding the   int symbol 'z' in symbol table");

        System.out.println();
        System.out.println("You will get "+total+" points.");
    }
}
