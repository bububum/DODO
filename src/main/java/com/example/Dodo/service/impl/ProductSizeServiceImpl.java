package com.example.Dodo.service.impl;

import com.example.Dodo.base.BaseServiceImpl;
import com.example.Dodo.dao.ProductSizeRepository;
import com.example.Dodo.exception.IncorrectRequest;
import com.example.Dodo.mapper.ProductSizeMapper;
import com.example.Dodo.microservices.FileService;
import com.example.Dodo.model.DTO.ProductDTO;
import com.example.Dodo.model.DTO.ProductSizeDTO;
import com.example.Dodo.model.entity.ProductSize;
import com.example.Dodo.model.request.ProductCreateRequest;
import com.example.Dodo.model.response.FileServiceUploadResponse;
import com.example.Dodo.service.CategoryService;
import com.example.Dodo.service.ProductService;
import com.example.Dodo.service.ProductSizeService;
import com.example.Dodo.service.SizeService;
import com.example.Dodo.util.Language;
import com.example.Dodo.util.ResourceBundleLanguage;
import org.springframework.stereotype.Service;

@Service
public class ProductSizeServiceImpl extends BaseServiceImpl<ProductSize, ProductSizeRepository, ProductSizeDTO, ProductSizeMapper> implements ProductSizeService {
    public ProductSizeServiceImpl(ProductSizeRepository rep, ProductSizeMapper mapper, CategoryService categoryService, ProductService productService, SizeService sizeService) {
        super(rep, mapper);
        this.categoryService = categoryService;
        this.productService = productService;
        this.sizeService = sizeService;
    }

    private final CategoryService categoryService;
//    private final FileService fileService;
    private final ProductService productService;
    private final SizeService sizeService;

    @Override
    public String create(ProductCreateRequest request, Integer languageOrdinal) {

//        FileServiceUploadResponse response=fileService.upload(request.getLogo());
        Language language = Language.getLanguage(languageOrdinal);

        if (request != null) {

            ProductDTO productDTO = new ProductDTO();
            productDTO.setCategory(categoryService.findById(request.getCategoryId()));
//            productDTO.setLogo(response.getDownloadUri());
            productDTO.setLogo("logo");
            productDTO.setName(request.getName());
            productDTO.setDescription(request.getDescription());

            productService.save(productDTO);

            ProductSizeDTO productSizeDTO = new ProductSizeDTO();
            productSizeDTO.setSize(sizeService.findById(request.getSizeId()));
            productSizeDTO.setProduct(productDTO);
            productSizeDTO.setPrice(request.getPrice());

            save(productSizeDTO);

            return "Success";
        } else {
            throw new IncorrectRequest(ResourceBundleLanguage.periodMessage(language, "incorrectRequest"));
        }

    }
}
