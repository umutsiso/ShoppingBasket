package com.siso;

public class StockItem implements Comparable<StockItem>{
    private final String name;
    private double price;
    private int quantityStock = 0;
    private int reserved = 0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock = 0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int QuantityInStock() {
        return quantityStock-reserved;
    }

    public int getReserved(){
        return reserved;
    }

    public int reserveItem(int reserveQuantity){
        if (reserveQuantity <= 0){
            return -1;
        }
        int available = quantityStock;
        if (reserveQuantity <= available){
            reserved += reserveQuantity;
            return reserveQuantity;
        } else {
            System.out.println("Not enough items");
            return -1;
        }
    }

    public int undoReserve(int reserveQuantity){
        if (reserveQuantity <= 0){
            return -1;
        }
        if (reserveQuantity <= reserved){
            reserved -= reserveQuantity;
            return  reserved;
        } else {
            return -1;
        }

    }


    public void setPrice(double price) {
        if (price > 0.0){
            this.price = price;
        } else {
            System.out.println("Enter a positive value greater than 0");
        }
    }

    public void adjustStock(int quantity){
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0){
            this.quantityStock = newQuantity;
        }
    }






    ///OVERRIDE METHODS

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (this == obj){
            return true;
        }

        if ((obj.getClass() != this.getClass()) || obj == null){
            return false;
        }

        StockItem temp = ((StockItem) obj);
        return this.name.equals(temp.getName());
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("Entering StockItem.compareTo");
        if (this == o){
            return 0;
        }
        if (o != null){
            return this.name.compareTo(o.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString(){
        return this.name + " : price " + this.price + " : reserved Amount " + this.reserved;
    }
}
