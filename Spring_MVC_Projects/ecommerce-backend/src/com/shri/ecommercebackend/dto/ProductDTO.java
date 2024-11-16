package com.shri.ecommercebackend.dto;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.shri.ecommercebackend.entity.Product;

@JsonSerialize(using = ProductDTOJsonSerializer.class)
public class ProductDTO {

	private ProductInventoryDTO productInventoryDTO;

	private String productName;

	public ProductDTO(Product product) {
		this.productInventoryDTO = new ProductInventoryDTO(product.getProductId(), 
				product.getAvailableQuantity(), product.getPrice());
		this.productName = product.getProductName();
	}

	public ProductInventoryDTO getProductInventoryDTO() {
		return productInventoryDTO;
	}

	public String getProductName() {
		return productName;
	}

	@Override
	public String toString() {
		return "ProductDTO [productInventoryDTO=" + productInventoryDTO + ", productName=" + productName + "]";
	}
	
}

//Custom serializer for ProductDTO
class ProductDTOJsonSerializer extends JsonSerializer<ProductDTO> {
	
	 @Override
	 public void serialize(ProductDTO value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	     gen.writeStartObject();
	     gen.writeNumberField("productId", value.getProductInventoryDTO().getProductId());
	     gen.writeNumberField("availableQuantity", value.getProductInventoryDTO().getAvailableQuantity());
	     gen.writeNumberField("price", value.getProductInventoryDTO().getPrice());
	     gen.writeStringField("productName", value.getProductName());
	     gen.writeEndObject();
	 }
 
}
