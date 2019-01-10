package edu.touro.mco152;
import java.util.List;

public interface CalculatorHistory {
    void addOperation(Operation operation);
    void redo();
    void undo();
    List<Operation> getHistory();
    Operation getCurrentOperation();
}
