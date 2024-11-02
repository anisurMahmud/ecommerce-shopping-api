package com.ecommerce.shopping.controller;

import com.ecommerce.shopping.dto.ImageDTO;
import com.ecommerce.shopping.response.ApiResponse;
import com.ecommerce.shopping.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

}
