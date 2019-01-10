package edu.touro.mco152;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private final CalculatorHistory history2;
    private final CalculatorHistory history;

    @Inject
    public Calculator(CalculatorHistory calculatorHistory, CalculatorHistory calculatorHistory2){
        this.history = calculatorHistory;
        this.history2 = calculatorHistory2;
    }

    /***
     * Returns an Operation based on the expression. For example, 3 + 6 will return an Operation
     * with leftOperand of 3, an operator of "+" and a right operand of 6.
     * This method should return null if the Operation could not be parsed
     */
    public Operation parseExpression(String expression){
        if (expression == null || expression.trim().equals("")){
            return null;
        }

        Pattern pattern = Pattern.compile("\\s*(\\d*\\.?\\d*)\\s*([-+*/%])\\s*(\\d*\\.?\\d*)\\s*");
        Matcher m = pattern.matcher(expression);

        if (m.find()){
            double leftOperand = Double.parseDouble(m.group(1));
            String operator = m.group(2);
            double rightOperand = Double.parseDouble(m.group(3));

            Operation operation = new Operation(leftOperand, operator, rightOperand);

            addOperation(operation);

            return operation;
        } else {
            return null;
        }
    }

    private void addOperation(Operation operation){
        history.addOperation(operation);
    }

    public List<Operation> getHistory() {
        return history.getHistory();
    }
}
