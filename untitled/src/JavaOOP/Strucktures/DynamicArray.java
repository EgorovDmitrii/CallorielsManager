package JavaOOP.Strucktures;

import java.util.Arrays;

public class DynamicArray {

  private int[] array;
  private int count;

  public DynamicArray(int size) {
    this.array = new int[size];

  }

  public DynamicArray() {
    this.array = new int[5];
  }

  public void add(int value) {
    if (this.count == this.array.length) {
      grow(array.length * 2);
    }
    this.array[this.count++] = value;
  }

  public void add(int[] arr) {
    add(arr, arr.length);
  }

  public void add(DynamicArray dynamicArray) {
    add(dynamicArray.array, dynamicArray.count);
  }

  private void add(final int[] arr, final int legnht) {
    //arr = new int[20];
    //legnht = 1;
    if (this.array.length - this.count < legnht) {
      grow(this.count + legnht);
    }
    System.arraycopy(arr,0, this.array, this.count, legnht);
    this.count += legnht;

  }

  private void grow(int legnht) {
    int[] newArray = new int[legnht];
    System.arraycopy(this.array, 0, newArray, 0, this.count);
    this.array = newArray;
  }

  public int[] toArray() {
    return Arrays.copyOf(this.array, this.count);
  }

  public void clear() {
    this.count = 0;
  }

  public boolean remove(final int value) {
    final int index = indexOf(value);

    if (index != -1) {
      removeByIndex(index);
      return true;
    }else {
      return false;
    }
  }

  private int indexOf(final  int value) {
    for (int i = 0; i < this.count; i++) {
      if (this.array[i] == value) {
        return i;
      }
    }
    return -1;
  }

  private void removeByIndex(final int index) {
    if (index < count -1) {
      for (int i = index; i < this.count - 1; i++) {
        this.array[i] = this.array[i + 1];
      }
    }
    this.count--;
  }

  public int[] getArray() {
    return array;
  }

  public int getCount() {
    return count;
  }

  public void setArray(int[] array) {
    this.array = array;
  }

  public void setCount(int count) {
    this.count = count;
  }
}
