package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRReadIntExp extends IRExp {

    boolean integer;

    public IRReadIntExp() {
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitIRReadIntExp(this, state);
    }

    @Override
    public String toString() {
            return "readInt()";
}
}
