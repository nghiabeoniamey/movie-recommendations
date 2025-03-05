package bytetech.movierecmommendations.server.core.user.review.service.impl;

import bytetech.movierecmommendations.server.core.common.base.PageableObject;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserFindReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserModifyReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.model.request.UserReviewRequest;
import bytetech.movierecmommendations.server.core.user.review.repository.UserReviewMovieRepository;
import bytetech.movierecmommendations.server.core.user.review.repository.UserReviewRepository;
import bytetech.movierecmommendations.server.core.user.review.repository.UserReviewUserRepository;
import bytetech.movierecmommendations.server.core.user.review.repository.UserReviewWatchHistoryRepository;
import bytetech.movierecmommendations.server.core.user.review.service.UserReviewService;
import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.entities.main.Reviewer;
import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import bytetech.movierecmommendations.server.util.AuditorProviderByAuthenticationUtil;
import bytetech.movierecmommendations.server.util.Helper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserReviewServiceImpl implements UserReviewService {

    private final UserReviewRepository reviewRepository;

    private final UserReviewUserRepository userRepository;

    private final UserReviewMovieRepository movieRepository;

    private final UserReviewWatchHistoryRepository watchHistoryRepository;

    @Override
    public ResponseObject<?> getReviews(UserFindReviewRequest request) {
        try {
            Pageable pageable = Helper.createPageable(request);
            return new ResponseObject<>(
                    PageableObject.of(reviewRepository.getReviews(pageable, request)),
                    HttpStatus.OK,
                    Message.Success.GET_SUCCESS
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseObject<?> getReviewById(String id) {
        try {
            return new ResponseObject<>(
                    reviewRepository.findUserById(id),
                    HttpStatus.OK,
                    Message.Success.GET_SUCCESS
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }

    @Override
    public ResponseObject<?> createReview(UserReviewRequest request) {
        String userId = AuditorProviderByAuthenticationUtil.getUserId();
        if (userId.equalsIgnoreCase(AuditorProviderByAuthenticationUtil.SYSTEM)) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.NOT_FOUND + " người dùng"
            );
        }

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.NOT_FOUND + ", người dùng trong hệ thống"
            );
        }

        Movie movie = movieRepository.findById(request.getMovieId()).orElse(null);
        if (movie == null) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.NOT_FOUND + " bộ phim trong hệ thống"
            );
        }

//        if (!watchHistoryRepository.existsWatchHistoriesByUserAndMovie(user, movie)) {
//            return ResponseObject.errorForward(
//                    HttpStatus.BAD_REQUEST,
//                    Message.Response.NOT_FOUND + " người dùng chưa xem phim để đánh giá"
//            );
//        }

        Reviewer reviewer = new Reviewer();
        reviewer.setUser(user);
        reviewer.setMovie(movie);
        reviewer.setComment(request.getComment());
        reviewer.setRating(request.getRating());
        reviewRepository.save(reviewer);
        return ResponseObject.successForward(
                HttpStatus.CREATED,
                Message.Success.CREATE_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> updateReview(String id, UserModifyReviewRequest request) {
        Reviewer reviewer = reviewRepository.findById(id).orElse(null);
        if (reviewer == null) {
            return ResponseObject.errorForward(
                    HttpStatus.BAD_REQUEST,
                    Message.Response.NOT_FOUND + " đánh giá của người dùng trong hệ thống"
            );
        }

        reviewer.setRating(request.getRating());
        reviewer.setComment(request.getComment());
        reviewRepository.save(reviewer);
        return ResponseObject.successForward(
                HttpStatus.OK,
                Message.Success.UPDATE_SUCCESS
        );
    }

    @Override
    public ResponseObject<?> deleteReviewById(String id) {
        try {
            Optional<Reviewer> reviewerOptional = reviewRepository.findById(id);
            if (reviewerOptional.isEmpty()) {
                return ResponseObject.errorForward(
                        HttpStatus.BAD_REQUEST,
                        Message.Response.NOT_FOUND + ", review"
                );
            }
            Reviewer newReview = reviewerOptional.get();
            newReview.setDeleted(!newReview.getDeleted());
            reviewRepository.save(newReview);
            return ResponseObject.successForward(
                    HttpStatus.CREATED,
                    Message.Success.UPDATE_SUCCESS
            );
        } catch (Exception e) {
            return ResponseObject.errorForward(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
