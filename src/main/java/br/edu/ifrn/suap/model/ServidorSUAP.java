package br.edu.ifrn.suap.model;

import br.edu.ifrn.suap.ClienteSUAP;

public class ServidorSUAP extends UsuarioSUAP {

	private VinculoServidorSUAP vinculo;

	public ServidorSUAP(ClienteSUAP clienteSUAP) {
		super(clienteSUAP);
	}

	public VinculoServidorSUAP getVinculo() {
		return vinculo;
	}
}
