package edu.touro.mco152;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.log4j.*;
import org.apache.log4j.spi.LoggingEvent;
import org.graalvm.compiler.replacements.Log;

import static org.apache.log4j.Level.INFO;


public class Main {
    public static void main(String[] args) {
//	    var injector = Guice.createInjector(new CalculatorModule());
//	    var calculator = injector.getInstance(Calculator.class);
//
//	    var s = "";
        Scanner keyboard = new Scanner(System.in);
        int i = 0;
        System.out.println("type exit, history, clear, clear 'index number', history 'index number\n to get their functions");
        while (i == 0) {
            System.out.println("Enter an operation");
            String str = "";
            Pattern ptn = Pattern.compile("[+|-|*|%|/]");
            String[] parts = ptn.split(str);
            try {
                double part1 = Double.parseDouble(parts[0]);
                String part2 = parts[1];
                double part3 = Double.parseDouble(parts[3]);
                Operation answer = new Operation(part1, part2, part3);
                Log.println("" + answer);
            } catch (Exception e) {
                Logger logger = new Logger("error").log(INFO, "invalid input");
            }


        }

    }
}
