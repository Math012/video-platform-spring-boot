package br.com.math012.controller;

import br.com.math012.VO.VideoVO;
import br.com.math012.models.UserModel;
import br.com.math012.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="Endpoint for users")
@RestController
@RequestMapping(value = "/api/user/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Return the all users")
    @GetMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucess", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VideoVO.class)))}),
            @ApiResponse(responseCode = "401", description = "Enauthorized", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content),
    })
    public List<UserModel> findAll(){
        return userService.findAll();
    }
}
