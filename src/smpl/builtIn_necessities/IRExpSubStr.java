package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpSubStr extends IRExp {
    String e1;
    String e2;
    String e3;

    public IRExpSubStr(String e1, String e2, String e3) {
        this.e1 = e1;
        this.e2 = e2;
        this.e3 = e3;
    }

    public String getE1() {
        return e1;
    }

    public String getE2() {
        return e2;
    }

    public String getE3() {
        return e3;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpSubStr(this, arg);
    }

    public String toString() {
        return "substr("+e1+", "+e2+", "+e3+")";
    }

}
