/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.util.Properties;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.search.FlagTerm;

public class EmailReader {

    public static void main(String args[]) {
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");
        try {
                Session session = Session.getDefaultInstance(props, null);
                Store store = session.getStore("imaps");

                // servidor IMAP para gmail. 
                // remplazar <usuario> con su usuario de Gmail.
                // remplazar <password> con el password correspondiente al usuario especificado.

                store.connect("imap.gmail.com", "Corposoft10@gmail.com", "lkanfgsrldgvplyz");

                // Servidor IMAP para yahoo.
                //store.connect("imap.mail.yahoo.com", "<username>", "<password>");

                System.out.println(store);

                Folder inbox = store.getFolder("Inbox");
                inbox.open(Folder.READ_ONLY);
                mostrarCorreosNoLeidos(inbox);//indicamos que lee los mails no leidos
                   
                
        } catch (NoSuchProviderException e) {
            System.out.println(e.toString());
            System.exit(1);
        } catch (MessagingException e) {
            System.out.println(e.toString());
            System.exit(2);
        }

    }
    
    static public void mostrarCorreosNoLeidos(Folder inbox){        
        try {
            FlagTerm ft = new FlagTerm(new Flags(Flags.Flag.SEEN), false);
            Message msg[] = inbox.search(ft);
            System.out.println("Correos sin leer: "+msg.length);
            
            for(Message message:msg) {
                try {
                    System.out.println("Fecha: "+message.getSentDate().toString());
                    System.out.println("De: "+message.getFrom()[0].toString());            
                    System.out.println("Asunto: "+message.getSubject().toString());
                    System.out.println("Contenido: "+message.getContent().toString());
                    System.out.println("******************************************");
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    System.out.println("Sin Información");
                }
            }
        } catch (MessagingException e) {
            System.out.println(e.toString());
        }
    }
}
