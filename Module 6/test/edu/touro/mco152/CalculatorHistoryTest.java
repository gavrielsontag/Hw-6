package edu.touro.mco152;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorHistoryTest {

    private double delta = .001;
    private InMemoryCalculatorHistory calculatorHistory;
    private Operation operationAddition = new Operation(1,Operators.Addition, 2);
    private Operation operationSubtraction = new Operation(5,Operators.Subtract, 3);

    private Operation[] operations = new Operation[]
    {
         operationAddition, operationSubtraction
    };

    @BeforeEach
    void setUp(){
        calculatorHistory = new InMemoryCalculatorHistory();
    }

    @Test
    void getHistory_shouldGetCorrectHistory() {
        //Arrange and Act
        var history = calculatorHistory.getHistory();
        //Assert
        assertEquals(0, history.size());
    }

    @Test
    void getHistory_shouldGetCorrectHistory_afterPerformExpression() {
        calculatorHistory.addOperation(operationAddition);

        //Arrange and Act
        var history = calculatorHistory.getHistory();
        //Assert
        assertEquals(1, history.size());
    }

    @Test
    void getHistory_shouldGetCorrectHistory_afterPerformExpressionTwice() {
        calculatorHistory.addOperation(operationAddition);
        calculatorHistory.addOperation(operationSubtraction);

        //Arrange and Act
        var history = calculatorHistory.getHistory();
        //Assert
        assertAll("Calculator History",
                () ->  assertEquals(2,history.size()),
                () -> {
                    var firstOperation = history.get(0);
                    assertEquals(operationAddition, firstOperation);
                }
                ,
                () -> {
                    var secondOperation = history.get(1);
                    assertEquals(operationSubtraction, secondOperation);
                });
    }

    @Test
    void getCurrentOperation_noHistory() {

        //Arrange and Act
        var history = calculatorHistory.getCurrentOperation();
        //Assert
        assertEquals(null,history);
    }

    @Test
    void getCurrentOperation() {
        calculatorHistory.addOperation(operationAddition);

        //Arrange and Act
        var history = calculatorHistory.getCurrentOperation();
        //Assert
        assertEquals(operationAddition,history);
    }

    @Test
    void undo_shouldRemoveLastItem() {
        calculatorHistory.addOperation(operationAddition);
        calculatorHistory.undo();

        //Arrange and Act
        var history = calculatorHistory.getHistory();
        //Assert
        assertEquals(0,history.size());
    }

    @Test
    void undo_empty_shouldDoNothing() {
        calculatorHistory.undo();

        //Arrange and Act
        var history = calculatorHistory.getHistory();
        //Assert
        assertEquals(0,history.size());
    }

    @Test
    void undo_redo_shouldHaveOperation() {
        calculatorHistory.addOperation(operationAddition);
        calculatorHistory.undo();
        calculatorHistory.redo();

        //Arrange and Act
        var history = calculatorHistory.getCurrentOperation();
        //Assert
        assertEquals(operationAddition,history);
    }
}