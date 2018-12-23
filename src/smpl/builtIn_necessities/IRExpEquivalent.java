package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpEquivalent extends IRExp {
    String e1;
    String e2;

    public IRExpEquivalent(String e1, String e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public String getE1() {
        return e1;
    }

    public String getE2() {
        return e2;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpEquivalent(this, arg);
    }

    public String toString() {
        return "eqv?("+e1+","+e2+")";
    }

}
