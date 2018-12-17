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

%%

<YYINITIAL>	{nl}	{
                        //skip newline, but reset char counter
			yychar = 0;
			}

<YYINITIAL>	{ws}	{/* ignore whitespace */}

<YYINITIAL>	{ws}+"+"{ws}+	{return new Symbol(sym.PLUS);}
<YYINITIAL>	{ws}+"-"{ws}+	{return new Symbol(sym.MINUS);}
<YYINITIAL>	{ws}+"*"{ws}+	{return new Symbol(sym.MUL);}
<YYINITIAL>	{ws}+"/"{ws}+	{return new Symbol(sym.DIV);}
<YYINITIAL>	{ws}+"%"{ws}+	{return new Symbol(sym.MOD);}
<YYINITIAL>	{ws}+"^"{ws}+	{return new Symbol(sym.EXPT);}

<YYINITIAL>	{ws}+"&"{ws}+	{return new Symbol(sym.BAND);}
<YYINITIAL>	{ws}+"|"{ws}+	{return new Symbol(sym.BOR);}
<YYINITIAL>	{ws}+"~"{ws}+	{return new Symbol(sym.BNOT);}

<YYINITIAL>	{ws}+"="{ws}+	{return new Symbol(sym.EQV);}
<YYINITIAL>	{ws}+">"{ws}+	{return new Symbol(sym.GRT);}
<YYINITIAL>	{ws}+"<"{ws}+	{return new Symbol(sym.LST);}
<YYINITIAL>	{ws}+"<="{ws}+	{return new Symbol(sym.LQT);}
<YYINITIAL>	{ws}+">="{ws}+	{return new Symbol(sym.GQT);}
<YYINITIAL>	{ws}+"!="{ws}+	{return new Symbol(sym.NQT);}

//<YYINITIAL>	{ws}+"and"{ws}+	{return new Symbol(sym.AND);}
//<YYINITIAL>	{ws}+"or"{ws}+     {return new Symbol(sym.OR);}
//<YYINITIAL>	{ws}+"not"{ws}+	{return new Symbol(sym.NOT);}

//<YYINITIAL>	{ws}+"@"+{ws}+	{return new Symbol(sym.CAT);}

<YYINITIAL>	 {ws}+":="{ws}+ 	{return new Symbol(sym.ASSIGN);}

<YYINITIAL>	{ws}+"."{ws}+	{return new Symbol(sym.DOT);}

<YYINITIAL>	"("	{return new Symbol(sym.LPAREN);}
<YYINITIAL>	")"	{return new Symbol(sym.RPAREN);}

//<YYINITIAL>	"["	{return new Symbol(sym.LBRAK);}
//<YYINITIAL>	"]"	{return new Symbol(sym.RBRAK);}

<YYINITIAL>	"{"	{return new Symbol(sym.LBRACE);}
<YYINITIAL>	"}"	{return new Symbol(sym.RBRACE);}

//<YYINITIAL>	":"	{return new Symbol(sym.COLN);}

//<YYINITIAL>	"[:"	{return new Symbol(sym.LVERT);}
//<YYINITIAL>	":]"	{return new Symbol(sym.RVERT);}


<YYINITIAL>	","	{return new Symbol(sym.COMMA);}
<YYINITIAL>	";"	{return new Symbol(sym.SEMI);}

<YYINITIAL>	"proc"	{return new Symbol(sym.PROC);}
<YYINITIAL>	"lazy"	{return new Symbol(sym.LAZY);}
<YYINITIAL>	"call"	{return new Symbol(sym.CALL);}
<YYINITIAL>	"let"	{return new Symbol(sym.LET);}
<YYINITIAL>	 "def"	{return new Symbol(sym.DEF);}

<YYINITIAL>	"if"	{return new Symbol(sym.IF);}
<YYINITIAL>	"then"	{return new Symbol(sym.THEN);}
<YYINITIAL>	"else"	{return new Symbol(sym.ELSE);}

//<YYINITIAL>	"case"	{return new Symbol(sym.CASE);}

//<YYINITIAL>	"print"	{return new Symbol(sym.PRINT);}
<YYINITIAL>	"println"	{return new Symbol(sym.PRINTLN);}

//<YYINITIAL>	"read"	{return new Symbol(sym.READ);}
//<YYINITIAL>	"readint"	{return new Symbol(sym.READINT);}

//<YYINITIAL>	"//"	{return new Symbol(sym.COMNLN);}
//<YYINITIAL>	"/*"	{return new Symbol(sym.LBCOMN);}
//<YYINITIAL>	"*/"	{return new Symbol(sym.RBCOMN);}

<YYINITIAL>    0|-?[1-9][0-9]*|#b[01]+|#x([a-fA-F0-9])+ {return new Symbol(sym.INTEGER, new Integer(yytext()));}

<YYINITIAL>    -?(({num}+".")|("."{num}+)|({num}+"."{num}+)) {return new Symbol(sym.DOUBLE, new Double(yytext()));}

//[ -~] matches all ascii characters in range 32-127

<YYINITIAL>   \"([ -~]|\n|\t)+\" {return new Symbol(sym.STRING, yytext());}

<YYINITIAL>   '([ -~]|\\n|\\t)'|#u{num}{4} {return new Symbol(sym.CHARACTER, yytext());}
//<YYINITIAL>   '([ -~]|\\n|\\t)'|#u{num}{4} {return new Symbol(sym.CHARACTER, new Character(yytext().charAt(0)));}

<YYINITIAL>   "#t"  {return new Symbol(sym.TRU, yytext());}

<YYINITIAL>   "#f"  {return new Symbol(sym.FALS, yytext());}

<YYINITIAL>   "#e"  {return new Symbol(sym.NIL, yytext());}

<YYINITIAL>  {valid}|{digitvalid}({digitvalid}|#)+  {return new Symbol(sym.VARIABLE, yytext());}

<YYINITIAL>    .        {
               // get here only if symbol has no matching rule
               // DO NOT add any rules below this one
               throw new TokenException(yytext());
               }