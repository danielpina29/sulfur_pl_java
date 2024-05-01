package main;

import java.io.ByteArrayInputStream;

import ast.Exp;
import interpreter.Interpreter;
import parser.ParseException;
import parser.SulfurParser;
import parser.TokenMgrError;
import values.Value;

public class Console {

    @SuppressWarnings("static-access")
    public static void main(String args[]) {
         SulfurParser parser = new SulfurParser(System.in);

        while (true) {
            try {
                Exp exp = parser.Start();
                System.out.println("Parsed Successfully" );
                Value value = Interpreter.interpret(exp);
                System.out.println("Interpret Successfully" );
                System.out.println("Interpreter Output: " + value.toString() );
            } catch (TokenMgrError e) {
                System.out.println("Lexical Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            }
        }
    }

    public static boolean accept(String s) throws ParseException {
        SulfurParser parser = new SulfurParser(new ByteArrayInputStream(s.getBytes()));
        try {
            parser.Start();
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}