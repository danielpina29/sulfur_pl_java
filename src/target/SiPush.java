package target;

public class SiPush extends Instruction {
    public SiPush(int n) {
        op = "sipush";
        args = new String[]{ Integer.toString(n) } ;
    }
}
