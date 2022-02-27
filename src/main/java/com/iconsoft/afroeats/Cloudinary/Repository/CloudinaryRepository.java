package com.iconsoft.afroeats.Cloudinary.Repository;

import com.iconsoft.afroeats.Cloudinary.Models.Cloudinarie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CloudinaryRepository extends JpaRepository<Cloudinarie, Long> {
}
