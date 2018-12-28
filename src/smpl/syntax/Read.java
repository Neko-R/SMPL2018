package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class Read extends IRExp {

    public enum Type {
        STRING("String"), INT("Int");

        private String name;

        public String getName(){
            return name;
        }

        private Type(String name) {
            this.name = name;
        }

    }
    Type type;

    public Read(String type) {
        if(type == "int")
            this.type = Type.INT;
        else
            this.type = Type.STRING;
    }

    public String getType() {
        return type.getName();
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	    return v.visitRead(this, arg);
    }

    @Override
    public String toString() {
        if(type == Type.STRING)
	        return "read()";
        else
            return "readint()";
    }
}
