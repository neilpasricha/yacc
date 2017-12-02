/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%class Lexer
%byaccj
%int

%{

  public Parser   yyparser;
  public int      linenum = 1;

  public Lexer(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

digit      = [0-9]
number     = {digit}+
real       = {number}("."{number})?(E[+-]?{number})?
identifier = [A-Za-z_][A-Za-z0-9_]*
newline    = \n
whitespace = [ \t\r]+
comment    = "//".*

%%

"main"        { yyparser.linenum = linenum;                                            return Parser.MAIN  ; }
"("           { yyparser.linenum = linenum;                                            return Parser.LPAREN; }
")"           { yyparser.linenum = linenum;                                            return Parser.RPAREN; }
"{"           { yyparser.linenum = linenum;                                            return Parser.BEGIN ; }
"}"           { yyparser.linenum = linenum;                                            return Parser.END   ; }
";"           { yyparser.linenum = linenum;                                            return Parser.SEMI  ; }
"int"         { yyparser.linenum = linenum;                                            return Parser.INT   ; }
"float"         { yyparser.linenum = linenum;                                            return Parser.FLOAT   ; }
"bool"        { yyparser.linenum = linenum;                                            return Parser.BOOL  ; }
"print"       { yyparser.linenum = linenum;                                            return Parser.PRINT ; }
"true"        { yyparser.linenum = linenum;                                            return Parser.TRUE  ; }
"false"       { yyparser.linenum = linenum;                                            return Parser.FALSE ; }
"+"           { yyparser.linenum = linenum;                                            return Parser.PLUS  ; }
"-"           { yyparser.linenum = linenum;                                            return Parser.MINUS  ; }
"*"           { yyparser.linenum = linenum;                                            return Parser.MUL   ; }
"="           { yyparser.linenum = linenum;                                            return Parser.ASSIGN; }
"=="           { yyparser.linenum = linenum;                                            return Parser.EQ; }
"!="           { yyparser.linenum = linenum;                                            return Parser.NEQ; }
">"           { yyparser.linenum = linenum;                                            return Parser.GTHAN; }
"<"           { yyparser.linenum = linenum;                                            return Parser.LTHAN; }
">="           { yyparser.linenum = linenum;                                            return Parser.GEQ; }
"<="           { yyparser.linenum = linenum;                                            return Parser.LEQ; }
","           { yyparser.linenum = linenum;                                            return Parser.COMMA; }
"("           { yyparser.linenum = linenum;                                            return Parser.LPAREN; }
")"           { yyparser.linenum = linenum;                                            return Parser.RPAREN; }

"return"           { yyparser.linenum = linenum;                                            return Parser.RETURN; }

"while"           { yyparser.linenum = linenum;                                            return Parser.WHILE; }

"or"           { yyparser.linenum = linenum;                                            return Parser.OR; }

"and"           { yyparser.linenum = linenum;                                            return Parser.AND; }

"/"           { yyparser.linenum = linenum;                                            return Parser.DIV; }

"%"           { yyparser.linenum = linenum;                                            return Parser.MOD; }

"not"           { yyparser.linenum = linenum;                                            return Parser.NOT; }

"if"           { yyparser.linenum = linenum;                                            return Parser.IF; }

"else"           { yyparser.linenum = linenum;                                            return Parser.ELSE; }

"NUM"           { yyparser.linenum = linenum;                                            return Parser.NUM; }

"REAL"           { yyparser.linenum = linenum;                                            return Parser.REAL; }

{number}      { yyparser.linenum = linenum; yyparser.yylval = new ParserVal(yytext()); return Parser.NUM   ; }
{real}        { yyparser.linenum = linenum; yyparser.yylval = new ParserVal(yytext()); return Parser.REAL  ; }
{identifier}  { yyparser.linenum = linenum; yyparser.yylval = new ParserVal(yytext()); return Parser.ID    ; }
{comment}     {            /* skip */ }
{newline}     { linenum++; /* skip */ }
{whitespace}  {            /* skip */ }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    {} /*{ System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }*/
