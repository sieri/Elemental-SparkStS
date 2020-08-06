package elementalSpark.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.DrawPower;
import elementalSpark.ElementalSpark;


public class ElementWaterStance extends ElementAbstractStance  {

    public static final String STANCE_ID = ElementalSpark.makeID("ElementWater");
    private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    public static final String NAME = stanceStrings.NAME;
    public static final String[] DESCRIPTION = stanceStrings.DESCRIPTION;

    public static final int boost = 1;


    public ElementWaterStance() {
        super("WaterLoop");
        name = NAME;
        ID = STANCE_ID;

        type = ElementType.Water;

        this.c = Color.NAVY.cpy();

        updateDescription();
    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();
        if(AbstractDungeon.player.hasPower(DrawPower.POWER_ID))
        {
            AbstractDungeon.player.getPower(DrawPower.POWER_ID).stackPower(boost);
        }
        else
        {
            AbstractDungeon.player.addPower(new DrawPower(AbstractDungeon.player,boost));
        }
    }

    @Override
    public void onExitStance() {
        super.onExitStance();
        if(AbstractDungeon.player.hasPower(DrawPower.POWER_ID))
        {
            AbstractDungeon.player.getPower(DrawPower.POWER_ID).reducePower(boost);

        }
        else
        {
            AbstractDungeon.player.addPower(new DrawPower(AbstractDungeon.player,-boost));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTION[0];
    }
}
