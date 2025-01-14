package com.sahaj.validator;

import com.sahaj.util.Constant;

/**
 * TopLineValidator class extending LineValidator.
 */
public class TopLineValidator extends LineValidator {

    /**
     * Returns the index of the bottom row.
     *
     * @return int representing the index of the bottom row.
     */
    @Override
    protected int getRowIndex() {
        return Constant.TOP_ROW;
    }
}
