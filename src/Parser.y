%{
import java.io.*;
%}

%left PLUS
%left MUL

%token <sval> NUM ID REAL
%token MAIN PRINT BEGIN END
%token LPAREN RPAREN PLUS MUL SEMI INT BOOL TRUE FALSE ASSIGN

%type <obj> program type_spec main_decl
%type <obj> local_decls local_decl
%type <obj> stmt_list stmt print_stmt compound_stmt expr_stmt
%type <sval> expr

%%

program       : main_decl                        { $$ = CallProgram($1);                  }
              ;
main_decl     : MAIN LPAREN RPAREN compound_stmt { $$ = CallMain($4);                     }
              ;
stmt_list     : stmt_list stmt                   { $$ = CallStmtListRec($1, $2);          }
              |                                  { $$ = CallStmtListEps();                }
              ;
stmt          : compound_stmt                    { $$ = CallStmtCompound($1);             }
              | expr_stmt                        { $$ = CallStmtExpr($1);                 }
              | print_stmt                       { $$ = CallStmtPrint($1);                }
              ;
expr_stmt     : ID ASSIGN expr SEMI              { $$ = CallExprStmtIdAssignExpr($1, $3); }
              | SEMI                             { }
              ;
compound_stmt : BEGIN                            { $$ = CallCompoundStmtBegin();          } /* (1) */
                      local_decls stmt_list END  { $$ = CallCompoundStmtRest($2, $3, $4); } /* $2 represents result from (1), $3 represents local_decls, and $4 represents stmt_list */
              ;
local_decls   : local_decls local_decl           { $$ = CallLocalDeclsRec($1, $2);        }
              |                                  { $$ = CallLocalDeclsEps();              }
              ;
local_decl    : type_spec ID SEMI                { $$ = CallLocalDecl($1, $2);            }
              ;
type_spec     : INT                              { $$ = CallTypeInt();                    }
              | BOOL                             { $$ = CallTypeBool();                   }
              ;
print_stmt    : PRINT expr SEMI                  { $$ = CallPrintExpr($2);                }
              ;
expr          : expr PLUS expr                   { $$ = CallExprAdd($1, $3);              }
              | expr MUL expr                    { $$ = CallExprMul($1, $3);              }
              | LPAREN expr RPAREN               { $$ = CallExprParen($2);                }
              | ID                               { $$ = CallExprId($1);                   }
              | NUM                              { $$ = CallExprNum($1);                  }
              | TRUE                             { $$ = CallExprTrue();                   }
              | FALSE                            { $$ = CallExprFalse();                  }
              ;

%%
private Lexer lexer;

private int yylex () {
    int yyl_return = -1;
    try {
        yylval = new ParserVal(0);
        yyl_return = lexer.yylex();
    }
    catch (IOException e) {
        System.err.println("IO error :"+e);
    }
    return yyl_return;
}

public void yyerror (String error) {
    System.out.println("Error: " + error);
}

public Parser(Reader r) {
    lexer = new Lexer(r, this);
}
