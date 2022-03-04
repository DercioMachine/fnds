package mz.fipag.grm.service;


import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
	
	public static final String ACCOUNT_SID = "AC210a20618423ed1ec9fabcd1a047dc9c";
    public static final String AUTH_TOKEN = "627b5ac718dce7c7a69a95537fcc9f29";

    public void sendSMS(String telefone,String mensagem) throws AuthenticationException {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(telefone),
                new com.twilio.type.PhoneNumber("+19107084699"),
                mensagem)
                .create();

        System.out.println(message.getSid());

    }

}
