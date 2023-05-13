/**
 * Esta classe contém uma enumeração de todas as palavras de comando conhecidas
 * ao programa.
 *
 * @autor David J. Barnes e Michael Kölling.
 * @versão 2016.02.29
 */
public class Comandos
{
    // a constant array that holds all valid command words
    /*private static final String comandosValidos[] = {

    };*/
    private static PalavrasComando comandosValidos;

    /**
     * Constructor de Comandos
     */
    public Comandos()
    {
    }

    /**
     * Verifique se uma determinada String é uma palavra de comando válida.
     * @param aString A string a ser verificada.
     * @return true se for válido, false se não for.
     */
    public boolean isCommand(String aString)
    {
        if(aString != null) {
           // try {
                for(PalavrasComando comando: comandosValidos.values() ) {
                    if(comando.valueOf(aString).toString().equals(aString))
                        return true;
                }
           // } catch (IllegalArgumentException e ) {
           //     return false;
           // }

        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Imprima todos os comandos válidos para System.out.
     */
    public void showAll() 
    {
        for(PalavrasComando command : comandosValidos.values()) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
