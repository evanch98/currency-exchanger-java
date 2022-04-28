import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;
import javafx.scene.text.Font;

/**
 * This program allows user to convert currency between selected currencies.
 * There are only six currencies included in this program.
 * @author Kyaw Thu
 *
 */
public class CurrencyExchanger extends Application {
	/**
	 * To launch the Application.
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Implementing the JavaFX's start() method.
	 */
	@Override
	public void start(Stage stage) {
		// Title of the application.
		Label title = new Label("Currency Exchanger");
		title.setFont(new Font(40));
		
		// To center the title.
		HBox titleBar = new HBox(title);
		titleBar.setAlignment(Pos.CENTER);
		
		// The elements that make up the fromBar START
		Label from = new Label("From");
		from.setFont(new Font(20));
		from.setMinWidth(50);
		
		// To allow user to choose currency they want to converted from.
		ChoiceBox<String> fCurrency = new ChoiceBox<String>();
		addChoices(fCurrency);		// Adding choices
		fCurrency.setValue("Euro");	// Setting default value
		
		TextField fAmount = new TextField();	// To allow user to enter the amount
		// The elements that make up the fromBar END
		
		// To align the elements related to "From Currency" horizontally.
		HBox fromBar = new HBox(20, from, fCurrency, fAmount);
		fromBar.setAlignment(Pos.CENTER);
		
		// The elements that make up the toBar START
		Label to = new Label("To");
		to.setFont(new Font(20));
		to.setMinWidth(50);
		
		// To allow user to choose currency they want to converted to.
		ChoiceBox<String> tCurrency = new ChoiceBox<String>();
		addChoices(tCurrency);
		tCurrency.setValue("US Dollar");
		
		TextField tAmount = new TextField();	// To display the converted value.
		tAmount.setEditable(false);
		// The elements that make up the toBar END
		
		// To align the elements related to "To Currency" horizontally.
		HBox toBar = new HBox(20, to, tCurrency, tAmount);
		toBar.setAlignment(Pos.CENTER);
		
		// Aligning the fromBar and toBar.
		VBox currencyBar = new VBox(20, fromBar, toBar);
		currencyBar.setAlignment(Pos.CENTER);
		
		Button convert = new Button("Convert");
		convert.setOnAction(e -> {
			// To make sure user enter valid input.
			try {
				convert(fCurrency, fAmount, tCurrency, tAmount);
			}
			catch (IllegalArgumentException error) {
				tAmount.setText("Please enter decimal number.");
			}
		});
		
		// To center the convert Button.
		HBox buttonBar = new HBox(convert);
		buttonBar.setAlignment(Pos.CENTER);
		
		// Final Layout
		BorderPane root = new BorderPane();
		root.setTop(titleBar);
		root.setCenter(currencyBar);
		root.setBottom(buttonBar);
		
		Scene scene = new Scene(root, 450, 200);
		stage.setScene(scene);
		stage.setTitle("Currency Exchanger");
		stage.show();
	}
	
	// To add choices to the ChoiceBox<String>
	private static void addChoices(ChoiceBox<String> currency) {
		currency.getItems().add("Euro");
		currency.getItems().add("Myanmar Kyat");
		currency.getItems().add("Pound Sterling");
		currency.getItems().add("Taiwan Dollar");
		currency.getItems().add("Thai Baht");
		currency.getItems().add("US Dollar");
	}
	
	// This method converts the currency and display the result in the parameter tAmount's TextField.
	private static void convert(
			ChoiceBox<String> from, TextField fAmount, ChoiceBox<String> to, TextField tAmount
			) {
		String fMoney = from.getValue();	// The value of from choice box.
		double fValue = Double.parseDouble(fAmount.getText());	// The amount from the fAmount.
		String tMoney = to.getValue();	// The value of to choice box.
		double converted = 0;
		
		// To use an appropriate conversion function.
		switch(fMoney) {
		case "Euro":
			converted = euro(fValue, tMoney);
			break;
		case "Myanmar Kyat":
			converted = mmk(fValue, tMoney);
			break;
		case "Pound Sterling":
			converted = pound(fValue, tMoney);
			break;
		case "Taiwan Dollar":
			converted = twd(fValue, tMoney);
			break;
		case "Thai Baht":
			converted = tb(fValue, tMoney);
			break;
		case "US Dollar":
			converted = usd(fValue, tMoney);
			break;
		}
		// Displaying the result
		tAmount.setText(String.format("%.2f", converted));
	}
	
	// Conversion Functions START
	private static double euro(double amount, String to) {
		switch(to) {
		case "Myanmar Kyat":
			return amount * 1943.20;
		case "Pound Sterling":
			return amount * 0.84;
		case "Taiwan Dollar":
			return amount * 31.03;
		case "Thai Baht":
			return amount * 36.19;
		case "US Dollar":
			return amount * 1.05;
		default:
			return amount;
		}
	}
	
	private static double mmk(double amount, String to) {
		switch(to) {
		case "Euro":
			return amount * 0.00051;
		case "Pound Sterling":
			return amount * 0.00043;
		case "Taiwan Dollar":
			return amount * 0.016;
		case "Thai Baht":
			return amount * 0.019;
		case "US Dollar":
			return amount * 0.00054;
		default:
			return amount;
		}
	}
	
	private static double pound(double amount, String to) {
		switch(to) {
		case "Euro":
			return amount * 1.19;
		case "Myanmar Kyat":
			return amount * 2304.87;
		case "Taiwan Dollar":
			return amount * 36.80;
		case "Thai Baht":
			return amount * 42.94;
		case "US Dollar":
			return amount * 1.25;
		default:
			return amount;
		}
	}
	
	private static double twd(double amount, String to) {
		switch(to) {
		case "Euro":
			return amount * 0.032;
		case "Myanmar Kyat":
			return amount * 62.63;
		case "Pound Sterling":
			return amount * 0.027;
		case "Thai Baht":
			return amount * 1.17;
		case "US Dollar":
			return amount * 0.034;
		default:
			return amount;
		}
	}
	
	private static double tb(double amount, String to) {
		switch(to) {
		case "Euro":
			return amount * 0.028;
		case "Myanmar Kyat":
			return amount * 53.68;
		case "Pound Sterling":
			return amount * 0.023;
		case "Taiwan Dollar":
			return amount * 0.86;
		case "US Dollar":
			return amount * 0.029;
		default:
			return amount;
		}
	}
	
	private static double usd(double amount, String to) {
		switch(to) {
		case "Euro":
			return amount * 0.95;
		case "Myanmar Kyat":
			return amount * 1850;
		case "Pound Sterling":
			return amount * 0.80;
		case "Taiwan Dollar":
			return amount * 29.54;
		case "Thai Baht":
			return amount * 34.47;
		default:
			return amount;
		}
	}
	// Conversion Functions END
}
