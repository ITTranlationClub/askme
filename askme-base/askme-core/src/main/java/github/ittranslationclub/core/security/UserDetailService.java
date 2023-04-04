package github.ittranslationclub.core.security;

import github.ittranslationclub.core.security.entity.UserDetail;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends UserDetailsService {

    UserDetail createUserDetailByUser(UserDetails userDetails);

}
