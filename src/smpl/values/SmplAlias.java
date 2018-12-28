package smpl.values;

import smpl.semantics.Environment;
import smpl.syntax.IRExp;

/**
 * SMPL representation for the procedures
 **/
public class SmplAlias extends SmplObj {

    private String location;
    private Environment<SmplObj> env;

    public SmplAlias() {
        super(Type.ALIAS);
    }

    public SmplAlias(String location, Environment<SmplObj> e) {
        this();
        this.location = location;
        this.env = e;
    }

    public String getLocation() {
        return location;
    }

    public Environment<SmplObj> getEnv() {
        return env;
    }

    @Override
    public String toString() {
        return "Alias for: " + location;
    }

}
