import java.util.LinkedList;

public class Queue<E> {      
  private LinkedList<E> list = new LinkedList<>();
  
  public boolean isEmpty() {
    return list.size() == 0;
  }
  
  public boolean offer(E element) {
    list.addLast(element);
    return true;
  }

  public E peek() {
    if (isEmpty()) return null;
    return list.peekFirst();
  }

  public E poll() {
    if (isEmpty()) return null;
    return list.removeFirst();
  }
}
