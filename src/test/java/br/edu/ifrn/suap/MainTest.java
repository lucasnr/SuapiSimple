package br.edu.ifrn.suap;

import br.edu.ifrn.suap.model.ServidorSUAP;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Digite a senha");
		ClienteSUAP clienteSUAP = new ClienteSUAP("3006324", "789123456abcdE");
		ServidorSUAP servidor = clienteSUAP.getUsuario(ServidorSUAP.class);
		System.out.println(servidor);
		System.out.println(servidor.getVinculo());
	}
}
