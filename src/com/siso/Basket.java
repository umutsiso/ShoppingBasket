package com.siso;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Basket {

    private final String name;
    private final Map<StockItem, Integer> list;
    private final Map<StockItem, Integer> sold;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
        this.sold = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity){
        if ((item != null) && quantity>0){
            int inBasket = list.getOrDefault(item, 0);
            list.put(item, inBasket + quantity);
            return inBasket;
        }
        return 0;
    }

    public int removeFromBasket(StockItem item, int quantity){
        if ((item!= null) && quantity>0 && list.containsKey(item) ){
            int inBasket = list.get(item);
            if (inBasket == quantity){
                list.remove(item);
                return 0;
            }
            if (inBasket >= quantity){
                list.put(item, inBasket - quantity);
            } else {
                System.out.println("reserve amount is not that big !");
            }
            return inBasket;
        }
        return -1;
    }

    public void checkOut(){
        for (StockItem item : list.keySet()){
            item.adjustStock(-item.getReserved());
            item.undoReserve(item.getReserved());
        }
        this.sold.putAll(list);
        this.list.clear();
    }

    public Map<StockItem, Integer> getList(){
        return Collections.unmodifiableMap(list);
    }



    public Map<StockItem, Integer> items(){
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString(){
        String s = "\nShopping basket " + name + " contains " + list.size() + (list.size() ==1 ? " item" : " items") + "\n";
        double totalCost = 0;
        for (Map.Entry<StockItem, Integer> item : list.entrySet()){
            s = s + item.getKey() + ". " + item.getValue() + " reserved" + " BOUGHT :" + sold.get(item.getKey()) + "\n";
            totalCost += item.getKey().getPrice() * item.getValue();
        }
        return s + "Total cost " + totalCost;
    }


}
