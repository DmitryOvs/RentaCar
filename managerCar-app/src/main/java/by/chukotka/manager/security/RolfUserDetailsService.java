package by.chukotka.manager.security;

import by.chukotka.manager.entity.Authority;
import by.chukotka.manager.repository.RolfUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RolfUserDetailsService implements UserDetailsService {// предназначен для поиска user в базе данных и при наличии вернуть всю информацию


    private final RolfUserRepository rolfUserRepository;

    @Override
    @Transactional(readOnly = true) //это для того чтобы список прав загружался в рамках этой транзакции (нелениво)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.rolfUserRepository.findByUserName(username)
                .map(user -> User.builder()
                        .username(user.getUserName())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities().stream()
                                .map(Authority::getAuthority)
                                .map(SimpleGrantedAuthority::new)
                                .toList())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }
}
