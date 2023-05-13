/**
 * Dados de nome, endereço e número de telefone.
 *
 * @autor Paulo
 * @versão 2023.05.12
 */
public class DetalhesContacto implements Comparable<DetalhesContacto>
{
    private String nome;
    private String telefone;
    private String morada;

    /**
     * Configure os detalhes de contato. Aos detalhes são removidos
     * espaço em branco à direita.
     * @param nome O nome.
     * @param telefone O número do telefone.
     * @param morada A morada.
     */
    public DetalhesContacto(String nome, String telefone, String morada)
    {
        // Use blank strings if any of the arguments is null.
        if(nome== null) {
            nome = "";
        }
        if(telefone == null) {
            telefone = "";
        }
        if(morada == null) {
            morada = "";
        }
        this.nome = nome.trim();
        this.telefone = telefone.trim();
        this.morada = morada.trim();

        if(this.nome.length() == 0 && this.telefone.length() == 0){
            throw new IllegalStateException("Nome e telefone não pode ser ambas vazias");
        }
    }
    
    /**
     * @return O nome.
     */
    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    /**
     * @return O número do telefone.
     */
    public String getTelefone()
    {
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    /**
     * @return A morada.
     */
    public String getMorada()
    {
        return morada;
    }

    public void setEndereco(String morada){
        this.morada = morada;
    }



    /**
     * Teste de igualdade de conteúdo entre dois objetos.
     * @param other O objeto a ser comparado a este.
     * @return true se o objeto do argumento for um conjunto
     * de detalhes de contato com atributos idênticos.
     */
    public boolean equals(Object other)
    {
        if(other instanceof DetalhesContacto) {
            DetalhesContacto otherDetails = (DetalhesContacto) other;
            return nome.equals(otherDetails.getNome()) &&
                    telefone.equals(otherDetails.getTelefone()) &&
                     morada.equals(otherDetails.getMorada());
        }
        else {
            return false;
        }
    }

    /**
     * Compare esses detalhes de um contacto com outro conjunto, para o propósito
     * de ordenação. Os campos são classificados por nome, telefone e morada.
     * @param otherDetails Os detalhes a serem comparados.
     * @return um inteiro negativo se vier antes do parâmetro,
     * zero se forem iguais e um número inteiro positivo se este
     * vem depois do segundo.
     */
    public int compareTo(DetalhesContacto otherDetails)
    {
        int comparison = nome.compareTo(otherDetails.getNome());
        if(comparison != 0){
            return comparison;
        }
        comparison = telefone.compareTo(otherDetails.getTelefone());
        if(comparison != 0){
            return comparison;
        }
        return morada.compareTo(otherDetails.getMorada());
    }

    /**
     * @return Uma string de várias linhas contendo o nome, telefone e morada.
     */
    public String toString()
    {
        return nome + "\n" + telefone + "\n" + morada;
    }

    /**
     * Calcule um hashcode usando as regras encontradas em
     * "Java Eficaz", de Joshua Bloch.
     * @return Um hashcode para DetalhesContacto.
     */
    public int hashCode()
    {
        int code = 17;
        code = 37 * code + nome.hashCode();
        code = 37 * code + telefone.hashCode();
        code = 37 * code + morada.hashCode();
        return code;
    }
}
