package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRListCon extends IRExp {
    IRExp first, second;

    public IRListCon(IRExp first, IRExp right) {
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
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitIRListCon(this, state);
    }

    @Override
    public String toString() {
        return "" + first + " @ " + second;
    }
}
