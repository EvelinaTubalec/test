package com.tubalec.security.service;

import com.tubalec.domain.Role;
import com.tubalec.domain.User;
import com.tubalec.repository.RoleDataRepository;
import com.tubalec.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserProviderService implements UserDetailsService {

    private final UserDataRepository userDataRepository;

    private final RoleDataRepository roleDataRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            Optional<User> searchResult = Optional.ofNullable(userDataRepository.findByLogin(login));
            if (searchResult.isPresent()) {
                User user = searchResult.get();
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roleDataRepository.findRolesByUserId(user.getId()).stream().map(Role::getRoleName).collect(Collectors.joining(",")))
                );
            } else {
                throw new UsernameNotFoundException(String.format("No user found with login '%s'.", login));
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this login not found");
        }
    }
}
