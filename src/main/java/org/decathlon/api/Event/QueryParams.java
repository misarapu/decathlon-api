package org.decathlon.api.Event;

import org.decathlon.api.utils.Parsers;

import java.net.URI;

public class QueryParams {

    private Event event;

    private double result;

    public QueryParams(URI uri) {
        String[] queryParams = uri.getQuery().split("&");

        for (String param : queryParams) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];
            String value = keyValue[1];

            if (key.equals("event")) {
                this.event = Parsers.tryParseEvent(value);
            }
            if (key.equals("result")) {
                this.result = Parsers.tryParseDouble(value);
            }
        }
    }

    public Event getEvent() {
        return this.event;
    }

    public double getResult() {
        return this.result;
    }
}
