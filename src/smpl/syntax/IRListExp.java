package smpl.syntax;

import smpl.semantics.Visitor;
import smpl.sys.SmplException;

public class IRListExp extends IRExp {
    IRExp values;

    public IRListExp(IRExp values){
        this.values = values;
    }

    public IRExp getList(){
        return values;
    }

    @Override
    public <S, T> T visit(Visitor<S, T> v, S state) throws SmplException {
        return v.visitIRListExp(this, state);
    }

    @Override
    public String toString() {
        return "[" + values + "]";
    }
}
