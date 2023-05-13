import java.util.Scanner;

/**
 * Uma classe que lê as linhas de entrada do usuário.
 * A entrada é filtrada via getCommand para comandos válidos.
 *
 * @autor Paulo
 * @versão 2023.05.12
 */
public class Parser 
{
    // guarda todas as palavras de comando válidas.
    private Comandos commands;
    private Scanner reader;

    public Parser() 
    {
        commands = new Comandos();
        reader = new Scanner(System.in);
    }

    /**
     * Leia o próximo comando do usuário.
     * O comando retornado será válido.
     * @return Um comando válido.
     */
    public String getCommand() 
    {
        String command = null;
        do {
           // Mostrar o prompt.
            System.out.print("> ");
            
            String word = reader.next();
            // Descarta o restante da linha.
            readLine();
            if(commands.isCommand(word)) {
                command = word;
            }
            else{
                System.out.println("Comando não reconhecido: " + word);
                System.out.print("Comandos válidos são: ");
                commands.showAll();
            }
        } while(command == null);
    
        return command;
    }

    /**
     * Imprima uma lista de palavras de comando válidas.
     */
    public void showCommands()
    {
        commands.showAll();
    }

    /**
     * @return Uma linha de texto do usuário.
     */
    public String readLine()
    {
        return reader.nextLine();
    }
}
