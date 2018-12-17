package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpCar extends IRExp {
    String p;

    public IRExpCar(String p) {
        this.p = p;
    }

    public String getP() {
        return p;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpCar(this, arg);
    }

    public String toString() {
        return "car(p)";
    }

}
