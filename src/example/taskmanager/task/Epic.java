package example.taskmanager.task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task{

    private List<Integer> subTaskId = new ArrayList<>();

    public Epic(String nameTask, String descriptionTask) {
        super(nameTask, descriptionTask);
    }

    public List<Integer> getSubTaskId()   {
        return subTaskId;
    }

    public void addSubTask(int id) {
        subTaskId.add(id);
    }

    public void removeSubTask(int id)    {
        subTaskId.remove(id);
    }

    @Override
    public String toString() {
        return "Epic{" + "nameTask=" + getNameTask() + ", descriptionTask=" + getDescriptionTask() + ", taskID=" +
                getTaskId() + ", status=" + getStatus() + "}";
    }
}
