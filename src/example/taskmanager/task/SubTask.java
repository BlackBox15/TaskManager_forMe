package example.taskmanager.task;

public class SubTask extends Task {

    private int epicId;     // Parent Epic ID.

    public SubTask(String nameTask, String descriptionTask, int epicId) {
        super(nameTask, descriptionTask);
        this.epicId = epicId;
    }

    public SubTask(String nameTask, String descriptionTask) {
        super(nameTask, descriptionTask);
    }

    public int getEpicId() {
        return this.epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" + "nameTask=" + getNameTask() + ", descriptionTask=" + getDescriptionTask() +
                ", epicID=" + getEpicId() + ", taskID=" + getTaskId() + ", status=" + getStatus() + "}";
    }
}
