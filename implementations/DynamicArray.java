@SuppressWarnings("unchecked")
public class DynamicArray<E> {
  private E[] array;
  private int size = 0;
  private int capacity = 0;

  DynamicArray() {
    this(10);
  }
    
  DynamicArray(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("Initial capacity must be > 0; got: " +
        initialCapacity);
    }
    array = (E[]) new Object[initialCapacity];
    capacity = initialCapacity;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }
  
  public boolean add(E element) {
    if (size < capacity) {
      array[size++] = element;
      return true;
    }

    capacity *= 2;
    E[] newArray = (E[]) new Object[capacity];
    for (int i = 0; i < array.length; i++) {
      newArray[i] = array[i];
    }
    newArray[size++] = element;
    array = newArray;
    return true;
  }  

  public E get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of bounds; got: " + 
        index);
    }

    return array[index];
  }  

  public E set(int index, E element) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of bounds; got:" + 
        index);
    }
    E previousElement = array[index];
    array[index] = element;
    return previousElement;
  }
  
  public E remove(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index is out of bounds; got: " + 
        index);
    }

    E element = array[index];
    E[] newArray = (E[]) new Object[size - 1];
    for (int i = 0, j = 0; i < size; i++, j++) {
      if (i == index) j--;        
      else newArray[j] = array[i];
    }
    array = newArray;
    capacity = --size; 
    return element;
  }          
}