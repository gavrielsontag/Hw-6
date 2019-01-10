package edu.touro.mco152;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy=strategy;
    }
    public double executeOperators(double leftOperand, String operator, double rightOperand){
        return strategy.operate(leftOperand,operator,rightOperand );
    }

}
