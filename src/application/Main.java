package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.image.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.shape.*;
import java.io.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) 
	{
		try
		{
			Font wont = Font.font("Times New Roman",32);
			Pane pane = new Pane();
			Scene scene = new Scene(pane,1280,720);
			Text gameTitle = new Text();
			Button [] buttons = new Button[6];
			gameTitle.setText("Knight of the Path");
			gameTitle.setFont(wont);
			gameTitle.setLayoutX(545);
			gameTitle.setLayoutY(100);
			for(int i = 0; i < 6; i++)
			{
				buttons[i] = new Button();
				buttons[i].setLayoutX(625);
				buttons[i].setLayoutY(300+i*50);
			}
			buttons[0].setText("7x7 puzzles");
			buttons[1].setText("9x9 puzzles");
			buttons[2].setText("11x11 puzzles");
			buttons[2].setLayoutX(620);
			buttons[3].setText("13x13 puzzles");
			buttons[3].setLayoutX(620);
			buttons[4].setText("Level Editor");
			buttons[5].setText("Quit");
			buttons[5].setLayoutX(645);
			
			EventHandler<ActionEvent> b7 = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e) {
					lS(7,primaryStage,buttons);
				}
			};
			
			EventHandler<ActionEvent> b9 = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e) {
					lS(9,primaryStage,buttons);
				}
			};
			
			EventHandler<ActionEvent> b11 = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e) {
					lS(11,primaryStage,buttons);
				}
			};
			
			EventHandler<ActionEvent> b13 = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e) {
					lS(13,primaryStage,buttons);
				}
			};
			
			EventHandler<ActionEvent> LevelEditor = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e) {
					LE(primaryStage, buttons, pane);
				}
			};
			
			EventHandler<ActionEvent> quit = new EventHandler<ActionEvent>()
			{
				public void handle(ActionEvent e) {
					System.exit(0);
				}
			};
			
			buttons[0].setOnAction(b7);
			buttons[1].setOnAction(b9);
			buttons[2].setOnAction(b11);
			buttons[3].setOnAction(b13);
			buttons[4].setOnAction(LevelEditor);
			buttons[5].setOnAction(quit);
			
			pane.getChildren().addAll(buttons[0],buttons[1],buttons[2],buttons[3],buttons[4],buttons[5],gameTitle);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Knight of the Path");
			primaryStage.show();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	private void gameController(int val, int level, Stage primaryStage)
	{
		int [][] board = new int[val][val];
		int [] actorarray;
		Pane all = new Pane();
		actorarray = filein(board, val, level);
		GridPane display = new GridPane();
		Scene scene = new Scene(all,1280,720);
		ImageView [][] array = new ImageView[val][val];
		Image floor = new Image("images/Floor.png");
		Image Wall = new Image("images/Wall.png");
		Image Knight = new Image("images/Knight.png");
		Image Troll = new Image("images/Troll.png");
		Image USpike = new Image("images/Up_Spike.png");
		Image DSpike = new Image("images/Down_Spike.png");
		Image Exit = new Image("images/Exit.png");
		Image Win = new Image("images/Win.png");
		Image Powerup = new Image("images/Powerup.png");
		Image USWP = new Image("images/USWP.png");
		Image DSWP = new Image("images/DSWP.png");
		Text moves = new Text();
		moves.setLayoutX(30);
		moves.setLayoutY(30);
		Button menu = new Button();
		menu.setLayoutX(1024);
		menu.setLayoutY(30);
		menu.setText("Return to Menu");
		Button quit = new Button();
		quit.setLayoutX(1150);
		quit.setLayoutY(30);
		quit.setText("Exit Game");
		all.getChildren().addAll(display,moves,menu,quit);
		
		if(val == 7)
		{
			display.setLayoutX(528);
			display.setLayoutY(220);
			moves.setText("Moves Left = " + String.valueOf(actorarray[1]));
		}
		else if(val == 9)
		{
			display.setLayoutX(496);
			display.setLayoutY(188);
			moves.setText("Moves Left = " + String.valueOf(actorarray[1]));
		}
		else if(val == 11)
		{
			display.setLayoutX(464);
			display.setLayoutY(156);
			moves.setText("Moves Left = " + String.valueOf(actorarray[1]));
		}
		else if(val == 13)
		{
			display.setLayoutX(432);
			display.setLayoutY(124);
			moves.setText("Moves Left = " + String.valueOf(actorarray[1]));
		}
		
		for(int i = 0; i < val; i++)
		{
			for(int j = 0; j < val; j++)
			{
				if(board[i][j] == 0)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(floor);
					display.add(array[i][j], i, j);
				}
				else if(board[i][j] == 1)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(Wall);
					display.add(array[i][j], i, j,1,1);
				}
				else if(board[i][j] == 2)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(Knight);
					display.add(array[i][j], i, j);
				}
				else if(board[i][j] == 3 || board[i][j] == 4 || board[i][j] == 5 || board[i][j] == 6)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(Troll);
					display.add(array[i][j], i, j);
				}
				else if(board[i][j] == 7)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(DSpike);
					display.add(array[i][j], i, j);
				}
				else if(board[i][j] == 8)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(USpike);
					display.add(array[i][j], i, j);
				}
				else if(board[i][j] == 9)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(Powerup);
					display.add(array[i][j], i, j);
				}
				else if(board[i][j] == 10)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(Exit);
					display.add(array[i][j], i, j);
				}
			}
		}
		
		EventHandler<ActionEvent> exit  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				System.exit(0);
			}
		};
		EventHandler<ActionEvent> Menu  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				all.getChildren().clear();
				start(primaryStage);
			}
		};
		quit.setOnAction(exit);
		menu.setOnAction(Menu);
		boolean []direction = new boolean[5];
		direction[4] = false;
		scene.setOnKeyPressed(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent e)
			{
				if(!(direction[0])||direction[1]||direction[2]||direction[3])
				{
					switch(e.getCode()) 
					{
						case W: direction[0] = true; break;
						case A: direction[1] = true; break;
						case S: direction[2] = true; break;
						case D: direction[3] = true; break; 
						default: break;
					};
					if(!direction[4])
					{
						if(actorarray[1] <= 0)
						{
							all.getChildren().clear();
							losin(primaryStage);
						}
						{
							if(direction[0])
							{
								if(board[actorarray[2]][actorarray[3]-1] != 1)
								{
									if(board[actorarray[2]][actorarray[3]-1] == 10)
									{
										moves.setText("YOU WIN");
										array[actorarray[2]][actorarray[3]].setImage(floor);
										array[actorarray[2]][actorarray[3]-1].setImage(Win);
										all.getChildren().clear();
										waitin(primaryStage);
									}
									if(board[actorarray[2]][actorarray[3]-1] == 3 ||board[actorarray[2]][actorarray[3]-1] == 4 || board[actorarray[2]][actorarray[3]-1] == 5 || board[actorarray[2]][actorarray[3]-1] == 6 || board[actorarray[2]][actorarray[3]-1] == 7)
									{
										actorarray[1] = actorarray[1] - 2;
										if(board[actorarray[2]][actorarray[3]-1] == 7)
										{
											board[actorarray[2]][actorarray[3]-1] = 12;
											array[actorarray[2]][actorarray[3]-1].setImage(USWP);
										}
										else 
										{
											board[actorarray[2]][actorarray[3]-1] = 2;
											array[actorarray[2]][actorarray[3]-1].setImage(Knight);
										}
									}
									else if(board[actorarray[2]][actorarray[3]-1] == 9)
									{
										actorarray[1] += 3;
										board[actorarray[2]][actorarray[3]-1] = 2;
										array[actorarray[2]][actorarray[3]-1].setImage(Knight);
									}
									else
									{
										actorarray[1] = actorarray[1]-1;
										if(board[actorarray[2]][actorarray[3]-1] == 8)
										{
											board[actorarray[2]][actorarray[3]-1] = 11;
											array[actorarray[2]][actorarray[3]-1].setImage(DSWP);
										}
										else
										{
											board[actorarray[2]][actorarray[3]-1] = 2;
											array[actorarray[2]][actorarray[3]-1].setImage(Knight);
										}
									}
									if(board[actorarray[2]][actorarray[3]] == 11 || board[actorarray[2]][actorarray[3]] == 12)
									{
										if(board[actorarray[2]][actorarray[3]] == 11)
										{
											board[actorarray[2]][actorarray[3]] = 7;
											array[actorarray[2]][actorarray[3]].setImage(USpike);
										}
										else if(board[actorarray[2]][actorarray[3]] == 12)
										{
											board[actorarray[2]][actorarray[3]] = 8;
											array[actorarray[2]][actorarray[3]].setImage(DSpike);
										}
									}
									else
									{
										board[actorarray[2]][actorarray[3]] = 0;
										array[actorarray[2]][actorarray[3]].setImage(floor);
									}
									actorarray[3] -= 1;
									enemies(actorarray,board,array);
								}
							}
							if(direction[1])
							{
								if(board[actorarray[2]-1][actorarray[3]] != 1)
								{
									if(board[actorarray[2]-1][actorarray[3]] == 10)
									{
										moves.setText("YOU WIN");
										array[actorarray[2]][actorarray[3]].setImage(floor);
										array[actorarray[2]-1][actorarray[3]].setImage(Win);
										all.getChildren().clear();
										waitin(primaryStage);
									}
									if(board[actorarray[2]-1][actorarray[3]] == 3 ||board[actorarray[2]-1][actorarray[3]] == 4 || board[actorarray[2]-1][actorarray[3]] == 5 || board[actorarray[2]-1][actorarray[3]] == 6 || board[actorarray[2]-1][actorarray[3]] == 7)
									{
										actorarray[1] = actorarray[1] - 2;
										if(board[actorarray[2]-1][actorarray[3]] == 7)
										{
											board[actorarray[2]-1][actorarray[3]] = 12;
											array[actorarray[2]-1][actorarray[3]].setImage(USWP);
										}
										else 
										{
											board[actorarray[2]-1][actorarray[3]] = 2;
											array[actorarray[2]-1][actorarray[3]].setImage(Knight);
										}
									}
									else if(board[actorarray[2]-1][actorarray[3]] == 9)
									{
										actorarray[1] += 3;
										board[actorarray[2]-1][actorarray[3]] = 2;
										array[actorarray[2]-1][actorarray[3]].setImage(Knight);
									}
									else
									{
										actorarray[1] = actorarray[1]-1;
										if(board[actorarray[2]-1][actorarray[3]] == 8)
										{
											board[actorarray[2]-1][actorarray[3]] = 11;
											array[actorarray[2]-1][actorarray[3]].setImage(DSWP);
										}
										else
										{
											board[actorarray[2]-1][actorarray[3]] = 2;
											array[actorarray[2]-1][actorarray[3]].setImage(Knight);
										}
									}
									if(board[actorarray[2]][actorarray[3]] == 11 || board[actorarray[2]][actorarray[3]] == 12)
									{
										if(board[actorarray[2]][actorarray[3]] == 11)
										{
											board[actorarray[2]][actorarray[3]] = 7;
											array[actorarray[2]][actorarray[3]].setImage(USpike);
										}
										else if(board[actorarray[2]][actorarray[3]] == 12)
										{
											board[actorarray[2]][actorarray[3]] = 8;
											array[actorarray[2]][actorarray[3]].setImage(DSpike);
										}
									}
									else
									{
										board[actorarray[2]][actorarray[3]] = 0;
										array[actorarray[2]][actorarray[3]].setImage(floor);
									}
									actorarray[2] -= 1;
									enemies(actorarray,board,array);
								}
							}
							if(direction[2])
							{
								if(board[actorarray[2]][actorarray[3]+1] != 1)
								{
									if(board[actorarray[2]][actorarray[3]+1] == 10)
									{
										moves.setText("YOU WIN");
										array[actorarray[2]][actorarray[3]].setImage(floor);
										array[actorarray[2]][actorarray[3]+1].setImage(Win);
										all.getChildren().clear();
										waitin(primaryStage);
									}
									if(board[actorarray[2]][actorarray[3]+1] == 3 ||board[actorarray[2]][actorarray[3]+1] == 4 || board[actorarray[2]][actorarray[3]+1] == 5 || board[actorarray[2]][actorarray[3]+1] == 6 || board[actorarray[2]][actorarray[3]+1] == 7)
									{
										actorarray[1] = actorarray[1] - 2;
										if(board[actorarray[2]][actorarray[3]+1] == 7)
										{
											board[actorarray[2]][actorarray[3]+1] = 12;
											array[actorarray[2]][actorarray[3]+1].setImage(USWP);
										}
										else 
										{
											board[actorarray[2]][actorarray[3]+1] = 2;
											array[actorarray[2]][actorarray[3]+1].setImage(Knight);
										}
									}
									else if(board[actorarray[2]][actorarray[3]+1] == 9)
									{
										actorarray[1] += 3;
										board[actorarray[2]][actorarray[3]+1] = 2;
										array[actorarray[2]][actorarray[3]+1].setImage(Knight);
									}
									else
									{
										actorarray[1] = actorarray[1]-1;
										if(board[actorarray[2]][actorarray[3]+1] == 8)
										{
											board[actorarray[2]][actorarray[3]+1] = 11;
											array[actorarray[2]][actorarray[3]+1].setImage(DSWP);
										}
										else
										{
											board[actorarray[2]][actorarray[3]+1] = 2;
											array[actorarray[2]][actorarray[3]+1].setImage(Knight);
										}
									}
									if(board[actorarray[2]][actorarray[3]] == 11 || board[actorarray[2]][actorarray[3]] == 12)
									{
										if(board[actorarray[2]][actorarray[3]] == 11)
										{
											board[actorarray[2]][actorarray[3]] = 7;
											array[actorarray[2]][actorarray[3]].setImage(USpike);
										}
										else if(board[actorarray[2]][actorarray[3]] == 12)
										{
											board[actorarray[2]][actorarray[3]] = 8;
											array[actorarray[2]][actorarray[3]].setImage(DSpike);
										}
									}
									else
									{
										board[actorarray[2]][actorarray[3]] = 0;
										array[actorarray[2]][actorarray[3]].setImage(floor);
									}
									actorarray[3] += 1;
									enemies(actorarray,board,array);
								}
							}
							else if(direction[3])
							{
								if(board[actorarray[2]+1][actorarray[3]] != 1)
								{
									if(board[actorarray[2]+1][actorarray[3]] == 10)
									{
										moves.setText("YOU WIN");
										array[actorarray[2]][actorarray[3]].setImage(floor);
										array[actorarray[2]+1][actorarray[3]].setImage(Win);
										all.getChildren().clear();
										waitin(primaryStage);
									}
									if(board[actorarray[2]+1][actorarray[3]] == 3 ||board[actorarray[2]+1][actorarray[3]] == 4 || board[actorarray[2]+1][actorarray[3]] == 5 || board[actorarray[2]+1][actorarray[3]] == 6 || board[actorarray[2]+1][actorarray[3]] == 7)
									{
										actorarray[1] = actorarray[1] - 2;
										if(board[actorarray[2]+1][actorarray[3]] == 7)
										{
											board[actorarray[2]+1][actorarray[3]] = 12;
											array[actorarray[2]+1][actorarray[3]].setImage(USWP);
										}
										else 
										{
											board[actorarray[2]+1][actorarray[3]] = 2;
											array[actorarray[2]+1][actorarray[3]].setImage(Knight);
										}
									}
									else if(board[actorarray[2]+1][actorarray[3]] == 9)
									{
										actorarray[1] += 3;
										board[actorarray[2]+1][actorarray[3]] = 2;
										array[actorarray[2]+1][actorarray[3]].setImage(Knight);
									}
									else
									{
										actorarray[1] = actorarray[1]-1;
										if(board[actorarray[2]+1][actorarray[3]] == 8)
										{
											board[actorarray[2]+1][actorarray[3]] = 11;
											array[actorarray[2]+1][actorarray[3]].setImage(DSWP);
										}
										else
										{
											board[actorarray[2]+1][actorarray[3]] = 2;
											array[actorarray[2]+1][actorarray[3]].setImage(Knight);
										}
									}
									if(board[actorarray[2]][actorarray[3]] == 11 || board[actorarray[2]][actorarray[3]] == 12)
									{
										if(board[actorarray[2]][actorarray[3]] == 11)
										{
											board[actorarray[2]][actorarray[3]] = 7;
											array[actorarray[2]][actorarray[3]].setImage(USpike);
										}
										else if(board[actorarray[2]][actorarray[3]] == 12)
										{
											board[actorarray[2]][actorarray[3]] = 8;
											array[actorarray[2]][actorarray[3]].setImage(DSpike);
										}
									}
									else
									{
										board[actorarray[2]][actorarray[3]] = 0;
										array[actorarray[2]][actorarray[3]].setImage(floor);
									}
									actorarray[2] += 1;
									enemies(actorarray,board,array);
								}
							}
						}
						direction[4] = true;
						moves.setText("Moves Left = " + String.valueOf(actorarray[1]));
					}
				}
			}
		});
		
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>()
		{
			public void handle(KeyEvent k)
			{
				switch(k.getCode())
				{
					case W: direction[0] = false; break;
					case A: direction[1] = false; break;
					case S: direction[2] = false; break;
					case D: direction[3] = false; break; 
					default:break;
				};
				if(!(direction[0] || direction[1] || direction[2] || direction[3]))
				{
					direction[4] = false;
				}
			}
		});
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private static int[] filein(int[][] stuff, int bS, int level)
	{
		if(bS == 7)
		{
			if(level == 1)
			{
				int ph1[] = {1,10,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,5,0,1,0,9,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,1,0,1,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,1,0,2,1};
				stuff[3] = ph4;
				int ph5[] = {1,1,9,1,9,1,1};
				stuff[4] = ph5;
				int ph6[] = {1,9,5,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,1,1,1,1,1,1};
				stuff[6] = ph7;

				int [] ids = new int[30];
				ids[0] = 3;
				ids[1] = 7;
				ids[2] = 3;
				ids[3] = 5;
				ids[4] = 5;
				ids[5] = 2;
				ids[6] = 1;
				ids[7] = 1;
				return ids;
			}
			else if(level == 2)
			{
				int ph1[] = {1,1,1,1,1,1,1,}; 
				 stuff[0] = ph1;
				int ph2[] = {1,1,1,1,1,1,1,}; 
				 stuff[1] = ph2;
				int ph3[] = {1,9,8,7,8,1,1,}; 
				 stuff[2] = ph3;
				int ph4[] = {10,8,8,7,2,1,1,}; 
				 stuff[3] = ph4;
				int ph5[] = {1,7,7,8,7,1,1,}; 
				 stuff[4] = ph5;
				int ph6[] = {1,1,1,1,1,1,1,}; 
				 stuff[5] = ph6;
				int ph7[] = {1,1,1,1,1,1,1,}; 
				 stuff[6] = ph7;
				int [] ids = new int[26];
				ids[0] = 11;
				ids[1] = 5;
				ids[2] = 3;
				ids[3] = 4;
				ids[4] = 2;
				ids[5] = 2;
				ids[6] = 2;
				ids[7] = 3;
				ids[8] = 2;
				ids[9] = 4;
				ids[10] = 3;
				ids[11] = 1;
				ids[12] = 3;
				ids[13] = 2;
				ids[14] = 3;
				ids[15] = 3;
				ids[16] = 4;
				ids[17] = 1;
				ids[18] = 4;
				ids[19] = 2;
				ids[20] = 4;
				ids[21] = 3;
				ids[22] = 4;
				ids[23] = 4;
				return ids;
			}
			else if(level == 3)
			{
				int ph1[] = {1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {10,0,0,0,0,2,1};
				stuff[3] = ph4;
				int ph5[] = {1,0,0,0,0,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,1,1,1,1,1,1};
				stuff[6] = ph7;
				
				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 730;
				ids[2] = 3;
				ids[3] = 5;
				return ids;
			}
			else if(level == 4)
			{
				int ph1[] = {1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {10,0,0,0,0,2,1};
				stuff[3] = ph4;
				int ph5[] = {1,0,0,0,0,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,1,1,1,1,1,1};
				stuff[6] = ph7;
				
				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 730;
				ids[2] = 3;
				ids[3] = 5;
				return ids;
			}
		}
		else if(bS == 9)
		{
			if(level == 1)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,9,5,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,1,1,7,1,1,9,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,9,0,0,1,4,1};
				stuff[3] = ph4;
				int ph5[] = {1,1,1,1,1,0,1,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,4,9,0,8,3,1,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,0,1,1,1,9,1,0,1};
				stuff[6] = ph7;
				int ph8[] = {1,0,9,5,0,0,1,2,1};
				stuff[7] = ph8;
				int ph9[] = {1,10,1,1,1,1,1,1,1};
				stuff[8] = ph9;
				
				int [] ids = new int[30];
				ids[0] = 8;
				ids[1] = 15;
				ids[2] = 7;
				ids[3] = 7;
				ids[4] = 5;
				ids[5] = 1;
				ids[6] = 1;
				ids[7] = 3;
				ids[8] = 7;
				ids[9] = 3;
				ids[10] = 2;
				ids[11] = 4;
				ids[12] = 5;
				ids[13] = 4;
				ids[14] = 5;
				ids[15] = 5;
				ids[16] = 3;
				ids[17] = 7;
				return ids;
			}
			if(level == 2)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,0,0,0,0,0,1};
				stuff[3] = ph4;
				int ph5[] = {10,0,0,0,0,0,0,2,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,0,0,0,0,0,0,0,1};
				stuff[6] = ph7;
				int ph8[] = {1,0,0,0,0,0,0,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,1,1,1,1,1,1,1,1};
				stuff[8] = ph9;
				
				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 999;
				ids[2] = 4;
				ids[3] = 7;
				return ids;
			}
			if(level == 3)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,0,0,0,0,0,1};
				stuff[3] = ph4;
				int ph5[] = {10,0,0,0,0,0,0,2,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,0,0,0,0,0,0,0,1};
				stuff[6] = ph7;
				int ph8[] = {1,0,0,0,0,0,0,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,1,1,1,1,1,1,1,1};
				stuff[8] = ph9;
				
				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 999;
				ids[2] = 4;
				ids[3] = 7;
				return ids;
			}
			if(level == 4)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,0,0,0,0,0,1};
				stuff[3] = ph4;
				int ph5[] = {10,0,0,0,0,0,0,2,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {1,0,0,0,0,0,0,0,1};
				stuff[6] = ph7;
				int ph8[] = {1,0,0,0,0,0,0,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,1,1,1,1,1,1,1,1};
				stuff[8] = ph9;
				
				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 999;
				ids[2] = 4;
				ids[3] = 7;
				return ids;
			}
		}
		else if(bS == 11)
		{
			if (level == 1) 
			{
				int ph1[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
				stuff[0] = ph1;
				int ph2[] = { 1, 2, 0, 0, 0, 8, 8, 8, 0, 0, 1 };
				stuff[1] = ph2;
				int ph3[] = { 1, 0, 1, 1, 1, 1, 1, 1, 7, 1, 1 };
				stuff[2] = ph3;
				int ph4[] = { 1, 0, 1, 9, 7, 0, 0, 0, 0, 0, 1 };
				stuff[3] = ph4;
				int ph5[] = { 1, 4, 1, 0, 1, 1, 1, 1, 1, 0, 1 };
				stuff[4] = ph5;
				int ph6[] = { 1, 0, 1, 0, 1, 5, 0, 0, 0, 0, 1 };
				stuff[5] = ph6;
				int ph7[] = { 1, 0, 1, 0, 1, 0, 5, 0, 0, 0, 1 };
				stuff[6] = ph7;
				int ph8[] = { 1, 0, 0, 0, 1, 9, 0, 1, 0, 0, 1 };
				stuff[7] = ph8;
				int ph9[] = { 1, 0, 1, 0, 1, 1, 1, 9, 1, 0, 1 };
				stuff[8] = ph9;
				int ph10[] = { 1, 9, 1, 0, 0, 9, 9, 9, 1, 0, 1 };
				stuff[9] = ph10;
				int ph11[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 10, 1 };
				stuff[10] = ph11;
				
				int[] ids = new int[30];
				ids[0] = 9;
				ids[1] = 21;
				ids[2] = 1;
				ids[3] = 1;
				ids[4] = 1;
				ids[5] = 5;
				ids[6] = 1;
				ids[7] = 6;
				ids[8] = 1;
				ids[9] = 7;
				ids[10] = 2;
				ids[11] = 8;
				ids[12] = 3;
				ids[13] = 4;
				ids[14] = 4;
				ids[15] = 1;
				ids[16] = 5;
				ids[17] = 5;
				ids[18] = 6;
				ids[19] = 6;
				return ids;
			}
			if (level == 2) 
			{
				int ph1[] = { 1, 1, 1, 1, 1, 10, 1, 1, 1, 1, 1 };
				stuff[0] = ph1;
				int ph2[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[1] = ph2;
				int ph3[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[2] = ph3;
				int ph4[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[3] = ph4;
				int ph5[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[4] = ph5;
				int ph6[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[5] = ph6;
				int ph7[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[6] = ph7;
				int ph8[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[7] = ph8;
				int ph9[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[8] = ph9;
				int ph10[] = { 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1 };
				stuff[9] = ph10;
				int ph11[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
				stuff[10] = ph11;
				
				int[] ids = new int[30];
				ids[0] = 1;
				ids[1] = 999;
				ids[2] = 9;
				ids[3] = 5;
				return ids;
			}
			if (level == 3) 
			{
				int ph1[] = { 1, 1, 1, 1, 1, 10, 1, 1, 1, 1, 1 };
				stuff[0] = ph1;
				int ph2[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[1] = ph2;
				int ph3[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[2] = ph3;
				int ph4[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[3] = ph4;
				int ph5[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[4] = ph5;
				int ph6[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[5] = ph6;
				int ph7[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[6] = ph7;
				int ph8[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[7] = ph8;
				int ph9[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[8] = ph9;
				int ph10[] = { 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1 };
				stuff[9] = ph10;
				int ph11[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
				stuff[10] = ph11;
				
				int[] ids = new int[30];
				ids[0] = 1;
				ids[1] = 999;
				ids[2] = 9;
				ids[3] = 5;
				return ids;
			}
			if (level == 4) 
			{
				int ph1[] = { 1, 1, 1, 1, 1, 10, 1, 1, 1, 1, 1 };
				stuff[0] = ph1;
				int ph2[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[1] = ph2;
				int ph3[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[2] = ph3;
				int ph4[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[3] = ph4;
				int ph5[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[4] = ph5;
				int ph6[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[5] = ph6;
				int ph7[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[6] = ph7;
				int ph8[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[7] = ph8;
				int ph9[] = { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 };
				stuff[8] = ph9;
				int ph10[] = { 1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1 };
				stuff[9] = ph10;
				int ph11[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
				stuff[10] = ph11;
				
				int[] ids = new int[30];
				ids[0] = 1;
				ids[1] = 999;
				ids[2] = 9;
				ids[3] = 5;
				return ids;
			}
		}
		else if(bS == 13)
		{
			if(level == 1)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,5,0,0,0,0,0,0,1,4,9,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,1,1,1,1,1,4,1,0,1,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,1,4,9,0,1,0,7,0,1,0,1};
				stuff[3] = ph4;
				int ph5[] = {1,0,1,0,1,0,9,0,1,0,1,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,8,0,1,0,1,1,1,0,1,0,1};
				stuff[5] = ph6;
				int ph7[] = {10,0,1,0,7,0,0,9,0,0,1,2,1};
				stuff[6] = ph7; 
				int ph8[] = {1,0,8,0,1,0,1,1,1,0,1,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,0,1,0,1,0,9,0,1,0,1,0,1};
				stuff[8] = ph9;
				int ph10[] = {1,0,1,0,9,4,1,0,7,0,1,0,1};
				stuff[9] = ph10;
				int ph11[] = {1,4,1,1,1,1,1,0,1,0,1,0,1};
				stuff[10] = ph11;
				int ph12[] = {1,0,0,0,0,0,0,6,1,0,9,3,1};
				stuff[11] = ph12;
				int ph13[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[12] = ph13;

				int [] ids = new int[30];
				ids[0] = 14;
				ids[1] = 35;
		    	ids[2] = 6;
		    	ids[3] = 11;
		    	ids[4] = 11;
		    	ids[5] = 11;
		    	ids[6] = 9;
		    	ids[7] = 5;
		    	ids[8] = 10;
		    	ids[9] = 1;
		    	ids[10] = 1;
		    	ids[11] = 9;
		    	ids[12] = 3;
		    	ids[13] = 3;
		    	ids[14] = 2;
		    	ids[15] = 7;
		    	ids[16] = 1;
		    	ids[17] = 1;
		    	ids[18] = 11;
		    	ids[19] = 7;
		    	ids[20] = 6;
		    	ids[21] = 4;
		    	ids[22] = 3;
		    	ids[23] = 8;
		    	ids[24] = 9;
		    	ids[25] = 8;
		    	ids[26] = 5;
		    	ids[27] = 2;
		    	ids[28] = 7;
		    	ids[29] = 2;
		    	return ids;
			}
			else if(level == 2)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[3] = ph4;
				int ph5[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {10,0,0,0,0,0,0,0,0,0,0,2,1};
				stuff[6] = ph7; 
				int ph8[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[8] = ph9;
				int ph10[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[9] = ph10;
				int ph11[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[10] = ph11;
				int ph12[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[11] = ph12;
				int ph13[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[12] = ph13;

				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 35;
		    	ids[2] = 6;
		    	ids[3] = 11;
		    	return ids;
			}
			else if(level == 3)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[3] = ph4;
				int ph5[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {10,0,0,0,0,0,0,0,0,0,0,2,1};
				stuff[6] = ph7; 
				int ph8[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[8] = ph9;
				int ph10[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[9] = ph10;
				int ph11[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[10] = ph11;
				int ph12[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[11] = ph12;
				int ph13[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[12] = ph13;

				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 35;
		    	ids[2] = 6;
		    	ids[3] = 11;
		    	return ids;
			}
			else if(level == 4)
			{
				int ph1[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[0] = ph1;
				int ph2[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[1] = ph2;
				int ph3[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[2] = ph3;
				int ph4[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[3] = ph4;
				int ph5[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[4] = ph5;
				int ph6[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[5] = ph6;
				int ph7[] = {10,0,0,0,0,0,0,0,0,0,0,2,1};
				stuff[6] = ph7; 
				int ph8[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[7] = ph8;
				int ph9[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[8] = ph9;
				int ph10[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[9] = ph10;
				int ph11[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[10] = ph11;
				int ph12[] = {1,0,0,0,0,0,0,0,0,0,0,0,1};
				stuff[11] = ph12;
				int ph13[] = {1,1,1,1,1,1,1,1,1,1,1,1,1};
				stuff[12] = ph13;

				int [] ids = new int[30];
				ids[0] = 1;
				ids[1] = 35;
		    	ids[2] = 6;
		    	ids[3] = 11;
		    	return ids;
			}
		}
		return null;
	}
	
	private void waitin(Stage primaryStage)
	{
		Text winnerIsU = new Text();
		Pane pane = new Pane();
		Scene scene = new Scene(pane,1280,720);
		winnerIsU.setText("You Win!");
		winnerIsU.setLayoutX(620);
		winnerIsU.setLayoutY(350);
		pane.getChildren().add(winnerIsU);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Button menu = new Button();
		menu.setText("Return To Menu");
		menu.setLayoutX(600);
		menu.setLayoutY(500);
		EventHandler<ActionEvent> m = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				pane.getChildren().clear();
				start(primaryStage);
			}
		};
		menu.setOnAction(m);
		pane.getChildren().add(menu);
	}
	
	private void losin(Stage primaryStage)
	{
		Text winnerIsU = new Text();
		Pane pane = new Pane();
		Scene scene = new Scene(pane,1280,720);
		winnerIsU.setText("You LOSE!");
		winnerIsU.setLayoutX(620);
		winnerIsU.setLayoutY(350);
		pane.getChildren().add(winnerIsU);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		Button menu = new Button();
		menu.setText("Return To Menu");
		menu.setLayoutX(600);
		menu.setLayoutY(500);
		EventHandler<ActionEvent> m = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) 
			{
				pane.getChildren().clear();
				start(primaryStage);
			}
		};
		menu.setOnAction(m);
		pane.getChildren().add(menu);
	}

	private void enemies(int[] aa, int[][] bn, ImageView[][] bi)
	{
		Image floor = new Image("images/Floor.png");
		Image Troll = new Image("images/Troll.png");
		Image USpike = new Image("images/Up_Spike.png");
		Image DSpike = new Image("images/Down_Spike.png");
		for(int i = 1; i < aa[0]; i++)
		{
			if(bn[aa[i*2+2]][aa[i*2+3]] == 7)
			{
				bn[aa[i*2+2]][aa[i*2+3]] = 8;
				bi[aa[i*2+2]][aa[i*2+3]].setImage(USpike);
			}
			else if(bn[aa[i*2+2]][aa[i*2+3]] == 8)
			{
				bn[aa[i*2+2]][aa[i*2+3]] = 7;
				bi[aa[i*2+2]][aa[i*2+3]].setImage(DSpike);
			}
			else if(bn[aa[i*2+2]][aa[i*2+3]] == 3)
			{
				if(bn[aa[i*2+2]-1][aa[i*2+3]] == 1 || bn[aa[i*2+2]-1][aa[i*2+3]] == 10)
				{
					if(bn[aa[i*2+2]+1][aa[i*2+3]] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]+1][aa[i*2+3]] = 4;
						bi[aa[i*2+2]+1][aa[i*2+3]].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2]++;
					}
				}
				else
				{
					if(bn[aa[i*2+2]-1][aa[i*2+3]] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]-1][aa[i*2+3]] = 3;
						bi[aa[i*2+2]-1][aa[i*2+3]].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2]--;
					}
				}
			}
			else if(bn[aa[i*2+2]][aa[i*2+3]] == 4)
			{
				if(bn[aa[i*2+2]+1][aa[i*2+3]] == 1 || bn[aa[i*2+2]+1][aa[i*2+3]] == 10)
				{
					if(bn[aa[i*2+2]-1][aa[i*2+3]] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]-1][aa[i*2+3]] = 3;
						bi[aa[i*2+2]-1][aa[i*2+3]].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2]--;
					}
				}
				else
				{
					if(bn[aa[i*2+2]+1][aa[i*2+3]] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]+1][aa[i*2+3]] = 4;
						bi[aa[i*2+2]+1][aa[i*2+3]].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2]++;
					}
				}
			}
			else if(bn[aa[i*2+2]][aa[i*2+3]] == 5)
			{
				if(bn[aa[i*2+2]][aa[i*2+3]+1] == 1 || bn[aa[i*2+2]][aa[i*2+3]+1] == 10)
				{
					if(bn[aa[i*2+2]][aa[i*2+3]-1] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]][aa[i*2+3]-1] = 6;
						bi[aa[i*2+2]][aa[i*2+3]-1].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+3]--;
					}
				}
				else
				{
					if(bn[aa[i*2+2]][aa[i*2+3]+1] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]][aa[i*2+3]+1] = 5;
						bi[aa[i*2+2]][aa[i*2+3]+1].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+3]++;
					}
				}
			}
			else if(bn[aa[i*2+2]][aa[i*2+3]] == 6)
			{
				if(bn[aa[i*2+2]][aa[i*2+3]-1] == 1 || bn[aa[i*2+2]][aa[i*2+3]-1] == 10)
				{
					if(bn[aa[i*2+2]][aa[i*2+3]+1] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]][aa[i*2+3]+1] = 5;
						bi[aa[i*2+2]][aa[i*2+3]+1].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+3]++;
					}
				}
				else
				{
					if(bn[aa[i*2+2]][aa[i*2+3]-1] == 2)
					{
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+2] = 0;
						aa[i*2+3] = 0;
					}
					else 
					{
						bn[aa[i*2+2]][aa[i*2+3]-1] = 6;
						bi[aa[i*2+2]][aa[i*2+3]-1].setImage(Troll);
						bn[aa[i*2+2]][aa[i*2+3]] = 0;
						bi[aa[i*2+2]][aa[i*2+3]].setImage(floor);
						aa[i*2+3]--;
					}
				}
			}
		}
	}
	
	public void lS(int bS, Stage primaryStage, Button[] buttons)
	{
		EventHandler<ActionEvent> b7 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				gameController(bS,1,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> b9 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				gameController(bS,2,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> b11 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				gameController(bS,3,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> b13 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				gameController(bS,4,primaryStage);
			}
		};
		buttons[0].setText("Level 1");
		buttons[0].setLayoutX(638);
		buttons[1].setText("Level 2");
		buttons[1].setLayoutX(638);
		buttons[2].setText("Level 3");
		buttons[2].setLayoutX(638);
		buttons[3].setText("Level 4");
		buttons[3].setLayoutX(638);
		buttons[0].setOnAction(b7);
		buttons[1].setOnAction(b9);
		buttons[2].setOnAction(b11);
		buttons[3].setOnAction(b13);
	}
	
	public void LE(Stage primaryStage, Button[] buttons, Pane pane)
	{
		EventHandler<ActionEvent> b7 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				Editor(7,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> b9 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				Editor(9,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> b11 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				Editor(11,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> b13 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				Editor(13,primaryStage);
			}
		};
		
		EventHandler<ActionEvent> Back = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e) {
				start(primaryStage);
			}
		};
		
		
		buttons[0].setText("Create 7x7");
		buttons[0].setLayoutX(625);
		buttons[1].setText("Create 9x9");
		buttons[1].setLayoutX(625);
		buttons[2].setText("Create 11x11");
		buttons[2].setLayoutX(620);
		buttons[3].setText("Create 13x13");
		buttons[3].setLayoutX(620);
		buttons[4].setText("Back");
		buttons[4].setLayoutX(640);
		buttons[0].setOnAction(b7);
		buttons[1].setOnAction(b9);
		buttons[2].setOnAction(b11);
		buttons[3].setOnAction(b13);
		buttons[4].setOnAction(Back);
		pane.getChildren().remove(buttons[5]);
	}
	
	public GridPane Refresh(ImageView[][] set,int SVal, double HGVal, double VGVal, double XPos, double YPos)
	{
		GridPane Refreshed = new GridPane();
		for(int i = 0; i < SVal; i++)
		{
			for(int j = 0; j < SVal; j++)
			{
				Refreshed.add(set[i][j], i, j);
			}
		}
		Refreshed.setLayoutX(XPos);
		Refreshed.setLayoutY(YPos);
		Refreshed.setHgap(HGVal);
		Refreshed.setVgap(VGVal);
		return Refreshed;
	}
	
	public void Editor(int SVal, Stage primaryStage)
	{
		int [][] board = new int[SVal][SVal];
		for(int i = 0; i < SVal; i++)
		{
			board[0][i] = 1;
			board[SVal-1][i] = 1;
			board[i][0] = 1;
			board[i][SVal-1] = 1;
		}
		int [] actorarray = new int[30];
		actorarray[0] = 1;
		int[] actualpos = new int[2];
		boolean[] isExit = new boolean[1];
		isExit[0] = false;
		int[] exitSet = new int[2];
		boolean[] isPlayer = new boolean[1];
		isPlayer[0] = false;
		int[] playerSet = new int[2];
		
		Pane normalVis = new Pane();
		GridPane[] display = new GridPane[1];
		display[0] = new GridPane();
		GridPane editor = new GridPane();
		Scene scene = new Scene(normalVis,1280,720);
		ImageView [][] array = new ImageView[SVal][SVal];
		Button [][] bArray = new Button[SVal][SVal];
		
		Image floor = new Image("images/Floor.png");
		ImageView Floor = new ImageView();
		Floor.setImage(floor);
		
		Image wall = new Image("images/Wall.png");
		ImageView Wall = new ImageView();
		Wall.setImage(wall);
		//weird fix
		Image wall2 = new Image("images/Wall.png");
		ImageView Wall2 = new ImageView();
		Wall2.setImage(wall2);
		
		Image knight = new Image("images/Knight.png");
		ImageView Knight = new ImageView();
		Knight.setImage(knight);
		
		Image TrollL = new Image("images/TrollRight.png");
		ImageView Ltroll = new ImageView();
		Ltroll.setImage(TrollL);
		
		Image TrollR = new Image("images/TrollLeft.png");
		ImageView Rtroll = new ImageView();
		Rtroll.setImage(TrollR);
		
		Image TrollU = new Image("images/TrollUp.png");
		ImageView Utroll = new ImageView();
		Utroll.setImage(TrollU);
		
		Image TrollD = new Image("images/TrollDown.png");
		ImageView Dtroll = new ImageView();
		Dtroll.setImage(TrollD);
		
		Image USpike = new Image("images/Up_Spike.png");
		ImageView SpikeU = new ImageView();
		SpikeU.setImage(USpike);
		
		Image DSpike = new Image("images/Down_Spike.png");
		ImageView SpikeD = new ImageView();
		SpikeD.setImage(DSpike);
		
		Image Exit = new Image("images/Exit.png");
		ImageView Xit = new ImageView();
		Xit.setImage(Exit);
		//weird Fix
		Image Exit2 = new Image("images/Exit.png");
		ImageView Xit2 = new ImageView();
		Xit2.setImage(Exit2);
		
		Image Win = new Image("images/Win.png");
		ImageView win = new ImageView();
		win.setImage(Win);
		
		Image Powerup = new Image("images/Powerup.png");
		ImageView POW = new ImageView();
		POW.setImage(Powerup);
		
		Text moves = new Text();
		moves.setText("Set Move Number");
		moves.setLayoutX(30);
		moves.setLayoutY(30);
		
		TextField numIn = new TextField();
		numIn.setLayoutX(30);
		numIn.setLayoutY(50);
		
		Button ScrapeNum = new Button();
		ScrapeNum.setLayoutX(15);
		ScrapeNum.setLayoutY(80);
		ScrapeNum.setText("Set Move Counter To New Value");
		
		Button menu = new Button();
		menu.setLayoutX(1024);
		menu.setLayoutY(30);
		menu.setText("Return to Menu");
		
		Button Build = new Button();
		Build.setLayoutX(610);
		Build.setLayoutY(680);
		Build.setText("Build Map");
		
		Button quit = new Button();
		quit.setLayoutX(1150);
		quit.setLayoutY(30);
		quit.setText("Exit Program");
		
		Rectangle grey = new Rectangle();
		grey.setX(0);
		grey.setY(240);
		grey.setWidth(1280);
		grey.setHeight(240);
		grey.setFill(Color.GREY);
		
		ImageView[][] images11 = new ImageView[6][2];
		images11[0][0] = Floor;
		images11[0][1] = Wall;
		images11[1][0] = Knight;
		images11[1][1] = POW;
		images11[2][0] = SpikeU;
		images11[2][1] = SpikeD;
		images11[3][0] = Ltroll;
		images11[3][1] = Dtroll;
		images11[4][0] = Utroll;
		images11[4][1] = Rtroll;
		images11[5][0] = Xit;
		Button[][] buttons11 = new Button[6][2];
		GridPane editor11 = new GridPane();
		editor11.setLayoutX(510);
		editor11.setLayoutY(275);
		editor11.setHgap(10);
		editor11.setVgap(27);
		for(int i = 0; i < 5; i++)
		{
			editor11.add(images11[i][0], i, 0);
			buttons11[i][0] = new Button();
			buttons11[i][0].setText(Integer.toString(i));
			editor11.add(buttons11[i][0], i, 1);
			editor11.add(images11[i][1], i, 2);
			buttons11[i][1] = new Button();
			buttons11[i][1].setText(Integer.toString(i+6));
			editor11.add(buttons11[i][1], i, 3);
		}
		editor11.add(images11[5][0], 5, 0);
		buttons11[5][0] = new Button();
		buttons11[5][0].setText(Integer.toString(5));
		editor11.add(buttons11[5][0], 5, 1);
		
		ImageView[] images2 = new ImageView[2];
		images2[0] = Wall2;
		images2[1] = Xit2;
		Button[] buttons2 = new Button[2];
		GridPane editor2 = new GridPane();
		editor2.setLayoutX(603);
		editor2.setLayoutY(320);
		editor2.setHgap(10);
		editor2.setVgap(27);
		for(int i = 0; i < 2; i++)
		{
			editor2.add(images2[i], i, 0);
			buttons2[i] = new Button();
			buttons2[i].setText(Integer.toString(i));
			editor2.add(buttons2[i], i, 1);
		}
		
		normalVis.getChildren().addAll(display[0],editor,moves,numIn,ScrapeNum,Build,menu,quit);
		
		if(SVal == 7)
		{
			display[0].setLayoutX(500);
			display[0].setLayoutY(192);
			editor.setLayoutX(505);
			editor.setLayoutY(222);
			editor.setHgap(13);
		}
		else if(SVal == 9)
		{
			display[0].setLayoutX(450);
			display[0].setLayoutY(128);
			editor.setLayoutX(455);
			editor.setLayoutY(158);
			editor.setHgap(13);
		}
		else if(SVal == 11)
		{
			display[0].setLayoutX(400);
			display[0].setLayoutY(64);
			editor.setLayoutX(405);
			editor.setLayoutY(94);
			editor.setHgap(10);
		}
		else if(SVal == 13)
		{
			display[0].setLayoutX(350);
			display[0].setLayoutY(0);
			editor.setLayoutX(355);
			editor.setLayoutY(30);
			editor.setHgap(10);
		}
		editor.setVgap(27);
		display[0].setVgap(20);
		display[0].setHgap(10);
		
		
		for(int i = 0; i < SVal; i++)
		{
			for(int j = 0; j < SVal; j++)
			{
				if(board[i][j] == 0)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(floor);
					bArray[i][j] = new Button(Integer.toString(i*SVal+j));
					display[0].add(array[i][j], i, j);
					editor.add(bArray[i][j], i, j);
				}
				else if(board[i][j] == 1)
				{
					array[i][j] = new ImageView();
					array[i][j].setImage(wall);
					bArray[i][j] = new Button(Integer.toString(i*SVal+j));
					display[0].add(array[i][j], i, j);
					editor.add(bArray[i][j], i, j);
				}
			}
		}
		
		EventHandler<ActionEvent> exit  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				System.exit(0);
			}
		};
		EventHandler<ActionEvent> Menu  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				normalVis.getChildren().clear();
				start(primaryStage);
			}
		};
		EventHandler<ActionEvent> AreYouSure  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				menu.setText("Are You Sure?");
				menu.setOnAction(Menu);
			}
		};
		EventHandler<ActionEvent> Scrape  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				try
				{
					actorarray[1]=Integer.parseInt(numIn.getText());
				}
				catch (NumberFormatException ex)
				{
					numIn.setText("NaN");
				}
			}
		};
		EventHandler<ActionEvent> GenerateMap  = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				actorarray[0] -= 1;
				String bigBoi = new String();
				for(int i = 0; i < SVal; i++)
				{
					bigBoi += ("int ph"+(i+1)+"[] = {");
					for (int j = 0; j < SVal; j++)
					{
						bigBoi += (board[i][j]+",");
					}
					bigBoi += ("}; \n stuff["+i+"] = ph"+(i+1)+";\n");
										//ids[0] = 3;
				}
				bigBoi+=("int [] ids = new int[" + Integer.toString((2+actorarray[0])*2) + "];\n");
				actorarray[2] = playerSet[0];
				actorarray[3] = playerSet[1];
				int tVal = 4;
				for(int i = 0; i < SVal; i++)
				{
					for(int j = 0; j < SVal; j++)
					{
						if(board[i][j] > 2 && board[i][j] < 9)
						{
							actorarray[tVal] = i;
							actorarray[tVal+1] = j;
							tVal += 2;
						}
						if(actorarray[0] == tVal)
						{
							break;
						}
					}
				}
				for(int i = 0; i < (2+(actorarray[0])*2); i++)
				{
					bigBoi+= ("ids["+i+"] = "+actorarray[i]+";\n");
				}
				bigBoi += "return ids;";
				try {
					FileOutputStream fout = new FileOutputStream("C:\\Users\\Kevin\\eclipse-workspace\\GP\\src\\levels\\LevelOut.txt");
					byte b[] = bigBoi.getBytes();
					fout.write(b);
					fout.flush();
					fout.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				normalVis.getChildren().remove(Build);
				normalVis.getChildren().remove(display[0]);
				normalVis.getChildren().remove(ScrapeNum);
				normalVis.getChildren().remove(editor);
				normalVis.getChildren().remove(numIn);
				
				moves.setText("Level data sent to file.");
				moves.setLayoutX(610);
				moves.setLayoutY(600);
				
				menu.setLayoutX(610);
				menu.setLayoutY(680);
				menu.setOnAction(Menu);
			}
		};
		EventHandler<ActionEvent> EditorButton = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				Button ref = (Button)e.getSource();
				String val = ref.getText();
				int pos = Integer.valueOf(val);
				actualpos[0] = pos/SVal;
				actualpos[1] = pos%SVal;
				normalVis.getChildren().add(grey);
				if(0<actualpos[0] && actualpos[0]<(SVal-1) && 0<actualpos[1] && actualpos[1]<(SVal-1))
				{
					normalVis.getChildren().add(editor11);
				}
				else
				{
					normalVis.getChildren().add(editor2);
				}
				normalVis.getChildren().remove(editor);
				normalVis.getChildren().remove(display[0]);
			}
		};
		EventHandler<ActionEvent> set11 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				Button ref = (Button)e.getSource();
				String val = ref.getText();
				int pos = Integer.valueOf(val);
				if(exitSet[0]==actualpos[0] && exitSet[1] == actualpos[1])
				{
					isExit[0] = false;
				}
				if(playerSet[0] == actualpos[0] && playerSet[1] == actualpos[1])
				{
					isPlayer[0] = false;
				}
				if((board[actualpos[0]][actualpos[1]] > 2 && board[actualpos[0]][actualpos[1]] < 9) || (playerSet[0] == actualpos[0] && playerSet[1] == actualpos[1]))
				{
					actorarray[0] -= 1;
				}
				if(pos == 0)
				{
					board[actualpos[0]][actualpos[1]] = 0;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(floor);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 1)
				{
					if(isPlayer[0])
					{
						if(0<playerSet[0] && playerSet[0]<(SVal-1) && 0<playerSet[1] && playerSet[1]<(SVal-1))
						{
							board[playerSet[0]][playerSet[1]] = 0;
							array[playerSet[0]][playerSet[1]] = new ImageView();
							array[playerSet[0]][playerSet[1]].setImage(floor);
							display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
						}
						else
						{
							board[playerSet[0]][playerSet[1]] = 1;
							array[playerSet[0]][playerSet[1]] = new ImageView();
							array[playerSet[0]][playerSet[1]].setImage(wall);
							display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
						}
					}
					playerSet[0] = actualpos[0];
					playerSet[1] = actualpos[1];
					isExit[0] = true; //its a trick
					actorarray[0] +=1;
					board[playerSet[0]][playerSet[1]] = 2;
					array[playerSet[0]][playerSet[1]] = new ImageView();
					array[playerSet[0]][playerSet[1]].setImage(knight);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 2)
				{
					board[actualpos[0]][actualpos[1]] = 8;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(USpike);
					actorarray[0] +=1;
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 3)
				{
					board[actualpos[0]][actualpos[1]] = 3;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(TrollL);
					actorarray[0] +=1;
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 4)
				{
					board[actualpos[0]][actualpos[1]] = 6;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(TrollU);
					actorarray[0] +=1;
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 5)
				{
					if(isExit[0])
					{
						if(0<exitSet[0] && exitSet[0]<(SVal-1) && 0<exitSet[1] && exitSet[1]<(SVal-1))
						{
							board[exitSet[0]][exitSet[1]] = 0;
							array[exitSet[0]][exitSet[1]] = new ImageView();
							array[exitSet[0]][exitSet[1]].setImage(floor);
							display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
						}
						else
						{
							board[exitSet[0]][exitSet[1]] = 1;
							array[exitSet[0]][exitSet[1]] = new ImageView();
							array[exitSet[0]][exitSet[1]].setImage(wall);
							display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
						}
					}
					exitSet[0] = actualpos[0];
					exitSet[1] = actualpos[1];
					isExit[0] = true; //its a trick
					board[exitSet[0]][exitSet[1]] = 10;
					array[exitSet[0]][exitSet[1]] = new ImageView();
					array[exitSet[0]][exitSet[1]].setImage(Exit);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 6)
				{
					board[actualpos[0]][actualpos[1]] = 1;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(wall);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 7)
				{
					board[actualpos[0]][actualpos[1]] = 9;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(Powerup);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 8)
				{
					board[actualpos[0]][actualpos[1]] = 7;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(DSpike);
					actorarray[0] +=1;
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 9)
				{
					board[actualpos[0]][actualpos[1]] = 5;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(TrollD);
					actorarray[0] +=1;
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				if(pos == 10)
				{
					board[actualpos[0]][actualpos[1]] = 4;
					array[actualpos[0]][actualpos[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(TrollR);
					actorarray[0] +=1;
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				
				normalVis.getChildren().add(display[0]);
				normalVis.getChildren().add(editor);
				normalVis.getChildren().remove(editor11);
				normalVis.getChildren().remove(grey);
			}
		};
		EventHandler<ActionEvent> set2 = new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent e)
			{
				Button ref = (Button)e.getSource();
				String val = ref.getText();
				int pos = Integer.valueOf(val);
				if(pos == 1)
				{
					if(isExit[0])
					{
						if(0<exitSet[0] && exitSet[0]<(SVal-1) && 0<exitSet[1] && exitSet[1]<(SVal-1))
						{
							board[exitSet[0]][exitSet[1]] = 0;
							array[exitSet[0]][exitSet[1]] = new ImageView();
							array[exitSet[0]][exitSet[1]].setImage(floor);
							display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
						}
						else
						{
							board[exitSet[0]][exitSet[1]] = 1;
							array[exitSet[0]][exitSet[1]] = new ImageView();
							array[exitSet[0]][exitSet[1]].setImage(wall);
							display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
						}
					}
					exitSet[0] = actualpos[0];
					exitSet[1] = actualpos[1];
					isExit[0] = true; //its a trick
					board[exitSet[0]][exitSet[1]] = 10;
					array[exitSet[0]][exitSet[1]] = new ImageView();
					array[exitSet[0]][exitSet[1]].setImage(Exit);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				else
				{
					if(exitSet[0]==actualpos[0] && exitSet[1] == actualpos[1])
					{
						isExit[0] = false;
					}
					board[actualpos[0]][actualpos[1]] = 1;
					array[actualpos[0]][exitSet[1]] = new ImageView();
					array[actualpos[0]][actualpos[1]].setImage(wall);
					display[0] = Refresh(array,SVal,display[0].getHgap(),display[0].getVgap(),display[0].getLayoutX(),display[0].getLayoutY());
				}
				normalVis.getChildren().add(display[0]);
				normalVis.getChildren().remove(editor2);
				normalVis.getChildren().add(editor);
				normalVis.getChildren().remove(grey);
			}
		};
		
		quit.setOnAction(exit);
		menu.setOnAction(AreYouSure);
		ScrapeNum.setOnAction(Scrape);
		Build.setOnAction(GenerateMap);
		
		for(int i = 0; i < SVal; i++)
		{
			for(int j = 0; j < SVal; j++)
			{
				bArray[i][j].setOnAction(EditorButton);
			}
		}
		
		for(int i = 0; i < 5; i++)
		{
			buttons11[i][0].setOnAction(set11);
			buttons11[i][1].setOnAction(set11);
		}
		buttons11[5][0].setOnAction(set11);
		
		for(int i = 0; i < 2; i++)
		{
			buttons2[i].setOnAction(set2);
		}
		
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}

