package example.taskmanager.task;

public class SubTask extends Task {

    private int epicID;

    public SubTask() {
        super();
    }

    public SubTask(String nameTask, String descriptionTask) {
        super(nameTask, descriptionTask);
    }

    public SubTask(int epicID) {
        this.epicID = epicID;
    }

    public SubTask(String nameTask, String descriptionTask, int epicID) {
        super(nameTask, descriptionTask);
        this.epicID = epicID;
    }

    public int getEpicID() {
        return epicID;
    }

    public void setEpicID(int epicID) {
        this.epicID = epicID;
    }

    @Override
    public String toString() {
        return "SubTask{" + "nameTask=" + getNameTask() + ", descriptionTask=" + getDescriptionTask() +
                ", epicID=" + getEpicID() + ", taskID=" + getTaskID() + ", status=" + getStatus() + "}";
    }
}
