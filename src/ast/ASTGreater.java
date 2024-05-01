package ast;

public class ASTGreater implements Exp {
    public Exp left, right;

    public ASTGreater(Exp left, Exp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E block) {
        return v.visit(this, block);
    }
}
