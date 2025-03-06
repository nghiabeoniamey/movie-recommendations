package bytetech.movierecmommendations.server.core.admin.movie.service;

import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminFindMovieRequest;
import bytetech.movierecmommendations.server.core.admin.movie.model.request.AdminMovieRequest;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import jakarta.validation.Valid;

public interface AdminMovieService {

    ResponseObject<?> getMovie(AdminFindMovieRequest request);

    ResponseObject<?> getMovieById(String id);

    ResponseObject<?> createMovie(@Valid AdminMovieRequest request);

    ResponseObject<?> updateMovie(String id, @Valid AdminMovieRequest request);

    ResponseObject<?> changeStatusMovieById(String id);

}
