package edu.touro.mco152;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DbCalculatorHistory implements CalculatorHistory {

    private final String connectionString;
    private ArrayList<Operation> history;
    private Stack<Operation> redoOperationStack;

    @Inject
    public DbCalculatorHistory(@Named("JDBC URL") String connectionString){
        history = new ArrayList<>();
        redoOperationStack = new Stack<>();
        this.connectionString = connectionString;
    }

    public void addOperation(Operation operation) {
         this.history.add(operation);
    }

    public List<Operation> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public Operation getCurrentOperation(){
        if (this.history.isEmpty()){
            return null;
        }

        return this.history.get(getLastHistoryIndex());
    }

    public void undo(){
        if (this.history.isEmpty()){
            return;
        }

        Operation removedItem = this.history.remove(getLastHistoryIndex());
        this.redoOperationStack.push(removedItem);
    }


    public void redo(){
        if (this.redoOperationStack.isEmpty()){
            return;
        }

        this.history.add(this.redoOperationStack.pop());
    }

    private int getLastHistoryIndex(){
        return this.history.size() - 1;
    }
}
