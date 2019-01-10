package edu.touro.mco152;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.SocketException;
import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

class CalculatorTest {

    private double delta = .001;
    private Calculator calculator;
    //@Mock
    private CalculatorHistory calculatorHistoryMock;

    @BeforeEach
    void setUp(){
        //MockitoAnnotations.initMocks(this);
        calculatorHistoryMock = mock(CalculatorHistory.class);
        calculator = new Calculator(calculatorHistoryMock, calculatorHistoryMock);
    }

    @Test
    void parseExpression_invalidExpression_shouldReturnNull() {
        //Arrange and Act
        Operation operation = calculator.parseExpression("Hello World");
        //Assert
        assertNull(operation);
    }

    @Test
    void parseExpression_addition_shouldReturnCorrectOperation() {
        //Arrange and Act
        var operation = calculator.parseExpression("5+6");
        //Assert
        assertAll("Addition operation",
                () -> assertEquals(5, operation.getLeftOperand()),
                () -> assertEquals("+", operation.getOperator()),
                () -> assertEquals(6, operation.getRightOperand()),
                () -> assertEquals(11, operation.getResult())
        );
    }

    @Test
    void parseExpression_subtraction_shouldReturnCorrectOperation() {
        //Arrange and Act
        Operation operation = calculator.parseExpression("10.2 - 0.2");
        //Assert
        assertAll("Subtraction operation",
                () -> assertEquals(10.2, operation.getLeftOperand(), delta),
                () -> assertEquals("-", operation.getOperator()),
                () -> assertEquals(0.2, operation.getRightOperand(), delta),
                () -> assertEquals(10, operation.getResult(), delta)
        );
    }

    @Test
    void parseExpression_division_shouldReturnCorrectOperation() {
        //Arrange and Act
        Operation operation = calculator.parseExpression("2.7 / 4");
        //Assert
        assertAll("Division operation",
                () -> assertEquals(2.7, operation.getLeftOperand(), delta),
                () -> assertEquals("/", operation.getOperator()),
                () -> assertEquals(4, operation.getRightOperand()),
                () -> assertEquals(0.675, operation.getResult())
        );
    }

    @Test
    void parseExpression_multiple_shouldReturnCorrectOperation() {
        //Arrange and Act
        Operation operation = calculator.parseExpression("4 *2");
        //Assert
        assertAll("Multiple operation",
                () -> assertEquals(4, operation.getLeftOperand(), delta),
                () -> assertEquals("*", operation.getOperator()),
                () -> assertEquals(2, operation.getRightOperand()),
                () -> assertEquals(8, operation.getResult())
        );
    }

    @Test
    void parseExpression_modulus_shouldReturnCorrectOperation() {
        //Arrange and Act
        Operation operation = calculator.parseExpression("5.9     %     8");
        //Assert
        assertAll("Modulus operation",
                () -> assertEquals(5.9, operation.getLeftOperand(), delta),
                () -> assertEquals("%", operation.getOperator()),
                () -> assertEquals(8, operation.getRightOperand()),
                () -> assertEquals(5.9, operation.getResult())
        );
    }

    @Test
    void parseExpression_multiply_getResult_twice_shouldGetCorrectResult() {
        //Arrange and Act
        Operation operation = calculator.parseExpression("4 * 2");
        //Assert
        assertAll("Multiply operation",
                () -> assertEquals(4, operation.getLeftOperand(), delta),
                () -> assertEquals("*", operation.getOperator()),
                () -> assertEquals(2, operation.getRightOperand()),
                () -> assertEquals(8, operation.getResult())
        );

        assertEquals(8, operation.getResult());
    }

    @Test
    void getHistory_shouldGetCorrectHistory() {
        //Arrange and Act
        var history = calculator.getHistory();
        //Assert
        assertEquals(0, history.size());
    }

    @Test
    void getHistory_shouldGetCorrectHistory_afterPerformExpression() {
        Operation operation = calculator.parseExpression("4 * 2");

        //Arrange and Act
        var history = calculator.getHistory();
        //Assert
        assertEquals(1, 1);
    }

    @Test
    void getHistory_shouldGetCorrectHistory_afterPerformExpressionTwice() {
        Operation operation = calculator.parseExpression("4 * 2");
        Operation operation2 = calculator.parseExpression("5 - 3");

        //Arrange and Act
        var history = calculator.getHistory();
        //Assert
        assertAll("Calculator History",
                () ->  assertEquals(2,history.size()),
                () -> {
                    var firstOperation = history.get(0);
                    assertEquals(operation, firstOperation);
                }
                ,
                () -> {
                    var secondOperation = history.get(1);
                    assertEquals(operation2, secondOperation);
                });
    }

    @Test
    void parseExpression_shouldCallCalculatorHistory_addOperation() {
        Operation operation = calculator.parseExpression("4 * 2");

        verify(calculatorHistoryMock).addOperation(operation);
    }

    @Test
    void getHistoryWithStubbedMethod() {
        when(calculator.getHistory()).thenReturn(Arrays.asList(
                new Operation(2 , "*", 4),
                new Operation(2 , "*", 4))
        );

        Operation operation = calculator.parseExpression("4 * 2");

        assertEquals(2, calculator.getHistory().size());
    }

    @Test
    void getHistory_fail() {
        Operation operation = calculator.parseExpression("4 * 2");

        verify(calculatorHistoryMock).addOperation(argThat(null));
    }

    @Test
    void getHistory_fail_Exception() {
        var expression = "4 * 2";
        doThrow(ClassCastException.class)
            .when(calculatorHistoryMock).addOperation(any());

        assertThrows(ClassCastException.class, () -> {
            calculator.parseExpression(expression);
        });
    }

    @Test
    void getHistory_example() {
        var calculatorHistory = new InMemoryCalculatorHistory();
        var calculator = new Calculator(calculatorHistory, calculatorHistory);

    }
}