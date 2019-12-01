package com.yanovski.omahacomp.models;

import com.yanovski.omahacomp.models.enums.OmahaHandRank;

public class OmahaEvaluationOutcome {
    private OmahaHandRank omahaHandRank;
    private Card highCard;

    //Getters and Setters
    public OmahaHandRank getOmahaHandRank() {
        return omahaHandRank;
    }

    public void setOmahaHandRank(OmahaHandRank omahaHandRank) {
        this.omahaHandRank = omahaHandRank;
    }

    public Card getHighCard() {
        return highCard;
    }

    public void setHighCard(Card highCard) {
        this.highCard = highCard;
    }
}
