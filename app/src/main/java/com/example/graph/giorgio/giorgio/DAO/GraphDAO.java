package com.example.graph.giorgio.giorgio.DAO;

import com.example.graph.giorgio.graph.stuffs.Edge;
import com.example.graph.giorgio.graph.stuffs.Nodo;
import com.example.graph.giorgio.graph.stuffs.SparseGraph;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class GraphDAO {
	private static final String INSERT_NODO = "INSERT INTO  nodo (X, Y, Piano) VALUES (?,?,?)";
	private static final String INSERT_ARCO = "INSERT INTO  arco (X1, Y1, Piano1, X2, Y2, Piano2) VALUES (?,?,?,?,?,?)";
	private static final String READ = "SELECT * FROM arco WHERE Piano1 = ?";
	
	public GraphDAO() {
		
	}

	public void create( SparseGraph<Nodo, String> g) throws SQLException
	{
		int i = 0;
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		System.out.print("connnnn");
			PreparedStatement ps = con.prepareStatement(INSERT_NODO);
			System.out.print("sono prima for");
			ArrayList<Nodo> nodi = g.vertices();
			for(Nodo n: nodi) {

				System.out.print("sono nel for");
				ps.setInt(1, n.getX());
				ps.setInt(2, n.getY());
				ps.setInt(3, 55);
				i = ps.executeUpdate();
			}
			
			ps = con.prepareStatement(INSERT_ARCO);
			
			ArrayList<Edge> archi = g.getAllEdges();
			
			for(Edge e: archi) {
				Nodo n1 = (Nodo) e.getIn();
				Nodo n2 = (Nodo) e.getOut();
				ps.setInt(1, n1.getX());
				ps.setInt(2, n1.getY());
				ps.setInt(3, 55);
				ps.setInt(4, n2.getX());
				ps.setInt(5, n2.getY());
				ps.setInt(6, 55);
				ps.executeUpdate();
			}
			con.commit();
			ps.close();
			DriverManagerConnectionPool.getIstance().releaseConnection(con);
			if (i != 1)
			{
				System.out.print("Cazzo");
				//throw new InsertFailedException("Si &egrave; verifacato un errore durante il salvataggio sul database");
			}
			
	}
	
	public SparseGraph<Nodo, String> read(int piano) throws SQLException
	{
		Connection con = DriverManagerConnectionPool.getIstance().getConnection();
		PreparedStatement ps = con.prepareStatement(READ);
		ps.setInt(1, piano);
		ResultSet rs = ps.executeQuery();
		SparseGraph<Nodo, String> grafo = new SparseGraph<Nodo, String>();
		while(rs.next())
		{
			Nodo n1 = new Nodo(rs.getInt(1),rs.getInt(2));
			Nodo n2 = new Nodo(rs.getInt(3),rs.getInt(4));
			grafo.addVertex(n1);
			grafo.addVertex(n2);
			grafo.addUndirectedEdge(n1, n2, "");
		}
		
		rs.close();
		ps.close();
		DriverManagerConnectionPool.getIstance().releaseConnection(con);
		return grafo;
		
		//throw new TuplaNotFoundException("La richiesta di tirocinio selezionata non &egrave; presente nel database");
		
	}

	
	
}
