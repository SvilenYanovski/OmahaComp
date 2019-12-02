package com.yanovski.omahacomp.io.reader.impl;

import com.yanovski.omahacomp.config.AppConstants;
import com.yanovski.omahacomp.io.reader.InputReader;
import com.yanovski.omahacomp.models.*;
import com.yanovski.omahacomp.models.enums.CardRank;
import com.yanovski.omahacomp.models.enums.CardSuite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class InputReaderImpl implements InputReader {
    @Override
    public Map<String, OmahaDeal> loadInput(String filePath) {

        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            Map<String, OmahaDeal> result = new HashMap<>();

            stream.forEach(line -> {
                OmahaDeal deal = new OmahaDeal();
                String[] fileLineTokens = line.split(" ");
                deal.getPlayers().add(createPlayer(fileLineTokens[0]));
                deal.getPlayers().add(createPlayer(fileLineTokens[1]));
                deal.setBoard(createBoard(fileLineTokens[2]));
                result.put(line, deal);
            });

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private OmahaBoard createBoard(String fileLineToken) {
        OmahaBoard board = new OmahaBoard();
        board.getBoardCards().addAll(parseHand(fileLineToken.substring(
                fileLineToken.lastIndexOf(':') + 1).trim()));
        return board;
    }

    private OmahaPlayer createPlayer(String fileLineToken) {
        OmahaHand hand = new OmahaHand();
        hand.getHandCards().addAll(parseHand(fileLineToken.substring(
                fileLineToken.lastIndexOf(':') + 1).trim()));

        OmahaPlayer player = new OmahaPlayer();
        player.setName(fileLineToken.substring(
                0, fileLineToken.lastIndexOf(':') + 1).trim());
        player.setHand(hand);

        return player;
    }

    private List<Card> parseHand(String cardString) {
        List<Card> result = new ArrayList<>();
        String[] cards = cardString.split("-");

        for (String card: cards) {
            result.add(parseCard(card));
        }
        return result;
    }

    private Card parseCard(String cardStr) {
        String suite = cardStr.substring(cardStr.length() - 1);
        String rank = cardStr.substring(0, cardStr.length() - 1);

        Card card = new Card();
        card.setSuite(parseSuite(suite));
        card.setRank(parseRank(rank));

        return card;
    }

    private CardRank parseRank(String rank) {
        if(AppConstants.RANK_ACE.equalsIgnoreCase(rank)){
            return CardRank.ACE;
        } else if(AppConstants.RANK_TWO.equalsIgnoreCase(rank)){
            return CardRank.TWO;
        } else if(AppConstants.RANK_THREE.equalsIgnoreCase(rank)){
            return CardRank.THREE;
        } else if(AppConstants.RANK_FOUR.equalsIgnoreCase(rank)){
            return CardRank.FOUR;
        } else if(AppConstants.RANK_FIVE.equalsIgnoreCase(rank)){
            return CardRank.FIVE;
        } else if(AppConstants.RANK_SIX.equalsIgnoreCase(rank)){
            return CardRank.SIX;
        } else if(AppConstants.RANK_SEVEN.equalsIgnoreCase(rank)){
            return CardRank.SEVEN;
        } else if(AppConstants.RANK_EIGHT.equalsIgnoreCase(rank)){
            return CardRank.EIGHT;
        } else if(AppConstants.RANK_NINE.equalsIgnoreCase(rank)){
            return CardRank.NINE;
        } else if(AppConstants.RANK_TEN.equalsIgnoreCase(rank)){
            return CardRank.TEN;
        } else if(AppConstants.RANK_JACK.equalsIgnoreCase(rank)){
            return CardRank.JACK;
        } else if(AppConstants.RANK_QUEEN.equalsIgnoreCase(rank)){
            return CardRank.QUEEN;
        } else if(AppConstants.RANK_KING.equalsIgnoreCase(rank)){
            return CardRank.KING;
        } else {
            return null;
        }
    }

    private CardSuite parseSuite(String suite) {
        if(AppConstants.SUITE_CLUB.equalsIgnoreCase(suite)){
            return CardSuite.CLUB;
        } else if(AppConstants.SUITE_HEART.equalsIgnoreCase(suite)){
            return CardSuite.HEART;
        } else if(AppConstants.SUITE_SPADE.equalsIgnoreCase(suite)){
            return CardSuite.SPADE;
        } else if(AppConstants.SUITE_DIAMONDS.equalsIgnoreCase(suite)){
            return CardSuite.DIAMOND;
        } else {
            return null;
        }
    }
}


