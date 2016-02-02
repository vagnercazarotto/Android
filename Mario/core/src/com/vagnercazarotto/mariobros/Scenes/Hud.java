package com.vagnercazarotto.mariobros.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.vagnercazarotto.mariobros.MarioBros;


/**
 * Created by vagner on 01/02/2016.
 */
// In this class we'll create our logic

public class Hud {
    // when our game world news we want the HUD to stay there, so we're use a new camera and a new viewport
    // So it's stays locked there and the world can move around
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label worldLabel;
    Label marioLabel;

    // we'll create a constructor
    public Hud(SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FillViewport(MarioBros.V_WIDTH,MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport,sb);

        // for provide organization we're create a Table inside of our stage
        Table table = new Table();
        table.top(); // put the table on top
        table.setFillParent(true); // use the entire father space
        // now we're divide our table in Label
        countdownLabel = new Label(String.format("%03d",worldTimer),new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // need to be a integer
        scoreLabel = new Label(String.format("%06d",score),new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // need to be a integer
        timeLabel = new Label("TIME",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("MARIO",new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        // now we need to add in our label
        table.add(marioLabel).expandX().padBottom(10);
        table.add(worldLabel).expandX().padBottom(10);
        table.add(timeLabel).expandX().padBottom(10);
        table.row();
        table.add(scoreLabel).padBottom(180);
        table.add(levelLabel).expandX().padBottom(180);
        table.add(countdownLabel).expandX().padBottom(180);

        stage.addActor(table);


    }

}
