package example.taskmanager.task;

public class Task implements Comparable<Task> {

    private Status status;
    private String nameTask;
    private String descriptionTask;
    private int taskID;

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

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" + "nameTask=" + nameTask +
                ", descriptionTask=" + descriptionTask +
                ", taskID=" + taskID + ", status=" + status + '}';
    }

    public String getNameTask() {
        return nameTask;
    }

    public String getDescriptionTask() {
        return descriptionTask;
    }

    public int getTaskId() {
        return taskID;
    }

    public Status getStatus() {
        return this.status;
    }

    @Override
    public int compareTo(Task taskToCompare) {
        return this.getTaskId() - taskToCompare.getTaskId();
    }
}