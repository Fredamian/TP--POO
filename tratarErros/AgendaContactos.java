import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Uma classe para manter um número diverso de contato.
 * Os detalhes são indexados por nome e número de telefone.
 *
 * @autor Paulo
 * @versão 2023.05.12
 *
 * complementada por @autor Fredy Brito
 */
public class AgendaContactos
{
    // // Armazenamento para detalhes de contacto.
    private TreeMap<String, DetalhesContacto> agenda;

    DetalhesContacto detalhes;
    private int qtDeEntradas;

    /**
     * Execute inicialização para a agenda de contactos.
     */
    public AgendaContactos()
    {
        agenda = new TreeMap<>();
        qtDeEntradas = 0;
    }
    
    /**
     * Procure um nome ou número de telefone e retorne o
     * detalhes de contato correspondentes.
     * @param key O nome ou número a ser pesquisado.
     * @return Os detalhes correspondentes à chave.
     */
    public DetalhesContacto obterDetalhes(String key){
        DetalhesContacto testar = agenda.get(key);
        if(key==null){
            throw new NullPointerException("null key em obterDetalhes");
        }
        else if(key.trim().equals("")){
            throw new IllegalArgumentException("Não é aceite String vazia como argumento");
        }
        return testar;
    }

    /**
     * Retorna se a chave atual está ou não em uso.
     * @param key O nome ou número a ser pesquisado.
     * @return true se a chave estiver em uso, false caso contrário.
     */
    public boolean chaveEmUso(String key)
    {
        return agenda.containsKey(key);
    }

    /**
     * Adicione um novo Contacto à agenda.
     * @param details Os detalhes a serem associados à pessoa.
     */
    public void addDetails(DetalhesContacto details)
    {
        agenda.put(details.getNome(), details);
        agenda.put(details.getTelefone(), details);
        qtDeEntradas++;
    }
    
    /**
     * Altere os detalhes armazenados anteriormente na chave fornecida.
     * @param *oldKey Uma das chaves usadas para armazenar os detalhes.
     * @param *details Os detalhes da substituição.
     */
    public void alterarDetalhes(String oldKey, DetalhesContacto details) throws ContactoInexistenteException {
        if (agenda.containsKey(oldKey)) { //TreeMap
            agenda.put(oldKey, details); // o put serve para adicionar ou mudar o valor do oldKey, a mesma ideia de python de chave e valor
        }else
            throw new ContactoInexistenteException("O chave de contacto passada não esta presente");
        }


    /**
     * Pesquise todos os detalhes armazenados em uma chave que começa com
     * o prefixo fornecido.
     * @param keyPrefix O prefixo da chave a ser pesquisado.
     * @return Um array dos detalhes que foram encontrados.
     */
    public DetalhesContacto[] search(String keyPrefix)
    {
        // Constrói uma lista das correspondências.
        List<DetalhesContacto> matches = new LinkedList<>();
        // Encontra chaves que são iguais ou maiores que o prefixo.
        SortedMap<String, DetalhesContacto> tail = agenda.tailMap(keyPrefix);
        Iterator<String> it = tail.keySet().iterator();
        // Para quando encontramos uma incompatibilidade.
        boolean endOfSearch = false;
        while(!endOfSearch && it.hasNext()) {
            String key = it.next();
            if(key.startsWith(keyPrefix)) {
                matches.add(agenda.get(key));
            }
            else {
                // À medida que a lista for ordenada, não haverá mais nenhuma.
                endOfSearch = true;
            }
        }
        DetalhesContacto[] results = new DetalhesContacto[matches.size()];
        matches.toArray(results);
        return results;
    }

    /**
     * Retorna o número de entradas atualmente no
     * agenda de endereço.
     * @return O número de entradas.
     */
    public int getQtDeEntradas()
    {
        return qtDeEntradas;
    }

    /**
     * Remova uma entrada com a chave fornecida da agenda de endereços.
     * @param key Uma das chaves da entrada a ser removida.
     */
    public void removeDetails(String key)
            throws ContactoInexistenteException { // ao fazer assim, diz que o removeDetails pode lancar a execao indicada no topo
        if(chaveEmUso(key)){
        DetalhesContacto details = agenda.get(key);
        agenda.remove(details.getNome());
        agenda.remove(details.getTelefone());
        qtDeEntradas--;
        }
        else{
            throw new ContactoInexistenteException(key);
    }

}

    /**
     * Retornar todos os detalhes de contato, classificados de acordo
     * à ordem de ordenação da classe DetalhesContacto.
     * @return Uma lista ordenada dos detalhes.
     */
    public String listaDetalhes()
    {
        // Como cada entrada é armazenada em duas chaves, é
        // necessário para construir um conjunto de DetalhesContacto. Esse
        // elimina duplicatas.
        StringBuilder allEntries = new StringBuilder();
        Set<DetalhesContacto> sortedDetails = new TreeSet<>(agenda.values());
        for(DetalhesContacto details : sortedDetails) {
            allEntries.append(details).append("\n\n");
        }
        return allEntries.toString();
    }
}