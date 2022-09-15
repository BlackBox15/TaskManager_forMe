package example.taskmanager.manager;

import example.taskmanager.task.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager{

    private static final int MAX_HISTORY = 10;
    private final Map<Integer, Node<Task>> historyMap = new HashMap<>();
    private Node<Task> head;
    private Node<Task> tail;
    private int size = 0;

    private Node<Task> linkLast(Task task) {
        Node<Task> newNode;
        Node<Task> oldTail = tail;

        if (size >= MAX_HISTORY)    {
            historyMap.remove(head.getData().getTaskID());
            removeNode(head);
        }

        newNode = new Node<>(oldTail, task, null);
        tail = newNode;

        if (oldTail == null)
            head = newNode;
        else
            oldTail.setNext(newNode);
        size++;

        return newNode;
    }

    private void removeNode(Node<Task> node)  {
        Node<Task> oldPrev = node.getPrev();       // Previous Node before removing.
        Node<Task> oldNext = node.getNext();       // Next Node before removing.

        // Removing node if it's a HEAD of the LinkedList.
        if (node.getPrev() == null) {
            node.getNext().setPrev(null);
            head = node.getNext();
        }
        // Removing node if it's a TAIL of the LinkedList.
        else if (node.getNext() == null) {
            node.getPrev().setNext(null);
        }
        else {
            node.getNext().setPrev(oldPrev);
            node.getPrev().setNext(oldNext);
        }
        size--;
    }

    private List<Task> getTasks() {

        ArrayList<Task> arrayList = new ArrayList<>();
        Node<Task> currentNode = head;

        while (currentNode != null) {
            arrayList.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }
        return arrayList;
    }

    @Override
    public void remove(int id) {

        if (historyMap.containsKey(id)) {
            removeNode(historyMap.remove(id));
            historyMap.remove(id);
        }
    }

    @Override
    public void add(Task task) {
        if (historyMap.containsKey(task.getTaskID())) {
            removeNode(historyMap.get(task.getTaskID()));
            historyMap.remove(task.getTaskID());
        }
        historyMap.put(task.getTaskID(), linkLast(task));
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }
}
