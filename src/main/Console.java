package main;

import java.io.ByteArrayInputStream;

import parser.ParseException;
import parser.SulfurParser;
import parser.TokenMgrError;

public class Console {

    @SuppressWarnings("static-access")
    public static void main(String args[]) {
         SulfurParser parser = new SulfurParser(System.in);

        while (true) {
            try {
                parser.Start();
                System.out.println("OK!" );
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