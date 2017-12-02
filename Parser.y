%{
import java.io.*;
%}
%left OR
%left AND
%right NOT
%left EQ NEQ LTHAN GTHAN GEQ LEQ
%left PLUS MINUS
%left MUL DIV MOD

%token <sval> NUM ID REAL
%token MAIN PRINT BEGIN END ELSE IF RETURN
%token LPAREN RPAREN SEMI 
%token INT BOOL FLOAT TRUE FALSE ASSIGN COMMA WHILE 
%token OR
%token AND
%token EQ NEQ LTHAN GTHAN GEQ LEQ
%token PLUS MINUS
%token MUL DIV MOD



%type <obj> program type_spec main_decl 
%type <obj> decl_list
%type <obj> decl local_decls 
%type <obj> local_decl fun_decl
%type <obj> param_list params
%type <obj> while_stmt if_stmt 
%type <obj> return_stmt 
%type <obj> arg_list args 
%type <obj> stmt_list stmt 
%type <obj> print_stmt 
%type <obj> compound_stmt expr_stmt
%type <obj> param
%type <sval> expr

%%

program       :  decl_list main_decl               { $$ = CallProgram($1,$2);                  }
              ;

decl_list     : decl_list decl {$$ = CallDeclList($1,$2);}
              |                 {$$ = CallDeclListEps();}
              ;
                //| eps{};
              

decl          : fun_decl {$$ = CallDecl($1);};

type_spec     : INT                              { $$ = CallTypeInt();                    }
              | BOOL                             { $$ = CallTypeBool();                   }
              | FLOAT                            { $$ = CallTypeFloat();}
              ;

main_decl     : MAIN LPAREN RPAREN compound_stmt { $$ = CallMain($4);                     }
              ;

fun_decl      : type_spec ID LPAREN params RPAREN SEMI {$$=CallFunDecl($1,$2,$4);};

params         : param_list {$$ = CallParams($1);}
                |          {$$ = CallParamsEps();};

param_list      : param_list COMMA param {$$=CallParamList($1,$3);}
                | param {$$=CallParamListParam($1);}
                ;

param         : type_spec{$$=CallParams($1);};


stmt_list     : stmt_list stmt                   { $$ = CallStmtListRec($1, $2);          }
              |                                  { $$ = CallStmtListEps();                }
              ;

stmt          : compound_stmt                    { $$ = CallStmtCompound($1);             }
              | expr_stmt                        { $$ = CallStmtExpr($1);                 }
              | print_stmt                       { $$ = CallStmtPrint($1);                }
              | if_stmt                           { $$ = CallStmtIf($1);}
              | while_stmt                        { $$ = CallStmtWhile($1);}
              | return_stmt                        { $$ = CallStmtReturn($1);}
              ;


print_stmt    : PRINT expr SEMI                  { $$ = CallPrintExpr($2);                }
              ;

expr_stmt     : ID ASSIGN expr SEMI              { $$ = CallExprStmtIdAssignExpr($1, $3); }
              | SEMI                             { $$ = CallExprStmtSemi();}
              ;

while_stmt      : WHILE LPAREN expr RPAREN stmt{$$ = CallWhileStmt($3,$5);};


compound_stmt : BEGIN                            { $$ = CallCompoundStmtBegin();          } /* (1) */
                      local_decls stmt_list END  { $$ = CallCompoundStmtRest($2, $3, $4); } /* $2 represents result from (1), $3 represents local_decls, and $4 represents stmt_list */
              ;

local_decls   : local_decls local_decl           { $$ = CallLocalDeclsRec($1, $2);        }
              |                                  { $$ = CallLocalDeclsEps();              }
              ;
local_decl    : type_spec ID SEMI                { $$ = CallLocalDecl($1, $2);            }
              ;

if_stmt       : IF LPAREN expr RPAREN stmt ELSE stmt{$$ = CallIfStmt($3,$5,$7);};


return_stmt     : RETURN SEMI {$$= CallReturn();};

arg_list        : arg_list COMMA expr {$$ = CallArgsList($1,$3);}
                | expr {$$ = CallArgsListExpr($1);}
                ;

args            : arg_list {$$= CallArgs($1);}
                |           {$$ = CallArgsEps();}
                ;
            
expr          : expr PLUS expr                   { $$ = CallExprAdd($1, $3);              }
              | expr MINUS expr                   { $$ = CallExprMinus($1,$3);}
              | expr MUL expr                    { $$ = CallExprMul($1, $3);              }
              | expr DIV expr                   { $$ = CallExprDiv($1, $3);              }
              | expr MOD expr                   { $$ = CallExprMod($1, $3);              }
              | LPAREN expr RPAREN               { $$ = CallExprParen($2);                }
              | ID                               { $$ = CallExprID($1);                   }
              | NUM                              { $$ = CallExprNum($1);                  }
              | TRUE                             { $$ = CallExprTrue();                   }
              | FALSE                            { $$ = CallExprFalse();                  }
              |  expr OR expr                   { $$ = CallExprOr($1, $3); }
              |  expr AND expr                   { $$ = CallExprAnd($1, $3); }
              |  expr NOT expr                   { $$ = CallExprNot($1, $3); }
              |  expr EQ expr                   { $$ = CallExprEQ($1, $3); }
              |  expr NEQ expr                   { $$ = CallExprNEQ($1, $3); }
              |  expr LTHAN expr                   { $$ = CallExprLTHAN($1, $3); }
              |  expr GTHAN expr                   { $$ = CallExprGTHAN($1, $3); }
              |  expr GEQ expr                   { $$ = CallExprGEQ($1, $3); }
              |  expr LEQ expr                   { $$ = CallExprLEQ($1, $3); }
              |  ID LPAREN args RPAREN            { $$ = CallIDParensArgs($1,$3);}
              | REAL                              { $$ = CallExprReal($1);}
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
