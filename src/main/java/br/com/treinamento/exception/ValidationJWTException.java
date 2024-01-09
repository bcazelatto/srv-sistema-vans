package br.com.treinamento.exception;

public class ValidationJWTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidationJWTException(String erro) {
		super(erro);
	}
}
