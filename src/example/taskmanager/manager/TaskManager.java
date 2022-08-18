package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.util.ArrayList;
import java.util.Collection;

 interface TaskManager {
     Collection<Task> listAllTasks();

     Collection<Epic> listAllEpics();

     Collection<SubTask> listAllSubTasks();

     void removeAll();

     Task getTask(int id);

     Epic getEpic(int id);

     SubTask getSubTask(int id);

    // Add all type tasks.
     void addTask(Task task);

     void addTask(Epic epic);

     void addTask(SubTask subTask);

     void addTask(SubTask subTask, Epic epic);

    // Update all type tasks.
     void updateTask(Task task);

     void updateEpic(Epic epic);

     void updateSubTask(SubTask subTask);

     void removeById(int id);

     ArrayList<SubTask> listSubTasksByEpic(Epic epic);

    int getUniqueID();
}
