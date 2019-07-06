package ru.ncedu.logistics.service;

import ru.ncedu.logistics.data.dao.ProductDAO;
import ru.ncedu.logistics.data.entity.ProductEntity;
import ru.ncedu.logistics.dto.ProductDTO;
import ru.ncedu.logistics.servlet.dispatcher.ProductDispatcher;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductService {

    @Inject
    private ProductDAO productDAO;

    public ProductDTO create(ProductDTO product){
        ProductEntity productEntity = toProductEntity(product);
        productDAO.create(productEntity);
        return toProductDTO(productEntity);
    }

    public ProductDTO update(ProductDTO product){
        ProductEntity productEntity = toProductEntity(product);
        productDAO.update(productEntity);
        return toProductDTO(productEntity);
    }

    public void delete(ProductDTO productDTO){
        ProductEntity productEntity = toProductEntity(productDTO);
        productDAO.delete(productEntity);
    }

    public void deleteById(int productId){
        productDAO.deleteById(productId);
    }

    public List<ProductDTO> findAll() {
        return productDAO.findAll().stream().map(this::toProductDTO).collect(Collectors.toList());
    }

    public ProductDTO findById(int productId){
        return toProductDTO(productDAO.findById(productId));
    }

    public ProductDTO findByName(String name){
        return toProductDTO(productDAO.findByName(name));
    }

    public boolean existsByName(String name){
        return productDAO.existsByName(name);
    }

    public boolean existsById(int id){
        return productDAO.existsById(id);
    }

    public ProductEntity toProductEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        return productEntity;
    }

    public ProductDTO toProductDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        return productDTO;
    }
}
