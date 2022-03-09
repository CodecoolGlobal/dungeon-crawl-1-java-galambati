package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapLoader;

import com.codecool.dungeoncrawl.logic.items.Inventory;
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
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application {
    GameMap map = MapLoader.loadMap();
    Canvas canvas = new Canvas(
            map.getWidth() * Tiles.TILE_WIDTH,
            map.getHeight() * Tiles.TILE_WIDTH);
    GraphicsContext context = canvas.getGraphicsContext2D();
    Label healthLabel = new Label();
    Label Inventory = new Label();
    Label items = new Label();
    Inventory inventory = new Inventory();


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

    public void setAction(Button button){
        button.setOnAction(e -> {
            Cell actualCell = map.getPlayer().getCell();
            if (actualCell.getItem().getTileName().equals("coin")){
                inventory.addToInventory(actualCell.getItem().getTileName());
                actualCell.setItem(null);
                actualCell.setType(CellType.FLOOR);
                refresh();
            }
            System.out.println(inventory.getInventory());
        });
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane ui = new GridPane();
        ui.setPrefWidth(300);
        ui.setPadding(new Insets(30));

        ui.add(new Label("Health: "), 0, 0);
        ui.add(healthLabel, 1, 0);
        ui.add(new Label("Inventory: "), 0, 1);
        ui.add(Inventory, 0, 2);
        ui.add(items,0, 20);

        FileInputStream input = new FileInputStream("resources/images/pickup.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        Button pickUpButton = new Button("", imageView);
        ui.add(pickUpButton, 0, 5);
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

    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
                map.getPlayer().move(0, -1);
                refresh();
                break;
            case DOWN:
                map.getPlayer().move(0, 1);
                refresh();
                break;
            case LEFT:
                map.getPlayer().move(-1, 0);
                refresh();
                break;
            case RIGHT:
                map.getPlayer().move(1, 0);
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
        items.setText("" + inventory.getInventory());
    }
}
