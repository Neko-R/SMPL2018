/*
 * SmplDouble.java
 * Created on 7-Nov-2018, 10:15:48 AM
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package smpl.values;

import smpl.sys.SmplException;

/**
 * A suggested implementation (sketch) for floating point numbers in SMPL.
 * @author newts
 */
public class SmplDouble extends SmplObj {

    private double value;
    
    public SmplDouble(double val) {
        super(Type.REAL);
        value = val;
    }
    
    public double value() {
        return value;
    }
    
    /**
     *
     * @param val
     * @return
     * @throws smpl.sys.SmplException
     */
    @Override
    public SmplObj add(SmplObj val) throws SmplException {
        if (val instanceof SmplDouble) {
            return new SmplDouble(value() + ((SmplDouble) val).value());
        } else if (val instanceof SmplInt) {
            return new SmplDouble(value() + ((SmplInt) val).value());
        } else {
            return super.add(val);
        }
    }

    @Override
    public SmplObj sub(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt) {
            return new SmplDouble(value() - ((SmplInt) arg).value());
        } else 	if (arg instanceof SmplDouble) {
            return new SmplDouble(value() - ((SmplDouble)arg).value());
        } else
            return super.add(arg);
    }

    @Override
    public SmplObj mul(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplDouble(value() * ((SmplInt) arg).value() );
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(value() * ((SmplDouble)arg).value());
        }else
            return super.mul(arg);
    }

    @Override
    public SmplObj div(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplDouble(value() / ((SmplInt) arg).value() );
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(value() / ((SmplDouble)arg).value());
        }else
            return super.div(arg);
    }

    @Override
    public SmplObj mod(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplDouble(value() % ((SmplInt) arg).value() );
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(value() % ((SmplDouble)arg).value());
        }else
            return super.mod(arg);
    }

    @Override
    public SmplObj expt(SmplObj arg) throws SmplException {
        if (arg instanceof SmplInt){
            return new SmplDouble(Math.pow(value(), (double) ((SmplInt) arg).value()));
        }else if (arg instanceof SmplDouble){
            return new SmplDouble(Math.pow(value(), ((SmplDouble) arg).value()));
        }else
            return super.expt(arg);
    }

    @Override
    public SmplObj neg() throws SmplException {
        return new SmplDouble(- value());
    }

    @Override
    public SmplObj eq(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() == ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() == ((SmplDouble)arg).value());
        }else
            return super.eq(arg);
    }

    public SmplObj neq(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() != ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() != ((SmplDouble)arg).value());
        }else
            return super.neq(arg);
    }

   /* @Override
    public boolean equals(SmplObj obj) {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(((SmplInt)arg).value() == ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(((SmplInt)arg).value() == ((SmplDouble)arg).value());
        }else
        return super.equals(obj);
    }*/

    @Override
    public SmplObj lt(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() < ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() < ((SmplDouble)arg).value());
        }else
            return super.lt(arg);
    }

    @Override
    public SmplObj le(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() <= ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() <= ((SmplDouble)arg).value());
        }else
            return super.le(arg);
    }

    @Override
    public SmplObj gt(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() > ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() > ((SmplDouble)arg).value());
        }else
            return super.gt(arg);
    }

    @Override
    public SmplObj ge(SmplObj arg) throws SmplException {
        if(arg instanceof SmplInt){
            return SmplBool.getConst(value() >= ((SmplInt)arg).value());
        }else if (arg instanceof SmplDouble){
            return SmplBool.getConst(value() >= ((SmplDouble)arg).value());
        }else
            return super.ge(arg);
    }

    @Override
    public SmplObj orBits(SmplObj arg) throws SmplException {
        return super.orBits(arg);
    }

    @Override
    public SmplObj andBits(SmplObj arg) throws SmplException {
        return super.andBits(arg);
    }

    @Override
    public SmplObj notBits() throws SmplException {
        return super.notBits();
    }

    @Override
    public String toString() {
        return String.format("%s", value());
    }

    @Override
    public boolean equals(SmplObj arg) {
        if (arg instanceof SmplDouble){
            return value() == ((SmplDouble) arg).value();
        }else
            return false;
    }
}
