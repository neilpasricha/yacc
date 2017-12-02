public class SemanticChecker {
	public static void main(String[] args) throws Exception
    {
        // The following shows a case of checking semantic error, and printing its proper error message.
        
      /*  ("main()\n"                    // line 1
        +"{\n"                         // line 2
        +"    bool x;\n"               // line 3 : add x into symbol table env
        +"    int  y;\n"               // line 4 : add y into symbol table env
        +"    y = y + 2 * ( 3 + 4 );\n"// line 5 : calculate y
        +"    x = y;\n"                // line 6 : assign int y into bool x <= error
        +"}"                           // line 7
        );*/
        if(args.length <= 0)
           return;
        String foopath = args[0];
        java.io.Reader r = new java.io.FileReader(foopath);

        Parser yyparser = new Parser(r);
        
            if (yyparser.yyparse() == 0)
                System.out.println("Success");
        
        
        
	}
}
