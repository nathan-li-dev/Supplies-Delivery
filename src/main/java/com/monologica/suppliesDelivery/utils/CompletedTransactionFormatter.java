package com.monologica.suppliesDelivery.utils;

import com.monologica.suppliesDelivery.supplyDelivery.CompletedTransaction;

public class CompletedTransactionFormatter extends ColourFormatter {

    private CompletedTransaction transaction;

    public CompletedTransactionFormatter(CompletedTransaction t) {
        this.transaction = t;
    }

    @Override
    public String format(String s) {

        String formatted = super.format(s);
        formatted = fastReplace(formatted, "{AMOUNT}", String.format("%,d", transaction.getAmountSold()));
        formatted = fastReplace(formatted, "{VALUE}", String.format("%,.2f", transaction.getEarned()));

        return formatted;
    }

    // Horcrux7 @ https://stackoverflow.com/questions/1010928/faster-alternatives-to-replace-method-in-a-java-string
    static String fastReplace( String str, String target, String replacement ) {
        int targetLength = target.length();
        if( targetLength == 0 ) {
            return str;
        }
        int idx2 = str.indexOf( target );
        if( idx2 < 0 ) {
            return str;
        }
        StringBuilder buffer = new StringBuilder( targetLength > replacement.length() ? str.length() : str.length() * 2 );
        int idx1 = 0;
        do {
            buffer.append( str, idx1, idx2 );
            buffer.append( replacement );
            idx1 = idx2 + targetLength;
            idx2 = str.indexOf( target, idx1 );
        } while( idx2 > 0 );
        buffer.append( str, idx1, str.length() );
        return buffer.toString();
    }
}
