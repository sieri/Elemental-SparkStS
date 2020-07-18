package elementalSpark.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import elementalSpark.ElementalSpark;
import elementalSpark.stances.ElementAbstractStance;


public abstract class AbstractStopElementPower extends AbstractPower implements CloneablePowerInterface {


    public  final String POWER_ID = ElementalSpark.makeID(this.getClass().getSimpleName());
    private final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public final String NAME = powerStrings.NAME;
    public final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;



    public ElementAbstractStance.ElementType elementType;

    public AbstractStopElementPower(int amount, ElementAbstractStance.ElementType elementType)
    {
        name = NAME;
        ID = POWER_ID;

        this.amount = amount;

        this.elementType = elementType;

        this.owner = AbstractDungeon.player;

        type = PowerType.DEBUFF;
        isTurnBased = true;



        updateDescription();
    }

    @Override
    public void updateDescription() {

        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else if (amount > 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }

    }

    @Override
    public AbstractPower makeCopy() {
        return new FireOfTheForgePower();
    }


}
