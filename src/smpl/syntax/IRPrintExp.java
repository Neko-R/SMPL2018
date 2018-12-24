package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.*;


public class IRPrintExp extends IRExp {

    IRExp exp;

    public IRPrintExp(IRExp exp) {
        this.exp = exp;
    }

    public IRExp getExp() {
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitIRPrintExp(this, state);
    }

    @Override
    public String toString() {
        return "print(" + exp + ")";
}
}
