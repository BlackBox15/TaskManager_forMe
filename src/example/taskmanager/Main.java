package example.taskmanager;

import example.taskmanager.manager.InMemoryTaskManager;
import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;
import example.taskmanager.task.Status;

public class Main {

    public static void main(String[] args) {

        InMemoryTaskManager manager = new InMemoryTaskManager();

        // Creating new objects.
        Task task1 = new Task("task1_name", "something about task1");
        Task task2 = new Task("task2_name", "something about task2");
        Epic epic1 = new Epic("epic1_name", "epic1_description");
        Epic epic2 = new Epic("epic2_name", "epic2_description");
        SubTask sub1_1 = new SubTask("sub1_name", "sub1_descr");
        SubTask sub1_2 = new SubTask("sub2_name", "sub2_descr");
        SubTask sub2_1 = new SubTask("sub3_name", "sub3_descr");

        // Adding a new objects with manager (attaching IDs).
        manager.addTask(task1);
        manager.addTask(task2);
        manager.addTask(epic1);
        manager.addTask(epic2);
        manager.addTask(sub1_1, epic1);
        manager.addTask(sub1_2, epic1);
        manager.addTask(sub2_1, epic2);

        // Test output (using customizable toString()).
        System.out.println(manager.listAllTasks());
        System.out.println(manager.listAllEpics());
        System.out.println(manager.listAllSubTasks());

        // Objects in work...
        sub1_1.setStatus(Status.IN_PROGRESS);
        sub1_2.setStatus(Status.DONE);
        sub2_1.setStatus(Status.DONE);

        // Update objects with manager.
        manager.updateSubTask(sub1_1);
        manager.updateSubTask(sub1_2);
        manager.updateSubTask(sub2_1);

        // Test output after changing.
        System.out.println("------------after update------------");
        System.out.println(manager.listAllTasks());
        System.out.println(manager.listAllEpics());
        System.out.println(manager.listAllSubTasks());
    }
}
