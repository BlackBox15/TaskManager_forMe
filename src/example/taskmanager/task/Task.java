package example.taskmanager.task;

public class Task {

    private Status status;
    private String nameTask;
    private String descriptionTask;
    private int taskId;

    public Task(String nameTask, String descriptionTask) {
        this.status = Status.NEW;
        this.nameTask = nameTask;
        this.descriptionTask = descriptionTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public void setDescriptionTask(String descriptionTask) {
        this.descriptionTask = descriptionTask;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" + "nameTask=" + nameTask + ", descriptionTask=" + descriptionTask + ", taskID=" + taskId + ", status=" + status + '}';
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public int getTaskId() {
        return taskId;
    }

    public Status getStatus() {
        return this.status;
    }

}