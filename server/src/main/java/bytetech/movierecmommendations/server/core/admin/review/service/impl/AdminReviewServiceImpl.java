package bytetech.movierecmommendations.server.core.admin.review.service.impl;

import bytetech.movierecmommendations.server.core.admin.review.model.request.AdminFindReviewRequest;
import bytetech.movierecmommendations.server.core.admin.review.repository.AdminReviewRepository;
import bytetech.movierecmommendations.server.core.admin.review.service.AdminReviewService;
import bytetech.movierecmommendations.server.core.common.base.PageableObject;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Reviewer;
import bytetech.movierecmommendations.server.infrastructure.constants.module.Message;
import bytetech.movierecmommendations.server.util.Helper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminReviewServiceImpl implements AdminReviewService {

    private final AdminReviewRepository reviewRepository;

    @Override
    public ResponseObject<?> getReviews(AdminFindReviewRequest request) {
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
