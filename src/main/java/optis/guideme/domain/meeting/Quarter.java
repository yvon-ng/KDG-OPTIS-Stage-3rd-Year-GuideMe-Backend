package optis.guideme.domain.meeting;

import lombok.Getter;
import optis.guideme.domain.exception.WrongInputException;

@Getter
public enum Quarter {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);

    private final int value;

    Quarter(int value) {
        this.value = value;
    }

    public static Quarter fromMonth(int month) {
        return switch ((month - 1) / 3 + 1) {
            case 1 -> ONE;
            case 2 -> TWO;
            case 3 -> THREE;
            case 4 -> FOUR;
            default -> throw new WrongInputException("Invalid month: " + month);
        };
    }
}
