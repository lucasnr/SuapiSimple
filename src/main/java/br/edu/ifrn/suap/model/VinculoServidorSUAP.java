package br.edu.ifrn.suap.model;

import com.google.gson.annotations.SerializedName;

public class VinculoServidorSUAP {

	private String matricula;
	
	private String nome;
	
	private String cargo;
	
	@SerializedName("setor_suap")
	private String setorSUAP;
	
	@SerializedName("setor_siape")
	private String setorSiape;
	
	@SerializedName("jornada_trabalho")
	private String jornadaTrabalho;
	
	private String[] funcao;
}
