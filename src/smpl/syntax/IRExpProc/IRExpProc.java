package smpl.syntax.IRExpProc;

import smpl.semantics.Visitor;
import smpl.syntax.IRExp;
import smpl.syntax.ParamLst;
import smpl.syntax.Statement;
import smpl.sys.SmplException;


public class IRExpProc extends IRExp {
    public enum Type {
        FIXED("Fixed"), LEAST("Least"), ANY("Any");

        private String name;

        private Type(String name) {
            this.name = name;
        }

    }
    Type type;
    private ParamLst parameter;
    private Statement body;

    public IRExpProc(){};

    public IRExpProc(ParamLst ps, Statement body, String type) {
        if(type == "fixed")
            this.type = Type.FIXED;
        else if(type == "least")
            this.type = Type.LEAST;
        else
            this.type = Type.ANY;

        this.parameter = ps;
        this.body = body;
    }

    public Type getType(){
        return this.type;
    }

    public ParamLst getParameters() {
        return parameter;
    }

    public Statement getBody() {
        return body;
    }

    public boolean isFixed() {
        return this.getType() == Type.FIXED;
    }

    public boolean isLeast() {
        return this.getType() == Type.LEAST;
    }

    public boolean isAny() {
        return this.getType() == Type.ANY;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
        return v.visitIRExpProc(this, arg);
    }

    @Override
    public String toString() {
        return "proc(" + parameter + ") {" + body + "}";
    }
}
