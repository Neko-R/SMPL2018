package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRWhileExp extends IRExp {

    IRExp predicate, exp;

    public IRWhileExp(IRExp predicate, IRExp exp) {
        this.predicate = predicate;
        this.exp = exp;
    }

    public IRExp getPredicate(){
        return predicate;
    }

    public IRExp getExp(){
        return exp;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitIRWhileExp(this, state);
    }

    @Override
    public String toString() {
        return "while(" + predicate + "){ " + exp + "}";
    }
}
