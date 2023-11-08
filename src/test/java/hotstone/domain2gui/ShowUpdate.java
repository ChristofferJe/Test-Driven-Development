/*
 * Copyright (C) 2023. Henrik Bærbak Christensen, Aarhus University.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package hotstone.domain2gui;

import hotstone.doubles.FakeObjectGame;
import hotstone.framework.Card;
import hotstone.framework.Game;
import hotstone.framework.Player;
import hotstone.standard.StandardHotStoneGame;
import hotstone.variants.AlphaGameFactory;
import hotstone.view.core.HotStoneDrawing;
import hotstone.view.core.HotStoneDrawingType;
import hotstone.view.core.HotStoneFactory;
import hotstone.view.tool.EndTurnTool;
import minidraw.framework.DrawingEditor;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.NullTool;

import java.awt.event.MouseEvent;

/** Visual tests of the ability of HotStoneDrawing to respond to
 * observer events notified by the Game instance - i.e. the Domain
 * to the GUI flow of events.
 */
public class ShowUpdate {
  public static void main(String[] args) {

    Game game = new StandardHotStoneGame(new AlphaGameFactory());

    DrawingEditor editor =
      new MiniDrawApplication( "Click anywhere to progress in an update sequence...",
                               new HotStoneFactory(game, Player.FINDUS,
                                       HotStoneDrawingType.HOTSEAT_MODE) );
    editor.open();
    editor.setTool( new TriggerGameUpdateTool(editor, game) );
  }
}

/** A tool whose only purpose is to trigger a new, visual,
 * test case for each click that the user makes.
 */
class TriggerGameUpdateTool extends NullTool {
  private DrawingEditor editor;
  private Game game;
  private int count;

  private HotStoneDrawing hotstoneDrawing;

  public TriggerGameUpdateTool(DrawingEditor editor, Game game) {
    this.editor = editor;
    this.game = game;
    hotstoneDrawing = (HotStoneDrawing) editor.drawing();
    count = 0;
  }

  @Override
  public void mouseUp(MouseEvent e, int x, int y) {
    // Switch on 'which visual test case is the next to execute'
    switch (count) {
      case 0: {
        editor.showStatus("Playing Findus Card # 2");
        Card c = game.getCardInHand(Player.FINDUS, 2);
        game.playCard(Player.FINDUS, c);
        break;
      }
      case 1: {
        editor.showStatus("Playing Findus Card # 1");
        Card c = game.getCardInHand(Player.FINDUS, 1);
        game.playCard(Player.FINDUS, c);
        break;

      }
      case 2: {
        editor.showStatus("Findus ends turn");
        game.endTurn();
        break;
      }
      case 3: {
        editor.showStatus("Exits HotSeatState");
        hotstoneDrawing.endHotSeatState();
        break;
      }
      case 4: {
        editor.showStatus("Playing Peddersen Card # 3");
        Card c = game.getCardInHand(Player.PEDDERSEN, 3);
        game.playCard(Player.PEDDERSEN, c);
        break;
      }
      case 5: {
        editor.showStatus("Peddersen ends turn");
        game.endTurn();
        break;
      }
      case 6: {
        hotstoneDrawing.endHotSeatState();
        break;
      }
      case 7: {
        Card attacker = game.getCardInField(Player.FINDUS, 0);
        Card defender = game.getCardInField(Player.PEDDERSEN, 0);
        editor.showStatus("Attack/Findus with " + attacker.getName() + " on " + defender.getName()
                + "; Peddersen Card REMOVED; Findus' card Health reduced.");
        game.attackCard(Player.FINDUS, attacker, defender);
        break;
      }
      case 8: {
        // have been tested and verified that the UI responds correctly.
        editor.showStatus("TODO: ADD SOME MORE game.doSomething(x,y,z) and develop GUI behaviour");
        break;
      }
      default: {
        editor.showStatus("No more events in the list...");
      }
    }
    // Increment count to prepare to pick a new 'visual test case' in the
    // above list
    count++;
  }
}
