package com.ti2cc;

public class Principal {
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		
		//Inserir um elemento na tabela
		Apartamento apartamento = new Apartamento(11, "kitnet", 1200.00, "joaquim");
		if(dao.inserirApartamento(apartamento) == true) {
			System.out.println("Inserção com sucesso -> " + apartamento.toString());
		}

		//Atualizar apartamento
		apartamento.setNumero("novo apartamento");
		dao.atualizarApartamento(apartamento);
		
		//Excluir usuário
		dao.excluirApartamento(apartamento.getNumero());
		
		//Mostrar apartamentos
		apartamentos = dao.getApartamentos();
		System.out.println("==== Mostrar apartamentos === ");		
		for(int i = 0; i < apartamentos.length; i++) {
			System.out.println(apartamentos[i].toString());
		}
		
		dao.close();
	}
}
