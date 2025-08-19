package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

import java.io.IOException;
import java.util.Scanner;

public class ImportarCsvPetAbrigoCommand implements Command{

    @Override
    public void execute() {
        try {

            ClientHttpConfiguration client = new ClientHttpConfiguration();
            PetService petservice = new PetService(client);

            System.out.println("Digite o id ou nome do abrigo:");
            String idOuNome = new Scanner(System.in).nextLine();

            System.out.println("Digite o nome do arquivo CSV:");
            String nomeArquivo = new Scanner(System.in).nextLine();

            petservice.importarPet(idOuNome, nomeArquivo);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}