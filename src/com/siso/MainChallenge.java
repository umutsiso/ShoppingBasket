package com.siso;

public class MainChallenge {
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
        temp = new StockItem("vase", 1.50, 60);
        stockList.addStock(temp);

        Basket myBasket = new Basket("myBasket");
        addToBasket(myBasket,"vase", 20);

        addToBasket(myBasket,"vase", 20);

        removeFromBasket(myBasket,"vase", 20);
        System.out.println(myBasket);
        System.out.println(stockList);

        checkOut(myBasket);
        System.out.println(stockList);

        addToBasket(myBasket,"vase", 20);

        System.out.println(myBasket);




    }


    public static int addToBasket(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We dont sell " + item);
            return 0;
        }
        if (stockList.reserveItem(item, quantity) != -1) {
            basket.addToBasket(stockItem, quantity);
            return quantity;
        }
        return 0;

    }

    public static int removeFromBasket(Basket basket, String item, int quantity){
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We dont sell " + item);
            return 0;
        }
        if(stockList.unReserveItem(item, quantity) > 0){
            basket.removeFromBasket(stockItem,quantity);
            return quantity;
        }
        return 0;
    }

    public static boolean checkOut(Basket basket){
        if (basket.getList()== null){
            System.out.println("Cart is empty !");
            return false;
        } else {
            basket.checkOut();
            return true;
        }
    }


}









