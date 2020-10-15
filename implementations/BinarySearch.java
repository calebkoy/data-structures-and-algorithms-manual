public class BinarySearch {
  public int search(int[] array, int element) {
    if (array.length == 0) return -1;
    int low = 0; 
    int high = array.length - 1;
    int mid;
    while (low <= high) {
      mid = (low + high) / 2;
      if (array[mid] < element) {
        low = mid + 1;
        continue;
      } else if (array[mid] > element) {
        high = mid - 1;
        continue;
      } else {
        return mid;
      }
    }
    return -1; // Didn't find the element
  }
}
