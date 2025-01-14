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

public class MiddleLineValidatorTest {

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
    public void testMiddleLineClaimAccepted() {
        List<Integer> announcedNumbers = Arrays.asList(7, 23, 38, 52, 80);
        Assertions.assertEquals(ClaimStatus.ACCEPTED,
                tambolaModerator.validateClaim(ClaimType.MIDDLE_LINE, announcedNumbers));
    }

    @Test
    public void testMiddleLineClaimRejectedDueToLateClaim() {
        List<Integer> announcedNumbers = Arrays.asList(7, 23, 38, 52, 80, 12);
        Assertions.assertEquals(ClaimStatus.REJECTED,
                tambolaModerator.validateClaim(ClaimType.MIDDLE_LINE, announcedNumbers));
    }

}
