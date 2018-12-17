package smpl.values;

import smpl.semantics.Environment;
import smpl.syntax.IRExp;
import smpl.syntax.ParamLst;

/**
 * SMPL representation for the procedures
 **/
public class SmplPromise extends SmplObj {

    private SmplObj value;
    private IRExp difexp;
    private Environment<SmplObj> env;

    public SmplPromise() {
        super(Type.PROMISE);
    }

    public SmplPromise(IRExp exp, Environment<SmplObj> e) {
        this();
        this.difexp = exp;
        this.env = e;
    }

    public SmplPromise(SmplObj obj, Environment<SmplObj> e) {
        this();
        this.value = obj;
        this.env = e;
    }

    public SmplPromise setValue(SmplObj v){
        this.value = v;
        return this;
    }

    public SmplObj getValue() {
        return this.value;
    }

    public IRExp getExp() { return this.difexp; }

    public Environment<SmplObj> getEnvironment() {
        return this.env;
    }

    @Override
    public String toString() {
        if(value == null) {
            return "Differed Expression: " + difexp;
        }else{
            return value.toString();
        }
    }

}
