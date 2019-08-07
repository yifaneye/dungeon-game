package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * A JavaFX controller for the dungeon.
 * @author Robert Clifton-Everest
 *
 */
public class DungeonController {

    @FXML
    public GridPane squares;

    private List<ImageView> initialEntities;

    private Player player;

    private Dungeon dungeon;
    
    private Text text_sword = new Text("0");
    private Text text_invincibility = new Text("0");
    private Text text_key = new Text("0");
    private Text text_bomb = new Text("0");
    private Text text_space = new Text("[SPACE]\n to PICK");
    private Text text_b = new Text("    [B]\nto DROP");
    private Text text_enter = new Text("[ENTER]\n to EXIT");
    /**
	 * 
	 */
    public DungeonController(Dungeon dungeon, List<ImageView> initialEntities) {
        this.dungeon = dungeon;
        dungeon.setController(this);
        this.player = dungeon.getPlayer();
        this.initialEntities = new ArrayList<>(initialEntities);
    }
    /**
	 * 
	 */
    @FXML
    public void initialize() {
        Image ground = new Image("ground.png");

        // Add the ground first so it is below all other entities
        for (int x = 0; x < dungeon.getWidth(); x++) {
            for (int y = 0; y < dungeon.getHeight() + 1; y++) {
                squares.add(new ImageView(ground), x, y);
            }
        }
        
        for (ImageView entity : initialEntities) {
        	squares.getChildren().add(entity);
        }
        
        // set bottom line 
        Image sword = new Image("sword.png");
        squares.add(new ImageView(sword), 0, dungeon.getHeight());
        
        Image invin = new Image("invincibility.png");
        squares.add(new ImageView(invin), 2, dungeon.getHeight());
        
        Image key = new Image("key.png");
        squares.add(new ImageView(key), 4, dungeon.getHeight());
        
        Image bomb = new Image("bomb_unlit.png");
        squares.add(new ImageView(bomb), 6, dungeon.getHeight());
        
        text_sword.setFill(Color.WHITE);
        text_sword.setFont(new Font(15));
        squares.add(text_sword, 1, dungeon.getHeight());
        
        text_invincibility.setFill(Color.WHITE);
        text_invincibility.setFont(new Font(15));
        squares.add(text_invincibility, 3, dungeon.getHeight());
        
        text_key.setFill(Color.WHITE);
        text_key.setFont(new Font(15));
        squares.add(text_key, 5, dungeon.getHeight());
        
        text_bomb.setFill(Color.WHITE);
        text_bomb.setFont(new Font(15));
        squares.add(text_bomb, 7, dungeon.getHeight());
        
        text_space.setFill(Color.WHITE);
        text_space.setFont(new Font(7.5));
        squares.add(text_space, 8, dungeon.getHeight());
        
        text_b.setFill(Color.WHITE);
        text_b.setFont(new Font(7.5));
        squares.add(text_b, 10, dungeon.getHeight());
        
        text_enter.setFill(Color.WHITE);
        text_enter.setFont(new Font(7.5));
        squares.add(text_enter, 12, dungeon.getHeight());
    }
    /**
	 * 
	 */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
        case UP:
            player.moveUp();
            dungeon.updatePlayerSubject();
            break;
        case DOWN:
            player.moveDown();
            dungeon.updatePlayerSubject();
            break;
        case LEFT:
            player.moveLeft();
            dungeon.updatePlayerSubject();
            break;
        case RIGHT:
            player.moveRight();
            dungeon.updatePlayerSubject();
            break;
        case SPACE:
            player.collect();
            dungeon.updatePlayerSubject();
            break;
        case ENTER:
            player.reach();
            break;
        case B:
            player.drop();
            break;
        case ESCAPE:
            System.exit(0);
            break;
        default:
            break;
        }
    }
    /**
	 * 
	 */
	public Text getText_sword() {
		return text_sword;
	}
	/**
	 * 
	 */
	public void setText_sword(String text_sword) {
		this.text_sword.setText(text_sword);
	}
	/**
	 * 
	 */
	public Text getText_invincibility() {
		return text_invincibility;
	}
	/**
	 * 
	 */
	public void setText_invincibility(String text_invincibility) {
		this.text_invincibility.setText(text_invincibility);
	}
	/**
	 * 
	 */
	public Text getText_key() {
		return text_key;
	}
	/**
	 * 
	 */
	public void setText_key(String text_key) {
		this.text_key.setText(text_key);
	}
	/**
	 * 
	 */
	public Text getText_bomb() {
		return text_bomb;
	}
	/**
	 * 
	 */
	public void setText_bomb(String text_bomb) {
		this.text_bomb.setText(text_bomb);
	}
	/**
	 * 
	 */
	public void setSquares(GridPane squares) {
		this.squares = squares;
	}
	/**
	 * 
	 */
	public void setInitialEntities(List<ImageView> initialEntities) {
		this.initialEntities = initialEntities;
	}
	/**
	 * 
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
	/**
	 * 
	 */
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	/**
	 * 
	 */
	public void addSquare(ImageView iv, int x, int y) {
		this.squares.add(iv, x, y);
	}
	/**
	 * 
	 */
	public void changeImage(int index, Image image) {
		this.initialEntities.get(index).setImage(image);
	}
}

