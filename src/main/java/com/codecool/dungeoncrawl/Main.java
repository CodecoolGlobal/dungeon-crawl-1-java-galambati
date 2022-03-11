package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;
import com.codecool.dungeoncrawl.logic.actors.Enemy;

import com.codecool.dungeoncrawl.logic.actors.Enemy;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.List;
import java.util.Random;

public class Main extends Application {
    private int level = 1;
    GameMap map = MapLoader.loadMap(level);
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label strengthLabel = new Label();
    Label Inventory = new Label();
    Label items = new Label();

    public static void main(String[] args) {
        launch(args);
    }

    private void buttonShadowing(Button button) {
        DropShadow shadow = new DropShadow();
        button.addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> button.setEffect(shadow));
        button.addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> button.setEffect(null));
    }

    public void setAction(Button button) {
        button.setOnAction(e -> {
            Cell actualCell = map.getPlayer().getCell();
            String itemOnCell = actualCell.getItem().getTileName();
            if (itemOnCell.equals("coin") || itemOnCell.equals("key 1") || itemOnCell.equals("key 2") || itemOnCell.equals("sword")) {
                map.getPlayer().addToInventory(actualCell.getItem().getTileName());
                actualCell.setItem(null);
                actualCell.setType(CellType.FLOOR);
                if (itemOnCell.equals("sword")) {
                    map.getPlayer().setAttackStrength(map.getPlayer().getAttackStrength() + 2);
                }
                refresh();
            }
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setStyle("-fx-background-color: #472D3C;");
        ui.setPrefWidth(180);
        ui.setPadding(new Insets(30));

        Label strengthText = new Label("Strength");
        ui.add(strengthText, 0, 2);
        strengthText.setTextFill(Color.WHITE);
        strengthText.setFont(Font.font("Arial", FontWeight.BOLD, 21));
        strengthLabel.setTextFill(Color.GOLD);
        ui.add(strengthLabel, 0, 3);
        strengthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        Label healthText = new Label("Health");
        ui.add(healthText, 0, 0);
        healthText.setTextFill(Color.WHITE);
        healthText.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        healthLabel.setTextFill(Color.GOLD);
        ui.add(healthLabel, 0, 1);
        healthLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        ui.add(new Label("\n\n"), 0, 4);

        ui.add(Inventory, 0, 5);

        Label inventoryLabel = new Label("\nInventory");
        ui.add(inventoryLabel, 0, 8);
        ui.add(items, 0, 10);
        inventoryLabel.setTextFill(Color.WHITE);
        inventoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 21));

        FileInputStream input = new FileInputStream("resources/images/pickup.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Button pickUpButton = new Button("", imageView);
        ui.add(pickUpButton, 0, 7);
        pickUpButton.setFocusTraversable(false);
        buttonShadowing(pickUpButton);
        setAction(pickUpButton);

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);
        borderPane.setRight(ui);

        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }

    private void moveEnemiesToRandomDirection() {
        for (Enemy enemy : map.getEnemies()) {
            int randomNumber = new Random().nextInt(4);
            switch (randomNumber) {
                case 0: // UP
                    enemy.move(0, -1);
                    break;
                case 1: // DOWN
                    enemy.move(0, 1);
                    break;
                case 2: // LEFT
                    enemy.move(-1, 0);
                    break;
                case 3: // RIGHT
                    enemy.move(1, 0);
                    break;
            }
        }
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                moveEnemiesToRandomDirection();
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                moveEnemiesToRandomDirection();
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                moveEnemiesToRandomDirection();
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
                moveEnemiesToRandomDirection();
                refresh();
                break;
        }
    }

    private void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else if (cell.getItem() != null) {
                    Tiles.drawTile(context, cell.getItem(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        healthLabel.setText("" + map.getPlayer().getHealth());
        strengthLabel.setText("" + map.getPlayer().getAttackStrength());
        String itemsText = "";
        for (String item : map.getPlayer().getInventory()) {
            itemsText += " - " + item + "\n";
        }
        items.setText(itemsText);
        items.setTextFill(Color.DEEPSKYBLUE);
        items.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        if (map.getPlayer().getCell().getTileName().equals("opened_door")) {
            level += 1;
            setNextMap();
        } else if (map.getPlayer().getCell().getTileName().equals("opened_door_2")) {
            level -= 1;
            setNextMap();
        }
    }

    private void setNextMap() {
        List<String> inventory = map.getPlayer().getInventory();
        int health = map.getPlayer().getHealth();
        int strength = map.getPlayer().getAttackStrength();
        this.map = MapLoader.loadMap(level);
        map.getPlayer().setInventory(inventory);
        map.getPlayer().setHealth(health);
        map.getPlayer().setAttackStrength(strength);
        refresh();
    }
}