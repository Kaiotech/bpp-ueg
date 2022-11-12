package bppueg.bootstrap;

import bppueg.entity.UserEntity;
import bppueg.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

    }

    private void loader() {
        UserEntity u1 = UserEntity.builder()
                .username("Gyula")
                .email("gyula@szopas.hu")
                .password("1234")
                .build();

        UserEntity u2 = UserEntity.builder()
                .username("Bella")
                .email("bella@szopas.hu")
                .password("1234")
                .build();

        UserEntity u3 = UserEntity.builder()
                .username("Javier")
                .email("javier@szopas.hu")
                .password("1234")
                .build();

        userRepository.save(u1);
        userRepository.save(u2);
        userRepository.save(u3);
    }
}
