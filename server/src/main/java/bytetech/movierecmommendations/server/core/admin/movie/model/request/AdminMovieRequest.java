package bytetech.movierecmommendations.server.core.admin.movie.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminMovieRequest {

    @NotBlank(message = "Title không được để trống")
    private String title;

    @NotBlank(message = "Description không được để trống")
    private String description;

    @NotBlank(message = "Picture URL không được để trống")
    private String pictureURL;

    @NotBlank(message = "Movies URL không được để trống")
    private String moviesURL;

    @NotBlank(message = "Author không được để trống")
    private String author;

    @NotBlank(message = "Actor không được để trống")
    private String actor;

    @NotNull(message = "Year không được để trống")
    private Integer year;

    @NotNull(message = "Danh mục không được để trống")
    @Size(min = 1, message = "Phải có ít nhất một danh mục")
    private List<String> categoryIds;
}
