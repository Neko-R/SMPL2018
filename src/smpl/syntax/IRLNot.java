package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRLNot extends IRExp {
    IRExp exp;

    public IRLNot(IRExp e) {
        exp = e;
    }

    public IRExp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRLNot(this, arg);
    }

    @Override
    public String toString() {
        return "not " + exp.toString();
    }
}
