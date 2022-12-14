package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

interface TaskManager {
     Collection<Task> listAllTasks();

     Collection<Epic> listAllEpics();

     Collection<SubTask> listAllSubTasks();

     void removeAll();

     Task getTask(int id) throws Exception;

     Epic getEpic(int id) throws Exception;

     SubTask getSubTask(int id) throws Exception;

    // Add all type tasks.
     void addTask(Task task);

     void addTask(Epic epic);

     void addTask(SubTask subTask, Epic epic);

     void addTask(SubTask subTask);

    // Update all type tasks.
     void updateTask(Task task);

     void updateEpic(Epic epic);

     void updateSubTask(SubTask subTask);

     void removeById(int id);

     ArrayList<SubTask> listSubTasksByEpic(Epic epic);

     List<Task> getHistory();
}
