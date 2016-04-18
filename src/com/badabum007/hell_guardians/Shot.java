package com.badabum007.hell_guardians;

import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

/**
 * �����, ����������� ������� �����
 * @author pixxx
 */
public class Shot extends Circle {
    /** ���� �������� */
    Enemy Target;
    
    public static int damage;
    
    /** ��������� ���������� �������� */
    double startX;
    double startY;
    
    double radius = 5;
    double duration = 200;
    
    /** ���� �������� � ��� �������� */
    Path ShotPath;
    PathTransition animation;
    
    /**
     * �����, ��������� ������� � ��������� �����������
     * @param Target - ���� ��������
     * @param startX - ��������� ���������� X
     * @param startY - ��������� ���������� Y
     */
    public Shot(Enemy Target, double startX, double startY){
        this.Target = Target;
        this.startX = startX;
        this.startY = startY;
        this.setCenterX(startX);
        this.setCenterY(startY);
        this.setRadius(radius);
        GameWindow.gameRoot.getChildren().add(this);
        // ������� ���� �������� - ������ � ����; ������� ��� ��������
        ShotPath = new Path(new MoveTo(startX, startY));
        ShotPath.getElements().add(new LineTo(Target.posX + Target.width/2, 
                Target.posY + Target.height/2));
        animation = new PathTransition(Duration.millis(duration), ShotPath, this);
        animation.play();
        // ��� ��������� � ����
        animation.setOnFinished(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent actionEvent) {
                 PathTransition finishedAnimation = 
                         (PathTransition) actionEvent.getSource();
                 Shot finishedShot = (Shot) finishedAnimation.getNode();
                 // �������� �������� �� Root-� � ��������� ����� �����
                 finishedShot.setVisible(false);
                 Target.GetDamage(damage);
                 GameWindow.gameRoot.getChildren().remove(finishedShot);
             }
        });
    }
}