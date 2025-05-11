package com.alten.ecommerce.controllers.apis;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.dtos.ProductUpdateDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alten.ecommerce.utils.Constants.APP_API_ROOT;

@CrossOrigin(origins = "http://localhost:4200/")
public interface ProductController {

    @Operation(summary = "Create product", description = "Create a new product (Admin only)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "403", description = "Forbidden (Admin role required)"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(value = APP_API_ROOT + "/products")
    ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto);

    @Operation(summary = "Get all ProductDtos", description = "Get a list of ProductDtos", tags = "ProductDto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a list of ProductDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "ProductDto Not Found", content = @Content())
    })
    @GetMapping(value = APP_API_ROOT + "/products")
    ResponseEntity<List<ProductDto>> findAllProducts();

    @Operation(summary = "Get a ProductDto", description = "Get a ProductDto with a given id", tags = "ProductDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get a ProductDto with a given id", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "ProductDto Not Found", content = @Content())
    })
    @GetMapping(value = APP_API_ROOT + "/products/{id}")
    ResponseEntity<ProductDto> findProductById(@PathVariable Long id);

    @Operation(summary = "Delete a ProductDto", description = "Delete a ProductDto with a given id", tags = "ProductDtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Delete a ProductDto with a given id", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "ProductDto Not Found", content = @Content())
    })
    @DeleteMapping(value = APP_API_ROOT + "/products/{id}")
    void deleteProduct(@PathVariable Long id);

    @Operation(summary = "Update ProductDto", description = "Update ProductDto", tags = "ProductDto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Updated ProductDto", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PatchMapping(value = APP_API_ROOT + "/products/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto dto);
}
