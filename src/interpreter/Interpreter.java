package interpreter;

import ast.*;
import values.BoolValue;
import values.IntValue;
import values.Value;

public class Interpreter implements Exp.Visitor<Value, Object> {

    public static Value interpret(Exp e) {
        Interpreter i = new Interpreter();
        return e.accept(i, null);
    }

    @Override
    public Value visit(ASTNum e, Object obj) {
        return new IntValue(e.value);
    }

    @Override
    public Value visit(ASTAdd e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() + rhs.getValue());
    }

    @Override
    public Value visit(ASTMinus e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() - rhs.getValue());
    }

    @Override
    public Value visit(ASTMult e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() * rhs.getValue());
    }

    @Override
    public Value visit(ASTDiv e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new IntValue(lhs.getValue() / rhs.getValue());
    }

    @Override
    public Value visit(ASTGreater e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new BoolValue(lhs.getValue() > rhs.getValue());
    }

    @Override
    public Value visit(ASTLess e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new BoolValue(lhs.getValue() < rhs.getValue());
    }

    @Override
    public Value visit(ASTEquals e, Object obj) {
        IntValue lhs = (IntValue) e.left.accept(this, null);
        IntValue rhs = (IntValue) e.right.accept(this, null);
        return new BoolValue(lhs.getValue() == rhs.getValue());
    }

    @Override
    public Value visit(ASTAnd e, Object obj) {
        BoolValue lhs = (BoolValue) e.left.accept(this, null);
        BoolValue rhs = (BoolValue) e.right.accept(this, null);
        return new BoolValue(lhs.getValue() && rhs.getValue());
    }

    @Override
    public Value visit(ASTOr e, Object obj) {
        BoolValue lhs = (BoolValue) e.left.accept(this, null);
        BoolValue rhs = (BoolValue) e.right.accept(this, null);
        return new BoolValue(lhs.getValue() || rhs.getValue());
    }

    @Override
    public Value visit(ASTNot e, Object obj) {
        BoolValue exp = (BoolValue) e.exp.accept(this, null);
        return new BoolValue(!exp.getValue());
    }

    @Override
    public Value visit(ASTBool e, Object obj) {
        return new BoolValue(e.value);
    }
}
