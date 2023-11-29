package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Template for the State tool - similar to MiniDraw SelectionTool
 * it is a tool that delegates all mouse events to a subtool, and
 * the kind of subtool to use is determined by what is clicked on in the
 * mouse down event. If it is a button, then delegate to ButtonTool,
 * if it is a card, delegate to PlayCardTool, if it is a minion,
 * delegate to a MinionAttackTool, etc.
 *
 * Quite a lot of the code is complete - fill in the missing pieces...
 */
public class DualUserInterfaceTool extends NullTool {
    private final Tool theNullTool;
    private final Drawing model;
    private final Player who;
    private Tool state;
    private DrawingEditor editor;
    private Game game;

    public DualUserInterfaceTool(DrawingEditor editor, Game game, Player who) {
        this.editor = editor;
        this.game = game;
        model = editor.drawing();
        state = theNullTool = new NullTool();
        this.who = who;
    }

    @Override
    public void mouseDown(MouseEvent e, int x, int y) {
        // Find the figure below mouse (x,y)
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        // Iff that figure is associated with our HotStone
        // (All MiniDraw figures that handle HotStone graphics are
        // implementing this role interface).
        if (figureAtPosition instanceof HotStoneFigure) {
            HotStoneFigure hsf = (HotStoneFigure) figureAtPosition;
            if (hsf.getType() == HotStoneFigureType.CARD_FIGURE) {
                state = new PlayCardTool(editor, game, who);
            } else if (hsf.getType() == HotStoneFigureType.TURN_BUTTON ||
                    hsf.getType() == HotStoneFigureType.SWAP_BUTTON) {
                state = new EndTurnTool(editor, game);
            } else if (hsf.getType() == HotStoneFigureType.MINION_FIGURE) {
                state = new AttackTool(editor, game, who);
            } else if (hsf.getType() == HotStoneFigureType.HERO_FIGURE) {
                state = new UsePowerTool(editor, game, who);
            } else if (hsf.getType() == HotStoneFigureType.WIN_BUTTON) {
                // Clicking the 'won button' should do nothing!
                state = theNullTool; // User have to close the window to restart.
            }
        }
        state.mouseDown(e, x, y);
    }

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        state.mouseUp(e, x, y);
        if(state != theNullTool){
          model.requestUpdate();
        }
        state = theNullTool;
    }

    @Override
    public void mouseDrag(MouseEvent e, int x, int y) {
        state.mouseDrag(e, x, y);
    }

    @Override
    public void mouseMove(MouseEvent e, int x, int y) {
        state.mouseMove(e, x, y);
    }

}
