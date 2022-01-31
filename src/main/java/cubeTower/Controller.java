package cubeTower;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Controller implements Initializable {

	private int pos;

	@FXML
	private GridPane view;

	@FXML
	private Button añadirButton;

	@FXML
	private Button moverButton;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pos = view.getColumnCount() - 1;
		System.out.println(pos);
		view.setGridLinesVisible(true);

	}

	public Controller() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CubeTower/cubeToweFxml.fxml"));
		loader.setController(this);
		loader.load();
	}

	public GridPane getView() {
		return view;
	}

	@FXML
	void onAñadirAction(ActionEvent event) {
		añadir();
	}

	private void añadir() {
		int posColumn = view.getColumnCount() - 1;
		for (int i = pos; i > 0; i--) {
			if (pos > 0) {
				Rectangle rectangulo = new Rectangle(view.getWidth() / view.getColumnCount() - 5,
						view.getHeight() / view.getRowCount() - 5, Color.RED);
				System.out.println("Añadido");
				view.add(rectangulo, posColumn - pos, 4);
				pos--;
			}
		}
		añadirButton.setDisable(true);
	}

	@FXML
	void onMoverAction(ActionEvent event) {
		int pos = 3;
		Node node = getNodeFromGridPane(view, view.getColumnCount() - view.getColumnCount(), 4);
		view.getChildren().remove(node);
		Rectangle rectangulo = new Rectangle(view.getWidth() / view.getColumnCount() - 5,
				view.getHeight() / view.getRowCount() - 5, Color.RED);
		view.add(rectangulo, pos, 4);
	}


	@FXML
	void onMoverDerechaAction(ActionEvent event) {
		int pos = 0;
		Node node = getNodeFromGridPane(view, view.getColumnCount() - 1, 4);
		view.getChildren().remove(node);
		Rectangle rectangulo = new Rectangle(view.getWidth() / view.getColumnCount() - 5,
				view.getHeight() / view.getRowCount() - 5, Color.RED);
		view.add(rectangulo, pos, 4);
	}

	private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
		ObservableList<Node> children = gridPane.getChildren();
		for (Node node : children) {
			Integer columnIndex = GridPane.getColumnIndex(node);
			Integer rowIndex = GridPane.getRowIndex(node);

			if (columnIndex == null)
				columnIndex = 0;
			if (rowIndex == null)
				rowIndex = 0;

			if (columnIndex == col && rowIndex == row) {
				return node;
			}
		}
		return null;
	}
}
