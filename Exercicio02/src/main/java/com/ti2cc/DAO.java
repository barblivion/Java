package com.ti2cc;

import java.sql.*;

public class DAO {
	private Connection conexao;
	
	public DAO() {
		conexao = null;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
		String mydatabase = "teste";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
	
	public boolean inserirApartamento(Apartamento apartamento) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO apartamento (numero, tipo, valorCondominio, proprietario) "
					       + "VALUES ("+apartamento.getNumero()+ ", '" + apartamento.getTipo() + "', '"  
					       + apartamento.getValorCondominio() + "', '" + apartamento.getProprietario() + "');");
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean atualizarApartamento(Apartamento apartamento) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE apartamento SET numero = '" + apartamento.getNumero() + "', tipo= '"  
				       + apartamento.getTipo() + "', valorCondominio = '" + apartamento.getValorCondominio() + "'"
					   + " WHERE numero = " + apartamento.getNumero();
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean excluirApartamento(int numero) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM apartamento WHERE numero = " + numero);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public Apartamento[] getApartamentos() {
		Apartamento[] apartamentos = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM apartamento");		
	         if(rs.next()){
	             rs.last();
	             apartamentos = new Apartamento[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                apartamentos[i] = new Apartamento(rs.getInt("numero"), rs.getString("tipo"), 
	                		                  rs.getDouble("valorCondominio"), rs.getString("proprietario"));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return apartamentos;
	}