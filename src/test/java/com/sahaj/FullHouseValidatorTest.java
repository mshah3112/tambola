package com.sahaj;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sahaj.enums.ClaimStatus;
import com.sahaj.enums.ClaimType;
import com.sahaj.modal.Ticket;
import com.sahaj.moderator.TambolaModerator;

public class FullHouseValidatorTest {
    private TambolaModerator tambolaModerator;
    private Ticket ticket;

    @BeforeEach
    public void setup() {
        tambolaModerator = TambolaModerator.getTambolaModerator();
        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(4, 16, 48, 63, 76),
                Arrays.asList(7, 23, 38, 52, 80),
                Arrays.asList(9, 25, 56, 64, 83));

        ticket = new Ticket(numbers);
        tambolaModerator.setTicket(ticket);
    }

    @Test
    public void testFullHouseClaimAccepted() {
        List<Integer> announcedNumbers = Arrays.asList(4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64, 83);
        Assertions.assertEquals(ClaimStatus.ACCEPTED,
                tambolaModerator.validateClaim(ClaimType.FULL_HOUSE, announcedNumbers));
    }

    @Test
    public void testFullHouseClaimRejectedDueToLateClaim() {
        List<Integer> announcedNumbers = Arrays.asList(4, 16, 48, 63, 76, 7, 23, 38, 52, 80, 9, 25, 56, 64, 83, 12);
        Assertions.assertEquals(ClaimStatus.REJECTED,
                tambolaModerator.validateClaim(ClaimType.FULL_HOUSE, announcedNumbers));
    }
}
