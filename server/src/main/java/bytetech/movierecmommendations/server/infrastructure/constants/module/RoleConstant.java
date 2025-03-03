package bytetech.movierecmommendations.server.infrastructure.constants.module;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RoleConstant {

    ADMIN,

    USER;

    public static List<String> Roles() {
        return Arrays.stream(RoleConstant.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
