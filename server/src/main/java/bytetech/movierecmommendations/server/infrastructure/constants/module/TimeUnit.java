package bytetech.movierecmommendations.server.infrastructure.constants.module;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum TimeUnit {

    YEAR,
    MONTH,
    WEEK,
    DAY;

    public static List<String> getTimeUnits() {
        return Arrays.stream(TimeUnit.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
