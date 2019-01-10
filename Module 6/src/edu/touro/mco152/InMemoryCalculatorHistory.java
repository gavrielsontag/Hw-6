package edu.touro.mco152;

import java.util.*;

public class InMemoryCalculatorHistory implements CalculatorHistory {

    private ArrayList<Operation> history;
    private Stack<Operation> redoOperationStack;

    public InMemoryCalculatorHistory(){
        history = new ArrayList<>();
        redoOperationStack = new Stack<>();
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
