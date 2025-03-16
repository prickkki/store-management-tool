package com.store.management.service;

import com.store.management.dtoRecord.UserDTO;
import com.store.management.entity.User;
import com.store.management.mapper.UserMapper;
import com.store.management.repository.UserRepository;
import com.store.management.security.CustomOAuth2User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

         Optional<User> optionalExistingUser = userRepository.findByEmail(email);
        if(optionalExistingUser.isPresent()) {
            User existingUser = optionalExistingUser.get();
            return new CustomOAuth2User(userMapper.userToDTO(existingUser), AuthorityUtils.createAuthorityList("ROLE_USER"));
        }
        else {
            UserDTO userDTO = new UserDTO(null,name, email,null,null,null);
            User newUser = userMapper.dtoToUser(userDTO);
            userRepository.save(newUser);
            return new CustomOAuth2User(userMapper.userToDTO(newUser), AuthorityUtils.createAuthorityList("ROLE_USER"));
        }
    }
}
