package es.iesazarquiel.onguarapp.models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.iesazarquiel.onguarapp.models.conexiones.Conexion;
import es.iesazarquiel.onguarapp.models.entidades.Faltas;

public class DaoFaltas {
/************************METODO PARA INSERTAR FALTA DE UN PROFE [  CRIS  ]*******************************/
					//MEJORA: tener en cuenta si tiene clase esa hora
	
	public void insertarFalta(Faltas f) throws SQLException,Exception{
		Connection con = null;
		PreparedStatement st = null;
		String ordenSQL = "INSERT INTO FALTAS(COD_PROFE_FALTA,"
											+ "DIA_SEMANA_FALTA,"
											+ "HORA_FALTA,"
											+ "FECHA,"
											+ "COD_PROFE_CUBRE_GUARDIA,"
											+ "DIA_SEMANA_GUARDIA,"
											+ "HORA_GUARDIA) VALUES(?,?,?,?,?,?,?)";
		Conexion miconex = new Conexion();
		try{
			con=miconex.getConexion();
			con.setAutoCommit(false);		
			Date fecha=new Date(f.getFecha().getTime());
			st=con.prepareStatement(ordenSQL);
			st.setString(1,f.getCod_profe_falta());
			st.setString(2,f.getDia_semana_falta());
			st.setString(3,f.getHora_falta());
			st.setDate(4,fecha);
			st.setString(5, f.getCod_profe_guardia());
			st.setString(6, f.getDia_semana_guardia());
			st.setString(7, f.getHora_guardia());
			st.executeUpdate();
			con.commit();
		}catch (SQLException sqle){
			throw sqle;
		}catch (Exception e){
			throw e;
		}finally{				
		if(st !=null)
			st.close();
		if (con !=null)
			con.close();
		}
		}
	

/******************METODO PARA ASIGNAR FALTAS(QUIEN CUBRE LA GUARDIA) [  CRIS  ]***************************/		
					//MEJORA: tener en cuenta si tiene existe esa falta en Faltas
	
	public void asignarFaltaAProf(Faltas f) throws SQLException,Exception{
		Connection con = null;
		PreparedStatement st = null;
		String ordenSQL = "UPDATE FALTAS SET COD_PROFE_CUBRE_GUARDIA=?,"
											+ "DIA_SEMANA_GUARDIA=?,"
											+ "HORA_GUARDIA=?"
											+ "WHERE COD_PROFE_FALTA=? AND "
											+ "DIA_SEMANA_FALTA=? AND "
											+ "HORA_FALTA=? AND "
											+ "FECHA=?";
		Conexion miconex = new Conexion();
		try{
			con=miconex.getConexion();
			con.setAutoCommit(false);		
			st=con.prepareStatement(ordenSQL);
			Date fecha=new Date(f.getFecha().getTime());
			st.setString(1, f.getCod_profe_guardia());
			st.setString(2, f.getDia_semana_guardia());
			st.setString(3, f.getHora_guardia());
			st.setString(4,f.getCod_profe_falta());
			st.setString(5,f.getDia_semana_falta());
			st.setString(6,f.getHora_falta());
			st.setDate(7,fecha);
			st.executeUpdate();
			con.commit();
		}catch (SQLException sqle){
			throw sqle;
		}catch (Exception e){
			throw e;
		}finally{			
		if(st !=null)
			st.close();
		if (con !=null)
			con.close();
		
		}
	}
/*********************** METODO MOSTRAR FALTAS [ JOSE ]***********************************/
	
	public ArrayList<Faltas> mostrarFaltas() throws SQLException,Exception {
		ArrayList<Faltas> listaFaltas = new ArrayList<Faltas>();
		
		Connection con = null;
		ResultSet rs = null; 
		Statement st = null;
		Conexion conexion = new Conexion();
	    
		String ordenSQL;
		
		try {
		con=conexion.getConexion();	
		st = con.createStatement();
		ordenSQL ="SELECT * FROM FALTAS";
	    rs=st.executeQuery(ordenSQL);
	    while(rs.next()) {
	    	Faltas falta = new Faltas();
	    	falta.setCod_profe_falta(rs.getString("COD_PROFE_FALTA"));
	    	falta.setDia_semana_falta(rs.getString("DIA_SEMANA_FALTA"));
	    	falta.setHora_falta(rs.getString("HORA_FALTA"));
	    	falta.setFecha(rs.getDate("FECHA"));
	    }
		} catch (SQLException se) {
			throw se;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con!=null)
				con.close();
		}
	    
	    return listaFaltas;
	}
/*********************** METODO CONSULTAR FALTAS ENTRE 2FECHAS [ CARLOS ]***********************************/
	/**
	 *  Metodo al que se le pasa un codigo de profesor y dos fechas, y debe buscar
	 *  en la tabla de faltas mediante un select todas las faltas de ese codigo de profesor.
	 *  Devuelve un arraylist con varias líneas, en cada línea va la fecha, 
	 *  la asignatura, el grupo y el profe que le cubrió (si lo hay). CARLOS.
	 * @throws SQLException 
	 * 
	 *   
	 */
	
	public ArrayList<String> verFaltasProf (String codProf, Date fechaIni, Date fechaFin) throws SQLException {
		
		String fechaIniNueva = transformarFecha(fechaIni);
		
		String fechaFinNueva = transformarFecha(fechaFin);
		
		ArrayList<String> faltasProf = new ArrayList<String>();
		
		/*Aquí va el código*/
		
		Connection con = null;
		ResultSet rs = null; 
		Statement st = null;
		Conexion conexion = new Conexion();
	    
		con=conexion.getConexion();	
		st = con.createStatement();
		String ordenSQL;
		
		ordenSQL = "SELECT DOCENTESF.NOMBRE PROFFALTA, DOCENTESG.NOMBRE PROFGUARDIA, "
				+ "FECHA, HORA_FALTA "
				+ "FROM FALTAS "
				+ "INNER JOIN DOCENTES DOCENTESF ON FALTAS.COD_PROFE_FALTA = DOCENTESF.COD_PROFE"
                + " INNER JOIN DOCENTES DOCENTESG ON FALTAS.COD_PROFE_CUBRE_GUARDIA = DOCENTESG.COD_PROFE " 
                + "WHERE FALTAS.FECHA BETWEEN '" + fechaIniNueva +
                "' AND '" + fechaFinNueva + "' AND DOCENTESF.COD_PROFE = '" + codProf + "'";
		rs=st.executeQuery(ordenSQL);
		
		while(rs.next()) {
	    	faltasProf.add(rs.getString("PROFFALTA"));
	    	faltasProf.add(rs.getDate("FECHA").toString());
	    	faltasProf.add(rs.getString("HORA_FALTA"));
	    	faltasProf.add(rs.getString("PROFGUARDIA"));
	    }
		
		
		return faltasProf;
		
	}
	
/*********************** METODO CONSULTAR GUARDIAS REALIZADAS ENTRE 2FECHAS [ CARLOS ]***********************************/
	/** 
	 * MÃ©todo al que se le pasa un cÃ³digo de profesor y dos fechas, y debe buscar
	 * en la tabla de faltas todas las filas que estÃ©n entre las dos fechas y tengan 
	 * rellena la columna COD_PROF_GUARDIA con ese cÃ³digo de profe. Devuelve un arraylist
	 * con varias lÃ­neas, en cada lÃ­nea va la la fecha, el nombre del profe al que cubriÃ³,
	 * y la asignatura y el grupo que cubriÃ³. CARLOS.
	 */
	
	public ArrayList<String> verGuardiasProf (String codProf, Date fechaIni, Date fechaFin) {
		
		ArrayList<String> guardiasProf = new ArrayList<String>();
		
		/*AquÃ­ va el cÃ³digo*/
		
		return guardiasProf;
	}
/*********************** METODO CONSULTAR ??  [ JOSE ]***********************************/
	/** 
	 * Método al que se le pasa una fecha y devuelve un arrayList con las 6 horas, y dentro de 
	 * cada hora, el profe que ha cubierto esa falta y el código de aula. JOSE.
	 * @throws SQLException 
	 */
	
	public ArrayList<String> generarInformeFaltasCub (Date fechaInforme) throws SQLException {
		
		String fechaInformeNueva = transformarFecha(fechaInforme);
		
		Connection con = null;
		ResultSet rs = null; 
		Statement st = null;
		Conexion conexion = new Conexion();
	    
		con=conexion.getConexion();	
		st = con.createStatement();
		String ordenSQL;
		
		ArrayList<String> informeFaltasCub = new ArrayList<String>();
		
		ordenSQL = "SELECT HORA_FALTA, HORA_CLASE.COD_AULA, FALTAS.COD_PROFE_CUBRE_GUARDIA PROFG "
				+ "FROM FALTAS "
				+ "INNER JOIN DOCENTES ON FALTAS.COD_PROFE_FALTA = DOCENTES.COD_PROFE "
				+ "INNER JOIN HORA_CLASE ON FALTAS.COD_PROFE_FALTA = HORA_CLASE.COD_PROFE_CLASE AND "
				+ "FALTAS.DIA_SEMANA_FALTA = HORA_CLASE.DIA_SEMANA "
				+ "AND FALTAS.HORA_FALTA = HORA_CLASE.HORA "
				+ "WHERE FALTAS.FECHA = '" + fechaInformeNueva + "'";
		rs=st.executeQuery(ordenSQL);
		
		while(rs.next()) {
	    	informeFaltasCub.add(rs.getString("HORA_FALTA"));
	    	informeFaltasCub.add(rs.getString("COD_AULA"));
	    	informeFaltasCub.add(rs.getString("PROFG"));
	    }
		
		return informeFaltasCub;
		
	}
	
	/**
	 * Se le pasa un codigo de profe y un día de la semana, y devuelve las horas
	 * de clase o guardia que tiene ese profe ese dia.
	 * Se necesita para el método Asignar Falta.
	 * @param codprof
	 * @param diaSem
	 */
	public ArrayList<String> verHorasDeUnDia(String codprof, String diaSem) {
		
		ArrayList<String> horasDia = new ArrayList<String>();
		
		return horasDia;
		
	}
	
	/** 
	 * Se le pasa un código de profe y una fecha y devuelve las faltas de ese profe ese día.
	 * Este método es necesario para hacer el método Asignar Faltas.
	 * 
	 * @param codprof
	 * @throws SQLException 
	 */
	public ArrayList<String> verFaltasDeUnDia(String codProf, Date dia) throws SQLException {
		
        ArrayList<String> faltasDia = new ArrayList<String>();
		
		Connection con = null;
		ResultSet rs = null; 
		Statement st = null;
		Conexion conexion = new Conexion();
	    
		con=conexion.getConexion();	
		st = con.createStatement();
		String ordenSQL;
		
		ArrayList<String> informeFaltasCub = new ArrayList<String>();
		
		String diaFormateado = transformarFecha(dia);
		
		ordenSQL = "SELECT HORA_FALTA FROM FALTAS "
				+ "INNER JOIN DOCENTES ON FALTAS.COD_PROFE_FALTA = DOCENTES.COD_PROFE "
				+ "WHERE FALTAS.FECHA = '" + diaFormateado + "' "
				+ "AND FALTAS.COD_PROFE_FALTA = '" + codProf + "' ";
		
		rs=st.executeQuery(ordenSQL);
		
		while(rs.next()) {
	    	faltasDia.add(rs.getString("HORA_FALTA"));
	    }
		
		return faltasDia;
	}
	
	
	/**método que transforma una fecha YYYY-MM-DD en formato DD-MM-YYYY
	 * 
	 * @param fech
	 * @return
	 */
	public String transformarFecha(Date fech) {
		
		String fech_ = fech.toString();
		String fechn = fech_.substring(8,10) + "-" + fech_.substring(5,7) + "-" + fech_.substring(0,4);
	    return fechn;
	}
	
	
}

