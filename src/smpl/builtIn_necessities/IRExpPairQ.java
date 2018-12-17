package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpPairQ extends IRExp {
    String p;

    public IRExpPairQ(String p) {
        this.p = p;
    }

    public String getP() {
        return p;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpPairQ(this, arg);
    }

    public String toString() {
        return "pair?(p)";
    }

}
