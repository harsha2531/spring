package org.example.z_13_springboot_validation.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.z_13_springboot_validation.dto.CustomerDTO;
import org.example.z_13_springboot_validation.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/customer")
public class CustomerController {
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> addCustomer(@Valid @RequestBody CustomerDTO customer) {
        ResponseDTO responseDTO = new ResponseDTO(
                201,
                "Customer Created",
                null
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
