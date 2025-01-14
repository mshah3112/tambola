package com.sahaj.validator;

import com.sahaj.util.Constant;

/**
 * MiddleLineValidator class extending LineValidator.
 */
public class MiddleLineValidator extends LineValidator {

    /**
     * Returns the index of the bottom row.
     *
     * @return int representing the index of the bottom row.
     */
    @Override
    protected int getRowIndex() {
        return Constant.MIDDLE_ROW;
    }
}
