package com.sahaj.validator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import com.sahaj.enums.ClaimStatus;
import com.sahaj.modal.Ticket;

/**
 * Abstract class LineValidator implementing ClaimValidator interface.
 */
public abstract class LineValidator implements ClaimValidator {

    /**
     * Abstract method to get the row index.
     *
     * @return int representing the row index.
     */
    protected abstract int getRowIndex();

    /**
     * Validates the claim based on the ticket and announced numbers.
     *
     * @param ticket           The Ticket object containing ticket details.
     * @param announcedNumbers List of integers representing announced numbers.
     * @return ClaimStatus indicating whether the claim is valid or not.
     */
    @Override
    public ClaimStatus validateClaim(Ticket ticket, List<Integer> announcedNumbers) {
        // Get the row index from subclass implementation
        int rowIndex = getRowIndex();

        // Get the row index from subclass implementation
        Set<Integer> rowSet = ticket.getTicketNumbers().get(rowIndex);

        // Atomic integer to count crossed (announced) numbers in the row
        AtomicInteger countCrossedNumbers = new AtomicInteger();

        // Iterate through each announced number and check if it exists in the row set
        announcedNumbers.forEach(number -> {
            if (rowSet.contains(number))
                countCrossedNumbers.getAndIncrement();
        });

        // Determine claim status based on crossed numbers count and last announced
        // number presence in row set
        return getClaimStatus(countCrossedNumbers.get(),
                rowSet.contains(announcedNumbers.get(announcedNumbers.size() - 1)));
    }
}
