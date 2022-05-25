package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Manager {

    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    int uniqueID = 0;
    final String NEW = "NEW";
    final String IN_PROGRESS = "IN_PROGRESS";
    final String DONE = "DONE";


    public Collection<Task> listAllTasks() {
        return tasks.values();
    }

    public Collection<Epic> listAllEpics() {
        return epics.values();
    }

    public Collection<SubTask> listAllSubTasks() {
        return subTasks.values();
    }

    public void removeAll()	{

        tasks.clear();
        subTasks.clear();
        epics.clear();

        uniqueID = 0;
    }

    public Task takeTaskByID(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }

    public Epic takeEpicByID(int id) {
        if (epics.containsKey(id))  {
            return epics.get(id);
        }
        return null;
    }

    public SubTask takeSubTaskByID(int id)  {
        if (subTasks.containsKey(id))   {
            return subTasks.get(id);
        }
        return null;
    }

// Add all type tasks.
    public void addTask(Task task)	{
        task.setTaskID(getUniqueID());
        tasks.put(task.getTaskID(), task);
    }

    public void addTask(Epic epic)	{
        epic.setTaskID(getUniqueID());
        epics.put(epic.getTaskID(), epic);
    }

    public void addTask(SubTask subTask)	{
        int epicID = subTask.getEpicID();

        subTask.setTaskID(getUniqueID());

        subTasks.put(subTask.getTaskID(), subTask);
        epics.get(epicID).addSubTask(subTask.getTaskID());
    }

    public void addTask(SubTask subTask, Epic epic) {
        subTask.setTaskID(getUniqueID());
        subTask.setEpicID(epic.getTaskID());
        subTasks.put(subTask.getTaskID(), subTask);
        epics.get(epic.getTaskID()).addSubTask(subTask.getTaskID());
    }

// Update all type tasks.
    public void updateTask(Task task) {
        tasks.put(task.getTaskID(), task);
    }

    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskID(), epic);
    }

    public void updateSubTask(SubTask subTask) {

        int epicID = subTask.getEpicID();
        ArrayList<Integer> subTasksID;
        boolean allSubTasksDone = false;

        subTasks.put(subTask.getTaskID(), subTask);

        if (subTask.getStatus() == IN_PROGRESS)   {
            epics.get(epicID).setStatus(IN_PROGRESS);
        }
        else if (subTask.getStatus() == DONE)   {
            allSubTasksDone = true;

            subTasksID = epics.get(epicID).getSubTasksID();

            for (Integer key :
                    subTasksID) {
                allSubTasksDone &= (subTasks.get(key).getStatus() == DONE);
            }

            if (allSubTasksDone) {
                epics.get(epicID).setStatus(DONE);
            }
            else {
                epics.get(epicID).setStatus(IN_PROGRESS);
            }
        }

    }

    public void removeById(int id)	{

        if (tasks.containsKey(id))	{
            tasks.remove(id);
        }
        else if (epics.containsKey(id))	{
            // Removing all subtasks in this epic.
            ArrayList<Integer> subTasksIdToRemove = epics.get(id).getSubTasksID();
            for (Integer key :
                    subTasksIdToRemove) {
                subTasks.remove(key);
            }
            // Removing epic.
            epics.remove(id);
        }
        else if (subTasks.containsKey(id))	{
            // Удаление id из массива подзадач эпика.
            epics.get(subTasks.get(id).getTaskID()).removeSubTask(id);
            // Удаление самой подзадачи.
            subTasks.remove(id);
        }
    }

    public ArrayList<SubTask> listSubTasksByEpic(Epic epic)   {
        int epicID = epic.getTaskID();
        ArrayList<SubTask> subTasksByEpic = new ArrayList<>();

        if (epics.containsKey(epicID))  {
            subTasksByEpic = (ArrayList<SubTask>) subTasks.values();

            for (SubTask subtask :
                    subTasksByEpic) {
                if (subtask.getEpicID() != epicID)  {
                    subTasksByEpic.remove(subtask);
                }
            }
        }
        return subTasksByEpic;
    }

    private int getUniqueID()	{
        return uniqueID++;
    }
}