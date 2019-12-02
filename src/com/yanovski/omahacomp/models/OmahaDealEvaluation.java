package com.yanovski.omahacomp.models;

import java.util.ArrayList;
import java.util.List;

public class OmahaDealEvaluation {
    private List<OmahaPlayer> highWinners = new ArrayList<>();
    private List<OmahaPlayer> lowWinners = new ArrayList<>();

    //Getters and Setters
    public List<OmahaPlayer> getHighWinners() {
        return highWinners;
    }

    public void setHighWinners(List<OmahaPlayer> highWinners) {
        this.highWinners = highWinners;
    }

    public List<OmahaPlayer> getLowWinners() {
        return lowWinners;
    }

    public void setLowWinners(List<OmahaPlayer> lowWinners) {
        this.lowWinners = lowWinners;
    }
}
