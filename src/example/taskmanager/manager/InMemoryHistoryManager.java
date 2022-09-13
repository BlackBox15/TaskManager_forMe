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

        if (size >= MAX_HISTORY)    {
            removeNode(head);
        }

        newNode = new Node<>(oldTail, task, null);
        tail = newNode;

        if (oldTail == null)
            head = newNode;
        else
            oldTail.next = newNode;
        size++;

        return newNode;
    }

    private void removeNode(Node<Task> node)  {
        Node<Task> oldPrev = node.prev;       // Previous Node before removing.
        Node<Task> oldNext = node.next;       // Next Node before removing.

        // Removing a HEAD of the LinkedList.
        if (node.prev == null) {
            node.next.prev = null;
            head = node.next;
        }
        // Removing a TAIL of the LinkedList.
        else if (node.next == null) {
            node.prev.next = null;
        }
        else {
            node.next.prev = oldPrev;
            node.prev.next = oldNext;
        }
        size--;
    }

    private ArrayList<Task> getTasks() {

        ArrayList<Task> arrayList = new ArrayList<>();
        Node<Task> currentNode = head;

        while (currentNode != null) {
            arrayList.add(currentNode.data);
            currentNode = currentNode.next;
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
        // If we already have the instance of task we should remove it from history list, and then add it as last member.
        // If we have not this task in our historyMap, we just put it in.
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
