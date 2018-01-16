package pl.scartout.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pl.scartout.model.Image;

@Transactional
@Repository
public interface ImageRepo extends JpaRepository<Image, Long> {
}