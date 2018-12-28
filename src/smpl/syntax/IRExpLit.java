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
        val = new SmplString(s);
    }

    public IRExpLit(Character s) {
        val = new SmplChar(s);
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
