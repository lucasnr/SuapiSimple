package br.edu.ifrn.suapi.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TokenSUAP implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;
}