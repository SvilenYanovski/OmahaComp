package com.yanovski.omahacomp.io.writer;

import com.yanovski.omahacomp.models.OmahaDeal;

import java.util.Map;

/**
 * File writer for output formatting.
 * Describes the Omaha Hand evaluation in a verbose way i.e.
 *
 * INPUT: HandA:Ac-Kd-Jd-3d HandB:5c-5d-6c-7d Board:Ah-Kh-5s-2s-Qd
 * OUTPUT: => HandB wins High (THREE_OF_A_KIND); HandB wins Low (7)
 *
 * @author SV
 */
public interface OutputWriter {

    /**
     * Main api for Writer class
     *
     * @param path output file path
     * @param deals evaluated deals for writing
     */
    void writeOutput(String path, Map<String, OmahaDeal> deals);
}
