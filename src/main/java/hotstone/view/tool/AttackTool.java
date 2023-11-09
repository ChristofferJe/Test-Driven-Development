package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.figure.HotStoneActorFigure;
import minidraw.framework.*;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class AttackTool extends NullTool {
    private DrawingEditor editor;
    private Game game;
    private HotStoneActorFigure draggedActor;
    private int lastX;
    private int lastY;
    private int orgX;
    private int orgY;
    private Player whoAmIPlaying;
    public AttackTool(DrawingEditor editor, Game game, Player playerInTurn) {
        this.editor = editor;
        this.game = game;
        this.whoAmIPlaying = whoAmIPlaying;
    }


    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        Drawing model = editor.drawing();
        // Note: The HotSeatStateTool should ensure that this tool
        // is only active iff there is a card figure below (x,y)
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        draggedActor = (HotStoneActorFigure) figureAtPosition;
        // Move the card to the visual top
        model.zOrder(draggedActor, ZOrder.TO_TOP);
        // And remember where the card was dragged from (orgX, orgY)
        lastX = x; lastY = y;
        orgX = x; orgY = y;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        // compute relative movement
        draggedActor.moveBy(x - lastX, y - lastY);
        // update last position
        lastX = x; lastY = y;
    }
}
