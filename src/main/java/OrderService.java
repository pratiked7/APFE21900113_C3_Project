import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static List<Item> selectedItems = new ArrayList<>();


    public List<Item> getSelectedItems(){
        return selectedItems;
    }

    public void selectItem(Item item){
        selectedItems.add(item);
    }

    public void unselectItem(Item item){
        selectedItems.remove(item);
    }

    public int getOrderValue(){
        int orderValue = 0;
        for (Item item : selectedItems){
            orderValue += item.getPrice();
        }
        return orderValue;
    }
}
