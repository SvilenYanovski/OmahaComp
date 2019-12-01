package com.yanovski.omahacomp.models;

import java.util.ArrayList;
import java.util.List;

public class OmahaDeal {
    private List<OmahaPlayer> players = new ArrayList<>();
    private OmahaBoard board;
    private OmahaDealEvaluation evaluation;

    //Getters and Setters
    public List<OmahaPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<OmahaPlayer> players) {
        this.players = players;
    }

    public OmahaBoard getBoard() {
        return board;
    }

    public void setBoard(OmahaBoard board) {
        this.board = board;
    }

    public OmahaDealEvaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(OmahaDealEvaluation evaluation) {
        this.evaluation = evaluation;
    }
}
