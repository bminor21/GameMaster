package com.bminor.gamemaster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("gameDAO")
public class GameDAO {
	
	private NamedParameterJdbcTemplate jdbc;

	/* returns a list of games in the database */
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
	
	/* Returns a game with the specified id */
	public Game getGame( int id ){

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
	
	/* Remove a game with a specific id from the database */
	public boolean deleteGameById( int id ){
		MapSqlParameterSource parms = new MapSqlParameterSource( "id", id );
		return ( jdbc.update( "delete from games where id = :id", parms ) == 1 );
	}
	
	/* Add a game to the database */
	public boolean addGame( Game game ){
		BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(game);
		return ( jdbc.update("insert into games (title, developer, platform ) values (:title, :developer, :platform)", bean) == 1 );
	}
	
	/* Updates a game */
	public boolean updateGame( Game game ){
		BeanPropertySqlParameterSource bean = new BeanPropertySqlParameterSource(game);
		return ( jdbc.update("update games set title = :title, developer = :developer, platform = :platform where id = :id", bean) == 1 );
	}
	
	/* SETTERS */
	@Autowired
	public void setDataSource(DataSource template) {
		this.jdbc = new NamedParameterJdbcTemplate( template );
	}
	
}
