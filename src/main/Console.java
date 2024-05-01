package main;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ast.Exp;
import compiler.CodeGen;
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
                String filename = "output.j";
                Exp exp = parser.Start();
                System.out.println("Parsed Successfully" );
                Value value = Interpreter.interpret(exp);
                System.out.println("Interpret Successfully" );
                System.out.println("Interpreter Output: " + value.toString() );
                CodeGen.writeToFile(exp,filename);
                executeJasmin(filename);
            } catch (TokenMgrError e) {
                System.out.println("Lexical Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            } catch (ParseException e) {
                System.out.println("Syntax Error!");
                e.printStackTrace();
                parser.ReInit(System.in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
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

    public static void executeJasmin(String filename) throws IOException {
        String command = "java -jar jasmin.jar " + filename;
        Process process = Runtime.getRuntime().exec(command);
        try {
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Jasmin assembly successful.");
            } else {
                System.out.println("Jasmin assembly failed. Exit code: " + exitCode);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}