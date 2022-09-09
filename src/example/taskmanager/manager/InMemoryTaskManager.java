package example.taskmanager.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import example.taskmanager.task.Status;
import example.taskmanager.task.Task;
import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;

public class InMemoryTaskManager implements TaskManager {

    private final HashMap<Integer, Epic> epics = new HashMap<>();
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, SubTask> subTasks = new HashMap<>();
    int uniqueID = 0;
    private HistoryManager historyManager;

    @Override
    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

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

    public Task getTask(int id) throws Exception {
        if (tasks.containsKey(id)) {
            historyManager.add(tasks.get(id));
            return tasks.get(id);
        }
        throw new Exception("Missing ID.");
    }

    @Override
    public Epic getEpic(int id) throws Exception {
        if (epics.containsKey(id))  {
            historyManager.add(epics.get(id));
            return epics.get(id);
        }
        throw new Exception("Missing ID.");
    }

    @Override
    public SubTask getSubTask(int id) throws Exception {
        if (subTasks.containsKey(id))   {
            historyManager.add(subTasks.get(id));
            return subTasks.get(id);
        }
        throw new Exception("Missing ID.");
    }

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
            //todo: add implementation of remove( )from HistoryManager.
        }
        else if (epics.containsKey(id))	{
            // Removing all subtasks in this epic.
            ArrayList<Integer> subTasksIdToRemove = epics.get(id).getSubTasksID();
            for (Integer key :
                    subTasksIdToRemove) {
                subTasks.remove(key);
                //todo: add implementation of remove( )from HistoryManager.
            }
            // Removing epic.
            epics.remove(id);
            //todo: add implementation of remove( )from HistoryManager.
        }
        else if (subTasks.containsKey(id))	{
            epics.get(subTasks.get(id).getTaskID()).removeSubTask(id);
            subTasks.remove(id);
            //todo: add implementation of remove( )from HistoryManager.
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

    private int getUniqueID()	{
        return uniqueID++;
    }
}