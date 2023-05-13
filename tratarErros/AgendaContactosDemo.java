/**
 * Fornecer uma demonstração simples da classe AgendaContactos.
 * Os dados de amostra são adicionados ao catálogo de endereços,
 * e um interface de linha de comando de texto é fornecida.
 *
 * @autor Paulo
 * @versão 2023.05.12
 */
public class AgendaContactosDemo
{
    private AgendaContactos book;
    private AgendaContactosTextUI interaction;

    /**
     * Configure uma AgendaContatos com dados de amostra.
     */
    public AgendaContactosDemo()
    {
        DetalhesContacto[] sampleDetails = {
            new DetalhesContacto("david",   "08459 100000", "address 1"),
            new DetalhesContacto("maira", "08459 200000", "address 2"),
            new DetalhesContacto("john",    "08459 300000", "address 3"),
            new DetalhesContacto("helena",   "08459 400000", "address 4"),
            new DetalhesContacto("emma",    "08459 500000", "address 5"),
            new DetalhesContacto("kate",    "08459 600000", "address 6"),
            new DetalhesContacto("cristy",   "08459 700000", "address 7"),
            new DetalhesContacto("ruth",    "08459 800000", "address 8"),
        };
        book = new AgendaContactos();
        for(DetalhesContacto details : sampleDetails) {
            book.addDetails(details);
        }
        interaction = new AgendaContactosTextUI(book);
    }

    /**
     * Permita que o usuário interaja com a agenda de contactos
     */
    public void showInterface(){
        interaction.run();
    }

    /**
     * @return a Agenda de contactos
     */
    public AgendaContactos getBook()
    {
        return book;
    }
}
