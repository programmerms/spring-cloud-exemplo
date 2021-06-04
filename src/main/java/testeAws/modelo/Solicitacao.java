package testeAws.modelo;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@DynamoDBTable(tableName = "solicitacao")
@Builder
@NoArgsConstructor
public class Solicitacao {

    public Solicitacao(String solicitacaoID, String codGer, String arquivo, String status, String log) {
        this.solicitacaoID = solicitacaoID;
        this.codGer = codGer;
        this.arquivo = arquivo;
        this.status = status;
        this.log = log;
    }

    @Id
    @Getter
    @Setter
    @DynamoDBHashKey(attributeName = "solicitacaoID")
    private String solicitacaoID;

    @Setter
    @Getter
    @DynamoDBAttribute(attributeName = "cod_ger")
    private String codGer;


    @Setter
    @Getter
    @DynamoDBAttribute(attributeName = "arquivo")
    private String arquivo;


    @Setter
    @Getter
    @DynamoDBAttribute(attributeName = "status")
    private String status;

    @Setter
    @Getter
    @DynamoDBAttribute(attributeName = "log")
    private String log;

}
