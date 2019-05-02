package br.edu.ifrn.suap.model;

import br.edu.ifrn.suap.ClienteSUAP;

public class AlunoSUAP extends UsuarioSUAP{

	private VinculoAlunoSUAP vinculo;
	
	private CursoSUAP curso;
	
	public AlunoSUAP(ClienteSUAP suapClient) {
		super(suapClient);
	}

	public CursoSUAP getCurso() {
		if (this.curso == null) {
			// O código do curso está presente na matricula
			// Ex: 20161164010023 -> 20161(16401)0023
			String cursoId = super.getMatricula().substring(5, 10);
			this.curso = super.clienteSUAP.getCurso(cursoId);
		}

		return this.curso;
	}

	public VinculoAlunoSUAP getVinculo() {
		return vinculo;
	}
	
}
