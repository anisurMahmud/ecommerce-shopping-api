package com.ecommerce.shopping.service.image;

import com.ecommerce.shopping.dto.ImageDTO;
import com.ecommerce.shopping.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long productId);
}
