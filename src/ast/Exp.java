package ast;

import values.Value;

public interface Exp {
    public interface Visitor<T,E> {
        public T visit(ASTNum e, E block);
        public T visit(ASTAdd e, E block);
        public T visit(ASTMinus e, E block);
        public T visit(ASTMult e, E block);
        public T visit(ASTDiv e, E block);
        public T visit(ASTGreater e, E block);
        public T visit(ASTLess e, E block);
        public T visit(ASTEquals e, E block);
        public T visit(ASTAnd e, E block);
        public T visit(ASTOr e, E block);
        public T visit(ASTNot e, E block);
        public T visit(ASTBool e, E block);
    }

    public <T,E> T accept(Visitor<T,E> v, E block);
}
