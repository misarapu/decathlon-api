package org.decathlon.api.Event;

public enum Event {

    TRACK_100(EventType.TRACK, new double[]{25.4347, 18.00, 1.81}),
    LONG_JUMP(EventType.JUMPING, new double[]{0.14354, 220.00, 1.40}),
    SHOT_PUT(EventType.THROWING, new double[]{51.39, 1.50, 1.05}),
    HIGH_JUMP(EventType.JUMPING, new double[]{0.8465, 75.00, 1.42}),
    TRACK_400(EventType.TRACK, new double[]{1.53775, 82.00, 1.81}),
    HURDLES_110(EventType.TRACK, new double[]{5.74352, 28.50, 1.92}),
    DISCUS_THROW(EventType.THROWING, new double[]{12.91, 4.00, 1.10}),
    POLE_VAULT(EventType.JUMPING, new double[]{0.2797, 100.00, 1.35}),
    JAVELIN_THROW(EventType.THROWING, new double[]{10.14, 7.00, 1.08}),
    TRACK_1500(EventType.TRACK, new double[]{0.03768, 480.00, 1.85});

    private final EventType eventType;
    private final double[] constants;

    Event(EventType eventType, double[] constants) {
        this.eventType = eventType;
        this.constants = constants;
    }

    private EventType eventType() {
        return eventType;
    }

    private double[] constants() {
        return constants;
    }

    // https://www.decathlon2000.com/upload/file/pdf/official_decathlon_formulas.pdf
    public int getScore(double result) {
        var a = constants()[0];
        var b = constants()[1];
        var c = constants()[2];

        var powerOf = (eventType() == EventType.TRACK ? 1 : -1) * (b - result);

        return (int) Math.floor((a * Math.pow(Math.max(powerOf, 0), c)));
    }
}

