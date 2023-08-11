package org.zerock.guestbook.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class PageRequestDTO {
    private int page;
    private int size;
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }
    public Pageable getPageable() {
        return PageRequest.of(this.page -1, this.size);
    }
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(this.page -1, this.size, sort);
    }
}
