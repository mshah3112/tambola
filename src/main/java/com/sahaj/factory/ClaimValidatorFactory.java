package com.sahaj.factory;

import java.util.EnumMap;

import com.sahaj.enums.ClaimType;
import com.sahaj.validator.BottomLineValidator;
import com.sahaj.validator.ClaimValidator;
import com.sahaj.validator.EarlyFiveValidator;
import com.sahaj.validator.FullHouseValidator;
import com.sahaj.validator.MiddleLineValidator;
import com.sahaj.validator.TopLineValidator;

/**
 * The ClaimValidatorFactory class is responsible for creating and managing 
 * instances of ClaimValidator based on the provided ClaimType. This factory 
 * pattern ensures that each claim type is associated with its specific validator, 
 * facilitating easy extension and maintenance.
 */
public class ClaimValidatorFactory {

    // EnumMap to hold the mapping between ClaimType and its corresponding
    // ClaimValidator
    private static final EnumMap<ClaimType, ClaimValidator> claimMap = new EnumMap<>(ClaimType.class);

    // Static block to initialize the claimMap with specific validators for each
    // ClaimType
    static {
        claimMap.put(ClaimType.TOP_LINE, new TopLineValidator());
        claimMap.put(ClaimType.MIDDLE_LINE, new MiddleLineValidator());
        claimMap.put(ClaimType.BOTTOM_LINE, new BottomLineValidator());
        claimMap.put(ClaimType.EARLY_FIVE, new EarlyFiveValidator());
        claimMap.put(ClaimType.FULL_HOUSE, new FullHouseValidator());
    }

    /**
     * Retrieves the appropriate ClaimValidator based on the provided ClaimType.
     *
     * @param claimType The type of the claim for which a validator is needed.
     * @return The corresponding ClaimValidator instance.
     */
    public static ClaimValidator getClaimType(ClaimType claimType) {
        if (claimType == null) {
            throw new IllegalArgumentException("Invalid " + claimType + "claim");
        }
        return claimMap.get(claimType);
    }
}
