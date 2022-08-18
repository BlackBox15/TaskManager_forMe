package example.taskmanager.manager;

import example.taskmanager.task.Epic;
import example.taskmanager.task.Status;
import example.taskmanager.task.SubTask;
import example.taskmanager.task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, SubTask> subTasks = new HashMap<>();

    int uniqueID = 0;
    final String NEW = "NEW";
    final String IN_PROGRESS = "IN_PROGRESS";
    final String DONE = "DONE";

    @Override
    public Collection<Task> listAllTasks() {
        return tasks.values();
    }

    @Override
    public Collection<Epic> listAllEpics() {
        return epics.values();
    }

    @Override
    public Collection<SubTask> listAllSubTasks() {
        return subTasks.values();
    }

    @Override
    public void removeAll()	{

        tasks.clear();
        subTasks.clear();
        epics.clear();

        uniqueID = 0;
    }

    public Task getTask(int id) {
        if (tasks.containsKey(id)) {
            return tasks.get(id);
        }
        return null;
    }

    @Override
    public Epic getEpic(int id) {
        if (epics.containsKey(id))  {
            return epics.get(id);
        }
        return null;
    }

    @Override
    public SubTask getSubTask(int id)  {
        if (subTasks.containsKey(id))   {
            return subTasks.get(id);
        }
        return null;
    }

// Add all type tasks.
    @Override
    public void addTask(Task task)	{
        task.setTaskID(getUniqueID());
        tasks.put(task.getTaskID(), task);
    }

    @Override
    public void addTask(Epic epic)	{
        epic.setTaskID(getUniqueID());
        epics.put(epic.getTaskID(), epic);
    }

    @Override
    public void addTask(SubTask subTask)	{
        int epicID = subTask.getEpicID();

        subTask.setTaskID(getUniqueID());

        subTasks.put(subTask.getTaskID(), subTask);
        epics.get(epicID).addSubTask(subTask.getTaskID());
    }

    @Override
    public void addTask(SubTask subTask, Epic epic) {
        subTask.setTaskID(getUniqueID());
        subTask.setEpicID(epic.getTaskID());
        subTasks.put(subTask.getTaskID(), subTask);
        epics.get(epic.getTaskID()).addSubTask(subTask.getTaskID());
    }

// Update all type tasks.
    @Override
    public void updateTask(Task task) {
        tasks.put(task.getTaskID(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskID(), epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {

        int epicID = subTask.getEpicID();
        ArrayList<Integer> subTasksID;
        boolean allSubTasksDone = false;

        subTasks.put(subTask.getTaskID(), subTask);

        if (subTask.getStatus() == Status.IN_PROGRESS)   {
            epics.get(epicID).setStatus(Status.IN_PROGRESS);
        }
        else if (subTask.getStatus() == Status.DONE)   {
            allSubTasksDone = true;

            subTasksID = epics.get(epicID).getSubTasksID();

            for (Integer key :
                    subTasksID) {
                allSubTasksDone &= (subTasks.get(key).getStatus() == Status.DONE);
            }

            if (allSubTasksDone) {
                epics.get(epicID).setStatus(Status.DONE);
            }
            else {
                epics.get(epicID).setStatus(Status.IN_PROGRESS);
            }
        }

    }

    @Override
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

    @Override
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

    @Override
//    Этот метод может быть убрать из интерфейса, поскольку он носит роль вспомогательного.
    public int getUniqueID()	{
        return uniqueID++;
    }
}