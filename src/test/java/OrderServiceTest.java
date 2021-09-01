import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;
    OrderService orderService = new OrderService();

    @BeforeEach
    public void init(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie", 319);

        orderService.selectItem(new Item("Sweet corn soup",119));
    }

    @Test
    public void selecting_item_should_increase_list_of_menu_by_1(){

        int initialSize = orderService.getSelectedItems().size();
        orderService.selectItem(new Item("Vegetable lasagne", 269));
        assertEquals(initialSize + 1, orderService.getSelectedItems().size());

    }

    @Test
    public void unselecting_item_should_decrease_list_of_menu_by_1(){

        int initialSize = orderService.getSelectedItems().size();
        Item item = orderService.getSelectedItems().get(0);
        orderService.unselectItem(item);
        assertEquals(initialSize - 1, orderService.getSelectedItems().size());
    }


    @Test
    public void get_correct_order_value(){
        assertEquals(119, orderService.getOrderValue());
    }

    @Test
    public void selecting_item_should_return_increased_order_value(){
        orderService.selectItem(new Item("Vegetable lasagne", 269));
        assertEquals(388, orderService.getOrderValue());
    }

    @Test
    public void unselecting_item_should_return_decreased_order_value(){
        Item item = orderService.getSelectedItems().get(0);
        orderService.unselectItem(item);
        assertEquals(0, orderService.getOrderValue());
    }
}
