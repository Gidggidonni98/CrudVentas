package com.example.CrudVentas.products.model;


import com.example.CrudVentas.service.ConnectionMySQL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductsDAO {
    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;

    public List<Products> findAll() {
        List<Products> listProducts = new ArrayList<>();
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM `products`;");
            rs = cstm.executeQuery();
            while (rs.next()) {
                Products products = new Products();
                products.setProductCode(rs.getString("productCode"));
                products.setProductName(rs.getString("productName"));
                products.setProductLine(rs.getString("productLine"));
                products.setProductScale(rs.getString("productScale"));
                products.setProductVendor(rs.getString("productVendor"));
                products.setProductDescription(rs.getString("productDescription"));
                products.setQuantityInStock(rs.getInt("quantityInStock"));
                products.setBuyPrice(rs.getDouble("buyPrice"));
                products.setMsrp(rs.getDouble("MSRP"));
                listProducts.add(products);
            }

        } catch (SQLException e) {
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return listProducts;
    }

    public Products findByProductCode(String productCode) {
        Products products = null;

        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM `products` WHERE `productCode` = ?  ;");
            cstm.setString(1, productCode);
            rs = cstm.executeQuery();

            if (rs.next()) {
                products = new Products();
                products.setProductCode(rs.getString("productCode"));
                products.setProductName(rs.getString("productName"));
                products.setProductLine(rs.getString("productLine"));
                products.setProductScale(rs.getString("productScale"));
                products.setProductVendor(rs.getString("productVendor"));
                products.setProductDescription(rs.getString("productDescription"));
                products.setQuantityInStock(rs.getInt("quantityInStock"));
                products.setBuyPrice(rs.getDouble("buyPrice"));
                products.setMsrp(rs.getDouble("MSRP"));


            }
        } catch (SQLException e) {
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);
        }
        return products;
    }

    public boolean saveProduct(Products products, boolean isCreate) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            if (isCreate) {
                cstm = con.prepareCall("INSERT INTO `products`(`productCode`, `productName`, `productLine`, `productScale`, `productVendor`, `productDescription`, `quantityInStock`, `buyPrice`, `MSRP`) VALUES (?,?,?,?,?,?,?,?,?);");
                cstm.setString(1, products.getProductCode());
                cstm.setString(2, products.getProductName());
                cstm.setString(3, products.getProductLine());
                cstm.setString(4, products.getProductScale());
                cstm.setString(5, products.getProductVendor());
                cstm.setString(6, products.getProductDescription());
                cstm.setInt(7, products.getQuantityInStock());
                cstm.setDouble(8, products.getQuantityInStock());
                cstm.setDouble(9, products.getMsrp());
            } else {
                con = ConnectionMySQL.getConnection();
                cstm = con.prepareCall("UPDATE `products` SET `productName`= ?,`productLine`= ?,`productScale`= ?,`productVendor`= ?,`productDescription`= ?,`quantityInStock`= ?,`buyPrice`= ?,`MSRP`= ? WHERE  `productCode`= ?;");
                cstm.setString(1, products.getProductName());
                cstm.setString(2, products.getProductLine());
                cstm.setString(3, products.getProductScale());
                cstm.setString(4, products.getProductVendor());
                cstm.setString(5, products.getProductDescription());
                cstm.setInt(6, products.getQuantityInStock());
                cstm.setDouble(7, products.getQuantityInStock());
                cstm.setDouble(8, products.getMsrp());
                cstm.setString(9, products.getProductCode());
            }
            flag = cstm.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.printf("Ha sucedido algún error: %s", e.getMessage());
        } finally {
            ConnectionMySQL.closeConnections(con, cstm, rs);

        }
        return flag;
    }

    public boolean deleteProduct(String productCode) {
        boolean flag = false;
        try {
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("");
            cstm.setString(1, productCode);
            flag = cstm.executeUpdate() == 1;

        } catch (SQLException e){
        System.out.printf("Ha sucedido algún error: %s", e.getMessage());
    }finally {
        ConnectionMySQL.closeConnections(con, cstm, rs);
    }
        return flag;
}
}



