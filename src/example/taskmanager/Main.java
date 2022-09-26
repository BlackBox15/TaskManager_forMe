package example.taskmanager;

import example.taskmanager.manager.Managers;
import example.taskmanager.manager.TaskManager;
import example.taskmanager.manager.HistoryManager;
import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;
import example.taskmanager.task.Status;

public class Main {

    public static void main(String[] args) {

//        TaskManager manager1 = Managers.getDefault();
        TaskManager manager2 = Managers.getDefault("history.csv");
        HistoryManager historyManager = Managers.getDefaultHistory();

        manager2.setHistoryManager(historyManager);

        Task task1 = new Task("task1", "task1_description");
        Task task2 = new Task("task2", "task2_description");
        Task task3 = new Task("task3", "task3_description");
        Task task4 = new Task("task4", "task4_description");
        Task task5 = new Task("task5", "task5_description");
        Task task6 = new Task("task6", "task6_description");
        Task task7 = new Task("task7", "task7_description");
        Task task8 = new Task("task8", "task8_description");
        Epic epic1 = new Epic("epic1", "epic1_description");
        Epic epic2 = new Epic("epic2", "epic2_description");
        SubTask sub1_1 = new SubTask("sub1_1", "sub1_1_description");
        SubTask sub1_2 = new SubTask("sub1_2", "sub1_2_description");
        SubTask sub1_3 = new SubTask("sub1_3", "sub1_3_description");

        manager2.addTask(task1);
        manager2.addTask(task2);
        manager2.addTask(task3);
        manager2.addTask(task4);
        manager2.addTask(task5);
        manager2.addTask(task6);
        manager2.addTask(task7);
        manager2.addTask(task8);
        manager2.addTask(epic1);
        manager2.addTask(epic2);
        manager2.addTask(sub1_1, epic1);
        manager2.addTask(sub1_2, epic1);
        manager2.addTask(sub1_3, epic1);

        // Objects in work...
        sub1_2.setStatus(Status.DONE);
        sub1_1.setStatus(Status.IN_PROGRESS);
        task1.setStatus(Status.IN_PROGRESS);
        epic2.setStatus(Status.DONE);
        task1.setStatus(Status.DONE);

        // Update objects with manager.
        manager2.updateSubTask(sub1_2);
        manager2.updateSubTask(sub1_1);
        manager2.updateEpic(epic2);
        manager2.updateTask(task1);

        // Get history.
        System.out.println("------------ Get history1:");
        System.out.println(historyManager.getHistory());

//        manager2.removeById(epic1.getTaskId());

        // Get history.
//        System.out.println("------------ Get history2:");
//        System.out.println(historyManager.getHistory());

    }
}
