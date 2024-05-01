package ast;

public class ASTMinus implements Exp {

    public Exp left;
    public Exp right;

    public ASTMinus(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E block) {
        return v.visit(this);
    }
}
