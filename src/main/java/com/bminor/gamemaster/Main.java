package com.bminor.gamemaster;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/bminor/gamemaster/beans/Beans.xml");

		GameDAO gameDao  = (GameDAO)context.getBean("gameDAO");
		List<Game> games = gameDao.getGames();
		
		for( Game game : games ){
			System.out.println( game );
		}
		
		System.out.println( gameDao.getGames( 3 ) );
		
		( ( ClassPathXmlApplicationContext ) context).close();
	}

}
