package main.java;

public class Main {

    public static void main(String[] args) {
        ProductWarehouseWithHistory juice = new ProductWarehouseWithHistory("Juice", 1000.0, 1000.0);
        juice.takeFromWarehouse(11.3);
        juice.addToWarehouse(1.0);

        ProductWarehouseWithHistory pwh = new ProductWarehouseWithHistory("coffee", 10, 5);
        double b = pwh.takeFromWarehouse(7);
        System.out.println(b); // [1000.0, 988.7, 989.7]

        juice.printAnalysis();
        pwh.printAnalysis();
    }
}
