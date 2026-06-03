package optis.guideme.domain.user;

import lombok.Getter;

@Getter
public enum Score {
    NULL(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10);

    public static final int LOW_SCORE_NOTIFICATION_THRESHOLD = 4;

    private final int value;

    Score(int value) {
        this.value = value;
    }

    public static Score fromValue(int value) {
        for (Score score : values()) {
            if (score.value == value) {
                return score;
            }
        }

        throw new IllegalArgumentException("Invalid score value: " + value);
    }

    public boolean isLow() {
        return this.value <= LOW_SCORE_NOTIFICATION_THRESHOLD;
    }
}
