import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class HelloFX extends Application
{
	private TextField textfield=new TextField();
	private long num1=0;
	private long num2=0;
	private String op="";
	private boolean start=true;
	
	@Override
	public void start(Stage primaryStage)
	{
		textfield.setFont(Font.font(20));
		textfield.setPrefHeight(50);
		textfield.setAlignment(Pos.CENTER_RIGHT);   //default position is from left
		textfield.setEditable(false);
		
		StackPane stackpane =new StackPane();
		stackpane.setPadding(new Insets(10));
		stackpane.getChildren().add(textfield);
		
		TilePane tile=new TilePane();
		tile.setHgap(10);
		tile.setVgap(10);
		tile.setAlignment(Pos.TOP_CENTER);
		tile.getChildren().addAll(
				createButtonForNumber("7"),
				createButtonForNumber("8"),
				createButtonForNumber("9"),
				createButtonForOperators("/"),
				
				createButtonForNumber("4"),
				createButtonForNumber("5"),
				createButtonForNumber("6"),
				createButtonForOperators("X"),
				
				createButtonForNumber("1"),
				createButtonForNumber("2"),
				createButtonForNumber("3"),
				createButtonForOperators("-"),
				
				createButtonForNumber("0"),
				createButtonForClear("C"),
				createButtonForOperators("="),
				createButtonForOperators("+")
				);		
		
		BorderPane root = new BorderPane();
		root.setTop(stackpane);
		root.setCenter(tile);
		Scene scene= new Scene(root,250,320);
		primaryStage.setTitle("My Calculator");      
        primaryStage.setScene(scene);            
		primaryStage.setResizable(false);
		primaryStage.show();
		
	}
	
	private Button createButtonForNumber(String ch)
	{
		Button button=new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);	
		button.setOnAction(this::processNumber);
		return button;
	}
	
	private Button createButtonForOperators(String ch)
	{
		Button button=new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		button.setOnAction(this::processOperator);
		return button;
	}
	
	private Button createButtonForClear(String ch)
	{
		Button button=new Button(ch);
		button.setFont(Font.font(18));
		button.setPrefSize(50,50);
		button.setOnAction(e->{
			textfield.setText("");
			op="";
			start=true;
		});
		return button;
	}
	
	private void processNumber(ActionEvent e)
	{
		if(start) {
			textfield.setText("");
			start=false;
		}
		String value=((Button)e.getSource()).getText();
		textfield.setText(textfield.getText()+value);
	}
	
	private void processOperator(ActionEvent e)
	{
		String value=((Button)e.getSource()).getText();
		if(value!="=")
		{
			if(!op.isEmpty())
				return;
			num1=Long.parseLong(textfield.getText());
			op=value;
			textfield.setText("");
		}
		else
		{
			if(op.isEmpty())
				return;
			num2=Long.parseLong(textfield.getText());
			float result=calculate(num1,num2,op);
			textfield.setText(String.valueOf(result));
			start=true;
			op="";
		}
	}
	
	private float calculate(long num1, long num2, String operator)
	{
		switch(operator)
		{
		case"+":
			return num1+num2;
		case"-":
			return num1-num2;
		case"X":
			return num1*num2;
		case"/":
			if(num2==0)
				return 0;
			return num1/num2;
		default:
			return 0;		
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}
