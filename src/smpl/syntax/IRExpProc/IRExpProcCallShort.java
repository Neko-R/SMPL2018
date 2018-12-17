package smpl.syntax.IRExpProc;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.sys.SmplException;
import smpl.values.SmplList;

import java.util.ArrayList;

public class IRExpProcCallShort extends IRExp {
    IRExp name;
    ArrayList<IRExp> arguments;

    public IRExpProcCallShort(IRExp f, ArrayList<IRExp> args) {
	   name = f;
	   arguments = args;
    }

    public ArrayList<IRExp> getArguments() {
	      return arguments;
    }

    public IRExp getName() {
	      return name;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpProcCallShort(this, arg);
    }

    public String toString() {
	    String result = name.toString() +"("+ arguments.toString()+")";
        return result;
    }

}
