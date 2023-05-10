package manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbConnect.DbConnect;
import entity.Product;

public class ProductManager {
    public boolean insert(Product product) throws SQLException {
        String sql = "insert into Product(productName,salesPrice) values(?,?)";
        PreparedStatement statement = DbConnect.conn.prepareStatement(sql);
        statement.setString(1, product.getProductName());
        statement.setDouble(2,product.getSalesPrice());
        int affected = statement.executeUpdate();
        return affected ==1?true:false;
    }

    public boolean update(Product product) throws SQLException {
        String sql = "update Product set productName=?, salesPrice=? where productId=?";
        PreparedStatement statement = DbConnect.conn.prepareStatement(sql);
        statement.setString(1, product.getProductName());
        statement.setDouble(2,product.getSalesPrice());
        statement.setLong(3,product.getProductId());
        int affected = statement.executeUpdate();
        return affected==1?true:false;
    }

    public Product find(long productId) throws SQLException {

        Product product = null;
        String sql = "select * from Product where productId=?";
        PreparedStatement statement = DbConnect.conn.prepareStatement(sql);
        statement.setLong(1,productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            product = new Product();
            product.setProductId(resultSet.getLong("productId"));
            product.setSalesPrice(resultSet.getDouble("salesPrice"));
            product.setProductName(resultSet.getString("productName"));

            System.out.println(productId);
        }
        else {
            System.out.println("Ürün bulunamadı");
        }
        return product;
    }


    public boolean delete(long productId) throws SQLException {
        String sql = "delete from Product where productId=?";
        PreparedStatement statement = DbConnect.conn.prepareStatement(sql);
        statement.setLong(1,productId);
        int affected = statement.executeUpdate();
        return  affected ==1?true:false;
    }

    public  void  list() throws SQLException {
        List<Product> productList = new ArrayList<>();
        String sql = "select * from Product";
        PreparedStatement statement = DbConnect.conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            String productName = resultSet.getString("productName");
            long productId = resultSet.getLong("productId");
            double salesPrice = resultSet.getDouble("salesPrice");
            Product product = new Product(productId,productName,salesPrice);
            productList.add(product);
        }
        for (Product products : productList){
            System.out.println("Ürün Id: "+products.getProductId()+" Ürün adı: "+products.getProductName()+" Ürün fiyatı: "+products.getSalesPrice());
        }
    }

}
