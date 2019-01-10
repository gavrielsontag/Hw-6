package edu.touro.mco152;

import java.lang.reflect.Array;

public class Operators {

    public class addition implements Strategy{
        @Override
        public double operate(double leftOperand, String operand, double rightOperand){
            return leftOperand + rightOperand;
        }
    }
    public class multiply implements Strategy {
        @Override
        public double operate(double leftOperand, String operand, double rightOperand) {

            return leftOperand * rightOperand;
        }
    }

    public class divide implements Strategy {
        @Override
        public double operate(double leftOperand, String operand, double rightOperand) {
            return leftOperand / rightOperand;
        }
    }

        public class subtract implements Strategy {
            @Override
            public double operate(double leftOperand, String operand, double rightOperand) {
                return leftOperand - rightOperand;
            }
        }

        public class modulus implements Strategy {
            @Override
            public double operate(double leftOperand, String operand, double rightOperand) {
                return leftOperand % rightOperand;
            }
        }

    public static final String Addition = "+";
    public static final String Multiply = "*";
    public static final String Subtract = "-";
    public static final String Divide = "/";
    public static final String Modulus = "%";

    public double Operator(double leftoperand, String operator, double rightoperand){
        switch (operator) {
            case Addition: {
                Context context = new Context(new addition());
                return context.executeOperators(leftoperand, operator, rightoperand);
            }
            case Multiply: {
                Context context = new Context(new multiply());
                return context.executeOperators(leftoperand, operator, rightoperand);
            }
            case Subtract: {
                Context context = new Context(new subtract());
                return context.executeOperators(leftoperand, operator, rightoperand);
            }
            case Divide: {
                Context context = new Context(new divide());
                return context.executeOperators(leftoperand, operator, rightoperand);
            }
            case Modulus: {
                Context context = new Context(new subtract());
                return context.executeOperators(leftoperand, operator, rightoperand);
            }
            default:
                return -1;
        }
    }}