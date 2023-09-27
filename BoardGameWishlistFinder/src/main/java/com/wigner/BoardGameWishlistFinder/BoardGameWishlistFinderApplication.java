package com.wigner.BoardGameWishlistFinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.wigner.BoardGameWishlistFinder.repositories")
@EntityScan("com.wigner.BoardGameWishlistFinder.model")
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class BoardGameWishlistFinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoardGameWishlistFinderApplication.class, args);
	}

}
