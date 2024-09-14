package challenge.reservation_service.service;

import challenge.reservation_service.domain.User;
import challenge.reservation_service.repos.UserRepository;
import challenge.reservation_service.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User get(final Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }


}
