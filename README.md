# decathlon-api

This is a lightweight api for decathlon score calculations.

Use following URL to get score of given event

```sh
http://localhost:8080/api/score?event={eventName}&result={result}
```

* Table of Event

| Event                   | Event name    | Unit  |
| ----------------------- |:------------- | :---- |
| 100 meters              | TRACK_100     | s     |
| Long jump               | LONG_JUMP     | cm    |
| Shot put                | SHOT_PUT      | m     |
| High jump               | HIGH_JUMP     | cm    |
| 400 meters              | TRACK_400     | s     |
| 110 meters hurdles      | HURDLES_110   | cm    |
| Discus throw            | DISCUS_THROW  | m     |
| Pole vault              | POLE_VAULT    | cm    |
| Javelin throw           | JAVELIN_THROW | m     |
| 1500 meters             | TRACK_1500    | cm    |
