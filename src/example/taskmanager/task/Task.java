package example.taskmanager.task;

public class Task {

    final private String NEW = "NEW";
    final private String IN_PROGRESS = "IN_PROGRESS";
    final private String DONE = "DONE";

    private Status status1;
    private String nameTask;
    private String descriptionTask;
    private int taskID;
    private String status;

    // We have assign NEW-status to the new object.
    // Empty task in this case.
    public Task() {
//        setStatus(NEW);
        this.status1 = Status.NEW;
    }

    // Task with descriptions in this case.
    public Task(String nameTask, String descriptionTask) {
//        setStatus(NEW);
        this.status1 = Status.NEW;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setStatus(String status)   {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" + "nameTask=" + nameTask + ", descriptionTask=" + descriptionTask + ", taskID=" + taskID + ", status=" + status + '}';
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getStatus() {
        return status;
    }

}