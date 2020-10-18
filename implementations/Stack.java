import java.util.EmptyStackException;
import java.util.LinkedList;

public class Stack<E> {
  private LinkedList<E> list;  
  
  public int size() {
    return list.size();    
  }

  public boolean empty() {
    return list.size() == 0;
  }    
  
  public E push(E element) {
    list.addLast(element);
    return element;
  }
  
  public E peek() {
    if (empty()) throw new EmptyStackException();
    return list.peekLast();
  }
  
  public E pop() {
    if (empty()) throw new EmptyStackException();
    return list.removeLast();
  }  
}