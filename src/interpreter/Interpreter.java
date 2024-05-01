package interpreter;

import ast.*;
import values.IntValue;
import values.Value;

public class Interpreter implements Exp.Visitor<Value, Object> {

    public static Value interpret(Exp e) {
        Interpreter i = new Interpreter();
        return e.accept(i, null);
    }

    @Override
    public Value visit(ASTNum e) {
        return new IntValue(e.value);
    }

    @Override
    public Value visit(ASTAdd e) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() + rhs.getValue());
    }

    @Override
    public Value visit(ASTMinus e) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() - rhs.getValue());
    }

    @Override
    public Value visit(ASTMult e) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() * rhs.getValue());
    }

    @Override
    public Value visit(ASTDiv e) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() / rhs.getValue());
    }
}
