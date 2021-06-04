package testeAws.service;

import testeAws.modelo.Solicitacao;
import testeAws.repository.SolicitacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SolicitacaoService {
    static Logger logger = LoggerFactory.getLogger(SolicitacaoService.class);

    @Autowired
    private SolicitacaoRepository repository;

    public Solicitacao update(Solicitacao solicitacao){

        logger.info("-- solicitacao id -> " + solicitacao.getSolicitacaoID() );

        Solicitacao buscaSolicitacao = repository.findBySolicitacaoID(solicitacao.getSolicitacaoID());

        logger.info("-- Encontrou registro do DybamoDB -> " +  buscaSolicitacao!=null?"Sim":"Nao" );


        if (buscaSolicitacao!=null){

            buscaSolicitacao.setArquivo(solicitacao.getArquivo());
            buscaSolicitacao.setStatus(solicitacao.getStatus());
            buscaSolicitacao.setLog(solicitacao.getLog());

            repository.save(buscaSolicitacao);

            logger.info("--- Ok registro persistido com sucesso--");
        }

        return buscaSolicitacao;

    }


}
