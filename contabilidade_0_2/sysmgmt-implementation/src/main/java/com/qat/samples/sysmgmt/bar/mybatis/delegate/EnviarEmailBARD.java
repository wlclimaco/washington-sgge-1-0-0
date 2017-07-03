/** create by system gera-java version 1.0.0 01/09/2016 12:56 : 31*/

package com.qat.samples.sysmgmt.bar.mybatis.delegate;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.qat.framework.validation.ValidationUtil;
import com.qat.samples.sysmgmt.bar.Empresa.IEmpresaBAR;
import com.qat.samples.sysmgmt.entidade.model.Empresa;
import com.qat.samples.sysmgmt.util.model.request.FetchByIdRequest;

/**
 * Delegate class for the SysMgmt DACs. Note this is a final class with ONLY
 * static methods so everything must be passed into the methods. Nothing
 * injected.
 */
public final class EnviarEmailBARD extends SqlSessionDaoSupport {

	/** The Constant ZERO. */
	private static final Integer ZERO = 0;

	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;

	/**
	 * Fetch objects by request.
	 *
	 * @param sqlSession
	 *            the sql session
	 * @param request
	 *            the request
	 * @param countStatement
	 *            the count statement
	 * @param fetchPagedStatement
	 *            the fetch paged statement
	 * @param response
	 *            the response
	 */
	@SuppressWarnings("unchecked")
	public static Boolean sendMailTLS(Integer empresaPai, IEmpresaBAR empresaBAR, Empresa emailDestino) {
		Boolean count = false;

		Empresa empresa = empresaBAR.fetchEmpresaById(new FetchByIdRequest(empresaPai));
		if (!ValidationUtil.isNull(empresa)) {
			if (!ValidationUtil.isNull(empresa.getConfiguracao())) {
				if ((!ValidationUtil.isNull(empresa.getConfiguracao().getConfSMTP()))&&(!ValidationUtil.isNull(empresa.getConfiguracao().getConfSMTP().getPorta()))) {
					// Step1
					System.out.println("\n 1st ===> setup Mail Server Properties..");
					mailServerProperties = System.getProperties();
					mailServerProperties.put("mail.smtp.port", empresa.getConfiguracao().getConfSMTP().getPorta());
					mailServerProperties.put("mail.smtp.auth", "true");
					mailServerProperties.put("mail.smtp.starttls.enable", "true");
					System.out.println("Mail Server Properties have been setup successfully..");

					// Step2
					System.out.println("\n\n 2nd ===> get Mail Session..");
					getMailSession = Session.getDefaultInstance(mailServerProperties, null);
					generateMailMessage = new MimeMessage(getMailSession);
					try {
						generateMailMessage.addRecipient(Message.RecipientType.TO,
								new InternetAddress(emailDestino.getUsuarios().get(0).getEmail()));

						 generateMailMessage.addRecipient(Message.RecipientType.CC,
						 new InternetAddress(empresa.getConfiguracao().getConfSMTP().getEndEmail()));
						generateMailMessage.setSubject("Greetings from Crunchify..");
						String emailBody = "<!-- #######  YAY, I AM THE SOURCE EDITOR! #########-->"+
"<h1 style='color: #5e9ca0;'>Bem vindo ao melhor sistema de gest&atilde;o empresarial GR&Aacute;TIS</h1>"+
"<h2 style='color: #2e6c80;'>O E-Cont&aacute;bil &eacute; um sistema totalmente online, n&atilde;o precisa instala&ccedil;&atilde;o, funciona diretamente no seu navegador e em qualquer sistema operacional. Tamb&eacute;m em celulares e tablets. Basta ter acesso a internet.</h2>"+
"<p>Integra&ccedil;&atilde;o dos processos de gest&atilde;o em sua micro ou pequena empresa, das finan&ccedil;as &agrave;s vendas, com cadastro de clientes, estoque e relat&oacute;rios.</p>"+
"<p>Contas a pagar e a receber com integra&ccedil;&atilde;o banc&aacute;ria em seu controle financeiro. Contas em dia &eacute; sa&uacute;de financeira para seus neg&oacute;cios.</p>"+
"<p>Controle de propostas e vendas integrado a emiss&atilde;o de NF-e e boleto. Compras e estoque integrado ao cadastro de clientes e fornecedores.</p>"+
"<p>Ganhe tempo na troca de documentos e informa&ccedil;&otilde;es com o contador e melhore a qualidade da rela&ccedil;&atilde;o de sua empresa com a contabilidade</p>"+
"<h2 style='color: #2e6c80;'>Some useful features:</h2>"+
"<ol style='list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;'>"+
"<li style='clear: both;'><img style='float: left;' src='https://html-online.com/img/01-interactive-connection.png' alt='interactive connection' width='45' />"+
"<h2 class=''>E-CONT&Aacute;BIL-MED</h2>"+
"<p style='text-align: left;'><span style='color: #000000;'>SISTEMA PARA CLINICAS MEDICAS&nbsp;</span>Integra&ccedil;&atilde;o dos processos de gest&atilde;o para clinicas de medicas, das finan&ccedil;as &agrave;s processos,agenda, com cadastro de clientes, medicos,prontuario eletronico e relat&oacute;rios.</p>"+
"</li>"+
"<li style='clear: both;'></li>"+
"<li style='clear: both;'><img style='float: left;' src='https://html-online.com/img/02-html-clean.png' alt='html cleaner' width='45' />"+
"<h2>E-CONT&Aacute;BIL-ADV:</h2>"+
"E&nbsp;o mais completo Software Jur&iacute;dico on-line para advogados e escrit&oacute;rios de advocacia. Com ele voc&ecirc; controla seus processos, clientes e financeiro, al&eacute;m de permitir a consulta dos andamentos processuais e no di&aacute;rio oficial de forma &aacute;gil. Tudo isso com uma interface amig&aacute;vel e totalmente on-line.</li>"+
"<li style='clear: both;'></li>"+
"<li style='clear: both;'></li>"+
"<li style='clear: both;'><img style='float: left;' src='https://html-online.com/img/03-docs-to-html.png' alt='Word to html' width='45' />"+
"<h2 class=''>E-CONT&Aacute;BIL-COND:</h2>"+
"<h2 id='offer-title' data-editable='true' data-field-name='landing_page[values[offer-title]]'>E&nbsp;o sistema para condom&iacute;nio para beneficiar s&iacute;ndicos, administradores e moradores de condom&iacute;nio.</h2>"+
"</li>"+
"<li style='clear: both;'><img style='float: left;' src='https://html-online.com/img/04-replace.png' alt='replace text' width='45' />"+
"<h2 class=''>E-CONT&Aacute;BIL-CONTABIL:</h2>"+
"</li>"+
"<li style='clear: both;'>Ganhe tempo na troca de documentos e informa&ccedil;&otilde;es com o contador e melhore a qualidade da rela&ccedil;&atilde;o de sua empresa com a contabilidade</li>"+
"<li style='clear: both;'><img style='float: left;' src='https://html-online.com/img/05-gibberish.png' alt='gibberish' width='45' />"+
"<h2 class=''>E-CONT&Aacute;BIL-GEST&Atilde;O:</h2>"+
"</li>"+
"<li style='clear: both;'>Integra&ccedil;&atilde;o dos processos de gest&atilde;o em sua micro ou pequena empresa, das finan&ccedil;as &agrave;s vendas, com cadastro de clientes, estoque e relat&oacute;rios</li>"+
"<li style='clear: both;'><img style='float: left;' src='https://html-online.com/img/6-table-div-html.png' alt='html table div' width='45' />"+
"<h2 class=''>E-CONT&Aacute;BIL-SPAR:</h2>"+
"</li>"+
"<li style='clear: both;'>O E-cont&aacute;bil-Spar e ideal para seu sal&atilde;o,barbearia, clinica de stetica entre outros. Faz campanhas de marketing para trazer novos clientes para os seus parceiros, mas al&eacute;m disso, se preocupa em oferecer uma ferramenta de marketing e adminitra&ccedil;&atilde;o que fideliza os seus clientes e garante que eles voltem mais vezes, enviando mensagens de texto e emails.</li>"+
"</ol>"+
"<p>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</p>'";
						generateMailMessage.setContent(emailBody, "text/html");
						System.out.println("Mail Session has been created successfully..");

						// Step3
						System.out.println("\n\n 3rd ===> Get Session and Send mail");
						Transport transport = getMailSession.getTransport("smtp");

						// Enter your correct gmail UserID and Password
						// if you have 2FA enabled then provide App Specific
						// Password
						transport.connect(empresa.getConfiguracao().getConfSMTP().getServSMTP(), empresa.getConfiguracao().getConfSMTP().getEndEmail(), empresa.getConfiguracao().getConfSMTP().getSenha());
						transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
						transport.close();
					} catch (AddressException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MessagingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		// final String username = "wlclimaco@gmail.com";
		// final String password = "gugubravo";
		//
		// Properties props = new Properties();
		// props.put("mail.smtp.auth", "true");
		// props.put("mail.smtp.starttls.enable", "true");
		// props.put("mail.smtp.host", "smtp.gmail.com");
		// props.put("mail.smtp.port", "587");
		//
		// Session session = Session.getInstance(props,
		// new javax.mail.Authenticator() {
		// protected PasswordAuthentication getPasswordAuthentication() {
		// return new PasswordAuthentication(username, password);
		// }
		// });
		//
		// try {
		//
		// Message message = new MimeMessage(session);
		// message.setFrom(new InternetAddress("wlclimaco@gmail.com"));
		// message.setRecipients(Message.RecipientType.TO,
		// InternetAddress.parse("wlclimaco@gmail.com"));
		// message.setSubject("Testing Subject");
		// message.setText("Dear Mail Crawler,"
		// + "\n\n No spam to my email, please!");
		//
		// Transport.send(message);
		//
		// System.out.println("Done");
		//
		// } catch (MessagingException e) {
		// throw new RuntimeException(e);
		// }

		return count;
	}

	public static Boolean sendMailSSL(Integer emprId, IEmpresaBAR empresaBAR) {
		Boolean count = false;
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("username", "password");
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from@no-spam.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("to@no-spam.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler," + "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return count;
	}
}
