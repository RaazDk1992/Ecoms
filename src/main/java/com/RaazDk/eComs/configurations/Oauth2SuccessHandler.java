package com.RaazDk.eComs.configurations;

import com.RaazDk.eComs.models.AppRole;
import com.RaazDk.eComs.models.EcomUser;
import com.RaazDk.eComs.models.Role;
import com.RaazDk.eComs.repository.RoleRepository;
import com.RaazDk.eComs.security.jwt.JwtUtils;
import com.RaazDk.eComs.security.services.EcomUserDetails;
import com.RaazDk.eComs.services.EcomUserService;
import com.stripe.model.tax.Registration;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Oauth2SuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {

    String username;
    String idAttributeKey;

    @Autowired
    EcomUserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Value("${front.end}")
    private String frontEndUrl;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        if("google".equals(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId())){
            DefaultOAuth2User principal= (DefaultOAuth2User) authentication.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            String email = attributes.getOrDefault("email","").toString();
            String name  = attributes.getOrDefault("name","").toString();
            username = email.split("@")[0];
            idAttributeKey = "sub";
            userService.findByEmail(email).ifPresentOrElse(
                   user->{
                       DefaultOAuth2User oauthUser = new DefaultOAuth2User(
                               List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name())),
                               attributes,
                               idAttributeKey
                       );
                       Authentication securityAuth = new OAuth2AuthenticationToken(
                               oauthUser,
                               List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name())),
                               authToken.getAuthorizedClientRegistrationId()
                       );

                       SecurityContextHolder.getContext().setAuthentication(securityAuth);

                   },() -> {
                        EcomUser user = new EcomUser();
                        Optional<Role> userRoles = roleRepository.findByRoleName(AppRole.ROLE_USER);
                        if(userRoles.isPresent()){
                            user.setRole(userRoles.get());
                        }else {
                            throw  new RuntimeException("Default userRole not found!!");
                        }

                        user.setEmail(email);
                        user.setUserName(username);
                        user.setPassword(UUID.randomUUID().toString());
                        user.setFirstName("Dummy");
                        user.setLastName("DumDum");
                        user.setSignupMethod(authToken.getAuthorizedClientRegistrationId());
                        userService.registerUser(user);

                        DefaultOAuth2User oAuth2User = new DefaultOAuth2User(
                                List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name())),
                                attributes,
                                idAttributeKey
                        );
                        Authentication securityAuth = new OAuth2AuthenticationToken(
                                oAuth2User,
                                List.of(new SimpleGrantedAuthority(user.getRole().getRoleName().name())),
                                authToken.getAuthorizedClientRegistrationId()
                        );
                        SecurityContextHolder.getContext().setAuthentication(securityAuth);


                    }
            );
        }


        this.setAlwaysUseDefaultTargetUrl(true);
        DefaultOAuth2User defaultOAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String,Object> attributes = defaultOAuth2User.getAttributes();
        String email =(String) attributes.get("email");
        EcomUserDetails ecomUserDetails = new EcomUserDetails(null,username,null,null,email,null,false,
                defaultOAuth2User.getAuthorities().stream()
                        .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                        .collect(Collectors.toList()));


        String jwtToken = jwtUtils.generateTokenFromUsername(ecomUserDetails);

        // Redirect to the frontend with the JWT token
        System.out.println("Front end ==="+frontEndUrl);
        String targetUrl = UriComponentsBuilder.fromUriString(frontEndUrl + "/oauth2/redirect")
                .queryParam("token", jwtToken)
                .build().toUriString();
        this.setDefaultTargetUrl(targetUrl);
        super.onAuthenticationSuccess(request, response, authentication);

        //
    }
}
