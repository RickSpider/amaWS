/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amaws.model;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackSpider
 */
@Service
public class PushService {
    
   private AsyncResult<String> result = new AsyncResult<String>("0 push enviados");
    
    @Async
    public void sendPushs(String token) throws IOException{
        
      /* BufferedWriter out = null;   
        try {   
            out = new BufferedWriter(new FileWriter("xxx.txt", true));   
            out.write("Hola");   
        } catch (IOException e) {   
            // error processing code   
        } finally {   
            if (out != null) {   
                out.close();   
            }   
        }*/
        
        
     /*  for (int i=0 ; i<10 ; i++){
            
            result = new AsyncResult("enviados "+i+" de 10");
            System.out.println("push "+i+" lml");
            sleepALittle(3000);
           
        }*/
       
        //System.out.println("entre en envio de notificacion");
        ApnsService service = APNS.newService().withCert("certificadoP.p12","123456").withSandboxDestination().build();
        //System.out.println("pase el apn service");
        String payload = APNS.newPayload().alertBody("hello push").build();
        //String token = "38F94B720425AEF3BE6547076668C2B4F6ED539FEB115C2588A5D05FA5C0CBF8";
       // System.out.println("enviado push...");
        service.push(token, payload);
        //System.out.println("push enviado");
        
        /*File wd = new File(".");
        System.out.println("working dir: " + wd.getAbsolutePath());*/
        
    }
    
    @Async
    public void sendPushs() throws IOException, SQLException{
        
        UserIDUS udius = new UserIDUS();
        ArrayList<String> tokens = udius.listarAlertas();
        
        if (!tokens.isEmpty() ){
            //System.out.println("entre en envio de notificacion");
            ApnsService service = APNS.newService().withCert("certificadoP.p12","123456").withSandboxDestination().build();
            //System.out.println("pase el apn service");
            String payload = APNS.newPayload().alertBody("Se detectaron nubes de lluvia").build();

            tokens.forEach((t) -> {
                service.push(t, payload);
            });
        } else {
            System.out.println("nada para enviar");
        }
    }
    
    
    
    public AsyncResult<String> getResult() {
	return result;
}
    
    public String getPushsend() throws ExecutionException{
        return result.get();
    }
   
    public void sendPush(int i){
        System.out.println(i+" Push enviados");
    }
    
    private void sleepALittle(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
