package org.zerock.guestbook.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.guestbook.entity.Guestbook;
//import sun.security.krb5.internal.ccache.MemoryCredentialsCache;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class GuestbookRepositoryTests {
    @Autowired
    private GuestbookRepository guestbookRepository;
    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300)
                .forEach(i -> {
                    Guestbook guestbook = Guestbook.builder()
                            .title("Title … " + i)
                            .content("Content … " + i)
                            .writer("user … " + i).build();
                    guestbookRepository.save(guestbook);
                });
    }

    @Transactional
    @Commit
    @Test
    public void updateTest(){
        Optional<Guestbook> result =
                guestbookRepository.findById(298L);
        if (result.isPresent()){
            Guestbook guestbook = result.get();
            guestbook.changeTitle(("Changed Title ... 2"));
            guestbook.changeContent("Changed Content ... 2");
            guestbookRepository.save(guestbook);
        }
    }
}
