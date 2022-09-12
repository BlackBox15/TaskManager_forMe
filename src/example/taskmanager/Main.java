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

        Task task1 = new Task("task1_name", "task1_description");
        Task task2 = new Task("task2_name", "task2_description");
        Epic epic1 = new Epic("epic1_name", "epic1_description");
        Epic epic2 = new Epic("epic2_name", "epic2_description");
        SubTask sub1_1 = new SubTask("sub1_1_name", "sub1_1_description");
        SubTask sub1_2 = new SubTask("sub1_2_name", "sub1_2_description");
        SubTask sub1_3 = new SubTask("sub1_3_name", "sub1_3_description");

        manager1.addTask(task1);
        manager1.addTask(task2);
        manager1.addTask(epic1);
        manager1.addTask(epic2);
        manager1.addTask(sub1_1);
        manager1.addTask(sub1_2);
        manager1.addTask(sub1_3);

        // Test output (using customizable toString()).
        System.out.println(manager1.listAllTasks());
        System.out.println(manager1.listAllEpics());
        System.out.println(manager1.listAllSubTasks());

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


        System.out.println(historyManager.getHistory());
    }
}
