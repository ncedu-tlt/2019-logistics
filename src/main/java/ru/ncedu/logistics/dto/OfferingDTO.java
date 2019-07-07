package ru.ncedu.logistics.dto;


public class OfferingDTO {

    private OfficeDTO office;
    private ProductDTO product;
    private Double price;

    public OfficeDTO getOffice() {
        return office;
    }

    public void setOffice(OfficeDTO office) {
        this.office = office;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPrice(int i) {
        this.price = Double.valueOf(i);
    }
}
