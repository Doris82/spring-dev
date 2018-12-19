package pl.training.bank.account;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name= "account")
@Data
public class AccountDto {
    private String number;
    private Long balance;
}
