package smpl.syntax;


import smpl.sys.*;
import smpl.semantics.Visitor;
import java.util.ArrayList;


public class IRCaseExp extends IRExp {

	ArrayList<IRCaseBinding> bindings;

	public IRCaseExp(ArrayList<IRCaseBinding> bindings)
	{
		this.bindings = bindings;
	}

	public ArrayList<IRCaseBinding> getBindings()
	{
		return bindings;
	}

	@Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRCaseExp(this, arg);
    }

    @Override
    public String toString() {
        return "case stmt";
    }
}
