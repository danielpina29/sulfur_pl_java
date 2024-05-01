package ast;

public class ASTBool implements Exp {
    public boolean value;
    public ASTBool(boolean value) {
        this.value = value;
    }
    @Override
    public <T, E> T accept(Visitor<T, E> v, E block) {
        return v.visit(this, block);
    }
}
