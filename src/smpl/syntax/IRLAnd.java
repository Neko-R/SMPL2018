package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class IRLAnd extends IRExp {

    IRExp first, second;

    public IRLAnd(IRExp first, IRExp second) {
        this.first = first;
        this.second = second;
    }

    public IRExp getFirst() {
        return first;
    }

    public IRExp getSecond() {
        return second;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRLAnd(this, arg);
    }

    @Override
    public String toString() {
        return first.toString() + " and " + second.toString();
    }
}
