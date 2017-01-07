package com.bminor.gamemaster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("gameDAO")
public class GameDAO {
	
	private NamedParameterJdbcTemplate jdbc;

	public List<Game> getGames(){
		
		return jdbc.query("select * from games", new RowMapper<Game>() {
			
			public Game mapRow(ResultSet rs, int rowNum ) throws SQLException {
				Game game = new Game();
				game.setId( rs.getInt("id") );
				game.setTitle( rs.getString("title") );
				game.setDeveloper( rs.getString("developer") );
				game.setPlatform( rs.getString("platform") );
				return game;
			}
		});
	}
	
	public Game getGames( int id ){

		MapSqlParameterSource parms = new MapSqlParameterSource( "id", id );
		
		return jdbc.queryForObject("select * from games where id = :id", parms, new RowMapper<Game>() {
			
			public Game mapRow(ResultSet rs, int rowNum ) throws SQLException {
				Game game = new Game();
				game.setId( rs.getInt("id") );
				game.setTitle( rs.getString("title") );
				game.setDeveloper( rs.getString("developer") );
				game.setPlatform( rs.getString("platform") );
				return game;
			}
		});
	}
	
	/* SETTERS */
	@Autowired
	public void setDataSource(DataSource template) {
		this.jdbc = new NamedParameterJdbcTemplate( template );
	}
	
}
