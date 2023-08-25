package rs.ac.bg.etf.pp1;

import java_cup.runtime.Symbol;

%%

%{

	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type) {
		return new Symbol(type, yyline+1, yycolumn);
	}
	
	// ukljucivanje informacije o poziciji tokena
	private Symbol new_symbol(int type, Object value) {
		return new Symbol(type, yyline+1, yycolumn, value);
	}

%}

%cup
%line
%column

%xstate COMMENT

%eofval{
	return new_symbol(sym.EOF);
%eofval}

%%

" " 	{ }
"\b" 	{ }
"\t" 	{ }
"\r\n" 	{ }
"\f" 	{ }

"program"   { return new_symbol(sym.PROG, yytext()); }
"print" 	{ return new_symbol(sym.PRINT, yytext()); }
"void" 		{ return new_symbol(sym.VOID, yytext()); }
"++" 	{ return new_symbol(sym.INC, yytext()); }
"+" 		{ return new_symbol(sym.PLUS, yytext()); }
"=" 		{ return new_symbol(sym.EQUAL, yytext()); }
";" 		{ return new_symbol(sym.SEMI, yytext()); }
"," 		{ return new_symbol(sym.COMMA, yytext()); }
"(" 		{ return new_symbol(sym.LPAREN, yytext()); }
")" 		{ return new_symbol(sym.RPAREN, yytext()); }
"{" 		{ return new_symbol(sym.LBRACE, yytext()); }
"}"			{ return new_symbol(sym.RBRACE, yytext()); }
"["		 	{ return new_symbol(sym.LSQUAR, yytext()); }
"]"		 	{ return new_symbol(sym.RSQUAR, yytext()); }
"const" 	{ return new_symbol(sym.CONST, yytext()); }
"new" 		{ return new_symbol(sym.NEW, yytext()); }
"read"	 	{ return new_symbol(sym.READ, yytext()); }
"findAny"	{ return new_symbol(sym.FINDANY, yytext()); }
"--" 		{ return new_symbol(sym.DEC, yytext()); }
"-" 		{ return new_symbol(sym.MINUS, yytext()); }
"*" 		{ return new_symbol(sym.TIMES, yytext()); }
"/" 		{ return new_symbol(sym.SOLIDUS, yytext()); }
"%" 		{ return new_symbol(sym.MODULUS, yytext()); }
"."			{ return new_symbol(sym.PERIOD, yytext()); }


<YYINITIAL> "//" 		     { yybegin(COMMENT); }
<COMMENT> .      { yybegin(COMMENT); }
<COMMENT> "\r\n" { yybegin(YYINITIAL); }

"true" {return new_symbol (sym.BOOL, Boolean.parseBoolean(yytext())); }
"false" {return new_symbol (sym.BOOL, Boolean.parseBoolean(yytext())); }
[0-9]+  { return new_symbol(sym.NUMBER, new Integer (yytext())); }
([a-z]|[A-Z])[a-z|A-Z|0-9|_]* 	{return new_symbol (sym.IDENT, yytext()); }
"'"[ -~]"'" {return new_symbol (sym.CHAR, yytext().charAt(1)); }


. { System.err.println("Leksicka greska ("+yytext()+") u liniji "+(yyline+1)); }






