package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class StmtPrint extends Statement {

    public enum Type {
        REGULAR("Regular"), LINE("Line");

        private String name;

        public String getName(){
            return name;
        }

        private Type(String name) {
            this.name = name;
        }

    }
    Type type;
    IRExp exp;

    public StmtPrint(IRExp e, String type) {
        if(type == "line")
            this.type = Type.LINE;
        else
            this.type = Type.REGULAR;
        exp = e;
    }

    public IRExp getExp() {
	    return exp;
    }

    public String getType() {
        return type.getName();
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	    return  v.visitStmtPrint(this, arg);
    }

    @Override
    public String toString() {
        if(type == Type.REGULAR)
	        return "print( "+ exp.toString() + " )";
        else
            return "println( "+ exp.toString() + " )";
    }
}
