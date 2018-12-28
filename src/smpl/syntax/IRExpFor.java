package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRExpFor extends IRExp {

    public enum Type {
        INCREMENT("++"), DECREMENT("--");

        private String name;

        public String getName(){
            return name;
        }

        private Type(String name) {
            this.name = name;
        }

    }
    Type type;
    Binding bind;
    IRExp bound;
    Statement body;

    public IRExpFor(Binding bind, IRExp bound, String c, Statement body){
        this.bind = bind;
        this.bound = bound;
        if(c.equals("++"))
            this.type = Type.INCREMENT;
        else
            this.type = Type.DECREMENT;
        this.body = body;
    }

    public Binding getBind() {
        return bind;
    }

    public String getType() {
        return type.getName();
    }

    public IRExp getBound() {
        return bound;
    }

    public Statement getBody() {
        return body;
    }

    public <S, T> T visit(Visitor<S, T> v, S arg) throws SmplException {
	      return v.visitIRExpFor(this, arg);
    }

    public String toString() {
	return "for("+bind+";"+bound+";"+type+")";
    }

}
