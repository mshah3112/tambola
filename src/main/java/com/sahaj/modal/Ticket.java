package com.sahaj.modal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a Ticket containing multiple rows of numbers.
 */
public class Ticket {
    // List of sets representing ticket numbers in each row
    private final List<Set<Integer>> ticketNumbers;

    /**
     * Constructs a Ticket object from a list of lists of integers.
     *
     * @param numbers A list of lists where each inner list represents a row of
     *                ticket numbers.
     */
    public Ticket(List<List<Integer>> numbers) {
        this.ticketNumbers = new ArrayList<>();
        numbers.forEach(row -> {
            Set<Integer> rowSet = new HashSet<>();
            row.stream().filter(Objects::nonNull).forEach(rowSet::add);
            this.ticketNumbers.add(rowSet);
        });
    }

    /**
     * Retrieves the set of numbers in the specified row index.
     *
     * @param index The index of the row to retrieve.
     * @return A set containing the numbers in the specified row.
     */
    public Set<Integer> getRow(int index) {
        return new HashSet<>(ticketNumbers.get(index));
    }

    /**
     * Retrieves all ticket numbers as a list of sets.
     *
     * @return A deep copy list containing sets for each row's ticket numbers.
     */
    public List<Set<Integer>> getTicketNumbers() {
        List<Set<Integer>> fetchNumbers = new ArrayList<>();
        ticketNumbers.forEach(row -> fetchNumbers.add(new HashSet<>(row)));
        return fetchNumbers;
    }

    /**
     * Checks if a specific number is present in any row on the ticket.
     *
     * @param number The number to check for presence on the ticket.
     * @return True if the number is present; false otherwise.
     */
    public boolean isNumberPresent(int number) {
        return ticketNumbers.stream().anyMatch(row -> row.contains(number));
    }
}
