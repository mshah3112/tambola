package com.sahaj.validator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.sahaj.enums.ClaimStatus;
import com.sahaj.modal.Ticket;
import com.sahaj.util.Constant;

/**
 * The FullHouseValidator class implements the ClaimValidator interface.
 * It validates claims for a full house in a bingo-like game based on a ticket
 * and announced numbers.
 */
public class FullHouseValidator implements ClaimValidator {

    /**
     * Validates a full house claim by checking if all numbers from the ticket are
     * present in the announced numbers.
     *
     * @param ticket           The Ticket object containing rows of numbers.
     * @param announcedNumbers The list of integers representing announced numbers.
     * @return ClaimStatus indicating whether the claim is accepted or not.
     */
    @Override
    public ClaimStatus validateClaim(Ticket ticket, List<Integer> announcedNumbers) {
        // To track if the last announced number is present in any row of the ticket
        AtomicBoolean isLastAnnouncedNumberPresent = new AtomicBoolean(false);

        // Retrieve rows of numbers from the ticket
        List<Set<Integer>> rows = ticket.getTicketNumbers();

        // Calculate total number of items in the ticket (rows x columns)
        int totalTicketItems = Constant.ROW_SIZE * Constant.COLUMN_SIZE;

        // To count numbers have been crossed(i.e.found in the announced numbers)
        AtomicInteger countCrossedNumbers = new AtomicInteger();

        announcedNumbers.forEach(number -> {
            rows.forEach(row -> {
                if (row.contains(number)) {
                    // Increment counter for crossed numbers
                    countCrossedNumbers.getAndIncrement();
                    // Mark that last checked number was present in some row of the ticket
                    isLastAnnouncedNumberPresent.set(true);
                } else
                    // If current number was not found, mark last checked as not present
                    isLastAnnouncedNumberPresent.set(false);
            });
        });
        boolean isValidated = (countCrossedNumbers.get() == totalTicketItems) && isLastAnnouncedNumberPresent.get();

        // Return claim status based on validation result
        return ClaimStatus.isAccepted(isValidated);
    }
}
