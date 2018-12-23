package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

import java.util.ArrayList;

public class IRExpGetIndex extends IRExp {
    IRExp vector;
    IRExp n;

    public IRExpGetIndex(IRExp v, IRExp n) {
        this.vector = v;
        this.n = n;
    }

    public IRExp getN() {
        return n;
    }

    public IRExp getVector() {
        return vector;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpGetIndex(this, arg);
    }

    public String toString() {
        return vector.toString() + "[" + n.toString() + "]";
    }
}
