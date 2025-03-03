package bytetech.movierecmommendations.server.core.admin.user.repository;

import bytetech.movierecmommendations.server.core.admin.user.model.request.AdminFindUserRequest;
import bytetech.movierecmommendations.server.core.admin.user.model.response.AdminUserResponse;
import bytetech.movierecmommendations.server.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminUserRepository extends UserRepository {

    @Query(value = """
            SELECT
                ROW_NUMBER() OVER(ORDER BY u.created_date DESC) AS catalog,
                u.id AS id,
                u.full_name AS name,
                u.email AS email,
                u.profile_picture AS profilePicture,
                u.role_constant AS role,
                u.deleted AS deleted
            FROM
                user u
            WHERE
                (:#{#req.keyword} IS NULL OR
                u.full_name LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                u.email LIKE CONCAT('%', :#{#req.keyword}, '%'))
            AND (:#{#req.status} IS NULL OR u.deleted = :#{#req.status})
            AND (:#{#req.role} IS NULL OR u.role_constant = :#{#req.role})
            """, countQuery = """
            SELECT
                COUNT(u.id) FROM user u
            WHERE
                (:#{#req.keyword} IS NULL OR
                u.full_name LIKE CONCAT('%', :#{#req.keyword}, '%') OR
                u.email LIKE CONCAT('%', :#{#req.keyword}, '%'))
            AND (:#{#req.status} IS NULL OR u.deleted = :#{#req.status})
            AND (:#{#req.role} IS NULL OR u.role_constant = :#{#req.role})
            """, nativeQuery = true)
    Page<AdminUserResponse> getUsers(Pageable pageable, @Param("req") AdminFindUserRequest req);

    @Query(value = """
                    SELECT
                        u.id AS id,
                        u.full_name AS name,
                        u.email AS email,
                        u.password AS password,
                        u.profile_picture AS profilePicture,
                        u.role_constant AS role,
                        u.deleted AS deleted
                    FROM user u
            """, nativeQuery = true)
    AdminUserResponse findUserById(String id);

    boolean existsByEmail(String email);

}
