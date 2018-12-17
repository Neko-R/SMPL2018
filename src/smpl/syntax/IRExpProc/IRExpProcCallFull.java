package smpl.syntax.IRExpProc;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;
import smpl.values.SmplList;

public class IRExpProcCallFull extends IRExp {
    IRExp name;
    IRExp arguments;

    public IRExpProcCallFull(IRExp f, IRExp args) {
	   name = f;
	   arguments = args;
    }

    public IRExp getArguments() {
	      return arguments;
    }

    public IRExp getName() {
	      return name;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	      return v.visitIRExpProcCallFull(this, arg);
    }

    public String toString() {
	    return "call(" + name.toString() + ", List<" + arguments.toString() + ">)";
    }

}
