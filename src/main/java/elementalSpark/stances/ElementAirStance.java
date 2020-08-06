package elementalSpark.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.StanceStrings;
import elementalSpark.ElementalSpark;

import java.util.LinkedList;


public class ElementAirStance extends ElementAbstractStance {

    public static final String STANCE_ID = ElementalSpark.makeID("ElementAir");
    private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    public static final String NAME = stanceStrings.NAME;
    public static final String[] DESCRIPTION = stanceStrings.DESCRIPTION;

    public LinkedList<AbstractCard> cards = new LinkedList<AbstractCard>();

    public ElementAirStance() {
        super("AirLoop");
        name = NAME;
        ID = STANCE_ID;
        type = ElementType.Air;
        this.c = Color.YELLOW.cpy();

        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        super.onPlayCard(card);
        if(!card.shuffleBackIntoDrawPile)
        {
            card.shuffleBackIntoDrawPile = true;
            cards.add(card);
        }


        super.onPlayCard(card);
    }

    @Override
    public void onExitStance() {
        super.onExitStance();

        for (AbstractCard card :cards)
        {
            card.shuffleBackIntoDrawPile = false;
        }
        cards.clear();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTION[0];
    }


}
