package com.ecommerce.shopping.service.image;

import com.ecommerce.shopping.dto.ImageDTO;
import com.ecommerce.shopping.exception.ResourceNotFoundException;
import com.ecommerce.shopping.model.Image;
import com.ecommerce.shopping.model.Product;
import com.ecommerce.shopping.repository.ImageRepository;
import com.ecommerce.shopping.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final IProductService productService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No image found with id: " +id ));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository :: delete, ()-> {
            throw new ResourceNotFoundException("No image found with id: " +id);
        });

    }

    @Override
    public List<ImageDTO> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productService.getProductById(productId);
        List<ImageDTO> savedImageDTOS = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                Image image = new Image();
                image.setFileType(file.getContentType());
                image.setFileName(file.getOriginalFilename());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl+image.getId();
                image.setDownloadUrl(downloadUrl);
                Image savedImage = imageRepository.save(image);

                savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
                imageRepository.save(savedImage);

                ImageDTO imageDTO = new ImageDTO();
                imageDTO.setId(savedImage.getId());
                imageDTO.setFileName(savedImage.getFileName());
                imageDTO.setDownloadUrl(savedImage.getDownloadUrl());
                savedImageDTOS.add(imageDTO);

            } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImageDTOS;
    }

    @Override
    public void updateImage(MultipartFile file, Long productId) {
        Image image = getImageById(productId);
        try {
            image.setFileType(file.getContentType());
            image.setFileName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
