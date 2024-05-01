package ast;

import values.Value;

public interface Exp {
    public interface Visitor<T,E> {
        public T visit(ASTNum e);
        public T visit(ASTAdd e);
        public T visit(ASTMinus e);
        public T visit(ASTMult e);
        public T visit(ASTDiv e);
    }

    public <T,E> T accept(Visitor<T,E> v, E block);
}
