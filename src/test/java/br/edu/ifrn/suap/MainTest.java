package br.edu.ifrn.suap;

import java.util.Scanner;

public class MainTest {

	public static void main(String[] args) {
		System.out.println("Digite a senha");
		Scanner scanner = new Scanner(System.in);
		String senha = scanner.nextLine();
		ClienteSUAP clienteSUAP = new ClienteSUAP("20161164010023", senha);
		clienteSUAP.getUsuario();
	}
}
