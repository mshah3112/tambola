package com.sahaj.validator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import com.sahaj.enums.ClaimStatus;
import com.sahaj.modal.Ticket;
import com.sahaj.util.Constant;

/**
 * The EarlyFiveValidator class implements the ClaimValidator interface.
 * It validates claims based on a bingo-like ticket and announced numbers.
 */
public class EarlyFiveValidator implements ClaimValidator {

    /**
     * Validates a claim by checking if exactly five numbers from the ticket are
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

        // To count how many numbers have been crossed (i.e., found in the announced numbers)
        AtomicInteger countCrossedNumbers = new AtomicInteger();

        // Iterate over each announced number
        announcedNumbers.forEach(number -> {
            // AtomicBoolean to check if current number is present in any row of the ticket
            AtomicBoolean isPresent = new AtomicBoolean(false);

            // Check each row to see if it contains the current number
            rows.forEach(row -> {
                if (row.contains(number) && !isPresent.get())
                    // Set true if number is found in this row and not already marked as present
                    isPresent.set(true);
            });
            if (isPresent.get()) {
                // Set true if number is found in this row and not already marked as present
                countCrossedNumbers.getAndIncrement(); 

                // Mark that last checked number was present in some row of the ticket
                isLastAnnouncedNumberPresent.set(true);
            } else
                // If current number was not found, mark last checked as not present
                isLastAnnouncedNumberPresent.set(false);
        });

        boolean isValidated = countCrossedNumbers.get() == Constant.COLUMN_SIZE && isLastAnnouncedNumberPresent.get();

        // Return claim status based on validation result
        return ClaimStatus.isAccepted(isValidated);
    }

}
