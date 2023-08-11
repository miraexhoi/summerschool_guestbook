package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;

@SpringBootTest
class GuestbookServiceTests {
    @Autowired
    private GuestbookService service;
    @Test
    public void testRegister() {
        GuestbookDTO dto = GuestbookDTO
                .builder()
                .title("Sample Title")
                .content("Sample Content")
                .writer("User0")
                .build();
        System.out.println(service.register(dto));
    }
    @Test
    public void testRead() {
        GuestbookDTO dto = service.read(302L);
        System.out.println(dto);
    }
    @Test
    public void testModify() {
        GuestbookDTO dto =
                GuestbookDTO.builder()
                .gno(1L)
                .title("Update...")
                .content("Update...")
                .build();
        service.modify(dto);
    }

    @Test
    public void testRemove() {
        Long gno = 1L;
        service.remove(gno);
    }

    @Test
    public void testGetList() {
        PageRequestDTO pageRequestDTO = new PageRequestDTO(2,10);
        service.getList(pageRequestDTO);
    }

}