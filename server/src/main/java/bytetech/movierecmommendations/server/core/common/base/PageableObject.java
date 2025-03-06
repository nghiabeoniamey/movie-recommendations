package bytetech.movierecmommendations.server.core.common.base;

import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class PageableObject<T> {

    private final List<T> data;

    private final long totalPages;

    private final int currentPage;

    private final long totalElements;

    public PageableObject(Page<T> page) {
        this.data = page.getContent();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.totalElements = page.getTotalElements();
    }

    public PageableObject(List<T> data, long totalPages, int currentPage, long totalElements) {
        this.data = data;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
        this.totalElements = totalElements;
    }

    public static <T> PageableObject<T> of(Page<T> page) {
        return new PageableObject<>(page);
    }

    public static <T> PageableObject<T> of(List<T> data, long totalPages, int currentPage, long totalElements) {
        return new PageableObject<>(data, totalPages, currentPage, totalElements);
    }

}
