package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpCreateList extends IRExp {
    String p;

    public IRExpCreateList(String p) {
        this.p = p;
    }

    public String getParameter() {
        return this.p;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpCreateList(this, arg);
    }

    public String toString() {
        return "List(" + p +")";
    }

}
