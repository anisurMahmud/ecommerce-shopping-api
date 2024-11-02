package com.ecommerce.shopping.controller;

import com.ecommerce.shopping.dto.ImageDTO;
import com.ecommerce.shopping.model.Image;
import com.ecommerce.shopping.response.ApiResponse;
import com.ecommerce.shopping.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix/images}")
public class ImageController {
    private final IImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> saveImage(@RequestParam List<MultipartFile> files, @RequestParam Long productId) {
        try {
            List<ImageDTO> imageDTOS = imageService.saveImages(files, productId);
            return ResponseEntity.ok(new ApiResponse("upload success", imageDTOS));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("upload failed", e.getMessage()));
        }
    }

    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<Resource>downloadImage(@PathVariable Long imageId) throws SQLException {
        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage().getBytes(1, (int) image.getImage().length()));
        return ResponseEntity.ok().contentType((MediaType.parseMediaType(image.getFileType())))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +image.getFileName()+"\"")
                .body(resource);
    }

}
