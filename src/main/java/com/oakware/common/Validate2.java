package com.oakware.common;

import static java.lang.String.format;

public class Validate2 {
    public static void isTrue(final boolean expression, final String message, final Object... values) {
        if (expression == false) {
            throw new IllegalArgumentException(String.format(message, values));
        }
    }

    public static void assertArgumentLength(String aString, int aMaximum, String aMessage) {
        if(aString == null)
            return;

        int length = aString.trim().length();
        isTrue(length <= aMaximum, aMessage);
    }

    public static void assertArgumentLength(String aString, int aMinimum, int aMaximum, String aMessage) {
        int length = aString.trim().length();
        if (length < aMinimum || length > aMaximum) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    public static void assertArgumentNotEmpty(String aString, String aMessage) {
        if (aString == null || aString.trim().isEmpty()) {
            throw new IllegalArgumentException(aMessage);
        }
    }

    public static void checkArgument(boolean expression) {
        if(!expression) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean expression, String errorMessageTemplate, Object... errorMessageArgs) {
        if (!expression) {
            throw new IllegalArgumentException(format(errorMessageTemplate, errorMessageArgs));
        }
    }

    public static void checkState(boolean expression) {
        if(!expression) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean expression, Object errorMessage) {
        if(!expression) {
            throw new IllegalStateException(String.valueOf(errorMessage));
        }
    }

    public static void guardedAssert(boolean expression) {
        checkArgument(expression);
    }

    public static void guardedAssert(boolean expression, String message) {
        checkArgument(expression, message);
    }
}
