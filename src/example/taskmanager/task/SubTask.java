package example.taskmanager.task;

public class SubTask extends Task {

    // Parent Epic ID.
    private int epicId;

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
                ", epicId=" + getEpicId() + ", taskID=" + getTaskId() + ", status=" + getStatus() + "}";
    }
}
