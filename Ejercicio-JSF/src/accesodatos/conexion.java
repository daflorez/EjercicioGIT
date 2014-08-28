package accesodatos;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.CallableStatement; 
import java.sql.Types;

import com.sun.media.sound.ModelOscillator;

import modelo.Usuario;

public class conexion {

	private Connection con= null;

	private final String user = "adminhcmps";
	private final String pass = "12345";
	private final String db = "HCMPS";

	public conexion() {
	try {

	String url = "jdbc:sqlserver://pspruebasbd:1433;databaseName=HCMPS";
	//System.out.println("===============\n");


	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

	con = DriverManager.getConnection(url, user, pass);


	if (con != null){
	System.out.println("Conexion con " + db + " creada con exito.");
	}

	}
	catch (Exception ex) {
	ex.printStackTrace();
	}
	}

	public ArrayList<modelo.Usuario> mostrarDatos(String id) {
			DatabaseMetaData dm = null;
			Scanner lee = new Scanner(System.in);
			String nombre = "";
			ArrayList<modelo.Usuario> listaDatos = new ArrayList<modelo.Usuario>();
		
		try {
				if (con != null) 
				{
					CallableStatement cStmt = con.prepareCall("{call SP_ADM_DatosPersonalesIdentificador_Sel(?)}");  
					cStmt.setString(1,id);  
					cStmt.execute();    
				    final ResultSet rs = cStmt.getResultSet();  
				    modelo.Usuario usu;
				   
					while(rs.next()){
						
						usu = new modelo.Usuario(); 
						usu.setNombre(rs.getString(6));
						usu.setApellido(rs.getString(7));
						
							 /*  
							PreparedStatement ps = con.prepareStatement("SELECT * FROM TBL_QMCO_FT_RAM WHERE id=? ");
							ps.setInt(1, id);
							ResultSet rs = ps.executeQuery();
							
							Campos campo;
							while(rs.next()){
								campo = new Campos();
								
								campo.setId(rs.getInt(1));
								campo.setId_medicamento(rs.getInt(2));
								campo.setFecha(rs.getDate(3));
								*/
						
						listaDatos.add(usu);
					}
				
				/*for (Campos campos : listaDatos) {
						System.out.println(campos.getId() + " : " + campos.getNombre());
				}*/
//				Statement statement = con.createStatement();
//				statement.executeUpdate("INSERT INTO TBL_QMCO_FT_RAM "
//				+ "VALUES(71, '2014-07-09 16:41:55.417','prueba java','','','',2,1,1,'2014-07-09 16:41:55.417',1,1,'')");
				rs.close();
				//ps.close();
				con.close();
			
				}
			} 
		catch (Exception e) {
				System.err.println("Got an exception! ");
				System.err.println(e.getMessage());
			}
		
		return listaDatos;
	}
}

