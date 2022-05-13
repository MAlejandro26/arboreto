package datos;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class DTEnviarEmailUsuario {
	//ATRIBUTOS
		/*---------------------- Configuración Localhost------------------------------*/
		private static final String SMTP_HOST_NAME = "smtp.gmail.com";
		private static final String SMTP_AUTH_USER = "gerenciaArboretoCP@gmail.com";
		private static final String SMTP_AUTH_PWD = "Tengohambre1!";
		
		//Enlace
	    String linkHR = "http://localhost:8080/arboreto-carmelo-palma/login.jsp";

	    
	    //DECLARAMOS UNA CLASE PRIVADA COMO ATRIBUTO QUE HEREDA JAVAX.MAIL.AUTHENTICATOR
	    private class SMTPAuthenticator extends javax.mail.Authenticator 
		{
			public PasswordAuthentication getPasswordAuthentication() 
			{
				String username = SMTP_AUTH_USER;
				String password = SMTP_AUTH_PWD;
				return new PasswordAuthentication(username, password);
			}
		}
	    
	   /*----------------------------------------------------------------------------*/

	  //METODO QUE ENVIA EL EMAIL DE VERIFICACION
	 
	  public boolean enviarEmailVerificacion(String usuario, String clave, String correo, String codigo) throws MessagingException{
		
			 boolean debug=false;
			
		   // Correo del solicitante
			String email_solicitante = correo;
		
		   // Correo del remitente
			String email_remitente = SMTP_HOST_NAME;
		
		   // Obtener propiedades del sistema
		   Properties properties = new Properties();
		   
		   
		   /*---------------------- Configuración del servidor de correo---------------------------*/ 
	  		   properties.setProperty("mail.smtp.host", SMTP_HOST_NAME);
	  		   properties.put("mail.smtp.auth", "true");
	  		   properties.setProperty("mail.smtp.port", "587");
	  		   properties.put("mail.smtp.starttls.enable", "true");
		   /*--------------------------------------------------------------------------------------*/
		   
		    Authenticator auth = new SMTPAuthenticator();

		     Session session = Session.getInstance(properties,auth);
			 session.setDebug(debug);
		   
		      // Create a default MimeMessage object.
		      	MimeMessage message = new MimeMessage(session);
		
		      // Establecer De (remitente)
		      	message.setFrom(new InternetAddress(email_remitente));
		
		      // Establecer Para (solicitante)
		      	message.addRecipient(Message.RecipientType.TO, new InternetAddress(email_solicitante));
		      
		      // Asunto: encabezado del archivo
			  message.setSubject("Proceso de verificación de usuario");
		        
		      //Cuerpo del correo  
		        String myMsg = "<meta charset=\"utf-8\">";
		      	myMsg += "A continuaci&oacute;n se detallan los datos: <br><br>";
		      	myMsg += "<strong>Usuario: </strong> "+usuario+"<br>";
		      	myMsg += "<strong>Correo electr&oacute;nico: </strong> "+ correo+"<br>";
	       	    myMsg += "Enlace de verificaci&oacute;n: "  + linkHR + "?codverif="+ codigo + "<br>";
	       	    myMsg += "Aseg&uacute;rate de hacer clic en el enlace de verificaci&oacute;n que has recibido para que podamos activar tu cuenta, en caso de no haber solicitado una cuenta, por favor hacer caso omiso al presente correo.";
		      	myMsg += "<br>----------------------------------------------------------<br>";
		      	myMsg += "Iris S.<br>";
		      	myMsg += "Coordinador de Arborerto Carmelo Palma<br>";
		      	myMsg += "Universidad Centroamericana";
		      
		      	message.setContent(myMsg, "text/html");
		      
		      //Enviar Correo
		      Transport transport = session.getTransport("smtp");
		      transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
		      Transport.send(message);
		      debug = true;
		      System.out.println("El mensaje fue enviado con éxito");
		      return debug;
		}
	  
	  
}
