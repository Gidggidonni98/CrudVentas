package com.example.CrudVentas.productLines.model;

import com.example.CrudVentas.service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductLinesDAO {

    private Connection con;
    private CallableStatement cstm;
    private ResultSet rs;
    PreparedStatement pstm;

    public List<ProductLines> findAll(){
        List<ProductLines> listProductsLines = new ArrayList<>();

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM productsline;");
            rs = cstm.executeQuery();

            while(rs.next()){
                ProductLines productsLines = new ProductLines();

                productsLines.setProductLine(rs.getString("productLine"));
                productsLines.setTextDescription(rs.getString("textDescription"));
                productsLines.setHtmlDescription(rs.getString("htmlDescription"));


                listProductsLines.add(productsLines);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return listProductsLines;
    }

    public ProductLines findByProductLines(String productLine){
        ProductLines productsLine = null;

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("SELECT * FROM productline WHERE productline = ?;");
            cstm.setString(1, productLine);
            rs = cstm.executeQuery();

            if(rs.next()){
                productsLine = new ProductLines();

                productsLine.setProductLine(rs.getString("productLine"));
                productsLine.setTextDescription(rs.getString("textDescription"));
                productsLine.setHtmlDescription(rs.getString("htmlDescription"));

            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return productsLine;
    }

    public boolean save(ProductLines productsLine, boolean isCreate){
        boolean flag = false;

        try{
            con = ConnectionMySQL.getConnection();
            if(isCreate){
                cstm = con.prepareCall("INSERT INTO productlines (productLine, textDescription, htmlDescription)VALUES(?,?,?);");

                cstm.setString(1, productsLine.getProductLine());
                cstm.setString(2, productsLine.getTextDescription());
                cstm.setString(3, productsLine.getHtmlDescription());

            } else {
                cstm = con.prepareCall("UPDATE productlines SET textDescription = ?, htmlDescription = ? where productLine = ?;");

                cstm.setString(2, productsLine.getTextDescription());
                cstm.setString(3, productsLine.getHtmlDescription());
                cstm.setString(1, productsLine.getProductLine());
            }
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public boolean delete(String productLine){
        boolean flag = false;

        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("DELETE FROM productslines WHERE productLine = ?;");
            cstm.setString(1, productLine);
            flag = cstm.executeUpdate() == 1;
        }catch(SQLException ex){
            ex.printStackTrace();
        }finally{
            closeConnection();
        }
        return flag;
    }

    public void closeConnection(){
        try{
            if(con != null){
                con.close();
            }
            if(pstm != null){
                pstm.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
