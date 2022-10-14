package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.io.*;
import java.util.*;

public class FilesBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    private final String filename;

    public FilesBackedTasksManager(String filename) {
        this.filename = filename;
        restore();
    }

    private void restore() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {

            String testString;
            StringBuilder testStringBuilder = new StringBuilder();

            while ((testString = bufferedReader.readLine()) != null)    {
                testStringBuilder.append(testString);
                testStringBuilder.append('\n');
            }

        } catch (IOException e) {
            System.out.println("IOException is here!");
        }



    }

    private void save() {

        StringBuilder resultStringBuilder = new StringBuilder();

        resultStringBuilder.append("id,type,name,status,description,epic\n");

        try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(filename))) {

            List<Task> tasksFromHistory = getHistoryManager().getHistory();
            List<String> idsList = new ArrayList<>();

            // An ids string.
            for (Task oneTask :
                    tasksFromHistory) {
                idsList.add(Integer.toString(oneTask.getTaskId()));
            }

            // Sort list of tasks by ID.
            Collections.sort(tasksFromHistory);

            for (Task oneTask :
                    tasksFromHistory) {

                List<String> listOfTaskMembers = new ArrayList<>();
                boolean isSubTask = false;

                listOfTaskMembers.add(Integer.toString(oneTask.getTaskId()));

                if (oneTask instanceof Epic) {
                    listOfTaskMembers.add("EPIC");
                } else if (oneTask instanceof SubTask) {
                    isSubTask = true;
                    listOfTaskMembers.add("SUBTASK");
                } else {
                    listOfTaskMembers.add("TASK");
                }

                listOfTaskMembers.add(oneTask.getNameTask());
                listOfTaskMembers.add(oneTask.getStatus().toString());
                listOfTaskMembers.add(oneTask.getDescriptionTask());

                if (isSubTask) {
                    listOfTaskMembers.add(Integer.toString(((SubTask) oneTask).getEpicId()));
                }

                resultStringBuilder.append(String.join(",", listOfTaskMembers));
                resultStringBuilder.append("\n");
            }

            resultStringBuilder.append("\n");
            resultStringBuilder.append(String.join(",", idsList));

            bufferWriter.write(resultStringBuilder.toString());
        } catch (IOException e) {
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
