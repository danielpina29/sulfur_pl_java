package ast;

public class ASTNot implements Exp {
    public Exp exp;
    public ASTNot(Exp exp) {
        this.exp = exp;
    }
    @Override
    public <T, E> T accept(Visitor<T, E> v, E block) {
        return v.visit(this, block);
    }
}
