package smpl.values;

import smpl.semantics.Environment;
import smpl.syntax.IRExp;
import smpl.syntax.IRExpProc.IRExpProc;
import smpl.syntax.ParamLst;
import smpl.syntax.Parameter;
import smpl.syntax.Statement;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SMPL representation for the procedures
 **/
public class SmplProc<T> extends SmplObj {

    //private ParamLst paramLst;		// paramLst of ids
    //private Statement body;
    private IRExpProc proc;
    private Environment<T> env;

    public SmplProc() {
        super(Type.PROC);
    }

    public SmplProc(IRExpProc p, Environment<T> env) {
        this();
        this.proc = p;
        this.env = env;
    }

    public SmplProc(IRExpProc p){
        this();
        this.proc = p;
        //this.body = bod;
    }


    /*public ParamLst getParamLst() {
        return this.paramLst;
    }

    public Statement getBody() { return this.body; }*/

    public IRExpProc getProc() { return this.proc; }

    public Environment<T> getEnvironment(){
        return this.env;
    }

    @Override
    public boolean isProc() {
        if(this.getType() == Type.PROC){
            return true;
        }else{
            return super.isProc();
        }
    }

    @Override
    public String toString() {
        return proc.toString();
    }

}
