package com.yanovski.omahacomp.models;

import java.util.ArrayList;
import java.util.List;

public class OmahaHand {
    List<Card> handCards = new ArrayList<>();

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }
}
