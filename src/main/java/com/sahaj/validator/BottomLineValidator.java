package com.sahaj.validator;

import com.sahaj.util.Constant;

/**
 * BottomLineValidator class extending LineValidator.
 */
public class BottomLineValidator  extends LineValidator{
    
    /**
     * Returns the index of the bottom row.
     *
     * @return int representing the index of the bottom row.
     */
    @Override
    protected int getRowIndex() {
        return Constant.BOTTOM_ROW;
    }

}
