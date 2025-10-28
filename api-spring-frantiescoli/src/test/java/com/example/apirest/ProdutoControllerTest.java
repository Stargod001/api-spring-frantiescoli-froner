package com.example.apirest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private String basic() {
        String raw = "admin:123456";
        return "Basic " + java.util.Base64.getEncoder().encodeToString(raw.getBytes());
    }

    @Test
    void deveCriarProduto() throws Exception {
        Map<String,Object> body = Map.of("nome","Caderno","preco",12.5,"estoque",5,"descricao","A4");
        mvc.perform(post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(body))
                .header("Authorization", basic()))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.nome").value("Caderno"));
    }

    @Test
    void deveListarProdutos() throws Exception {
        mvc.perform(get("/produtos").header("Authorization", basic()))
                .andExpect(status().isOk());
    }
}
