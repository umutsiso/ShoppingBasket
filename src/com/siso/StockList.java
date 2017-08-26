package com.siso;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {

    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item){
        if (item != null){
            StockItem inStock = list.getOrDefault(item.getName(), item);
            //Check if we already have quantities
            if (inStock != item){
                item.adjustStock(inStock.QuantityInStock());
            }

            list.put(item.getName(), item);
            return item.QuantityInStock();
        }
        return 0;
    }

    public int sellStock(String item, int quantity){
        StockItem inStock = list.getOrDefault(item, null);

        if ((inStock != null) && (inStock.QuantityInStock() >= quantity) && (quantity>0) ){
            inStock.adjustStock(-quantity);
            return quantity;
        }
        return 0;
    }

    public int reserveItem(String item, int quantity){
        StockItem instock = list.getOrDefault(item, null);

        if((instock != null) && (instock.QuantityInStock() >= quantity) && (quantity>0)){
            int result = instock.reserveItem(quantity);
            return result;
        }
        return -1;
    }

    public int unReserveItem(String item, int quantity){
        StockItem instock = list.getOrDefault(item, null);
        if (instock == null){
            System.out.println("There is no such item in cart");
        }
        if((instock != null) && (instock.QuantityInStock() >= quantity) && (quantity>0)){
            instock.undoReserve(quantity);
            return quantity;
        }
        return 0;
    }


    public StockItem get(String key){
        return list.get(key);
    }

    public  Map<String, Double> PriceList(){
        Map<String, Double> prices = new LinkedHashMap<>();
        for (Map.Entry<String, StockItem> item : list.entrySet()){
            prices.put(item.getKey(), item.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    public Map<String ,StockItem> Items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nStock List\n";
        double totalCost = 0.0;
        for (Map.Entry<String,StockItem> item : list.entrySet()){
            StockItem stockItem = item.getValue();
            double itemValue = stockItem.getPrice() * stockItem.QuantityInStock();

            s = s + stockItem + ". There are " + stockItem.QuantityInStock() + " in stock. Value of items: ";
            s = s + String.format("%.2f", itemValue) + "\n";
            totalCost += itemValue;

        }
        return s + "total stock value " + totalCost;
    }
}
