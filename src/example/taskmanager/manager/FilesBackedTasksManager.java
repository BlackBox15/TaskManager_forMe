package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FilesBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    private final String filename;

    public FilesBackedTasksManager(String filename) {
        this.filename = filename;
    }

    private void save() {

        StringBuilder sb = new StringBuilder();
        sb.append("id,type,name,status,description,epic\n");
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {

            List<Task> sortedByIdList = getHistoryManager().getHistory();
            StringBuilder histItems2 = new StringBuilder();

            for (Task t :
                    sortedByIdList) {
                histItems2.append(t.getTaskId());
            }

            Collections.sort(sortedByIdList);

            for (Task t :
                    sortedByIdList) {

                List<String> specOfTask = new ArrayList<>();

                specOfTask.add(Integer.toString(t.getTaskId()));

                if (t instanceof Epic) {
                    specOfTask.add("EPIC");
                } else if (t instanceof SubTask)   {
                    specOfTask.add("SUBTASK");
                } else   {
                    specOfTask.add("TASK");
                }

                specOfTask.add(t.getNameTask());
                specOfTask.add(t.getStatus().toString());
                specOfTask.add(t.getDescriptionTask());

                sb.append(String.join(",", specOfTask));
                sb.append("\n\n");
                // todo: put "epic"  field logic here
            }
//            sb.append(String.join(",", ));
            bw.write(sb.toString());
        }
        catch (IOException e)   {
            System.out.println("IOException is here!");
        }
    }

    @Override
    public void setHistoryManager(HistoryManager historyManager) {
        super.setHistoryManager(historyManager);
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
