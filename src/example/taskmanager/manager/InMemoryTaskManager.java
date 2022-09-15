package example.taskmanager.manager;

import java.util.*;

import example.taskmanager.task.Status;
import example.taskmanager.task.Task;
import example.taskmanager.task.Epic;
import example.taskmanager.task.SubTask;

public class InMemoryTaskManager implements TaskManager {

    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();
    int uniqueId = 0;
    private HistoryManager historyManager;

    @Override
    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public List<Task> listAllTasks() {
        return new ArrayList<Task>(tasks.values());
    }

    @Override
    public List<Epic> listAllEpics() {
        return new ArrayList<Epic>(epics.values());
    }

    @Override
    public List<SubTask> listAllSubTasks() {
        return new ArrayList<SubTask>(subTasks.values());
    }

    @Override
    public void removeAll()	{
        tasks.clear();
        subTasks.clear();
        epics.clear();
        uniqueId = 0;
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
    public void addTask(Task task) {
        task.setTaskID(getUniqueId());
        tasks.put(task.getTaskID(), task);
        historyManager.add(task);
    }

    @Override
    public void addTask(Epic epic) {
        epic.setTaskID(getUniqueId());
        epics.put(epic.getTaskID(), epic);
        historyManager.add(epic);
}

    @Override
    public void addTask(SubTask subTask) {
        int epicID = subTask.getEpicId();

        subTask.setTaskID(getUniqueId());

        subTasks.put(subTask.getTaskID(), subTask);
        epics.get(epicID).addSubTask(subTask.getTaskID());
        historyManager.add(subTask);
    }

    @Override
    public void addTask(SubTask subTask, Epic epic) {
        subTask.setTaskID(getUniqueId());
        subTask.setEpicId(epic.getTaskID());
        subTasks.put(subTask.getTaskID(), subTask);
        epics.get(epic.getTaskID()).addSubTask(subTask.getTaskID());
        historyManager.add(subTask);
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getTaskID(), task);
        historyManager.add(task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskID(), epic);
        historyManager.add(epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {

        int epicId = subTask.getEpicId();
        List<Integer> subTasksId;
        boolean allSubTasksDone = false;

        subTasks.put(subTask.getTaskID(), subTask);

        if (subTask.getStatus() == Status.IN_PROGRESS)   {
            epics.get(epicId).setStatus(Status.IN_PROGRESS);
        }
        else if (subTask.getStatus() == Status.DONE)   {
            allSubTasksDone = true;

            subTasksId = epics.get(epicId).getSubTasksId();

            for (Integer key :
                    subTasksId) {
                allSubTasksDone &= (subTasks.get(key).getStatus() == Status.DONE);
            }

            if (allSubTasksDone) {
                epics.get(epicId).setStatus(Status.DONE);
            }
            else {
                epics.get(epicId).setStatus(Status.IN_PROGRESS);
            }
        }
        historyManager.add(subTask);
    }

    @Override
    public void removeById(int id)	{

        if (tasks.containsKey(id))	{
            tasks.remove(id);
            historyManager.remove(id);
        }
        else if (epics.containsKey(id))	{
            List<Integer> subTasksIdToRemove = epics.get(id).getSubTasksId();
            for (Integer key :
                    subTasksIdToRemove) {
                subTasks.remove(key);
                historyManager.remove(key);
            }
            epics.remove(id);
            historyManager.remove(id);
        }
        else if (subTasks.containsKey(id))	{
            epics.get(subTasks.get(id).getTaskID()).removeSubTask(id);
            subTasks.remove(id);
            historyManager.remove(id);
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
                if (subtask.getEpicId() != epicID)  {
                    subTasksByEpic.remove(subtask);
                }
            }
        }
        return subTasksByEpic;
    }

    private int getUniqueId()	{
        return uniqueId++;
    }
}