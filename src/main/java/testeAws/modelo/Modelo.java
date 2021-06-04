package testeAws.modelo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@Builder
public class Modelo implements Serializable {

    @Getter
    @Setter
    private String solicitaID;

    @Getter
    @Setter
    private String arquivo;

    @Getter
    @Setter
    private String codGer;

    @Getter
    @Setter
    private String status;


    public Modelo() {

    }
}
