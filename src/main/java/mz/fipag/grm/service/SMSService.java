package mz.fipag.grm.service;


import com.twilio.Twilio;
import com.twilio.exception.AuthenticationException;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.stereotype.Service;

@Service
public class SMSService {
	
	 public static final String ACCOUNT_SID = "yhdufjfemdgjdbhfec9fabcd1a047dc9c";
	//public static final String ACCOUNT_SID = "poljdtgefshujde4362a10f97c3590ce380";
    public static final String AUTH_TOKEN = "83ueteveyhd2519302209ccd7bec";

    public void sendSMS(String telefone,String mensagem) throws AuthenticationException {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                //new MYCOMPANY
                new com.twilio.type.PhoneNumber(telefone),
                new com.twilio.type.PhoneNumber("873u7eys7sywtyeebe296ae590c39"),
                //new com.twilio.type.PhoneNumber("+19451524854699"),
               // new com.twilio.type.PhoneNumber("+1855478547545944611"),
                mensagem)
                .create();

        System.out.println(message.getSid());

    }

}
