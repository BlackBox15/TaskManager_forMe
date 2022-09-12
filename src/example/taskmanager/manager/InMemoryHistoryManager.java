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


    private Node<Task> linkLast(Task task) {
        Node<Task> newNode;
        Node<Task> oldTail = tail;

        newNode = new Node<>(oldTail, task, null);
        tail = newNode;
        oldTail.next = newNode;
        size++;
        return newNode;
    }

    private void removeNode(Node node)  {
        taskList.remove(node);
    }

    private ArrayList<Task> getTasks() {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (Task task :
                taskList) {
            arrayList.add(task);
        }

        return arrayList;
    }

    @Override
    public void remove(int id) {
        if (historyMap.containsKey(id)) {
            removeNode(historyMap.remove(id));
        }
    }

    @Override
    public void add(Task task) {
        if (historyMap.containsKey(task.getTaskID())) {
            removeNode(historyMap.get(task.getTaskID()));
        }
        historyMap.put(task.getTaskID(), linkLast(task));
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}
