package ru.ncedu.logistics.dto;


public class OfferingDTO {

    private OfficeDTO officeDTO;
    private ProductDTO productDTO;
    private Double price;

    public OfficeDTO getOfficeDTO() {
        return officeDTO;
    }

    public void setOfficeDTO(OfficeDTO officeDTO) {
        this.officeDTO = officeDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
