package example.taskmanager.task;

import java.util.ArrayList;

public class Epic extends Task{

    private ArrayList<Integer> subTasksID = new ArrayList<>();

    public Epic() {
        super();
    }

    public Epic(String nameTask, String descriptionTask) {
        super(nameTask, descriptionTask);
    }

    public ArrayList<Integer> getSubTasksID()   {
        return subTasksID;
    }

    public void addSubTask(int id) {
        subTasksID.add(id);
    }

    public void removeSubTask(int id)    {
        subTasksID.remove(id);
    }

    @Override
    public String toString() {
        return "Epic{" + "nameTask=" + getNameTask() + ", descriptionTask=" + getDescriptionTask() + ", taskID=" +
                getTaskID() + ", status=" + getStatus() + "}";
    }
}
