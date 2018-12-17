package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpVar extends IRExp {

    String var;

    public IRExpVar(String id) {
	var = id;
    }

    public String getVar() {
	return var;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException
    {
	return v.visitExpVar(this, arg);
    }

    @Override
    public String toString() {
	return var;
    }
}
