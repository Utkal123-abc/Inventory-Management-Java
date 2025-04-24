package inventory_Management_System;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection con;

    public ProductDAO() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory_db", "root", "root");
    }

    public void addProduct(Product p) throws SQLException {
        String query = "INSERT INTO products (product_name, quantity) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, p.getProductName());
        ps.setInt(2, p.getQuantity());
        ps.executeUpdate();
    }

    public void updateProductStock(int id, int qty) throws SQLException {
        String query = "UPDATE products SET quantity = quantity + ? WHERE product_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, qty);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public void deleteProduct(int id) throws SQLException {
        String query = "DELETE FROM products WHERE product_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> list = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM products");

        while (rs.next()) {
            list.add(new Product(
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("quantity")
            ));
        }
        return list;
    }
}