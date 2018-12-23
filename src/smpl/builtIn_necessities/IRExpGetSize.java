package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

public class IRExpGetSize extends IRExp {
    String vec;

    public IRExpGetSize(String vec) {
        this.vec = vec;
    }

    public String getVec() {
        return vec;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpGetSize(this, arg);
    }

    public String toString() {
        return "size(vec)";
    }

}
