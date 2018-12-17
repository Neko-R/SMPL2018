package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpExpt extends IRExpOp {

    public IRExpExpt(IRExp e1, IRExp e2) {
        super("^", e1, e2);
    }

  @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	return v.visitExpExpt(this, arg);
    }

}

