package smpl.values;


public class SmplChar extends SmplObj {

    char value;

    public SmplChar(char c) {
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

    public char getValue() {
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
