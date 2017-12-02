//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 1 "./Parser.y"

import java.io.*;
//#line 20 "Parser.java"




public class Parser
             extends ParserBase
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short OR=257;
public final static short AND=258;
public final static short NOT=259;
public final static short EQ=260;
public final static short NEQ=261;
public final static short LTHAN=262;
public final static short GTHAN=263;
public final static short GEQ=264;
public final static short LEQ=265;
public final static short PLUS=266;
public final static short MINUS=267;
public final static short MUL=268;
public final static short DIV=269;
public final static short MOD=270;
public final static short NUM=271;
public final static short ID=272;
public final static short REAL=273;
public final static short MAIN=274;
public final static short PRINT=275;
public final static short BEGIN=276;
public final static short END=277;
public final static short ELSE=278;
public final static short IF=279;
public final static short RETURN=280;
public final static short LPAREN=281;
public final static short RPAREN=282;
public final static short SEMI=283;
public final static short INT=284;
public final static short BOOL=285;
public final static short FLOAT=286;
public final static short TRUE=287;
public final static short FALSE=288;
public final static short ASSIGN=289;
public final static short COMMA=290;
public final static short WHILE=291;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    3,    3,    4,    1,    1,    1,    2,    7,    9,
    9,    8,    8,   20,   15,   15,   16,   16,   16,   16,
   16,   16,   17,   19,   19,   10,   22,   18,    5,    5,
    6,   11,   12,   13,   13,   14,   14,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   21,   21,
};
final static short yylen[] = {                            2,
    2,    2,    0,    1,    1,    1,    1,    4,    6,    1,
    0,    3,    1,    1,    2,    0,    1,    1,    1,    1,
    1,    1,    3,    4,    1,    5,    0,    5,    2,    0,
    3,    7,    2,    3,    1,    1,    0,    3,    3,    3,
    3,    3,    3,    1,    1,    1,    1,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    4,    1,
};
final static short yydefred[] = {                         3,
    0,    0,    0,    5,    6,    7,    0,    1,    2,    4,
    0,    0,    0,    0,   27,    8,   14,    0,    0,   13,
   30,    0,    0,    0,   12,    9,    0,   29,    0,    0,
    0,    0,   28,    0,    0,   25,    0,   21,   20,   22,
   15,   19,   17,   18,   31,    0,   45,    0,   58,    0,
   46,   47,    0,    0,   33,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   23,    0,    0,   24,    0,    0,    0,
   43,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   40,   41,   42,    0,    0,    0,   57,    0,
   26,    0,    0,   32,
};
final static short yydgoto[] = {                          1,
   17,    8,    2,    9,   24,   28,   10,   18,   19,   38,
   39,   40,   78,   79,   29,   41,   42,   43,   44,   20,
   53,   21,
};
final static short yysindex[] = {                         0,
    0, -228, -276,    0,    0,    0, -270,    0,    0,    0,
 -237, -226, -180, -242,    0,    0,    0, -190, -178,    0,
    0, -242, -153, -242,    0,    0, -137,    0, -186, -152,
 -146,  -58,    0, -136, -139,    0, -135,    0,    0,    0,
    0,    0,    0,    0,    0,  -58,    0, -133,    0,  -58,
    0,    0,  -21,  -58,    0,  -58,    6,  -58,   33,  -58,
  -58,  -58,  -58,  -58,  -58,  -58,  -58,  -58,  -58,  -58,
  -58,  -58,  -58,    0,   59,   85,    0, -122, -113,  121,
    0,  134,   -9,   -9, -219, -219, -219, -219, -219, -219,
 -131, -131,    0,    0,    0, -102, -102,  -58,    0, -106,
    0,  121, -102,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0, -111,    0,    0,    0, -103,    0,    0,
    0,    0,    0,   86,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -229,    0,    0,
    0,    0,    0,    0,    0,    0,    0, -100,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  -96,    0, -281,
    0, -154, -198, -156, -150, -141, -107,  -98,  -64,  -55,
 -195, -184,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -279,    0,    0,
};
final static short yygindex[] = {                         0,
  123,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -90,    0,  167,    0,  165,
  -46,    0,
};
final static int YYTABLESIZE=404;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
   35,   12,   34,   59,   11,  100,  101,   75,   35,   76,
   34,   80,  104,   82,   83,   84,   85,   86,   87,   88,
   89,   90,   91,   92,   93,   94,   95,   44,   44,   44,
   44,   44,   44,   44,   44,   44,   44,   44,   44,   44,
   44,    4,    5,    6,   13,    3,   69,   70,   71,   72,
   73,  102,   44,   44,   14,    4,    5,    6,   49,   49,
   44,   38,   38,   38,   38,   38,   38,   38,   38,   38,
   38,   38,   39,   39,   39,   39,   39,   39,   39,   39,
   39,   39,   39,   49,   49,   31,   38,   38,   32,   15,
   33,   49,   34,   35,   38,   15,   36,   39,   39,   22,
   50,   50,   48,   23,   37,   39,   51,   51,   51,   51,
   51,   51,   51,   51,   51,   52,   52,   52,   52,   52,
   52,   52,   52,   52,    7,   50,   50,   48,   48,   26,
   45,   51,   51,   50,   30,   48,   71,   72,   73,   51,
   52,   52,   46,   55,   54,   56,   27,   58,   52,   53,
   53,   53,   53,   53,   53,   53,   53,   53,   54,   54,
   54,   54,   54,   54,   54,   54,   54,   98,   99,   31,
   11,  103,   32,   15,   53,   53,   34,   35,   10,   16,
   36,   37,   53,   54,   54,   36,   25,    0,   37,    0,
    0,   54,   55,   55,   55,   55,   55,   55,   55,   55,
   55,   56,   56,   56,   56,   56,   56,   56,   56,   56,
    0,    0,   47,   48,   49,    0,    0,   55,   55,    0,
    0,    0,   50,    0,    0,   55,   56,   56,   51,   52,
    0,    0,    0,    0,   56,   60,   61,   62,   63,   64,
   65,   66,   67,   68,   69,   70,   71,   72,   73,   62,
   63,   64,   65,   66,   67,   68,   69,   70,   71,   72,
   73,   74,   60,   61,   62,   63,   64,   65,   66,   67,
   68,   69,   70,   71,   72,   73,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   77,   60,
   61,   62,   63,   64,   65,   66,   67,   68,   69,   70,
   71,   72,   73,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   81,   60,   61,   62,   63,   64,
   65,   66,   67,   68,   69,   70,   71,   72,   73,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   96,   60,   61,   62,   63,   64,   65,   66,   67,   68,
   69,   70,   71,   72,   73,    0,    0,   16,    0,    0,
   16,   16,   16,    0,   16,   16,   97,    0,   16,    0,
    0,    0,    0,    0,    0,    0,   16,   60,   61,   62,
   63,   64,   65,   66,   67,   68,   69,   70,   71,   72,
   73,   61,   62,   63,   64,   65,   66,   67,   68,   69,
   70,   71,   72,   73,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         46,
  282,  272,  282,   50,  281,   96,   97,   54,  290,   56,
  290,   58,  103,   60,   61,   62,   63,   64,   65,   66,
   67,   68,   69,   70,   71,   72,   73,  257,  258,  259,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  284,  285,  286,  282,  274,  266,  267,  268,  269,
  270,   98,  282,  283,  281,  284,  285,  286,  257,  258,
  290,  257,  258,  259,  260,  261,  262,  263,  264,  265,
  266,  267,  257,  258,  259,  260,  261,  262,  263,  264,
  265,  266,  267,  282,  283,  272,  282,  283,  275,  276,
  277,  290,  279,  280,  290,  276,  283,  282,  283,  290,
  257,  258,  257,  282,  291,  290,  257,  258,  259,  260,
  261,  262,  263,  264,  265,  257,  258,  259,  260,  261,
  262,  263,  264,  265,    2,  282,  283,  282,  283,  283,
  283,  282,  283,  290,  272,  290,  268,  269,  270,  290,
  282,  283,  289,  283,  281,  281,   24,  281,  290,  257,
  258,  259,  260,  261,  262,  263,  264,  265,  257,  258,
  259,  260,  261,  262,  263,  264,  265,  290,  282,  272,
  282,  278,  275,  276,  282,  283,  279,  280,  282,   13,
  283,  282,  290,  282,  283,  282,   22,   -1,  291,   -1,
   -1,  290,  257,  258,  259,  260,  261,  262,  263,  264,
  265,  257,  258,  259,  260,  261,  262,  263,  264,  265,
   -1,   -1,  271,  272,  273,   -1,   -1,  282,  283,   -1,
   -1,   -1,  281,   -1,   -1,  290,  282,  283,  287,  288,
   -1,   -1,   -1,   -1,  290,  257,  258,  259,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  270,  259,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  283,  257,  258,  259,  260,  261,  262,  263,  264,
  265,  266,  267,  268,  269,  270,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  257,
  258,  259,  260,  261,  262,  263,  264,  265,  266,  267,
  268,  269,  270,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  282,  257,  258,  259,  260,  261,
  262,  263,  264,  265,  266,  267,  268,  269,  270,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  282,  257,  258,  259,  260,  261,  262,  263,  264,  265,
  266,  267,  268,  269,  270,   -1,   -1,  272,   -1,   -1,
  275,  276,  277,   -1,  279,  280,  282,   -1,  283,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  291,  257,  258,  259,
  260,  261,  262,  263,  264,  265,  266,  267,  268,  269,
  270,  258,  259,  260,  261,  262,  263,  264,  265,  266,
  267,  268,  269,  270,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=291;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"OR","AND","NOT","EQ","NEQ","LTHAN","GTHAN","GEQ","LEQ","PLUS",
"MINUS","MUL","DIV","MOD","NUM","ID","REAL","MAIN","PRINT","BEGIN","END","ELSE",
"IF","RETURN","LPAREN","RPAREN","SEMI","INT","BOOL","FLOAT","TRUE","FALSE",
"ASSIGN","COMMA","WHILE",
};
final static String yyrule[] = {
"$accept : program",
"program : decl_list main_decl",
"decl_list : decl_list decl",
"decl_list :",
"decl : fun_decl",
"type_spec : INT",
"type_spec : BOOL",
"type_spec : FLOAT",
"main_decl : MAIN LPAREN RPAREN compound_stmt",
"fun_decl : type_spec ID LPAREN params RPAREN SEMI",
"params : param_list",
"params :",
"param_list : param_list COMMA param",
"param_list : param",
"param : type_spec",
"stmt_list : stmt_list stmt",
"stmt_list :",
"stmt : compound_stmt",
"stmt : expr_stmt",
"stmt : print_stmt",
"stmt : if_stmt",
"stmt : while_stmt",
"stmt : return_stmt",
"print_stmt : PRINT expr SEMI",
"expr_stmt : ID ASSIGN expr SEMI",
"expr_stmt : SEMI",
"while_stmt : WHILE LPAREN expr RPAREN stmt",
"$$1 :",
"compound_stmt : BEGIN $$1 local_decls stmt_list END",
"local_decls : local_decls local_decl",
"local_decls :",
"local_decl : type_spec ID SEMI",
"if_stmt : IF LPAREN expr RPAREN stmt ELSE stmt",
"return_stmt : RETURN SEMI",
"arg_list : arg_list COMMA expr",
"arg_list : expr",
"args : arg_list",
"args :",
"expr : expr PLUS expr",
"expr : expr MINUS expr",
"expr : expr MUL expr",
"expr : expr DIV expr",
"expr : expr MOD expr",
"expr : LPAREN expr RPAREN",
"expr : ID",
"expr : NUM",
"expr : TRUE",
"expr : FALSE",
"expr : expr OR expr",
"expr : expr AND expr",
"expr : expr NOT expr",
"expr : expr EQ expr",
"expr : expr NEQ expr",
"expr : expr LTHAN expr",
"expr : expr GTHAN expr",
"expr : expr GEQ expr",
"expr : expr LEQ expr",
"expr : ID LPAREN args RPAREN",
"expr : REAL",
};

//#line 139 "./Parser.y"

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
//#line 394 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
throws Exception
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 39 "./Parser.y"
{ yyval.obj = CallProgram(val_peek(1).obj,val_peek(0).obj);                  }
break;
case 2:
//#line 42 "./Parser.y"
{yyval.obj = CallDeclList(val_peek(1).obj,val_peek(0).obj);}
break;
case 3:
//#line 43 "./Parser.y"
{yyval.obj = CallDeclListEps();}
break;
case 4:
//#line 48 "./Parser.y"
{yyval.obj = CallDecl(val_peek(0).obj);}
break;
case 5:
//#line 50 "./Parser.y"
{ yyval.obj = CallTypeInt();                    }
break;
case 6:
//#line 51 "./Parser.y"
{ yyval.obj = CallTypeBool();                   }
break;
case 7:
//#line 52 "./Parser.y"
{ yyval.obj = CallTypeFloat();}
break;
case 8:
//#line 55 "./Parser.y"
{ yyval.obj = CallMain(val_peek(0).obj);                     }
break;
case 9:
//#line 58 "./Parser.y"
{yyval.obj=CallFunDecl(val_peek(5).obj,val_peek(4).sval,val_peek(2).obj);}
break;
case 10:
//#line 60 "./Parser.y"
{yyval.obj = CallParams(val_peek(0).obj);}
break;
case 11:
//#line 61 "./Parser.y"
{yyval.obj = CallParamsEps();}
break;
case 12:
//#line 63 "./Parser.y"
{yyval.obj=CallParamList(val_peek(2).obj,val_peek(0).obj);}
break;
case 13:
//#line 64 "./Parser.y"
{yyval.obj=CallParamListParam(val_peek(0).obj);}
break;
case 14:
//#line 67 "./Parser.y"
{yyval.obj=CallParams(val_peek(0).obj);}
break;
case 15:
//#line 70 "./Parser.y"
{ yyval.obj = CallStmtListRec(val_peek(1).obj, val_peek(0).obj);          }
break;
case 16:
//#line 71 "./Parser.y"
{ yyval.obj = CallStmtListEps();                }
break;
case 17:
//#line 74 "./Parser.y"
{ yyval.obj = CallStmtCompound(val_peek(0).obj);             }
break;
case 18:
//#line 75 "./Parser.y"
{ yyval.obj = CallStmtExpr(val_peek(0).obj);                 }
break;
case 19:
//#line 76 "./Parser.y"
{ yyval.obj = CallStmtPrint(val_peek(0).obj);                }
break;
case 20:
//#line 77 "./Parser.y"
{ yyval.obj = CallStmtIf(val_peek(0).obj);}
break;
case 21:
//#line 78 "./Parser.y"
{ yyval.obj = CallStmtWhile(val_peek(0).obj);}
break;
case 22:
//#line 79 "./Parser.y"
{ yyval.obj = CallStmtReturn(val_peek(0).obj);}
break;
case 23:
//#line 83 "./Parser.y"
{ yyval.obj = CallPrintExpr(val_peek(1).sval);                }
break;
case 24:
//#line 86 "./Parser.y"
{ yyval.obj = CallExprStmtIdAssignExpr(val_peek(3).sval, val_peek(1).sval); }
break;
case 25:
//#line 87 "./Parser.y"
{ yyval.obj = CallExprStmtSemi();}
break;
case 26:
//#line 90 "./Parser.y"
{yyval.obj = CallWhileStmt(val_peek(2).sval,val_peek(0).obj);}
break;
case 27:
//#line 93 "./Parser.y"
{ yyval.obj = CallCompoundStmtBegin();          }
break;
case 28:
//#line 94 "./Parser.y"
{ yyval.obj = CallCompoundStmtRest(val_peek(3).obj, val_peek(2).obj, val_peek(1).obj); }
break;
case 29:
//#line 97 "./Parser.y"
{ yyval.obj = CallLocalDeclsRec(val_peek(1).obj, val_peek(0).obj);        }
break;
case 30:
//#line 98 "./Parser.y"
{ yyval.obj = CallLocalDeclsEps();              }
break;
case 31:
//#line 100 "./Parser.y"
{ yyval.obj = CallLocalDecl(val_peek(2).obj, val_peek(1).sval);            }
break;
case 32:
//#line 103 "./Parser.y"
{yyval.obj = CallIfStmt(val_peek(4).sval,val_peek(2).obj,val_peek(0).obj);}
break;
case 33:
//#line 106 "./Parser.y"
{yyval.obj= CallReturn();}
break;
case 34:
//#line 108 "./Parser.y"
{yyval.obj = CallArgsList(val_peek(2).obj,val_peek(0).sval);}
break;
case 35:
//#line 109 "./Parser.y"
{yyval.obj = CallArgsListExpr(val_peek(0).sval);}
break;
case 36:
//#line 112 "./Parser.y"
{yyval.obj= CallArgs(val_peek(0).obj);}
break;
case 37:
//#line 113 "./Parser.y"
{yyval.obj = CallArgsEps();}
break;
case 38:
//#line 116 "./Parser.y"
{ yyval.sval = CallExprAdd(val_peek(2).sval, val_peek(0).sval);              }
break;
case 39:
//#line 117 "./Parser.y"
{ yyval.sval = CallExprMinus(val_peek(2).sval,val_peek(0).sval);}
break;
case 40:
//#line 118 "./Parser.y"
{ yyval.sval = CallExprMul(val_peek(2).sval, val_peek(0).sval);              }
break;
case 41:
//#line 119 "./Parser.y"
{ yyval.sval = CallExprDiv(val_peek(2).sval, val_peek(0).sval);              }
break;
case 42:
//#line 120 "./Parser.y"
{ yyval.sval = CallExprMod(val_peek(2).sval, val_peek(0).sval);              }
break;
case 43:
//#line 121 "./Parser.y"
{ yyval.sval = CallExprParen(val_peek(1).sval);                }
break;
case 44:
//#line 122 "./Parser.y"
{ yyval.sval = CallExprID(val_peek(0).sval);                   }
break;
case 45:
//#line 123 "./Parser.y"
{ yyval.sval = CallExprNum(val_peek(0).sval);                  }
break;
case 46:
//#line 124 "./Parser.y"
{ yyval.sval = CallExprTrue();                   }
break;
case 47:
//#line 125 "./Parser.y"
{ yyval.sval = CallExprFalse();                  }
break;
case 48:
//#line 126 "./Parser.y"
{ yyval.sval = CallExprOr(val_peek(2).sval, val_peek(0).sval); }
break;
case 49:
//#line 127 "./Parser.y"
{ yyval.sval = CallExprAnd(val_peek(2).sval, val_peek(0).sval); }
break;
case 50:
//#line 128 "./Parser.y"
{ yyval.sval = CallExprNot(val_peek(2).sval, val_peek(0).sval); }
break;
case 51:
//#line 129 "./Parser.y"
{ yyval.sval = CallExprEQ(val_peek(2).sval, val_peek(0).sval); }
break;
case 52:
//#line 130 "./Parser.y"
{ yyval.sval = CallExprNEQ(val_peek(2).sval, val_peek(0).sval); }
break;
case 53:
//#line 131 "./Parser.y"
{ yyval.sval = CallExprLTHAN(val_peek(2).sval, val_peek(0).sval); }
break;
case 54:
//#line 132 "./Parser.y"
{ yyval.sval = CallExprGTHAN(val_peek(2).sval, val_peek(0).sval); }
break;
case 55:
//#line 133 "./Parser.y"
{ yyval.sval = CallExprGEQ(val_peek(2).sval, val_peek(0).sval); }
break;
case 56:
//#line 134 "./Parser.y"
{ yyval.sval = CallExprLEQ(val_peek(2).sval, val_peek(0).sval); }
break;
case 57:
//#line 135 "./Parser.y"
{ yyval.sval = CallIDParensArgs(val_peek(3).sval,val_peek(1).obj);}
break;
case 58:
//#line 136 "./Parser.y"
{ yyval.sval = CallExprReal(val_peek(0).sval);}
break;
//#line 775 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
//## The -Jnorun option was used ##
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
