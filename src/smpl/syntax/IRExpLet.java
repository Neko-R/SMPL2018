package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpLet extends IRExp {
    BindingList bindings;
    Statement body;

    public IRExpLet(BindingList bs, Statement bod) {
	   bindings = bs;
	   body = bod;
    }

    public BindingList getBindings() {
	      return bindings;
    }

    public Statement getBody() {
	      return body;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	      return v.visitIRExpLet(this, arg);
    }

    public String toString() {
	return "let " + bindings + body;
    }

}
