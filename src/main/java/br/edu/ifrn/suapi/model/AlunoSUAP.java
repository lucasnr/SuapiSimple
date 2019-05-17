package br.edu.ifrn.suapi.model;

import java.util.Arrays;

/**
 * Classe modelo de um usuário do SUAP com vinculo do tipo Aluno
 * 
 * @author Lucas do Nascimento Ribeiro
 * @since 1.1
 * @version 1.1
 */
public class AlunoSUAP extends UsuarioSUAP {

	/**
	 * Vinculo do aluno com o SUAP
	 * 
	 * @see VinculoAlunoSUAP
	 * 
	 * @since 1.1
	 */
	private VinculoAlunoSUAP vinculo;

	/**
	 * Curso ao qual este aluno pertence
	 * 
	 * @since 1.1
	 */
	private CursoSUAP curso;

	private AlunoSUAP() {
	}

	/**
	 * Retorna o curso do aluno, caso o atributo seja nulo, o mesmo será buscado
	 * através da conexão com o cliente a partir do código do curso presente na
	 * matrícula
	 * 
	 * @return O {@link CursoSUAP} deste aluno
	 */
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

	@Override
	public String toString() {
		return "AlunoSUAP [curso=" + curso + ", suapId=" + suapId + ", matricula=" + matricula + ", nomeUsual="
				+ nomeUsual + ", CPF=" + CPF + ", RG=" + RG + ", filiacao=" + Arrays.toString(filiacao)
				+ ", dataDeNascimento=" + dataDeNascimento + ", naturalidade=" + naturalidade + ", tipoSanguineo="
				+ tipoSanguineo + ", email=" + email + ", urlFoto=" + urlFoto + ", urlFotoGrande=" + urlFotoGrande
				+ ", tipoVinculo=" + tipoVinculo + "]";
	}

}
