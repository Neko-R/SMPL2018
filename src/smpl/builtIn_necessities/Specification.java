package smpl.builtIn_necessities;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.syntax.IRExpProc.IRExpProc;
import smpl.sys.SmplException;

public class Specification {

    IRExp size;
    IRExpProc proc;

    public Specification(IRExp s, IRExpProc p) {
        size = s;
        proc = p;
    }

    public Specification(IRExp s) {
        size = s;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitSpecification(this, arg);
    }

    public IRExp getExp() {
	return this.size;
    }

    public IRExpProc getProc() {
	return this.proc;
    }

}
