package testeAws.repository;

import testeAws.modelo.Solicitacao;
import org.springframework.data.repository.CrudRepository;

public interface  SolicitacaoRepository extends CrudRepository<Solicitacao,String> {

    Solicitacao findBySolicitacaoID(String pk);
}
