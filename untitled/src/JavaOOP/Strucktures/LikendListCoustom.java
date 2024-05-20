package JavaOOP.Strucktures;

public class LikendListCoustom {

  private Item firsItem;
  private Item lastItem;

  public void add(final int value) {
    Item currentItem = new Item(value);

    if (firsItem == null) {
      firsItem = lastItem = currentItem;
    } else {
      lastItem.nextLink = currentItem;
      lastItem = currentItem;
    }

  }

  public int[] toArray() {
    DynamicArray dynamicArray = new DynamicArray();
    Item current = firsItem;

    while (current != null) {
      dynamicArray.add(current.value);
      current = current.nextLink;
    }
    return dynamicArray.toArray();
  }

  public String asString() {
    StringBuilder result = new StringBuilder();
    result.append("[ ");

    Item current = firsItem;
    while (current != null) {
      result.append(current.value);

      if (current.nextLink != null) {
        result.append(", ");
      }
      current = current.nextLink;
    }

    result.append(" ]");
    return result.toString();
  }

  public void clear() {
    this.firsItem = null;
    this.lastItem = null;
  }

  public boolean remove(final int value) {
    final Pair pair = findPair(value);

    if (pair != null) {
      if (pair.current == firsItem && pair.current == lastItem) {          // означает что в списке у нас только один элемент
        this.firsItem = null;
        this.lastItem = null;
      } else if (pair.current == this.firsItem) {             // хотим удалить первый элемент из списка (ставим ссылку на следующий элемент по отношению к предидущему
        this.firsItem = pair.current.nextLink;
      } else {
        // запрашиваем предидущий элемент по отношению к текущему
        // и в его поле nextLink записываем то значение, которое записано в поле nextLink текущего элемента
        pair.previous.nextLink = pair.current.nextLink;
        if (pair.current == this.lastItem) {                  // удаляется элемент в конце списка
          this.lastItem = pair.previous;                     // удаляем ссылку last 
        }
      }
      return true;
    }
    return false;
  }

  private Pair findPair(int element) {
    Item previosItem = this.firsItem;
    Item currentItem = this.firsItem;

    while (currentItem != null) {
      if (currentItem.value == element) {
        return new Pair(previosItem, currentItem);
      }else {
        previosItem = currentItem;
        currentItem = currentItem.nextLink;
      }
    }
    return null;
  }

  private static class Pair {

    private final Item previous;
    private final Item current;

    public Pair(Item previous, Item current) {
      this.previous = previous;
      this.current = current;
    }
  }

  private static class Item {

    int value;
    Item nextLink;

    public Item(int value) {
      this.value = value;
    }
  }


}
