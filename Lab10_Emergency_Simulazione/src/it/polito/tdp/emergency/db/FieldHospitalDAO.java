////////////////////////////////////////////////////////////////////////////////
//             //                                                             //
//   #####     // Field hospital simulator                                    //
//  ######     // (!) 2013 Giovanni Squillero <giovanni.squillero@polito.it>  //
//  ###   \    //                                                             //
//   ##G  c\   // Field Hospital DAO                                          //
//   #     _\  // Test with MariaDB 10 on win                                 //
//   |   _/    //                                                             //
//   |  _/     //                                                             //
//             // 03FYZ - Tecniche di programmazione 2012-13                  //
////////////////////////////////////////////////////////////////////////////////

package it.polito.tdp.emergency.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.polito.tdp.emergency.simulation.Core;
import it.polito.tdp.emergency.simulation.Evento;
import it.polito.tdp.emergency.simulation.Evento.TipoEvento;
import it.polito.tdp.emergency.simulation.Paziente;
import it.polito.tdp.emergency.simulation.Paziente.StatoPaziente;

public class FieldHospitalDAO {

	public void getAll(Core simulatore){
		
		Connection conn = DBConnect.getInstance().getConnection();
		String sql = "SELECT * FROM arrivals ORDER BY timestamp";
		PreparedStatement st;
		try {
			st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			long mezzanotte = 0;
			while(res.next()){
				String tipo = res.getString("triage");
				if(tipo.compareTo("White")==0)
					simulatore.aggiungiPaziente(new Paziente(res.getInt("patient"), StatoPaziente.BIANCO));
				if(tipo.compareTo("Green")==0)
					simulatore.aggiungiPaziente(new Paziente(res.getInt("patient"), StatoPaziente.VERDE));
				if(tipo.compareTo("Yellow")==0)
					simulatore.aggiungiPaziente(new Paziente(res.getInt("patient"), StatoPaziente.GIALLO));
				if(tipo.compareTo("Red")==0)
					simulatore.aggiungiPaziente(new Paziente(res.getInt("patient"), StatoPaziente.ROSSO));
				if(mezzanotte==0)
					mezzanotte = res.getTimestamp("timestamp").getTime()-143000;
				long ora = (res.getTimestamp("timestamp").getTime()-mezzanotte)/(1000*60);
				simulatore.aggiungiEvento(new Evento(ora, TipoEvento.PAZIENTE_ARRIVA, res.getInt("patient")));
			}
			res.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
