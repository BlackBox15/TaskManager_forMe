package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class FilesBackedTasksManager extends InMemoryTaskManager implements TaskManager{
    private final String filename;

    public FilesBackedTasksManager(String filename) {
        this.filename = filename;
    }

    public void save() {
        // Save this manager's state to the file.
        // Write data in CSV-format.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("history.csv"))) {
            StringBuilder sb = new StringBuilder();

            sb.append("id,type,name,status,description,epic\n");
            // todo: make a tasks to create a StringBuilder with all necessary data.

            bw.write(sb.toString());
        }
        catch (IOException e)   {
            System.out.println("IOException is here!");
        }

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
