package hotstone.view.tool;

import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.framework.Status;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.figure.HeroFigure;
import hotstone.view.figure.HotStoneFigure;
import hotstone.view.figure.HotStoneFigureType;
import minidraw.framework.Drawing;
import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.framework.Tool;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

public class UsePowerTool extends NullTool {
    private final Game game;
    private final Player whoAmIPlaying;
    private final DrawingEditor editor;
    private final Drawing model;


    public UsePowerTool(DrawingEditor editor, Game game, Player whoAmIPlaying) {
        this.editor = editor;
        this.game = game;
        this.whoAmIPlaying = whoAmIPlaying;
        model = editor.drawing();
    }
    @Override
    public void mouseDown(MouseEvent e, int x, int y) {}

    @Override
    public void mouseUp(MouseEvent e, int x, int y) {
        // Find the button below
        Figure figureAtPosition = model.findFigure(e.getX(), e.getY());
        if (figureAtPosition instanceof HotStoneFigure hsf) {
            if (hsf instanceof HeroFigure hf) {
                Player owner = hf.getAssociatedHero().getOwner();
                if (owner.equals(whoAmIPlaying)) {
                    Status status = game.usePower(whoAmIPlaying);
                    editor.showStatus(whoAmIPlaying + " uses hero power. Result = " + status);
                }
            }
        }
    }
}
