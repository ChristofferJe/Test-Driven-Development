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

package hotstone.figuretestcase;

import hotstone.doubles.SimpleViewFactory;
import hotstone.framework.Player;

import hotstone.view.GfxConstants;
import hotstone.view.figure.CardFigure;
import hotstone.doubles.StubCard;

import minidraw.framework.DrawingEditor;
import minidraw.framework.Figure;
import minidraw.standard.MiniDrawApplication;
import minidraw.standard.SelectionTool;

import java.awt.*;

/** Visual demonstration of the Figure depicting a card in the player's hand. */

public class ShowCardFigure {
  public static void main(String[] args) {
    DrawingEditor editor =
      new MiniDrawApplication( "Showing CardFigure, can be moved by selection tool...",
                               new SimpleViewFactory() );
    editor.open();
    Figure card1 = new CardFigure(new StubCard("Uno", Player.FINDUS), new Point(100, GfxConstants.MY_HAND_POSITION_Y));
    Figure card2 = new CardFigure(new StubCard("Brown-Rice", Player.FINDUS,
            7, "Eat 1 Cake"), new Point(180,GfxConstants.MY_HAND_POSITION_Y));
    // Demo of a card which has not graphical image associated
    Figure card3 = new CardFigure(new StubCard("Fiskedeller", Player.FINDUS,
            8, "Do Stuff"), new Point(340,GfxConstants.MY_HAND_POSITION_Y));

    editor.drawing().add(card1);
    editor.drawing().add(card2);
    editor.drawing().add(card3);
    editor.setTool(new SelectionTool(editor));
  }
}
