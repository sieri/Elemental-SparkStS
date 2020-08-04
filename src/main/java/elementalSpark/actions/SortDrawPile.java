package elementalSpark.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

import static elementalSpark.ElementalSpark.makeID;

public class SortDrawPile extends AbstractGameAction {

    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final float startingDuration;
    private CardGroup cardGroup;


    public SortDrawPile()
    {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    private void cardInvert()
    {
        ArrayList<AbstractCard> original = cardGroup.group;
        ArrayList<AbstractCard> reversed = new ArrayList<AbstractCard>();
        for (int i = original.size()-1; i >= 0 ; i--) {
            reversed.add(original.get(i));
        }
        cardGroup.group = reversed;
    }

    @Override
    public void update() {

        if (this.duration == this.startingDuration) {

            cardGroup = AbstractDungeon.player.drawPile;
            cardInvert();
            AbstractDungeon.gridSelectScreen.open(cardGroup, 2, true,  TEXT[0]);
        }
        else if (!AbstractDungeon.gridSelectScreen.confirmScreenUp){
            if(AbstractDungeon.gridSelectScreen.selectedCards.size() >= 2){

                ArrayList<AbstractCard> cards = AbstractDungeon.gridSelectScreen.selectedCards;

                int j = -1;
                boolean done = false;
                for (int i = 0; i < cardGroup.group.size(); i++) {
                    for (AbstractCard card: cards) {
                        if(cardGroup.group.get(i) == card)
                        {
                            if( j == -1)
                            {
                                j = i;
                            }
                            else
                            {
                                AbstractCard tempCard = cardGroup.group.get(i);
                                cardGroup.group.set(i, cardGroup.group.get(j));
                                cardGroup.group.set(j, tempCard);
                                done = true;
                            }
                            break;
                        }

                    }

                    if (done)
                        break;

                }

                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.gridSelectScreen.open(cardGroup, 2, true,  TEXT[0]);



            }else{
                this.isDone = true;
                cardInvert();
            }
        }


        this.tickDuration();

    }



    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(makeID("uiSortingGroup"));
        TEXT = uiStrings.TEXT;
    }
}
