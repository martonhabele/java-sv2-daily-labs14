package day01;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class OrderService {
    private List<Order> orders = new ArrayList<>();

    public void saveOrder(Order order) {
        orders.add(order);
    }

    public List<Order> findOrderByStatus(String status) {
        return orders
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public long countOrdersByStatus(String status) {
        return orders
                .stream()
                .filter(order -> order.getStatus().equals(status))
                .count();
    }

    public List<Product> findProductsOverPrice(int amount) {
        return orders
                .stream()
                .flatMap(order -> order.getProducts().stream())
                .filter(product -> product.getPrice() > amount)
                .distinct()
                .toList();
    }

    public String orderToString(Order order) {
        return order.getStatus() + " " + order.getOrderDate().toString();
    }

    public List<Order> getOrdersBetweenDates(LocalDate start, LocalDate end) {
        return orders
                .stream()
                .filter(o -> o.getOrderDate().isAfter(start))
                .filter(o -> o.getOrderDate().isBefore(end))
                .collect(Collectors.toList());
    }

    public List<Order> sortOrdersByStatusAndOrderDate() {
        return orders
                .stream()
                .sorted(Comparator.comparing(Order::getOrderDate))
                .sorted(Comparator.comparing(Order::getStatus))
                .toList();
    }

    public boolean isOrderWithLessProductThan(int number) {
        return orders
                .stream()
                .mapToInt(o -> o.getProducts().size())
                .anyMatch(i -> i < number);
    }

    public Order getOrderWithMaxNumberOfProducts() {
        return orders.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(o -> o.getProducts().size())))
                .findFirst().orElseThrow(() -> new IllegalStateException("Empty"));
    }

    public List<Order> getOrdersWithProductInCategory(String category) {
        return orders.stream()
                .filter(o -> o.getProducts().stream().anyMatch(p -> p.getCategory().equals(category)))
                .toList();
    }
}