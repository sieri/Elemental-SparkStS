package elementalSpark.stances;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;


public class ElementEarthStance extends ElementAbstractStance  {

    public static final String STANCE_ID = ElementalSpark.makeID("ElementEarth");
    private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    public static final String NAME = stanceStrings.NAME;
    public static final String[] DESCRIPTION = stanceStrings.DESCRIPTION;

    public static final int boost = 2;


    public ElementEarthStance() {
        super();
        name = NAME;
        ID = STANCE_ID;

        type = ElementType.Earth;

        this.c = Color.GREEN.cpy();
        updateDescription();
    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();
        if(AbstractDungeon.player.hasPower(DexterityPower.POWER_ID))
        {
            AbstractDungeon.player.getPower(DexterityPower.POWER_ID).stackPower(boost);
        }
        else
        {
            AbstractDungeon.player.addPower(new DexterityPower(AbstractDungeon.player,boost));
        }
    }

    @Override
    public void onExitStance() {
        super.onExitStance();
        if(AbstractDungeon.player.hasPower(DexterityPower.POWER_ID))
        {
            AbstractDungeon.player.getPower(DexterityPower.POWER_ID).stackPower(-boost);
        }
        else
        {
            AbstractDungeon.player.addPower(new DexterityPower(AbstractDungeon.player,-boost));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTION[0];
    }
}
