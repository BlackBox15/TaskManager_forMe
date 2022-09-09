package example.taskmanager.manager;

import example.taskmanager.task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager{


    private final Integer MAX_HISTORY = 10;
    private final List<Task> historyList = new ArrayList<>();
    private Map<Integer, Node<Task>> historyMap = new HashMap<>();
    private List<Task> taskList = new LinkedList<>();
    private Node<Task> head;
    // Указатель на последний элемент списка. Он же last
    private Node<Task> tail;
    private int size = 0;


    public void linkLast(Task element) {
        Node<Task> oldTail = tail;
        Node<Task> newNode = new Node<>(oldTail, element, null);
        tail = newNode;
        oldTail.next = newNode;
        size++;
    }

    public void removeNode(Node node)  {

    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (Task task :
                taskList) {
            arrayList.add(task);
        }

        return arrayList;
    }

    @Override
    public void remove(int id) {
        historyList.remove(id);
    }

    @Override
    public void add(Task task) {
        if(MAX_HISTORY <= historyList.size()) {
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public List<Task> getHistory() {
        return historyList;
    }
}
