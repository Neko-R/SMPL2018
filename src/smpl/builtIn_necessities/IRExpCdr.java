package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpCdr extends IRExp {
    String p;

    public IRExpCdr(String p) {
        this.p = p;
    }

    public String getP() {
        return p;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpCdr(this, arg);
    }

    public String toString() {
        return "cdr(p)";
    }

}
