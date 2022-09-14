package example.taskmanager;

import example.taskmanager.manager.Managers;
import example.taskmanager.manager.TaskManager;
import example.taskmanager.manager.HistoryManager;
import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;
import example.taskmanager.task.Status;

public class Main {

    public static void main(String[] args) throws Exception {

        TaskManager manager1 = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        manager1.setHistoryManager(historyManager);

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
        SubTask sub1_1 = new SubTask("sub1_1", "dsdfsdf");
        SubTask sub1_2 = new SubTask("sub1_2", "sub1_2_description");
        SubTask sub1_3 = new SubTask("sub1_3", "sub1_3_description");

        manager1.addTask(task1);
        manager1.addTask(task2);
        manager1.addTask(task3);
        manager1.addTask(task4);
        manager1.addTask(task5);
        manager1.addTask(task6);
        manager1.addTask(task7);
        manager1.addTask(task8);
        manager1.addTask(epic1);
        manager1.addTask(epic2);
        manager1.addTask(sub1_1, epic1);
        manager1.addTask(sub1_2, epic1);
        manager1.addTask(sub1_3, epic1);

        // Objects in work...
        sub1_2.setStatus(Status.DONE);
        sub1_1.setStatus(Status.IN_PROGRESS);
        task1.setStatus(Status.IN_PROGRESS);
        epic2.setStatus(Status.DONE);
        task1.setStatus(Status.DONE);

        // Update objects with manager.
        manager1.updateSubTask(sub1_2);
        manager1.updateSubTask(sub1_1);
        manager1.updateEpic(epic2);
        manager1.updateTask(task1);

        // Get history.
        System.out.println("------------ Get history:");
        System.out.println(historyManager.getHistory());


//        // Adding a new objects with manager (attaching IDs).
//        manager1.addTask(task1);
//        manager1.addTask(task2);
//        manager1.addTask(epic1);
//        manager1.addTask(epic2);
//        manager1.addTask(sub1_1, epic1);
//        manager1.addTask(sub1_2, epic1);
//        manager1.addTask(sub2_1, epic2);
//
//        // Test output (using customizable toString()).
//        System.out.println(manager1.listAllTasks());
//        System.out.println(manager1.listAllEpics());
//        System.out.println(manager1.listAllSubTasks());
//
//        // Objects in work...
//        sub1_1.setStatus(Status.IN_PROGRESS);
//        sub1_2.setStatus(Status.DONE);
//        sub2_1.setStatus(Status.DONE);
//
//        // Update objects with manager.
//        manager1.updateSubTask(sub1_1);
//        manager1.updateSubTask(sub1_2);
//        manager1.updateSubTask(sub2_1);
//
//        // Test output after changing.
//        System.out.println("------------after update------------");
//        System.out.println(manager1.listAllTasks());
//        System.out.println(manager1.listAllEpics());
//        System.out.println(manager1.listAllSubTasks());
//
//        System.out.println("----tests for getHistory()------");
//        // Simulate getting some tasks.
//        manager1.getEpic(3);
//        manager1.getTask(0);
//        manager1.getEpic(2);
//        manager1.getTask(1);
//        manager1.getTask(0);
//        manager1.getEpic(2);
//        // ---------- 10 changes to history below
//        // ---------- check it by ID
//        manager1.getTask(1);
//        manager1.getTask(0);
//        manager1.getEpic(2);
//        manager1.getTask(1);
//        manager1.getTask(0);
//        manager1.getEpic(2);
//        manager1.getEpic(2);
//        //
//        manager1.getTask(1);
//        manager1.getTask(0);
//        manager1.getSubTask(5);
//        manager1.getEpic(2);
//        manager1.getSubTask(4);
//        manager1.getTask(1);
//        manager1.getTask(1);
//        //
//        manager1.getSubTask(5);
//        manager1.getSubTask(5);
//        manager1.getTask(0);
//        manager1.getSubTask(6);
//        manager1.getEpic(2);
//        manager1.getTask(0);
//        manager1.getSubTask(4);
//        manager1.getEpic(2);
//        manager1.getTask(1);
//        manager1.getSubTask(6);

    }
}
