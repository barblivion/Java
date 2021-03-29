package com.ti2cc;

public class Apartamento {
	private int numero;
	private String tipo;
	private double valorCondominio;
	private String proprietario;
	
	public Apartamento() {
		this.numero = 0;
		this.tipo = "";
		this.valorCondominio = 0.0;
		this.proprietario = "";
	}
	
	public Apartamento(int numero, String tipo, double valorCondominio, String proprietario) {
		this.numero = numero;
		this.tipo = tipo;
		this.valorCondominio = valorCondominio;
		this.proprietario = proprietario;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getValorCondominio() {
		return valorCondominio;
	}

	public void setValorCondominio(double valorCondominio) {
		this.valorCondominio = valorCondominio;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	@Override
	public String toString() {
		return "Apartamento [numero=" + numero + ", tipo=" + tipo + ", valorCondominio=" + valorCondominio + ", proprietario=" + proprietario + "]";
	}
	
}
