package pl.training.bank.disposition;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "disposition")
@Data
public class DispositionTo {

    private String accountNumber;
    private long funds;
    private String operationName;
}
