package compiler;

import ast.*;
import target.BasicBlock;
import target.IAdd;
import target.SiPush;
import values.IntValue;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class CodeGen implements Exp.Visitor<Void, BasicBlock> {

    @Override
    public Void visit(ASTNum e, BasicBlock block) {
        block.addInstruction(new SiPush(e.value));
        return null;
    }

    @Override
    public Void visit(ASTAdd e, BasicBlock block) {
        e.left.accept(this, block);
        e.right.accept(this, block);
        block.addInstruction(new IAdd());
        return null;
    }

    @Override
    public Void visit(ASTMinus e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTMult e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTDiv e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTGreater e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTLess e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTEquals e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTAnd e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTOr e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTNot e, BasicBlock block) {
        return null;
    }

    @Override
    public Void visit(ASTBool e, BasicBlock block) {
        return null;
    }


    public static BasicBlock codeGen(Exp e) {
        CodeGen cg = new CodeGen();
        BasicBlock block = new BasicBlock();
        e.accept(cg, block);
        return block;
    }


    private static StringBuilder genPreAndPost(BasicBlock block) {
        String preamble = """
					  .class public Demo
					  .super java/lang/Object 
					  .method public <init>()V
					     aload_0
					     invokenonvirtual java/lang/Object/<init>()V
					     return
					  .end method
					  .method public static main([Ljava/lang/String;)V
					   .limit locals 10
					   .limit stack 256
					   ; setup local variables:
					   ;    1 - the PrintStream object held in java.lang.out
					  getstatic java/lang/System/out Ljava/io/PrintStream;					          
				   """;
        String footer =
                """
                invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
                invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
                return
                .end method
                """;
        StringBuilder sb = new StringBuilder(preamble);
        block.build(sb);
        sb.append(footer);
        return sb;

    }

    public static void writeToFile(Exp e, String filename) throws FileNotFoundException {
        StringBuilder sb = genPreAndPost(codeGen(e));
        PrintStream out = new PrintStream(new FileOutputStream(filename));
        out.print(sb.toString());
        out.close();
    }
}
