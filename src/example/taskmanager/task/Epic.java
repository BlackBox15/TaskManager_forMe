package example.taskmanager.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private List<Integer> subTasksID = new ArrayList<>();

    public Epic(String nameTask, String descriptionTask) {
        super(nameTask, descriptionTask);
    }

    public List<Integer> getSubTasksId()   {
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
