package com.sahaj.validator;

import java.util.List;

import com.sahaj.enums.ClaimStatus;
import com.sahaj.modal.Ticket;
import com.sahaj.util.Constant;

/**
 * ClaimValidator interface for validating claims.
 */
public interface ClaimValidator {

    /**
     * Validates the claim based on the ticket and announced numbers.
     *
     * @param ticket           The Ticket object containing ticket details.
     * @param announcedNumbers List of integers representing announced numbers.
     * @return ClaimStatus indicating whether the claim is valid or not.
     */
    ClaimStatus validateClaim(Ticket ticket, List<Integer> announcedNumbers);

    /**
     * Determines the claim status based on crossed count and last number
     * containment.
     *
     * @param crossedCount        Number of crossed (announced) numbers in the
     *                            specified row/column.
     * @param lastNumberContained Boolean indicating if last announced number is
     *                            present in specified row/column.
     * @return ClaimStatus indicating validity of claim.
     */
    default public ClaimStatus getClaimStatus(int crossedCount, boolean lastNumberContained) {
        return ClaimStatus.isAccepted(crossedCount == Constant.COLUMN_SIZE && lastNumberContained);
    }
}
