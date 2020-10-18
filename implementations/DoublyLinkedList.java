import java.util.NoSuchElementException;

public class DoublyLinkedList<E> {
  private Node<E> head, tail;
  private int size;
  
  private static class Node<E> {
    private Node<E> previous, next;
    private E data;
    
    public Node(Node<E> previous, Node<E> next, E data) {
      this.previous = previous;
      this.next = next;
      this.data = data;
    }
  }
  
  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size == 0;
  }
  
  // Assume this list permits duplicates.
  public void add(E element) {
    addLast(element);
  }
  
  // Insert specified element at specified index
  public void add(int index, E element) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index; got: " + index);
    }

    if (index == 0) {
      addFirst(element);
      return; 
    }

    if (index == size) {
      addLast(element);
      return;
    }
                
    int i;
    Node<E> traverser;
    if (index > size/2) {
      for (i = size-1, traverser = tail; i > index; i--) {
        traverser = traverser.previous;
      }
    } else {
      for (i = 0, traverser = head; i < index; i++) {
        traverser = traverser.next;
      }
    }        
    Node<E> newNode = new Node<>(traverser.previous, traverser, element);                
    traverser.previous.next = newNode;
    traverser.previous = newNode;    
    size++;
  }
  
  public void addFirst(E element) {    
    if (isEmpty()) {
      head = tail = new Node<>(null, null, element);
    } else {
      head = head.previous = new Node<>(null, head, element);      
    }
    size++;            
  }
  
  public void addLast(E element) {
    if (isEmpty()) {
      head = tail = new Node<>(null, null, element);
    } else {
      tail = tail.next = new Node<>(tail, null, element);
    }
    size++;        
  }
  
  public E getFirst() {
    return head.data;
  }
  
  public E getLast() {
    return tail.data;
  }
  
  public E get(int index) {    
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index; got: " + index);
    }

    if (index == 0) return getFirst();
    if (index == size-1) return getLast();
    
    int i;
    Node<E> traverser;
    if (index > size/2) {      
      for (i = size-1, traverser = tail; i > index; i--) {
        traverser = traverser.previous;
      }
    } else {      
      for (i = 0, traverser = head; i < index; i++) {
        traverser = traverser.next;
      }
    }
    return traverser.data;    
  }
  
  public E removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove from empty list");
    }
    
    E data = head.data;    
    if (head.next == null) {            
      tail = null;
    } else {      
      head.next.previous = null;                        
    } 
    head = head.next;           
    size--;        
    return data;    
  }
  
  public E removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot remove from empty list");
    }
    
    E data = tail.data;
    if (tail.previous == null) {
      head = null;
    } else {
      tail.previous.next = null;
    }
    tail = tail.previous;    
    size--;
    return data;    
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Invalid index; got: " + index);
    }

    if (index == 0) return removeFirst();
    if (index == size-1) return removeLast();

    int i;
    Node<E> traverser;
    if (index > size/2) {      
      for (i = size-1, traverser = tail; i > index; i--) {
        traverser = traverser.previous;
      }
    } else {      
      for (i = 0, traverser = head; i < index; i++) {
        traverser = traverser.next;
      }
    }
    E data = traverser.data;
    traverser.previous.next = traverser.next;
    traverser.next.previous = traverser.previous;
    traverser = null;
    size--;
    return data;
  }
  
  public int indexOf(Object object) {    
    if (isEmpty()) return -1;
    
    int index = 0;
    Node<E> traverser = head;
    if (object == null) {
      for (; traverser != null; traverser = traverser.next, index++) {        
        if (traverser.data == null) return index;
      }
    } else {
      for (; traverser != null; traverser = traverser.next, index++) {        
        if (object.equals(traverser.data)) return index;
      }
    }
    return -1;
  }
  
  public boolean contains(Object object) {
    return indexOf(object) != -1;
  }
}
