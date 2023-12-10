package org.decathlon.api.utils;

import org.decathlon.api.Event.Event;

public class Parsers {

    public static double tryParseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new NumberFormatException();
        }
    }

    public static Event tryParseEvent(String eventName) {
        try {
            return Event.valueOf(eventName);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Unknown event '%s'", eventName));
        }
    }
}
