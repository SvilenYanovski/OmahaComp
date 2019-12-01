package com.yanovski.omahacomp.engine.evaluator;

import com.yanovski.omahacomp.models.Card;
import com.yanovski.omahacomp.models.OmahaDeal;
import com.yanovski.omahacomp.models.OmahaEvaluationOutcome;
import com.yanovski.omahacomp.models.OmahaPlayer;
import com.yanovski.omahacomp.models.enums.OmahaHandRank;

import java.util.List;

public class OmahaEvaluator {
    public static void evaluateHand(OmahaDeal deal) {
        for(OmahaPlayer player: deal.getPlayers()) {
            player.setHiEvaluation(evaluateHigh(player.getHand().getHandCards(), deal.getBoard().getBoardCards()));
            player.setLoEvaluation(evaluateLow(player.getHand().getHandCards(), deal.getBoard().getBoardCards()));
        }
    }

    private static OmahaEvaluationOutcome evaluateLow(List<Card> handCards, List<Card> boardCards) {
        OmahaEvaluationOutcome outcome = new OmahaEvaluationOutcome();
        outcome.setHighCard(handCards.get(0));
        outcome.setOmahaHandRank(OmahaHandRank.FLUSH);
        return outcome;
    }

    private static OmahaEvaluationOutcome evaluateHigh(List<Card> handCards, List<Card> boardCards) {
        OmahaEvaluationOutcome outcome = new OmahaEvaluationOutcome();
        outcome.setHighCard(handCards.get(1));
        outcome.setOmahaHandRank(OmahaHandRank.HIGH_CARD);
        return outcome;
    }
}
