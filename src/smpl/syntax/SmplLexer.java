/* The following code was generated by JFlex 1.4.3 on 12/23/18 2:05 PM */

/* Specification for ArithExp tokens */

// user customisations
package smpl.syntax;

import java_cup.runtime.*;
import java.io.IOException;
import smpl.sys.TokenException;

// JFlex directives

/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 12/23/18 2:05 PM from the specification file
 * <tt>SMPL_Lexer.jflex</tt>
 */
public class SmplLexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\1\2\1\3\1\66\1\0\1\2\1\1\22\0\1\65\1\22"+
    "\1\63\1\56\1\5\1\12\1\14\1\67\1\25\1\26\1\10\1\6"+
    "\1\33\1\7\1\24\1\11\1\55\1\60\10\4\1\23\1\34\1\21"+
    "\1\17\1\20\2\5\6\62\24\5\1\27\1\64\1\30\1\13\1\5"+
    "\1\5\1\42\1\57\1\40\1\47\1\45\1\50\1\5\1\52\1\51"+
    "\2\5\1\41\1\5\1\53\1\37\1\35\1\5\1\36\1\54\1\46"+
    "\1\70\2\5\1\61\1\44\1\43\1\31\1\15\1\32\1\16\uff81\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\2\5\1\6\1\5"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\7\5\1\4\3\1\20\0\1\17\1\5\1\20\1\21"+
    "\7\5\1\22\1\23\1\24\1\25\4\0\1\26\2\0"+
    "\1\27\1\30\1\31\1\32\1\33\1\34\1\35\1\36"+
    "\1\37\1\40\1\41\1\0\1\42\3\0\1\43\4\5"+
    "\1\44\2\5\1\45\2\4\1\0\1\46\1\47\1\50"+
    "\1\51\1\52\1\53\1\5\1\54\1\55\1\56\1\57"+
    "\1\0\1\5\1\0\1\5\1\60";

  private static int [] zzUnpackAction() {
    int [] result = new int[110];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\71\0\162\0\162\0\253\0\344\0\u011d\0\u0156"+
    "\0\u018f\0\71\0\71\0\u01c8\0\71\0\71\0\71\0\71"+
    "\0\71\0\u0201\0\u023a\0\u0273\0\u02ac\0\u02e5\0\u031e\0\u0357"+
    "\0\u0390\0\u03c9\0\u0402\0\u043b\0\162\0\u0474\0\u04ad\0\u04e6"+
    "\0\u051f\0\u0558\0\u0591\0\u05ca\0\u0603\0\u063c\0\u0675\0\u06ae"+
    "\0\u06e7\0\u0720\0\u0759\0\u0792\0\u018f\0\u0390\0\71\0\71"+
    "\0\u07cb\0\u0804\0\u083d\0\u0876\0\u08af\0\u08e8\0\u0921\0\344"+
    "\0\71\0\71\0\71\0\u095a\0\u0993\0\u09cc\0\u0402\0\u0402"+
    "\0\u0a05\0\u0a3e\0\u0474\0\u04ad\0\u04e6\0\u051f\0\u0558\0\u0591"+
    "\0\u05ca\0\u0603\0\u063c\0\u0675\0\u0a77\0\u0ab0\0\u0ae9\0\u0b22"+
    "\0\u0b5b\0\u0b94\0\u0792\0\u0bcd\0\u0c06\0\u0c3f\0\u0c78\0\344"+
    "\0\u0cb1\0\u0cea\0\344\0\u095a\0\u0993\0\u0d23\0\71\0\u0ab0"+
    "\0\u0b22\0\u0b5b\0\u0b94\0\344\0\u0d5c\0\344\0\344\0\344"+
    "\0\344\0\u0d95\0\u0dce\0\u0e07\0\u0e40\0\344";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[110];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\2\4\1\5\2\6\1\7\13\6\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\2\6\1\23\1\24\3\6\1\25\1\26"+
    "\1\27\1\6\1\30\3\6\1\31\1\32\1\6\1\5"+
    "\2\6\1\33\1\2\1\4\1\3\1\34\1\6\72\0"+
    "\3\35\2\0\1\36\1\37\1\40\1\41\1\42\1\43"+
    "\1\44\1\45\1\46\1\47\1\50\1\51\1\52\1\53"+
    "\1\54\40\0\2\35\6\0\1\5\16\6\1\0\1\55"+
    "\10\0\20\6\1\5\2\6\1\5\2\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\26\6\5\0\1\6"+
    "\4\0\1\5\16\6\1\0\1\11\10\0\20\6\1\56"+
    "\2\6\1\5\2\6\5\0\1\6\30\0\1\57\44\0"+
    "\1\55\16\6\1\0\1\6\10\0\20\6\1\55\2\6"+
    "\1\55\2\6\5\0\1\6\23\0\1\60\51\0\17\6"+
    "\1\0\1\6\10\0\1\6\1\61\24\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\5\6\1\62\20\6"+
    "\5\0\1\6\4\0\17\6\1\0\1\6\10\0\5\6"+
    "\1\63\2\6\1\64\15\6\5\0\1\6\4\0\17\6"+
    "\1\0\1\6\10\0\4\6\1\65\21\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\15\6\1\66\10\6"+
    "\5\0\1\6\4\0\17\6\1\0\1\6\10\0\10\6"+
    "\1\67\15\6\5\0\1\6\4\0\17\6\1\0\1\6"+
    "\10\0\13\6\1\70\12\6\5\0\1\6\4\0\1\56"+
    "\16\6\1\0\1\55\10\0\20\6\1\56\2\6\1\56"+
    "\2\6\5\0\1\6\45\0\1\71\1\72\1\0\1\73"+
    "\6\0\1\74\1\0\1\75\6\0\1\76\3\0\60\77"+
    "\1\100\5\77\4\0\60\101\1\102\1\101\1\0\2\101"+
    "\1\0\3\103\61\0\2\103\3\0\3\104\61\0\2\104"+
    "\3\0\3\105\61\0\2\105\3\0\3\106\61\0\2\106"+
    "\3\0\3\107\61\0\2\107\3\0\3\110\61\0\2\110"+
    "\3\0\3\111\61\0\2\111\3\0\3\112\61\0\2\112"+
    "\3\0\3\113\61\0\2\113\3\0\3\114\61\0\2\114"+
    "\3\0\3\115\13\0\1\116\45\0\2\115\3\0\3\117"+
    "\13\0\1\120\45\0\2\117\21\0\1\121\70\0\1\122"+
    "\52\0\3\123\61\0\2\123\6\0\17\6\1\0\1\6"+
    "\10\0\2\6\1\124\11\6\1\125\11\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\4\6\1\126\21\6"+
    "\5\0\1\6\4\0\17\6\1\0\1\6\10\0\6\6"+
    "\1\127\17\6\5\0\1\6\4\0\17\6\1\0\1\6"+
    "\10\0\11\6\1\130\14\6\5\0\1\6\4\0\17\6"+
    "\1\0\1\6\10\0\17\6\1\131\6\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\10\6\1\132\15\6"+
    "\5\0\1\6\4\0\17\6\1\0\1\6\10\0\13\6"+
    "\1\133\12\6\5\0\1\6\55\0\1\134\2\0\1\134"+
    "\14\0\1\135\33\0\1\135\1\0\1\135\2\0\1\135"+
    "\1\0\2\135\4\0\1\135\1\0\2\135\1\0\1\135"+
    "\12\0\1\136\50\0\1\136\2\0\1\136\77\0\1\137"+
    "\47\0\1\101\4\0\1\101\13\0\1\137\2\0\3\115"+
    "\61\0\2\115\3\0\3\140\61\0\2\140\3\0\3\117"+
    "\61\0\2\117\3\0\3\141\61\0\2\141\3\0\3\142"+
    "\61\0\2\142\3\0\3\143\61\0\2\143\6\0\17\6"+
    "\1\0\1\6\10\0\3\6\1\144\22\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\16\6\1\145\7\6"+
    "\5\0\1\6\4\0\17\6\1\0\1\6\10\0\4\6"+
    "\1\146\21\6\5\0\1\6\4\0\17\6\1\0\1\6"+
    "\10\0\7\6\1\147\16\6\5\0\1\6\4\0\17\6"+
    "\1\0\1\6\10\0\10\6\1\150\15\6\5\0\1\6"+
    "\4\0\17\6\1\0\1\6\10\0\16\6\1\151\7\6"+
    "\5\0\1\6\4\0\1\152\50\0\1\152\2\0\1\152"+
    "\14\0\17\6\1\0\1\6\10\0\11\6\1\153\14\6"+
    "\5\0\1\6\4\0\1\154\50\0\1\154\2\0\1\154"+
    "\14\0\17\6\1\0\1\6\10\0\4\6\1\155\21\6"+
    "\5\0\1\6\4\0\1\137\50\0\1\137\2\0\1\137"+
    "\14\0\17\6\1\0\1\6\10\0\16\6\1\156\7\6"+
    "\5\0\1\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3705];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\7\1\2\11\1\1\5\11\13\1\20\0"+
    "\2\1\2\11\10\1\3\11\4\0\1\1\2\0\13\1"+
    "\1\0\1\1\3\0\13\1\1\0\1\11\12\1\1\0"+
    "\1\1\1\0\2\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[110];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
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


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public SmplLexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public SmplLexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 140) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException, TokenException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          zzR = false;
          break;
        case '\r':
          yyline++;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
          }
          break;
        default:
          zzR = false;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 3: 
          { /* ignore whitespace */
          }
        case 49: break;
        case 27: 
          { return new Symbol(sym.MOD);
          }
        case 50: break;
        case 33: 
          { return new Symbol(sym.GRT);
          }
        case 51: break;
        case 2: 
          { //skip newline, but reset char counter
			yychar = 0;
          }
        case 52: break;
        case 43: 
          { return new Symbol(sym.PROC);
          }
        case 53: break;
        case 34: 
          { return new Symbol(sym.LST);
          }
        case 54: break;
        case 20: 
          { return new Symbol(sym.TRU, yytext());
          }
        case 55: break;
        case 1: 
          { // get here only if symbol has no matching rule
               // DO NOT add any rules below this one
               throw new TokenException(yytext());
          }
        case 56: break;
        case 18: 
          { return new Symbol(sym.IF);
          }
        case 57: break;
        case 37: 
          { return new Symbol(sym.DEF);
          }
        case 58: break;
        case 28: 
          { return new Symbol(sym.EXPT);
          }
        case 59: break;
        case 40: 
          { return new Symbol(sym.LQT);
          }
        case 60: break;
        case 8: 
          { return new Symbol(sym.RPAREN);
          }
        case 61: break;
        case 36: 
          { return new Symbol(sym.LET);
          }
        case 62: break;
        case 24: 
          { return new Symbol(sym.MINUS);
          }
        case 63: break;
        case 23: 
          { return new Symbol(sym.PLUS);
          }
        case 64: break;
        case 9: 
          { return new Symbol(sym.LBRAK);
          }
        case 65: break;
        case 10: 
          { return new Symbol(sym.RBRAK);
          }
        case 66: break;
        case 15: 
          { return new Symbol(sym.DOUBLE, new Double(yytext()));
          }
        case 67: break;
        case 30: 
          { return new Symbol(sym.BOR);
          }
        case 68: break;
        case 48: 
          { return new Symbol(sym.PRINTLN);
          }
        case 69: break;
        case 46: 
          { return new Symbol(sym.ELSE);
          }
        case 70: break;
        case 11: 
          { return new Symbol(sym.LBRACE);
          }
        case 71: break;
        case 13: 
          { return new Symbol(sym.COMMA);
          }
        case 72: break;
        case 22: 
          { return new Symbol(sym.STRING, yytext());
          }
        case 73: break;
        case 47: 
          { return new Symbol(sym.THEN);
          }
        case 74: break;
        case 42: 
          { return new Symbol(sym.ASSIGN);
          }
        case 75: break;
        case 25: 
          { return new Symbol(sym.MUL);
          }
        case 76: break;
        case 19: 
          { return new Symbol(sym.NIL, yytext());
          }
        case 77: break;
        case 45: 
          { return new Symbol(sym.LAZY);
          }
        case 78: break;
        case 39: 
          { return new Symbol(sym.GQT);
          }
        case 79: break;
        case 35: 
          { return new Symbol(sym.DOT);
          }
        case 80: break;
        case 6: 
          { return new Symbol(sym.COLN);
          }
        case 81: break;
        case 14: 
          { return new Symbol(sym.SEMI);
          }
        case 82: break;
        case 4: 
          { return new Symbol(sym.INTEGER, new Integer(yytext()));
          }
        case 83: break;
        case 41: 
          { return new Symbol(sym.NQT);
          }
        case 84: break;
        case 44: 
          { return new Symbol(sym.CALL);
          }
        case 85: break;
        case 17: 
          { return new Symbol(sym.LVEC);
          }
        case 86: break;
        case 7: 
          { return new Symbol(sym.LPAREN);
          }
        case 87: break;
        case 5: 
          { return new Symbol(sym.VARIABLE, yytext());
          }
        case 88: break;
        case 38: 
          { return new Symbol(sym.CHARACTER, yytext());
          }
        case 89: break;
        case 16: 
          { return new Symbol(sym.RVEC);
          }
        case 90: break;
        case 21: 
          { return new Symbol(sym.FALS, yytext());
          }
        case 91: break;
        case 12: 
          { return new Symbol(sym.RBRACE);
          }
        case 92: break;
        case 26: 
          { return new Symbol(sym.DIV);
          }
        case 93: break;
        case 32: 
          { return new Symbol(sym.EQV);
          }
        case 94: break;
        case 31: 
          { return new Symbol(sym.BNOT);
          }
        case 95: break;
        case 29: 
          { return new Symbol(sym.BAND);
          }
        case 96: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
              { 	return new Symbol(sym.EOF);
 }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
