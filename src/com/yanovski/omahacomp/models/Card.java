package com.yanovski.omahacomp.models;

import com.yanovski.omahacomp.models.enums.CardRank;
import com.yanovski.omahacomp.models.enums.CardSuite;

public class Card {
    private CardRank rank;
    private CardSuite suite;

    //Getters and Setters
    public CardRank getRank() {
        return rank;
    }

    public void setRank(CardRank rank) {
        this.rank = rank;
    }

    public CardSuite getSuite() {
        return suite;
    }

    public void setSuite(CardSuite suite) {
        this.suite = suite;
    }
}
