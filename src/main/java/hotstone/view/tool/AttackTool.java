package hotstone.view.tool;

import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.view.GfxConstants;
import hotstone.view.figure.*;
import minidraw.framework.*;
import minidraw.standard.NullTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AttackTool extends NullTool {
    private Drawing model;
    private DrawingEditor editor;
    private Game game;
    private HotStoneActorFigure draggedActor;
    private int lastX;
    private int lastY;
    private int orgX;
    private int orgY;
    private Player whoAmIPlaying;
    private PlayCardTool playcardTool;
    public AttackTool(DrawingEditor editor, Game game, Player playerInTurn) {
        this.editor = editor;
        this.game = game;
        this.whoAmIPlaying = playerInTurn;
        playcardTool = new PlayCardTool(editor, game, playerInTurn);
    }


    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        model = editor.drawing();
        // Note: The HotSeatStateTool should ensure that this tool
        // is only active iff there is a card figure below (x,y)
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        Player owner = ((HotStoneActorFigure) figureAtPosition).getAssociatedCard().getOwner();
        if (owner.equals(whoAmIPlaying)) {
            draggedActor = (HotStoneActorFigure) figureAtPosition;
            // Move the card to the visual top
            model.zOrder(draggedActor, ZOrder.TO_TOP);
            // And remember where the card was dragged from (orgX, orgY)
            lastX = x;
            lastY = y;
            orgX = x;
            orgY = y;
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // compute relative movement
        draggedActor.moveBy(x - lastX, y - lastY);
        // update last position
        lastX = x; lastY = y;

    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        // define booleans
        boolean isHittingHero = false;
        boolean isHittingMinion = false;
        // Invoke related facade method, if figure is a card
        boolean isDraggingAnActor = draggedActor != null;
        // are we dropping the card on the opponent Hero
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure hsf) {
            isHittingHero = hsf.getType() == HotStoneFigureType.HERO_FIGURE;
            isHittingMinion = hsf.getType() == HotStoneFigureType.MINION_FIGURE;
        }
        // are we dropping the card on the opponents minion

        Card attackingCard = draggedActor.getAssociatedCard();
        if (isDraggingAnActor && isHittingHero) {
            Status status = game.attackHero(whoAmIPlaying, attackingCard);
            editor.showStatus(whoAmIPlaying + " attack opponent hero. Result =" + status);
        }
        if (isDraggingAnActor && isHittingMinion) {
            HotStoneActorFigure hsf = (HotStoneActorFigure) figureAtPosition;
            Card defendingCard = hsf.getAssociatedCard();
            Status status = game.attackCard(whoAmIPlaying, attackingCard, defendingCard);
            editor.showStatus(whoAmIPlaying + " attack opponent card. Result =" + status);
        }
        draggedActor.moveBy(orgX - x, orgY - y);
        draggedActor = null;
    }


}
