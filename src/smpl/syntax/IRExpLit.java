package smpl.syntax;

import smpl.semantics.Environment;
import smpl.values.*;
import smpl.semantics.Visitor;
import smpl.sys.SmplException;

/**
 * AST node to represent a literal value (eg number, string, character).
 * @author newts
 */
public class IRExpLit extends IRExp {

    SmplObj val;

    public IRExpLit(Integer v) {
        val = new SmplInt(v);
    }
    //public IRExpLit(IRExp exp, Environment<SmplObj> env) {
     //   val = new SmplPromise(exp, env);
   // }

    public IRExpLit() {
        val = new SmplNil();
    }

    public IRExpLit(Double v) {
        val = new SmplDouble(v);
    }

    public IRExpLit(SmplList v) {
        val = v;
    }

    public IRExpLit(Boolean v) {
        val = new SmplBool(v);
    }

    public IRExpLit(String s) {
        val = new SmplString(s.substring(1,s.length()-1));
    }

    public IRExpLit(String i, String v) {
        if(i == "c")
            val = new SmplChar(v.charAt(1));
        else if(i == "u")
            val = new SmplChar((char) Integer.parseInt(v.substring(2),16));
        else if(i == "b")
            val = new SmplInt(Integer.parseInt(v.substring(2),2));
        else if(i == "h")
            val = new SmplInt(Integer.parseInt(v.substring(2),16));
    }


    public SmplObj getVal() {
        return val;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitExpLit(this, arg);
    }

    @Override
    public String toString() {
        return val.toString();
    }
}
