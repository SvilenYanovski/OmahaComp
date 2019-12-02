package com.yanovski.omahacomp.models;

public class OmahaPlayer {
    private String name;
    private OmahaHand hand;
    private OmahaEvaluationOutcome hiEvaluation;
    private OmahaEvaluationOutcome loEvaluation;

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OmahaHand getHand() {
        return hand;
    }

    public void setHand(OmahaHand hand) {
        this.hand = hand;
    }

    public OmahaEvaluationOutcome getHiEvaluation() {
        return hiEvaluation;
    }

    public void setHiEvaluation(OmahaEvaluationOutcome hiEvaluation) {
        this.hiEvaluation = hiEvaluation;
    }

    public OmahaEvaluationOutcome getLoEvaluation() {
        return loEvaluation;
    }

    public void setLoEvaluation(OmahaEvaluationOutcome loEvaluation) {
        this.loEvaluation = loEvaluation;
    }
}
