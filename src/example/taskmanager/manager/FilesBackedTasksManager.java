package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.util.Collection;
import java.util.List;

public class FilesBackedTasksManager extends InMemoryTaskManager implements TaskManager{
    private final String filename;

    public FilesBackedTasksManager(String filename) {
        this.filename = filename;
    }

    public void save() {
        // Save this manager's state to the file.
    }

    @Override
    public void setHistoryManager(HistoryManager historyManager) {
        super.setHistoryManager(historyManager);
        save();
    }

    @Override
    public void removeAll() {
        super.removeAll();
        save();
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public void addTask(Epic epic) {
        super.addTask(epic);
        save();
    }

    @Override
    public void addTask(SubTask subTask) {
        super.addTask(subTask);
        save();
    }

    @Override
    public void addTask(SubTask subTask, Epic epic) {
        super.addTask(subTask, epic);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        save();
    }

    @Override
    public void removeById(int id) {
        super.removeById(id);
        save();
    }
}
