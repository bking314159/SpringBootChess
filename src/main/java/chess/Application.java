package chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import chess.ai.ChessAi;
import chess.backend.BoardMover;

/**
 * This is the default main of a Spring Boot application
 *
 */
@SpringBootApplication
@ComponentScan(basePackageClasses= {BoardMover.class,BoardController.class, ChessAi.class})
public class Application {

	/**
	 * This method is the entry point of the server.  It calls
	 *  SpringApplication.run(...)
	 * 
	 * @param args the command line arguments (not used)
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}