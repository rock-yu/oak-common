package com.oakware.common.util;

import java.io.Serializable;
import java.util.Objects;


/**
 * A simple Pojo to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user
 */

public class LabelValue implements Serializable, Comparable<LabelValue> {
    private String label;
    private String value;

    public LabelValue(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }
    public void setLabel(String label) {
        this.label = label;
    }


    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }


    @Override
	public String toString() {
        return "LabelValue[" + this.label + ", " + this.value + "]";
    }

    /**
     * Equality test is based on value of the object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabelValue that = (LabelValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * Compare LabelValue based on the label, because that's the human viewable part of the object
     */
    @Override
    public int compareTo(LabelValue o) {
        String otherLabel = o.getLabel();
        return this.getLabel().compareTo(otherLabel);
    }
}
