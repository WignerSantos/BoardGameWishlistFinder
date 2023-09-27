package com.wigner.BoardGameWishlistFinder.repositories;

import com.wigner.BoardGameWishlistFinder.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Integer> {

    Roles getRoleByRoleName(String roleName);

}
