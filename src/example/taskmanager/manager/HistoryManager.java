package example.taskmanager.manager;

import example.taskmanager.task.Task;

import java.util.List;

public interface HistoryManager {

    void add(Task task);

    List<Task> getHistory();

}
