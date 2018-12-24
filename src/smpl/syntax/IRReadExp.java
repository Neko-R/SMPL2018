package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRReadExp extends IRExp {

    boolean integer;

    public IRReadExp() {
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitIRReadExp(this, state);
    }

    @Override
    public String toString() {
            return "read()";
}
}
