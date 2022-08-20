package example.taskmanager.manager;

import example.taskmanager.task.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{

    private static final int MAX_HISTORY = 10;
    private final List<Task> historyList = new ArrayList<>();

    @Override
    public void add(Task task) {
        if(MAX_HISTORY <= historyList.size()) {
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}
