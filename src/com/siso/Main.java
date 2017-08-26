package com.siso;

import java.util.Map;

public class Main {

    // LINKEDHASHMAP IS THE SORTED HASHMAP
    private static StockList stockList = new StockList();

    public static void main(String[] args) {

        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", 5.0, 200);
        stockList.addStock(temp);
        temp = new StockItem("cup", 4.0, 7);
        stockList.addStock(temp);


        temp = new StockItem("door", 12.00, 6);
        stockList.addStock(temp);

        temp = new StockItem("juice", 4.00, 300);
        stockList.addStock(temp);

        temp = new StockItem("phone", 7.00, 20);
        stockList.addStock(temp);
        temp = new StockItem("towel", 2.50, 15);
        stockList.addStock(temp);
        temp = new StockItem("vase", 1.50, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for(String s : stockList.Items().keySet()){
            System.out.println(s);
        }

        Basket myBasket = new Basket("My Basket");
        sellItem(myBasket, "phone", 10);
        System.out.println(myBasket);

        sellItem(myBasket, "vase", 23);
        System.out.println(myBasket);


        for (Map.Entry<String,Double> price : stockList.PriceList().entrySet()){
            System.out.println(price.getKey() + " costs " + price.getValue());
        }


    }


    public static int sellItem(Basket basket, String item, int quantity){
        //retrieve item from stockList first
        StockItem stockItem = stockList.get(item);
        if (stockItem == null){
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.sellStock(item, quantity) != 0){
            basket.addToBasket(stockItem,quantity);
            return quantity;
        }
        return 0;
    }


}
