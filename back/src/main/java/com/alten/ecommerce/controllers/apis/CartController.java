package com.alten.ecommerce.controllers.apis;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

import static com.alten.ecommerce.utils.Constants.APP_API_ROOT;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController

public interface CartController {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product added", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = APP_API_ROOT + "/cart/add")
    public ResponseEntity<Void> addToCart(@RequestBody ProductDto product, Principal principal);

    @Operation(summary = "Get all Products", description = "Get a list of Products", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get a list of Products", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Product Not Found", content = @Content())
    })
    @GetMapping(value = APP_API_ROOT + "/cart")
    public Set<ProductDto> getCart(Principal principal);


    @Operation(summary = "Delete Product", description = "Delete Product", tags = "Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Delete Product", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Product Not Found", content = @Content())
    })
    @DeleteMapping (value = APP_API_ROOT + "/remove")
    public ResponseEntity<String> removeProductFromCart(@RequestParam Long productId, Principal principal);
}
