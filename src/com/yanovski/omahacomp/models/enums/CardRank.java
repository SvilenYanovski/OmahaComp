package com.yanovski.omahacomp.models.enums;

public enum CardRank {
    ACE(15),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(12),
    QUEEN(13),
    KING(14);

    private int value;

    private CardRank(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
