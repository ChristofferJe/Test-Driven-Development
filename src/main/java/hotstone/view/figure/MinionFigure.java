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

package hotstone.view.figure;

import hotstone.framework.Card;

import java.awt.*;
import java.util.Map;

import static java.util.Map.entry;

/** Figure representing the card as a minion on the field,
 * that is, with a small frame and no mana cost, but
 * a Z figure to indicate if the minion is 'not active'.
 */
public class MinionFigure extends HotStoneActorFigure implements HotStoneFigure{
  private QuarterImageFigure inactiveImage;

  public MinionFigure(Card associatedCard, Point position) {
    super(HotStoneFigureType.MINION_FIGURE,
            associatedCard, position,
            false ? // Iff you include 'taunt', then change the 'false' to a condition
                    "taunt-frame-sd" : "frame",
            Map.ofEntries(
                    entry(CardFigurePartType.MANA_FIGURE, new Point(0,0)),
                    entry(CardFigurePartType.ATTACK_FIGURE, new Point(20,106)),
                    entry(CardFigurePartType.HEALTH_FIGURE, new Point(90,106)),
                    entry(CardFigurePartType.EMBLEM_FIGURE, new Point(10,9)),
                    entry(CardFigurePartType.ACTIVE_FIGURE, new Point(52,110)))
            );
    // Create the 'active' image (the green Z)
    inactiveImage = new QuarterImageFigure("Z", new Point(0,0));

    // Force the ZZZ in case it is necessary
    updateStats();
  }

  @Override
  public void updateStats() {
    writeLock().lock();
    super.updateStats();
    try {
      if (!associatedCard.isActive()) {
        Point Zpos = new Point(displayBox.x, displayBox.y);
        Zpos.translate(positions.get(CardFigurePartType.ACTIVE_FIGURE).x,
                positions.get(CardFigurePartType.ACTIVE_FIGURE).y);
        inactiveImage.moveTo(Zpos.x, Zpos.y);
        add(inactiveImage);
      } else {
        remove(inactiveImage);
      }
    } finally {
      writeLock().unlock();
    }
  }
}
