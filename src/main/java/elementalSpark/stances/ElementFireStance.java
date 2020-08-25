package elementalSpark.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.monsters.city.BookOfStabbing;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;
import elementalSpark.relics.UntamedFire;


public class ElementFireStance extends ElementAbstractStance  {

    public static final String STANCE_ID = ElementalSpark.makeID("ElementFire");
    private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    public static final String NAME = stanceStrings.NAME;
    public static final String[] DESCRIPTION = stanceStrings.DESCRIPTION;

    public static final int boost = 2;


    public ElementFireStance() {
        super();
        name = NAME;
        ID = STANCE_ID;

        type = ElementType.Fire;

        this.c = Color.RED.cpy();

        updateDescription();
    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();
        if(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID))
        {
            AbstractDungeon.player.getPower(StrengthPower.POWER_ID).stackPower(boost);
        }
        else
        {
            AbstractDungeon.player.addPower(new StrengthPower(AbstractDungeon.player,boost));
        }
    }

    @Override
    public void onExitStance() {
        super.onExitStance();

        //check if the player has the Untamed fire relic
        if (AbstractDungeon.player.hasRelic(UntamedFire.ID))
        {
            return;
        }

        //else remove the strength
        if(AbstractDungeon.player.hasPower(StrengthPower.POWER_ID))
        {
            AbstractDungeon.player.getPower(StrengthPower.POWER_ID).stackPower(-boost);
        }
        else
        {
            AbstractDungeon.player.addPower(new StrengthPower(AbstractDungeon.player,-boost));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTION[0];
    }
}
