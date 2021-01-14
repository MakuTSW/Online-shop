package org.maku.classes;

public class Product {
    String name;
    int unitprice;
    String categoryName;
    int unitAmount;
    int productId;


    @Override
    public String toString() {
        return "<tr> <td><span style=\"color:black;\">" + name
             + "</span></td> <td><span style=\"color:black;\">" + unitprice
             + "</span></td> <td><span style=\"color:black;\">" + categoryName
             + "</span></td> <td><span style=\"color:black;\">" + unitAmount
             + "</span></td> "
             + "<td><input class=\"buttonintable\" type=\"button\" "
             + "onclick=\"window.location.href='showProduct?id="+productId+"'\" value=\"Przejdź do produktu\"> </td> </tr>";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitprice(int unitprice) {
        this.unitprice = unitprice;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setUnitAmount(int unitAmount) {
        this.unitAmount = unitAmount;
    }

    public void setProductId(int productId) { this.productId = productId; }

    public String getName() {
        return name;
    }

    public int getUnitprice() {
        return unitprice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public int getUnitAmount() {
        return unitAmount;
    }

    public int getProductId() { return productId; }
}
