package smpl.values;


public class SmplChar extends SmplObj {

    String value;

    public SmplChar(String c) {
        super(Type.CHAR);
        value = c;
    }
  
    @Override
    public boolean isFalse() {
        return true;
    }
    
    @Override
    public String toString() {
        return ""+value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(SmplObj arg) {
        if (arg instanceof SmplChar){
            return value == ((SmplChar) arg).getValue();
        }else
            return false;
    }
}
