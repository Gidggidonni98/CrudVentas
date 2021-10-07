package com.example.CrudVentas.productLines.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="productLines")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductLines {

    @XmlElement
    private String productLine;
    @XmlElement
    private String textDescription;
    @XmlElement
    private String htmlDescription;

    public ProductLines(){

    }

    public ProductLines(String productLine, String textDescription, String htmlDescription) {
        this.productLine = productLine;
        this.textDescription = textDescription;
        this.htmlDescription = htmlDescription;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getTextDescription() {
        return textDescription;
    }

    public void setTextDescription(String textDescription) {
        this.textDescription = textDescription;
    }

    public String getHtmlDescription() {
        return htmlDescription;
    }

    public void setHtmlDescription(String htmlDescription) {
        this.htmlDescription = htmlDescription;
    }


}
