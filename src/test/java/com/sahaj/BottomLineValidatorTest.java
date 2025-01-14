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

public class BottomLineValidatorTest {
    private TambolaModerator tambolaModerator;
    private Ticket ticket;

    @BeforeEach
    public void setup() {
        tambolaModerator = TambolaModerator.getTambolaModerator();
        List<List<Integer>> numbers = Arrays.asList(
                Arrays.asList(4, 16, null, null, 48, null, 63, 76, null),
                Arrays.asList(7, null, 23, 38, null, 52, null, null, 80),
                Arrays.asList(9, null, 25, null, null, 56, 64, null, 83));
        ticket = new Ticket(numbers);
        tambolaModerator.setTicket(ticket);
    }

    @Test
    public void testBottomLineClaimAccepted() {
        List<Integer> announcedNumbers = Arrays.asList(9, 25, 56, 64, 83);
        Assertions.assertEquals(ClaimStatus.ACCEPTED,
                tambolaModerator.validateClaim(ClaimType.BOTTOM_LINE, announcedNumbers));
    }

    @Test
    public void testBottomLineClaimRejectedDueToLateClaim() {
        List<Integer> announcedNumbers = Arrays.asList(9, 25, 56, 64, 83, 12);
        Assertions.assertEquals(ClaimStatus.REJECTED,
                tambolaModerator.validateClaim(ClaimType.BOTTOM_LINE, announcedNumbers));
    }
}
