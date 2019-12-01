package com.yanovski.omahacomp.models;

import java.util.ArrayList;
import java.util.List;

public class OmahaBoard {
    private List<Card> boardCards = new ArrayList<>();

    public List<Card> getBoardCards() {
        return boardCards;
    }

    public void setBoardCards(List<Card> boardCards) {
        this.boardCards = boardCards;
    }
}
