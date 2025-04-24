package inventory_Management_System;

import java.util.List;
import java.util.Scanner;

public class InventoryApp {
    public static void main(String[] args) {
        try {
            ProductDAO dao = new ProductDAO();
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n=== Inventory Management ===");
                System.out.println("1. Add Product");
                System.out.println("2. Update Stock");
                System.out.println("3. Delete Product");
                System.out.println("4. Show All Products");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Product Name: ");
                        sc.nextLine();
                        String name = sc.nextLine();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();
                        dao.addProduct(new Product(name, qty));
                        System.out.println("Product added!");
                        break;
                    case 2:
                        System.out.print("Product ID: ");
                        int id = sc.nextInt();
                        System.out.print("Quantity to add: ");
                        int quantity = sc.nextInt();
                        dao.updateProductStock(id, quantity);
                        System.out.println("Stock updated!");
                        break;
                    case 3:
                        System.out.print("Product ID to delete: ");
                        int delId = sc.nextInt();
                        dao.deleteProduct(delId);
                        System.out.println("Product deleted.");
                        break;
                    case 4:
                        List<Product> products = dao.getAllProducts();
                        System.out.println("Product List:");
                        for (Product p : products) {
                            System.out.println("ID: " + p.getProductId() +
                                               ", Name: " + p.getProductName() +
                                               ", Quantity: " + p.getQuantity());
                        }
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}