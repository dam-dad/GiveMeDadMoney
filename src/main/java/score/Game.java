package score;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

@XmlType
public class Game {

	private IntegerProperty gameID;
	private IntegerProperty gameScore;
	
	public Game() {
		gameID = new SimpleIntegerProperty(this, "gameID");
		gameScore = new SimpleIntegerProperty(this, "gameScore");
	}

	public final IntegerProperty gameIDProperty() {
		return this.gameID;
	}
	
	@XmlElement
	public final int getGameID() {
		return this.gameIDProperty().get();
	}
	

	public final void setGameID(final int gameID) {
		this.gameIDProperty().set(gameID);
	}
	

	public final IntegerProperty gameScoreProperty() {
		return this.gameScore;
	}
	
	@XmlElement
	public final int getGameScore() {
		return this.gameScoreProperty().get();
	}
	

	public final void setGameScore(final int gameScore) {
		this.gameScoreProperty().set(gameScore);
	}
	
	
	
}
