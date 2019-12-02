package com.yanovski.omahacomp.engine.evaluator;

import com.yanovski.omahacomp.models.*;
import com.yanovski.omahacomp.models.enums.CardRank;
import com.yanovski.omahacomp.models.enums.CardSuite;
import com.yanovski.omahacomp.models.enums.OmahaHandRank;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Main logic for evaluating the winning hands
 *
 * @author SV
 */
public class OmahaEvaluator {

    /**
     * Main api for evaluating Omaha hand (deal)
     *
     * @param deal metadata for the Omaha deal
     */
    public static void evaluateHand(OmahaDeal deal) {
        //first evaluate each players' hand
        for(OmahaPlayer player: deal.getPlayers()) {
            //generate all possible hands - 2 cards from the player and 3 cards from the board
            List<OmahaHand> possibleHands = generatePossibleHands(player.getHand().getHandCards(), deal.getBoard().getBoardCards());

            //Evaluate Hi and Lo
            player.setHiEvaluation(evaluateHigh(possibleHands));
            player.setLoEvaluation(evaluateLow(possibleHands));
        }

        //Generate DealEvaluation metadata object and attach it to the OmahaDeal Object
        OmahaDealEvaluation dealEvaluation = new OmahaDealEvaluation();

        dealEvaluation.getHighWinners().add(deal.getPlayers().get(0).getHiEvaluation().getOmahaHandRank().getValue() >
                deal.getPlayers().get(1).getHiEvaluation().getOmahaHandRank().getValue() ? deal.getPlayers().get(0) : deal.getPlayers().get(1));

        int firstPlayerLoCard = deal.getPlayers().get(0).getLoEvaluation().getHighCard() != null
                ? deal.getPlayers().get(0).getLoEvaluation().getHighCard().getRank().getValue()
                : 100;
        int secondPlayerLoCard = deal.getPlayers().get(1).getLoEvaluation().getHighCard() != null
                ? deal.getPlayers().get(1).getLoEvaluation().getHighCard().getRank().getValue()
                : 100;

        dealEvaluation.getLowWinners().add(firstPlayerLoCard + secondPlayerLoCard == 200 ?
                null : firstPlayerLoCard < secondPlayerLoCard ? deal.getPlayers().get(0) : deal.getPlayers().get(1) );
        deal.setEvaluation(dealEvaluation);
    }

    /**
     * Creates the permutations for all possible hand variants - 2 cards from the player + 3 cards from the board
     *
     * @param handCards player cards
     * @param boardCards board cards
     * @return list with the permutations
     */
    private static List<OmahaHand> generatePossibleHands(List<Card> handCards, List<Card> boardCards) {
        List<List<Card>> ownHandPairs = new ArrayList<>();
        for(int i = 0; i < handCards.size() - 1; i++ ) {
            for (int j = i + 1; j < handCards.size(); j++) {
                ownHandPairs.add(Arrays.asList(handCards.get(i), handCards.get(j)));
            }
        }

        List<List<Card>> boardTriples = new ArrayList<>();
        for(int i = 0; i < boardCards.size() - 2; i++ ) {
            for (int j = i + 1; j < boardCards.size() - 1; j++) {
                for (int k = j + 1; k < boardCards.size(); k++) {
                    boardTriples.add(Arrays.asList(boardCards.get(i), boardCards.get(j), boardCards.get(k)));
                }
            }
        }

        List<OmahaHand> possibleHands = new ArrayList<>();
        for (List<Card> handPair: ownHandPairs) {
            for (List<Card> boardTriple: boardTriples) {
                OmahaHand hand = new OmahaHand();
                hand.getHandCards().addAll(handPair);
                hand.getHandCards().addAll(boardTriple);
                possibleHands.add(hand);
            }
        }
        return possibleHands;
    }

    /**
     * Omaha LO Evaluator
     *
     * @param possibleHands hand permutations
     * @return Metadata object with winner (if any)
     */
    private static OmahaEvaluationOutcome evaluateLow(List<OmahaHand> possibleHands) {
        OmahaEvaluationOutcome outcome = new OmahaEvaluationOutcome();

        for(OmahaHand hand: possibleHands) {
            OmahaHandRank handRank = isLowHand(hand) ? OmahaHandRank.LOW_HAND : null;
            if(handRank != null) {
                outcome.setOmahaHandRank(handRank);
                outcome.setHighCard(getHighCard(hand));
            }
        }
        return outcome;
    }

    /**
     * Retrieves the high card from the hand
     *
     * @param hand players cards
     * @return high card
     */
    private static Card getHighCard(OmahaHand hand) {
        Card highCard = hand.getHandCards().get(0);
        for (Card card: hand.getHandCards()){
            if(card.getRank().getValue() > highCard.getRank().getValue()) {
                highCard = card;
            }
        }
        return highCard;
    }

    /**
     * Evaluates Omaha HI hand from the possible permutations
     *
     * @param hand player hand
     * @return hand rank, i.e Flush, straight, 3-of-a-kind, etc.
     */
    private static OmahaHandRank evaluateHandRank(OmahaHand hand) {
        //map to hold cards' ranks with their quantity into the player hand
        Map<CardRank, Integer> cardRanks = new HashMap<>();

        //cards' suites from the player hand, determines a possible Flush
        Set<CardSuite> suites = new HashSet<>();

        //load the 2 data structures
        for(Card card: hand.getHandCards()) {
            if(cardRanks.containsKey(card.getRank())) {
                cardRanks.put(card.getRank(), cardRanks.get(card.getRank()) + 1);
            } else {
                cardRanks.put(card.getRank(), 1);
            }

            suites.add(card.getSuite());
        }

        //evaluates the hand rank, if any (the lowest is high card)
        if (isStraitFlush(cardRanks, suites)) {
            return OmahaHandRank.STRAIGHT_FLUSH;
        } else if (is4ofAKind(cardRanks)) {
            return OmahaHandRank.FOUR_OF_A_KIND;
        } else if (isFullHouse(cardRanks)) {
            return OmahaHandRank.FULL_HOUSE;
        } else if (isFlush(suites)) {
            return OmahaHandRank.FLUSH;
        } else if (isStrait(cardRanks)) {
            return OmahaHandRank.STRAIGHT;
        } else if (is3ofAKind(cardRanks)) {
            return OmahaHandRank.THREE_OF_A_KIND;
        } else if (isTwoPair(cardRanks)) {
            return OmahaHandRank.TWO_PAIR;
        } else if (isOnePair(cardRanks)) {
            return OmahaHandRank.ONE_PAIR;
        } else {
            return OmahaHandRank.HIGH_CARD;
        }
    }

    private static boolean isOnePair(Map<CardRank, Integer> cardRanks) {
        return cardRanks.values().size() == 4;
    }

    private static boolean isTwoPair(Map<CardRank, Integer> cardRanks) {
        return cardRanks.containsValue(2) && cardRanks.values().size() == 3;
    }

    private static boolean is3ofAKind(Map<CardRank, Integer> cardRanks) {
        return cardRanks.containsValue(3);
    }

    private static boolean isStrait(Map<CardRank, Integer> cardRanks) {
        if(cardRanks.values().size() != 5) {
            return false;
        }

        List<Integer> straigthCheck = cardRanks.keySet().stream()
                .map(CardRank::getValue)
                .sorted()
                .collect(Collectors.toList());
        for (int i = 1; i < straigthCheck.size(); i++) {
            if(straigthCheck.get(i) != straigthCheck.get(i-1) + 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFlush(Set<CardSuite> suites) {
        return suites.size() == 1;
    }

    private static boolean isFullHouse(Map<CardRank, Integer> cardRanks) {
        return cardRanks.containsValue(3) && cardRanks.containsValue(2);
    }

    private static boolean is4ofAKind(Map<CardRank, Integer> cardRanks) {
        return cardRanks.containsValue(4);
    }

    private static boolean isStraitFlush(Map<CardRank, Integer> cardRanks, Set<CardSuite> suites) {
        return isFlush(suites) && isStrait(cardRanks);
    }

    /**
     * Determines if a hand is qualified for a LO evaluation
     * There are 2 rules:
     * - 5 unique cards
     * - high card no greater than 8 (Ace counts are Lo card)
     *
     * @param hand player hand
     * @return outcome
     */
    private static boolean isLowHand(OmahaHand hand) {
        Set<Integer> ranks = new HashSet<>();
        for (Card card: hand.getHandCards()) {
            if (card.getRank().getValue() > CardRank.EIGHT.getValue()
                    || !card.getRank().equals(CardRank.ACE)) {
                return false;
            }
            ranks.add(card.getRank().getValue());
        }
        return ranks.size() == 5;
    }

    /**
     * Omaha HI evaluation workflow
     *
     * @param possibleHands permutations
     * @return outcome
     */
    private static OmahaEvaluationOutcome evaluateHigh(List<OmahaHand> possibleHands) {
        OmahaEvaluationOutcome outcome = new OmahaEvaluationOutcome();
        outcome.setHighCard(null);
        outcome.setOmahaHandRank(OmahaHandRank.HIGH_CARD);

        for(OmahaHand hand: possibleHands) {
            OmahaHandRank handRank = evaluateHandRank(hand);
            if(handRank.getValue() > outcome.getOmahaHandRank().getValue()) {
                outcome.setOmahaHandRank(handRank);
                outcome.setHighCard(getHighCard(hand));
            }
        }
        return outcome;
    }
}
