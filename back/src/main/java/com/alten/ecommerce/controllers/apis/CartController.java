package com.alten.ecommerce.controllers.apis;

import com.alten.ecommerce.dtos.ProductDto;
import com.alten.ecommerce.entities.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import static com.alten.ecommerce.utils.Constants.APP_API_ROOT;

public interface CartController {

    @Operation(summary = "Add product to Cart", description = "Add product to Cart", tags = "Cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product added", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping(value = APP_API_ROOT + "/cart/add")
    public void addToCart(@RequestParam Long productId, Principal principal);

    @Operation(summary = "Get all Products", description = "Get a list of Products", tags = "Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Get a list of Products", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Product Not Found", content = @Content())
    })
    @GetMapping(value = APP_API_ROOT + "/cart")
    public Set<Product> getCart(Principal principal);
}
