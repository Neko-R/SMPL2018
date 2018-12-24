package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;


public class IRLOr extends IRExp {

    IRExp first, second;

    public IRLOr(IRExp first, IRExp second) {
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
        return v.visitIRLOr(this, arg);
    }

    @Override
    public String toString() {
        return first.toString() + " or " + second.toString();
    }
}
