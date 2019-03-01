package com.monologica.suppliesDelivery.supplyDelivery;

public class CompletedTransaction {
    int amountSold;
    double earned;

    public CompletedTransaction(int amt, double earned) {
        this.amountSold = amt;
        this.earned = earned;
    }

    public double getEarned() {
        return earned;
    }

    public int getAmountSold() {
        return amountSold;
    }
}
