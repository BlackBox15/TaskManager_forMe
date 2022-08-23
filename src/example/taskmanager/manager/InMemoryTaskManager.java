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
    private int uniqueId = 0;
    private HistoryManager historyManager;

    public void setHistoryManager(HistoryManager historyManager) {
        this.historyManager = historyManager;
    }

    @Override
    public List<Task> listAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> listAllEpics() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<SubTask> listAllSubTasks() {
        return new ArrayList<>(subTasks.values());
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
    public void addTask(Task task)	{
        task.setTaskId(getUniqueId());
        tasks.put(task.getTaskId(), task);
    }

    @Override
    public void addTask(Epic epic)	{
        epic.setTaskId(getUniqueId());
        epics.put(epic.getTaskId(), epic);
}

    @Override
    public void addTask(SubTask subTask)	{
        int epicId = subTask.getEpicId();

        subTask.setTaskId(getUniqueId());

        subTasks.put(subTask.getTaskId(), subTask);
        epics.get(epicId).addSubTask(subTask.getTaskId());
    }

    @Override
    public void addTask(SubTask subTask, Epic epic) {
        subTask.setTaskId(getUniqueId());
        subTask.setEpicId(epic.getTaskId());
        subTasks.put(subTask.getTaskId(), subTask);
        epics.get(epic.getTaskId()).addSubTask(subTask.getTaskId());
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
    }

    @Override
    public void updateSubTask(SubTask subTask) {

        int epicId = subTask.getEpicId();
        List<Integer> subTaskId;
        boolean allSubTasksDone = false;

        subTasks.put(subTask.getTaskId(), subTask);

        if (subTask.getStatus() == Status.IN_PROGRESS)   {
            epics.get(epicId).setStatus(Status.IN_PROGRESS);
        }
        else if (subTask.getStatus() == Status.DONE)   {
            allSubTasksDone = true;

            subTaskId = epics.get(epicId).getSubTaskId();

            for (Integer key :
                    subTaskId) {
                allSubTasksDone &= (subTasks.get(key).getStatus() == Status.DONE);
            }

            if (allSubTasksDone) {
                epics.get(epicId).setStatus(Status.DONE);
            }
            else {
                epics.get(epicId).setStatus(Status.IN_PROGRESS);
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
            List<Integer> subTasksIdToRemove = epics.get(id).getSubTaskId();
            for (Integer key :
                    subTasksIdToRemove) {
                subTasks.remove(key);
            }
            // Removing epic.
            epics.remove(id);
        }
        else if (subTasks.containsKey(id))	{
            epics.get(subTasks.get(id).getTaskId()).removeSubTask(id);
            subTasks.remove(id);
        }
    }

    @Override
    public List<SubTask> listSubTasksByEpic(Epic epic)   {
        int epicId = epic.getTaskId();
        ArrayList<SubTask> subTasksByEpic = new ArrayList<>();

        if (epics.containsKey(epicId))  {
            subTasksByEpic = (ArrayList<SubTask>) subTasks.values();

            for (SubTask subtask :
                    subTasksByEpic) {
                if (subtask.getEpicId() != epicId)  {
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