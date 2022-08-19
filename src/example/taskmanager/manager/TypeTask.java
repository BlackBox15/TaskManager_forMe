package example.taskmanager.manager;

public enum TypeTask {
    Epic ("Epic"),
    Task ("Task"),
    SubTask ("SubTask");

    private String title;

    TypeTask(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "TypeTask{" +
                "title='" + title + '\'' +
                '}';
    }
}
