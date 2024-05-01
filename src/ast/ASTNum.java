package ast;

public class ASTNum implements Exp {
    public int value;

    public ASTNum(int value) {
        this.value = value;
    }

    @Override
    public <T, E> T accept(Visitor<T, E> v, E block) {
        return v.visit(this);
    }
}
