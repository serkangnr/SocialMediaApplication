package com.serkanguner.repository;

import com.serkanguner.constant.Role;
import com.serkanguner.entity.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<Auth,Long> {

    /**
     * Username daha önce alınmış mı kontrol sağlamak için:
     * @param username
     * @return
     */
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    /**
     * username ve password vt'da kayıtlı mı kontrolü yapar.
     */
    Optional<Auth> findOptionalByEmailAndPassword(String email,String password);

    boolean findByActivationCode(String activationCode);

    Optional<Auth> findByRole(Role role);



    //password ve repassword kontrolu yapin
    //Boolean existsByPasswordAndRepassword(String password,String repassword);

}
