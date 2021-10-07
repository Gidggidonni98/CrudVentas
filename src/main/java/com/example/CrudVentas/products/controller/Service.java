package com.example.CrudVentas.products.controller;

import com.example.CrudVentas.products.model.ProductsDAO;
import com.example.CrudVentas.products.model.Products;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

@Path("/products")
public class Service {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getProducts() {
        return new ProductsDAO().findAll();
    }

    @GET
    @Path("/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getProducts(@PathParam("productCode") String productCode){
        return new ProductsDAO().findByProductCode(productCode);
    }

    @POST
    @Path("/saveProduct")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Products saveProduct(MultivaluedMap<String, String> formParams){
        String productCode = formParams.get("productCode").get(0);
        if (new ProductsDAO().saveProduct(getParams(productCode, formParams), true))
            return new ProductsDAO().findByProductCode(productCode);
            return null;
    }
    @POST
    @Path("/saveProduct/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Products save(@PathParam("productCode") String productCode, MultivaluedMap<String, String> formParams){
        if (new ProductsDAO().saveProduct(getParams(productCode, formParams), false))
            return new ProductsDAO().findByProductCode(productCode);
        return null;
    }

    @DELETE
    @Path("/deleteProduct/{productCode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public boolean deleteProduct(@PathParam("productCode") String productCode){
        return new ProductsDAO().deleteProduct(productCode);
    }

    private Products getParams(String productCode, MultivaluedMap<String, String> formParams){
        String productName = formParams.get("productName").get(0);
        String productLine = formParams.get("productLine").get(0);
        String productScale = formParams.get("productScale").get(0);
        String productVendor = formParams.get("productVendor").get(0);
        String productDescription = formParams.get("productDescription").get(0);
        int quantityInStock = Integer.parseInt(formParams.get("quantityInStock").get(0));
        double buyPrice = Double.parseDouble(formParams.get("buyPrice").get(0));
        double msrp = Double.parseDouble(formParams.get("MSRP").get(0));
        Products products = new Products(productCode,productName, productLine,productScale,productVendor,productDescription,quantityInStock,buyPrice,msrp);
        System.out.println(products);
        return products;
    }

    }

