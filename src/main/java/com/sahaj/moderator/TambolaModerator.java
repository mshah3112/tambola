package com.sahaj.moderator;

import java.util.List;

import com.sahaj.modal.Ticket;
import com.sahaj.validator.ClaimValidator;
import com.sahaj.enums.ClaimStatus;
import com.sahaj.enums.ClaimType;
import com.sahaj.factory.ClaimValidatorFactory;

/**
 * The Tambolamoderator class is responsible for managing validation of claims
 * in a tambolagame.It ensures that claims are validated against annouced
 * numbers using appropriate validators
 */
public class TambolaModerator {

    // Singleton Instance
    private static TambolaModerator tambolaModerator;

    private Ticket ticket;

    /**
     * Create and Retrieves singleton instance of Tamobla moderator
     *
     * @return Singleton instance of Tamoblamoderator
     */
    public static TambolaModerator getTambolaModerator() {
        if (tambolaModerator == null) {
            synchronized (TambolaModerator.class) {
                if (tambolaModerator == null)
                    tambolaModerator = new TambolaModerator();
            }
        }
        return tambolaModerator;
    }

    /**
     * Sets tickect to be used for calim validation
     *
     * @param tickect Tickect object representing player’s tickect
     */
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    /**
     * Validates claim based on provided Claimtype and annouced numbers,
     * If no ticket provided it will throw error as ticket not initialized
     *
     * @param claimtype       Type of claim(like TOPLINE,FULLHOUSE),
     * @param annoucednumbers List of numbers that been announced so far,
     * @return Status of the claim(ACCEPTED OR REJECTED),
     */
    public ClaimStatus validateClaim(ClaimType claimType, List<Integer> announcedNumbers) {
        if (ticket == null) {
            throw new IllegalStateException("Ticket is not initialized.");
        }
        ClaimValidator claimValidator = ClaimValidatorFactory.getClaimType(claimType);
        return claimValidator.validateClaim(ticket, announcedNumbers);
    }
}
