package org.zerock.guestbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.repository.GuestbookRepository;

import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class GuestbookServiceImpl implements GuestbookService {
    private final GuestbookRepository guestbookRepository;

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO dto) {
        Sort sort = Sort.by("gno").descending();
        Page<Guestbook> result = guestbookRepository.findAll(dto.getPageable(sort));
        Function<Guestbook, GuestbookDTO> fn
                = (entity -> entityToDTO(entity));
        return new PageResultDTO<>(result, fn);
    }

    @Override
    public Long register(GuestbookDTO dto) {
        log.info("====================");
        log.info("GuestBoot DTO : {}", dto);
        Guestbook guestbook = dtoToEntity(dto);
        guestbookRepository.save(guestbook);
        return guestbook.getGno();
    }

    @Override
    public GuestbookDTO read(Long gno) {
        Optional<Guestbook> result = guestbookRepository
                .findById(gno);
        return (result.isPresent()) ? entityToDTO(result.get()) : null;
//        if(result.isPresent()) {
//            Guestbook guestbook = result.get();
//            GuestbookDTO guestbookDTO = entityToDTO(guestbook);
//        }
//        return null;
    }

    @Override
    public void modify(GuestbookDTO dto) {
        Optional<Guestbook> result = guestbookRepository.findById(dto.getGno());
        if (result.isPresent()) {
            Guestbook guestbook = result.get();
            guestbook.changeTitle(dto.getTitle());
            guestbook.changeContent(dto.getContent());
            guestbookRepository.save(guestbook);
        }
    }

    @Override
    public void remove(Long gno) {
        guestbookRepository.deleteById(gno);
    }
}
