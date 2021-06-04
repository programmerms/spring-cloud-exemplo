package testeAws;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent;
import testeAws.modelo.Modelo;
import testeAws.modelo.Solicitacao;
import testeAws.service.SolicitacaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FunctionConfiguration {
	private static Logger logger = LoggerFactory.getLogger(FunctionConfiguration.class);

	/*
	 * You need this main method or explicit <start-class>example.FunctionConfiguration</start-class>
	 * in the POM to ensure boot plug-in makes the correct entry
	 */
	public static void main(String[] args) {
		SpringApplication.run(FunctionConfiguration.class, args);
	}

    @Autowired
	private SolicitacaoService service;

	@Bean
	public Consumer<DynamodbEvent> processDynamDBEvent() {
		return dynamoDBEvent -> {

			try {
				logger.info("Querida cheguei ...Iabadabadu!!!");

				if (dynamoDBEvent == null) {
					logger.info("Ihhh deu ruim sou null :-( !!!");
				} else {
					List<Modelo> listModelo = new ArrayList<>();
					dynamoDBEvent.getRecords().stream().filter(filtro -> filtro.getEventName().equals("INSERT"))
							.forEach(registro -> {

                                Modelo modelo = Modelo.builder()
										        .solicitaID(registro.getDynamodb().getKeys().get("solicitacaoID").getS())
										        .arquivo(registro.getDynamodb().getNewImage().get("arquivo").getS())
										        .codGer(registro.getDynamodb().getNewImage().get("cod_ger").getS())
										        .status(registro.getDynamodb().getNewImage().get("status").getS())
										       .build();

								listModelo.add(modelo);

   	 					   }
					);

					logger.info("--- Atualizando Registro -----");

					listModelo.forEach( modelo->{

						long timestamp = Instant.now().toEpochMilli();

						Solicitacao solicitacao = Solicitacao.builder()
								.solicitacaoID(modelo.getSolicitaID())
								.codGer(modelo.getCodGer())
								.status("2")
								.arquivo("ARQ" + modelo.getCodGer()+modelo.getSolicitaID()+"ALTERADO")
								.log("Registro Atualizado em  : "+ String.valueOf(timestamp))
								.build();

						logger.info("-- Enviando para service --");
						service.update(solicitacao);

					});

				}

			} catch (RuntimeException e) {

				logger.info(" Opa deu erro :-( " + e.getMessage());

			}

			logger.info("Querida passei ...Iabadabadu!!!");

		};
	}


}
