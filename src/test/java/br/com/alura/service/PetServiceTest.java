package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PetServiceTest {

    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private PetService petService = new PetService(client);
    private HttpResponse<String> response = mock(HttpResponse.class);
    Pet pet = new Pet("Cão", "Pitoco", "SR", 14, "preto", 6.5f);

    @Test
    public void deveVerificarQuandoHaPet() throws IOException, InterruptedException {
        pet.setId(0L);
        String expectedPetsCadastrados = "Pets cadastrados:";
        String expectedIdENome = "0 - Cão - Pitoco - SR - 14 ano(s)";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(pet);

        when(response.body()).thenReturn("[" + json + "]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        petService.listarPets("0");

        String[] lines = baos.toString().split(System.lineSeparator());
        String actualPetsCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals(expectedPetsCadastrados, actualPetsCadastrados);
        Assertions.assertEquals(expectedIdENome, actualIdENome);
    }

    @Test
    public void deveVerificarQuandoNaoHaPet() throws IOException, InterruptedException {

        pet.setId(0L);
        String expected = "Não há Pets cadastrados";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        when(response.body()).thenReturn("[]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);

        petService.listarPets("0");

        String[] lines = baos.toString().split(System.lineSeparator());
        String atual = lines[0];

        Assertions.assertEquals(expected, atual);
    }


    @Test
    public void deveVerificarSeDispararRequisicaoPostSeraChamado() throws IOException, InterruptedException {
        String userInput = String.format("Teste%spets.csv",
        System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        when(client.dispararRequisicaoPost(anyString(), any())).thenReturn(response);

        petService.importarPet("0", "pets.csv");
        verify(client.dispararRequisicaoPost(anyString(), anyString()), atLeast(1));
    }
}
