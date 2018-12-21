package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;

import java.util.ArrayList;

public class IRExpVector extends IRExp {
    ArrayList<Specification> slst;

    public IRExpVector(ArrayList<Specification> s) {
        this.slst = s;
    }

    public ArrayList<Specification> getSpecLst() {
        return this.slst;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpVector(this, arg);
    }

    public String toString() {
        return "A vector";
    }

}
