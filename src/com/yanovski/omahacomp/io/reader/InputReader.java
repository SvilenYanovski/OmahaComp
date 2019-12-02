package com.yanovski.omahacomp.io.reader;

import com.yanovski.omahacomp.models.OmahaDeal;

import java.util.Map;

/**
 * Input reader
 * Receives Omaha Deals in for of strings from a txt file i.e.
 * Omaha Deal: HandA:Ac-Kd-Jd-3d HandB:5c-5d-6c-7d Board:Ah-Kh-5s-2s-Qd
 *
 * Parses the input and creates the data models for processing:
 *
 * The key of the input map is the input string, the value is a OmahaDeal model
 * OmahaDeal model contains all necessary data for processing the winner hand:
 * - players with their hands
 * - board with its cards
 * - evaluation data
 *
 * @author SV
 */
public interface InputReader {

    /**
     * Main api for the imput reader
     *
     * @param filePath file path for the input file
     * @return data model for processing the hand
     */
    Map<String, OmahaDeal> loadInput(String filePath);
}
