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
"bool"        { yyparser.linenum = linenum;                                            return Parser.BOOL  ; }
"print"       { yyparser.linenum = linenum;                                            return Parser.PRINT ; }
"true"        { yyparser.linenum = linenum;                                            return Parser.TRUE  ; }
"false"       { yyparser.linenum = linenum;                                            return Parser.FALSE ; }
"+"           { yyparser.linenum = linenum;                                            return Parser.PLUS  ; }
"*"           { yyparser.linenum = linenum;                                            return Parser.MUL   ; }
"="           { yyparser.linenum = linenum;                                            return Parser.ASSIGN; }
{number}      { yyparser.linenum = linenum; yyparser.yylval = new ParserVal(yytext()); return Parser.NUM   ; }
{real}        { yyparser.linenum = linenum; yyparser.yylval = new ParserVal(yytext()); return Parser.REAL  ; }
{identifier}  { yyparser.linenum = linenum; yyparser.yylval = new ParserVal(yytext()); return Parser.ID    ; }
{comment}     {            /* skip */ }
{newline}     { linenum++; /* skip */ }
{whitespace}  {            /* skip */ }

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    {} /*{ System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }*/
