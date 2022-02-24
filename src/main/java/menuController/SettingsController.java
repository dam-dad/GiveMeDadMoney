package menuController;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import estasdisticas.EstadisticasLista;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import main.GiveMeDADMoney;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * The type Settings controller. Controllador de los ajustes del juego
 */
public class SettingsController implements Initializable {
	
	private static String JRXML="informes/estadisticas.jrxml";

	@FXML
	private Button backButton;

	@FXML
	private Button generarButton;

	@FXML
	private Button playMusicButton;

	@FXML
	private BorderPane settingsView;

	@FXML
	private Button stopMusicButton;

	@FXML
	private Slider volumenSlider;
	
	private FileChooser fileChooser = new FileChooser();

	/**
	 * Instantiates a new Settings controller.
	 *
	 * @throws IOException the io exception
	 */
	public SettingsController() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Menu/SettingsView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		volumenSlider.valueProperty().addListener((o, ov, nv) -> {
			BaseController.getInstance().musica.fondoReproductor.volumeProperty().bind(volumenSlider.valueProperty());
		});
	}

	/**
	 * On music stop acction. Para la musica de fondo
	 *
	 * @param event the event
	 */
	@FXML
	void onMusicStopAcction(ActionEvent event) {
		try {
			BaseController.getInstance().musica.pause();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * On music play acction. Reproduce la musica de fondo
	 *
	 * @param event the event
	 */
	@FXML
	void onMusicPlayAcction(ActionEvent event) {
		try {
			BaseController.getInstance().musica.play();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * On back acction. Vuelve al menu
	 *
	 * @param event the event
	 */
	@FXML
	void onBackAcction(ActionEvent event) {
		BaseController.getInstance().showMenu();
	}

	@FXML
	void onGenerarAction(ActionEvent event) throws JRException {
		// compila el informe
		JasperReport report = JasperCompileManager.compileReport(SettingsController.class.getResourceAsStream(JRXML));

		// mapa de parámetros para el informe
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("anyo", 2014); // no lo uso, pero se lo paso

		// generamos el informe (combinamos el informe compilado con los datos)
		JasperPrint print = JasperFillManager.fillReport(report, parameters,new JRBeanCollectionDataSource(EstadisticasLista.getPersonas()));
		
		File file = fileChooser.showSaveDialog(GiveMeDADMoney.getPrimaryStage());
		
		

//		// exporta el informe a un fichero PDF
		JasperExportManager.exportReportToPdfFile(print, file.getPath());
//
//		// Abre el archivo PDF generado con el programa predeterminado del sistema
//		Desktop.getDesktop().open(new File(PDF_FILE));
	}

	/**
	 * Gets view.
	 *
	 * @return the view
	 */
	public BorderPane getView() {
		return settingsView;
	}

}
