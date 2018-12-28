/* Specification for ArithExp tokens */

// user customisations
package smpl.syntax;

import java_cup.runtime.*;
import java.io.IOException;
import smpl.sys.TokenException;

// JFlex directives
%%
    
%cup
%public

%class SmplLexer
%throws TokenException

%type java_cup.runtime.Symbol

%eofval{
	return new Symbol(sym.EOF);
%eofval}

%eofclose false

%char
%line

%{
    StringBuffer string = new StringBuffer();

    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
      }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }

    Character ch = Character.MIN_VALUE;

    int commentNest = 0;
    int lineCount = 1;
%}

%{
    public int getChar() {
	    return yychar + 1;
    }

    public int getColumn() {
    	return yycolumn + 1;
    }

    public int getLine() {
	    return yyline + 1;
    }

    public String getText() {
	    return yytext();
    }
%}


nl = [\n\r]

cc = [\b\f]|{nl}

ws = {cc}|[\t ]

num = [0-9]

alpha = [a-zA-Z_]

alphanum = {alpha}|{num}


valid = [!$%&*+\-./<-Z\^-z~|]
digitvalid = [!$%&*+\-./-9<-Z\^-z~|]

%state STRING COMMENTLN COMMENTBLK CHAR HEXA BINARY UNICODE

%%

<YYINITIAL>{

	{nl}	{
                        //skip newline, but reset char counter
			yychar = 0;
			}

	{ws}	{/* ignore whitespace */}

    "/*"	{ yybegin(COMMENTBLK); commentNest++; }

    "//"	{yybegin(COMMENTLN);}

	{ws}+"+"{ws}+	{return new Symbol(sym.PLUS);}
	{ws}+"-"{ws}+	{return new Symbol(sym.MINUS);}
	{ws}+"*"{ws}+	{return new Symbol(sym.MUL);}
	{ws}+"/"{ws}+	{return new Symbol(sym.DIV);}
	{ws}+"%"{ws}+	{return new Symbol(sym.MOD);}
	{ws}+"^"{ws}+	{return new Symbol(sym.EXPT);}

	{ws}+"&"{ws}+	{return new Symbol(sym.BAND);}
	{ws}+"|"{ws}+	{return new Symbol(sym.BOR);}
	"~"{ws}+	{return new Symbol(sym.BNOT);}

	{ws}+"="{ws}+	{return new Symbol(sym.EQV);}
	{ws}+">"{ws}+	{return new Symbol(sym.GRT);}
	{ws}+"<"{ws}+	{return new Symbol(sym.LST);}
	{ws}+"<="{ws}+	{return new Symbol(sym.LQT);}
	{ws}+">="{ws}+	{return new Symbol(sym.GQT);}
	{ws}+"!="{ws}+	{return new Symbol(sym.NQT);}

    {ws}+"and"{ws}+	{return new Symbol(sym.AND);}
	{ws}+"or"{ws}+     {return new Symbol(sym.OR);}
	"not"{ws}+	{return new Symbol(sym.NOT);}

	{ws}+"@"+{ws}+	{return new Symbol(sym.CAT);}

	{ws}+":="{ws}+ 	{return new Symbol(sym.ASSIGN);}

	{ws}+"."{ws}+	{return new Symbol(sym.DOT);}

	"("	{return new Symbol(sym.LPAREN);}
	")"	{return new Symbol(sym.RPAREN);}

	"["	{return new Symbol(sym.LBRAK);}
    "]"	{return new Symbol(sym.RBRAK);}

	"{"	{return new Symbol(sym.LBRACE);}
	"}"	{return new Symbol(sym.RBRACE);}

	":"	{return new Symbol(sym.COLN);}

	"[:"	{return new Symbol(sym.LVEC);}
	":]"	{return new Symbol(sym.RVEC);}


	","	{return new Symbol(sym.COMMA);}
	";"	{return new Symbol(sym.SEMI);}

	"proc"	{return new Symbol(sym.PROC);}
	"lazy"	{return new Symbol(sym.LAZY);}
	"call"	{return new Symbol(sym.CALL);}
	"let"	{return new Symbol(sym.LET);}
	 "def"	{return new Symbol(sym.DEF);}

	"if"	{return new Symbol(sym.IF);}
	"then"	{return new Symbol(sym.THEN);}
	"else"	{return new Symbol(sym.ELSE);}

	"case"	{return new Symbol(sym.CASE);}

	"print"	{return new Symbol(sym.PRINT);}
	"println"	{return new Symbol(sym.PRINTLN);}

	"read"	{return new Symbol(sym.READ);}
	"readint"	{return new Symbol(sym.READINT);}

   -?[0-9]* {return new Symbol(sym.INTEGER, new Integer(yytext()));}

    -?(({num}+".")|("."{num}+)|({num}+"."{num}+)) {return new Symbol(sym.DOUBLE, new Double(yytext()));}

   "#t"  {return new Symbol(sym.TRU, yytext());}

   "#f"  {return new Symbol(sym.FALS, yytext());}

   "#e"  {return new Symbol(sym.NIL, yytext());}

  {valid}|{digitvalid}({digitvalid}|#)+  {return new Symbol(sym.VARIABLE, yytext());}

//[ -~] matches all ascii characters in range 32-127

    \"   { string.setLength(0); yybegin(STRING); }

    '       { yybegin(CHAR); }
    "#b"    { yybegin(BINARY); }

    "#x"    { yybegin(HEXA); }

    "#u"    { yybegin(UNICODE); }
}

<HEXA>{
    [a-fA-F0-9]+ {return new Symbol(sym.INTEGER, Integer.parseInt(yytext(),16));}
}

<BINARY>{
    [01]+ {return new Symbol(sym.INTEGER, Integer.parseInt(yytext(),2));}

}

<UNICODE>{
    [a-fA-F0-9]{4} {return new Symbol(sym.CHARACTER, (char) Integer.parseInt(yytext(),16));}
}

<COMMENTBLK>{
    "/*"    { commentNest++; }
    "*/"	{ commentNest--; if(commentNest == 0) yybegin(YYINITIAL);}
    {nl}    { lineCount++; }
    .       {}
}

<COMMENTLN>{
    {nl}    { yybegin(YYINITIAL); }
    .       {}

}

<CHAR>{
    '               { yybegin(YYINITIAL); return symbol(sym.CHARACTER, ch);}
    [^\n\r\"\\]     { ch = yytext().charAt(0); }

    \\t      { ch = '\t';}

    \\n      { ch = '\n'; }

    \\       { ch = '\\'; }
}

<STRING> {
      \"       { yybegin(YYINITIAL); return symbol(sym.STRING, string.toString()); }

      [^\n\r\"\\]+       { string.append( yytext() ); }
      \\t    { string.append('\t'); }
      \\n    { string.append('\n'); }
      \\     { string.append('\\'); }
      \\\"      { string.append('\"'); }

    }

<YYINITIAL>    .        {
               // get here only if symbol has no matching rule
               // DO NOT add any rules below this one
               throw new TokenException(yytext());
               }