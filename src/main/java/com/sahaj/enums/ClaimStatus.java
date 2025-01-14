package com.sahaj.enums;

/**
 * The ClaimStatus enum represents the status of a claim.
 * It can either be ACCEPTED or REJECTED.
 */
public enum ClaimStatus {
    ACCEPTED,
    REJECTED;

    /**
     * Determines the ClaimStatus based on a boolean value.
     *
     * @param isAccepted A boolean indicating if the claim is accepted
     * @return The corresponding ClaimStatus (ACCEPTED or REJECTED)
     */
    public static ClaimStatus isAccepted(boolean isAccepted) {
        return isAccepted ? ACCEPTED : REJECTED;
    }
}
